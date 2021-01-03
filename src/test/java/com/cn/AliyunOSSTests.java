package com.cn;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.Bucket;
import com.aliyun.oss.model.GetObjectRequest;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Random;

@SpringBootTest
public class AliyunOSSTests {

    //创建存储空间
    @Test
    public void testCreateBucket(){

        // Endpoint以杭州为例，其它Region请按实际情况填写。Region：存储地址
        String endpoint = "https://oss-cn-beijing.aliyuncs.com";
        // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录RAM控制台创建RAM账号。
        String accessKeyId = "LTAI4GDcL8CjWfeKwJt74VZL";
        String accessKeySecret = "0Nua9gduSseWFMO7YMWiJmiCwzBrjs";

        //创建存储空间
        String bucketName = "yingxs2006";

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 创建存储空间。
        ossClient.createBucket(bucketName);

        // 关闭OSSClient。
        ossClient.shutdown();
    }

    //上传文件  本地文件
    @Test
    public void testUploadFile(){
        // Endpoint以杭州为例，其它Region请按实际情况填写。Region：存储地址
        String endpoint = "https://oss-cn-beijing.aliyuncs.com";
        // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录RAM控制台创建RAM账号。
        String accessKeyId = "LTAI4GDcL8CjWfeKwJt74VZL";
        String accessKeySecret = "0Nua9gduSseWFMO7YMWiJmiCwzBrjs";

        String bucketName="yx-2006";  //指定上传的存储空间
        String objectName="测试.mp4";  //文件名
        String localFile="E:\\桌面\\后期项目\\1.mp4";  //本地文件

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 创建PutObjectRequest对象。
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, objectName, new File(localFile));

        // 上传文件。
        ossClient.putObject(putObjectRequest);

        // 关闭OSSClient。
        ossClient.shutdown();
    }

    //上传文件  文件流
    @Test
    public void testUploadFiles() throws FileNotFoundException {
        // Endpoint以杭州为例，其它Region请按实际情况填写。Region：存储地址
        String endpoint = "https://oss-cn-beijing.aliyuncs.com";
        // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录RAM控制台创建RAM账号。
        String accessKeyId = "LTAI4GDcL8CjWfeKwJt74VZL";
        String accessKeySecret = "0Nua9gduSseWFMO7YMWiJmiCwzBrjs";

        String bucketName="yingx2006";  //指定上传的存储空间
        String objectName="cover/测试.jpg";  //文件名
        String localFile="E:\\桌面\\后期项目\\1.mp4";  //本地文件

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 上传文件流。
        InputStream inputStream = new FileInputStream(localFile);
        ossClient.putObject(bucketName, objectName, inputStream);

        // 关闭OSSClient。
        ossClient.shutdown();
    }

    @Test
    public void testUploadNetFile() throws IOException {
        // Endpoint以杭州为例，其它Region请按实际情况填写。Region：存储地址
        String endpoint = "https://oss-cn-beijing.aliyuncs.com";
        // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录RAM控制台创建RAM账号。
        String accessKeyId = "LTAI4GDcL8CjWfeKwJt74VZL";
        String accessKeySecret = "0Nua9gduSseWFMO7YMWiJmiCwzBrjs";

        String bucketName="yingx2006";  //指定上传的存储空间
        String objectName="cover/小汽车.jpg";  //文件名

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 上传网络流。
        InputStream inputStream = new URL("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3860576747,4222045237&fm=26&gp=0.jpg").openStream();
        ossClient.putObject(bucketName, objectName, inputStream);

        // 关闭OSSClient。
        ossClient.shutdown();}

    //下载文件到本地
    @Test
    public void testDowloadFile() throws IOException {
        // Endpoint以杭州为例，其它Region请按实际情况填写。Region：存储地址
        String endpoint = "https://oss-cn-beijing.aliyuncs.com";
        // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录RAM控制台创建RAM账号。
        String accessKeyId = "LTAI4GDcL8CjWfeKwJt74VZL";
        String accessKeySecret = "0Nua9gduSseWFMO7YMWiJmiCwzBrjs";


        String bucketName="yingx2006";  //指定上传的存储空间
        String objectName="娇娃.jpg";  //文件名
        String localFile="C:\\Users\\506B教师机\\Desktop\\aaa\\娇娃s.jpg";

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 下载OSS文件到本地文件。如果指定的本地文件存在会覆盖，不存在则新建。
        ossClient.getObject(new GetObjectRequest(bucketName, objectName), new File(localFile));

        // 关闭OSSClient。
        ossClient.shutdown();
    }

    @Test
    public void showBucket(){
        // Endpoint以杭州为例，其它Region请按实际情况填写。Region：存储地址
        String endpoint = "https://oss-cn-beijing.aliyuncs.com";
        // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录RAM控制台创建RAM账号。
        String accessKeyId = "LTAI4GDcL8CjWfeKwJt74VZL";
        String accessKeySecret = "0Nua9gduSseWFMO7YMWiJmiCwzBrjs";

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 列举存储空间。
        List<Bucket> buckets = ossClient.listBuckets();
        for (Bucket bucket : buckets) {
            System.out.println(" - " + bucket.getName());
        }

        // 关闭OSSClient。
        ossClient.shutdown();
    }

    @Test
    public void testDeleteBucket(){
        // Endpoint以杭州为例，其它Region请按实际情况填写。Region：存储地址
        String endpoint = "https://oss-cn-beijing.aliyuncs.com";
        // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录RAM控制台创建RAM账号。
        String accessKeyId = "LTAI4GDcL8CjWfeKwJt74VZL";
        String accessKeySecret = "0Nua9gduSseWFMO7YMWiJmiCwzBrjs";
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 删除存储空间。
        ossClient.deleteBucket("hr-6");

        // 关闭OSSClient。
        ossClient.shutdown();
    }



    public static void main(String[] args) {

    }

}
