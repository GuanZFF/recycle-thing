package pers.zhenfeng.oss.util;

import com.aliyun.oss.OSSClient;
import pers.zhenfeng.core.util.NumberUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * @author Grow-Worm
 * @date 2018/09/29
 */
public class CommonUtil {

    public static String uploadFile(InputStream inputStream, String fileName) {
        String filePath = "https://recycle-thing.oss-cn-hangzhou.aliyuncs.com/" + fileName;

        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = "https://oss-cn-hangzhou.aliyuncs.com";
        // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
        String accessKeyId = "LTAIdHh2bZGgWM24";
        String accessKeySecret = "dTrzWWJ2Q2tI75ChO7iT8kNuoX3Ooo";
        String bucketName = "recycle-thing";

        // 创建OSSClient实例。
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        // 上传网络流。
        ossClient.putObject(bucketName, fileName, inputStream);
        // 关闭OSSClient。
        ossClient.shutdown();

        return filePath;
    }

    public static void main(String[] args) throws Exception {
        InputStream inputStream = new FileInputStream(new File("/Users/Grow-Worm/Downloads/tt.jpeg"));

        String filePath = uploadFile(inputStream, NumberUtil.generateImageName() + ".jpeg");

        inputStream.close();

        System.out.println(filePath);
    }

}
