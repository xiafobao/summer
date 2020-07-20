package com.xia.demo.oss;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.GetObjectRequest;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import org.apache.commons.lang3.time.StopWatch;

import java.io.File;

/**
 * oss上传文件并监控上传百分比
 * 相关依赖
 * <dependency>
 *    <groupId>com.aliyun.oss</groupId>
 *    <artifactId>aliyun-sdk-oss</artifactId>
 *    <version>3.8.0</version>
 * </dependency>
 */

public class OSSFileUpload {
   /* //这些配置不用改
    private static String endpoint = "http://oss-us-west-1.aliyuncs.com";
    // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
    private static String bucketName = "xfbm";*/
   //<<===========================美国=============================
    /*private static String endpoint = "http://oss-us-west-1.aliyuncs.com";
    private static String bucketName = "xfbm";*/
    //===========================美国=============================>>
    //<<===========================深圳=============================
    /*private static String endpoint = "http://oss-cn-shenzhen.aliyuncs.com";
    private static String bucketName = "zzwm";*/
    //===========================深圳=============================>>
    //<<===========================香港=============================
    private static String endpoint = "http://oss-cn-hongkong.aliyuncs.com";
    private static String bucketName = "xfbxg";
    //===========================香港=============================>>
    //<<===========================新加坡=============================
    //private static String endpoint = "http://oss-ap-southeast-1.aliyuncs.com";
    //private static String bucketName = "xfbxjp";
    //===========================新加坡=============================>>

    private static String accessKeyId = "";
    private static String accessKeySecret = "";
    private static String remotePath = "tmp/";

    public static void main(String[] args) throws InterruptedException {
        System.out.println("OSSFileUpload当前线程：" + Thread.currentThread().getName());
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        uploadFile();
        stopWatch.stop();
        System.out.println("耗时："+stopWatch.getTime());
        //downFile();
    }


    private static void uploadFile() {
        //用自己电脑的文件
        String filePath = "D:\\tool\\windows\\KMPlayer4.2.2.31.rar";
        File file = new File(filePath);
        //==============================================================
        //下面可以不用动
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        // 创建PutObjectRequest对象。
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, remotePath+file.getName(), file).withProgressListener(new PutObjectProgressListener());

        // 如果需要上传时设置存储类型与访问权限，请参考以下示例代码。
        // ObjectMetadata metadata = new ObjectMetadata();
        // metadata.setHeader(OSSHeaders.OSS_STORAGE_CLASS, StorageClass.Standard.toString());
        // metadata.setObjectAcl(CannedAccessControlList.Private);
        // putObjectRequest.setMetadata(metadata);
        // 上传文件。
        PutObjectResult putObjectResult = ossClient.putObject(putObjectRequest);
        System.out.println(putObjectResult.toString());
        // 关闭OSSClient。
        ossClient.shutdown();
        System.out.println("上传完成。。。。");
    }

    private static void downFile() {
        //用自己电脑的文件及目录
        String fileName = "eDiary_3.3.4.zip";
        String localPath = "E:\\tmp\\oss_file\\";
        //==============================================================
        //下面可以不用动
        String remoteFileName = remotePath + fileName;
        String localFileName = localPath + fileName;
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        // 下载OSS文件到本地文件。如果指定的本地文件存在会覆盖，不存在则新建。
        ossClient.getObject(new GetObjectRequest(bucketName, remoteFileName), new File(localFileName));

        // 关闭OSSClient。
        ossClient.shutdown();
        System.out.println("下载完成。。。。");
    }


}
