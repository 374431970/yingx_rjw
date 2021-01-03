package com.cn.serviceImpl;

import com.cn.annotation.AddLog;
import com.cn.dao.VideoDao;
import com.cn.entity.AdminExample;
import com.cn.entity.Video;
import com.cn.entity.VideoExample;
import com.cn.po.VideoPO;
import com.cn.po.VideoVO;
import com.cn.service.VideoService;
import com.cn.util.*;
import com.cn.util.VideoInterceptCoverUtil;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * @PackageName
 * @ClassName VideoServiceImpl
 * @Description
 * @Author
 * @Date 2020/12/23 19:48
 */
@Service("videoservice")
@Transactional
public class VideoServiceImpl implements VideoService {
    @Resource
    VideoDao dao;
    @Resource
    HttpServletRequest request;

    @Override
    public HashMap<String, Object> queryVideo(Integer page, Integer rows) {
        HashMap<String,Object> map=new HashMap<>();
        map.put("page",page);
        AdminExample example=new AdminExample();
        //创建分页对象
        RowBounds rowBounds=new RowBounds((page-1)*rows,rows);
        List<Video> list=dao.selectByExampleAndRowBounds(example,rowBounds);

        map.put("rows",list);
        //查询总条数
        int records = dao.selectCountByExample(example);
        map.put("records",records);

        //计算总页数
        Integer tolal=records%rows==0?records/rows:records/rows+1;
        map.put("total",tolal);
        return map;
    }
    @AddLog(value = "添加视频")
    @Override
    public String add(Video video) {
        video.setId(UUID.randomUUID().toString());
        video.setUploadTime(new Date());

        //执行添加
        dao.insertSelective(video);

        //将id返回
        return video.getId();
    }
    @AddLog(value = "修改视频")
    @Override
    public void edit(Video video) {
        if (video.getVideoPath() == "") {
            video.setVideoPath(null);
        }
        System.out.println("修改：" + video);
        dao.updateByPrimaryKeySelective(video);
    }
    @AddLog(value = "删除视频")
    @Override
    public void del(Video video) {
        //设置条件
        VideoExample example = new VideoExample();
        example.createCriteria().andIdEqualTo(video.getId());
        //根据id查询视频数据
        Video videos = dao.selectOneByExample(example);

        //1.删除数据
        dao.deleteByExample(example);
        String videoPath = videos.getVideoPath();

        //1.获取文件上传的路径  根据相对路径获取绝对路径
        String realPath = request.getSession().getServletContext().getRealPath("/upload/video");

        //2.删除本地文件
        File file1 = new File(realPath+"/"+videoPath);
        //判断是一个文件，并且文件存在
        if(file1.isFile()&&file1.exists()){
            //删除文件
            boolean isDel = file1.delete();
            System.out.println("删除："+isDel);
        }
    }

    @Override
    public void uploadVdieos(MultipartFile videoPath, String id, HttpServletRequest request) {
        //1.获取文件上传的路径  根据相对路径获取绝对路径
        String realPath = request.getSession().getServletContext().getRealPath("/upload/video");

        //2.判断文件夹是否存在
        File file = new File(realPath);
        if (!file.exists()) {
            file.mkdirs();  //创建文件夹
        }
        //获取文件名
        String filename = videoPath.getOriginalFilename();

        //创建一个新的名字    原名称-时间戳  10.jpg-1590390153130
        String newName = new Date().getTime() + "-" + filename;

        //3.文件上传
        try {
            videoPath.transferTo(new File(realPath, newName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //4.修改图片路径
        //修改的条件
        VideoExample example = new VideoExample();
        example.createCriteria().andIdEqualTo(id);

        Video video = new Video();
        video.setCoverPath("封面"); //设置封面
        video.setVideoPath(newName); //设置视频地址

        //修改
        dao.updateByExampleSelective(video, example);
    }

    @Override
    public void uploadVdieosAliyun(MultipartFile videoPath, String id, HttpServletRequest request) {

        //获取文件名   动画.mp4
        String filename = videoPath.getOriginalFilename();
        //拼接时间戳  2341423424-动画.mp4
        String newName=new Date().getTime()+"-"+filename;
        //拼接视频名   video/2341423424-动画.mp4
        String objectName="video/"+newName;


        /*1.上传至阿里云
         * 将文件上传至阿里云
         * 参数：
         *   headImg：MultipartFile类型的文件
         *   bucketName:存储空间名
         *   objectName:文件名
         * */
        AliyunOSSUtil.uploadBytesFile(videoPath,"yingx2006",objectName);


        //拼接视频网络路径
        String videoNetPath="http://yingx2006.oss-cn-beijing.aliyuncs.com/"+objectName;

        //1.获取文件上传的路径  根据相对路径获取绝对路径
        String realPath = request.getSession().getServletContext().getRealPath("/upload/cover");

        //2.判断文件夹是否存在
        File file = new File(realPath);
        if (!file.exists()) {
            file.mkdirs();  //创建文件夹
        }

        //根据视频名拆分    0:2341423424-动画    1:mp4
        String[] split = newName.split("\\.");
        //获取视频名字  0:2341423424-动画
        String splitName=split[0];
        //拼接封面的本地路径
        String coverLocalPath=realPath+"/"+splitName+".jpg";
        System.out.println("本地路径："+coverLocalPath);

        /**2.截取封面
         * 获取指定视频的帧并保存为图片至指定目录
         * @param videofile  源视频文件路径
         * @param framefile  截取帧的图片存放路径
         * @throws Exception
         */
        try {
            VideoInterceptCoverUtil.fetchFrame(videoNetPath,coverLocalPath);
        } catch (Exception e) {
            e.printStackTrace();
        }


        //拼接封面网路经名
        String netCoverName="cover/"+splitName+".jpg";


        /*
         * 3.上传封面
         * 参数：
         *   bucketName:存储空间名
         *   objectName:文件名
         *   localFilePath:本地文件路径
         * */
        AliyunOSSUtil.uploadLocalFile("yingx2006",netCoverName,coverLocalPath);

        //4.删除本地文件
        File file1 = new File(coverLocalPath);
        //判断是一个文件，并且文件存在
        if(file1.isFile()&&file1.exists()){
            //删除文件
            boolean isDel = file1.delete();
            System.out.println("删除："+isDel);
        }

        //5.修改数据
        //修改的条件
        VideoExample example = new VideoExample();
        example.createCriteria().andIdEqualTo(id);

        Video video = new Video();
        video.setCoverPath("http://yingx2006.oss-cn-beijing.aliyuncs.com/"+netCoverName); //设置封面
        video.setVideoPath("http://yingx2006.oss-cn-beijing.aliyuncs.com/"+objectName); //设置视频地址

        //修改
        dao.updateByExampleSelective(video, example);
    }

    @Override
    public List<VideoVO> queryByReleaseTime() {
        List<VideoPO> videoPOList = dao.queryByReleaseTime();

        ArrayList<VideoVO> videoVOS = new ArrayList<>();

        for (VideoPO videoPO : videoPOList) {

            //封装VO对象
            VideoVO videoVO = new VideoVO(
                    videoPO.getId(),
                    videoPO.getTitle(),
                    videoPO.getCoverPath(),
                    videoPO.getVideoPath(),
                    videoPO.getUploadTime(),
                    videoPO.getDescription(),
                    18,  //根据videoPO中的视频id  redis  查询视频点赞数
                    videoPO.getCateName(),
                    videoPO.getHeadImg()
            );
            //将vo对象放入集合
            videoVOS.add(videoVO);
        }

        return videoVOS;
    }

}