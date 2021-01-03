package com.cn.util;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

/**
 * @author AliyunOSSUtil
 * @time 2020/12/23-15:58
 */
public class AliyunOSSUtil {


    // Endpoint以杭州为例，其它Region请按实际情况填写。Region：存储地址
    private static String endpoint = "https://oss-cn-beijing.aliyuncs.com";
    // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录RAM控制台创建RAM账号。
    private static String accessKeyId = "LTAI4GDcL8CjWfeKwJt74VZL";
    private static String accessKeySecret = "0Nua9gduSseWFMO7YMWiJmiCwzBrjs";

    /*
     * 将文件上传至阿里云
     * 参数：
     *   headImg：MultipartFile类型的文件
     *   bucketName:存储空间名
     *   objectName:文件名
     * */
    public static void uploadBytesFile(MultipartFile headImg,String bucketName,String objectName){

        byte[] bytes =null;
        try {
            //将文件转为byte数组
            bytes = headImg.getBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId,accessKeySecret);

        // 上传Byte数组。
        ossClient.putObject(bucketName, objectName, new ByteArrayInputStream(bytes));

        // 关闭OSSClient。
        ossClient.shutdown();
    }


    /*
     * 将本地文件上传至阿里云
     * 参数：
     *   bucketName:存储空间名
     *   objectName:文件名
     *   localFilePath:本地文件路径
     * */
    public static void uploadLocalFile(String bucketName,String objectName,String localFilePath){

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 创建PutObjectRequest对象。
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, objectName, new File(localFilePath));

        // 上传文件。
        ossClient.putObject(putObjectRequest);

        // 关闭OSSClient。
        ossClient.shutdown();
    }

}
