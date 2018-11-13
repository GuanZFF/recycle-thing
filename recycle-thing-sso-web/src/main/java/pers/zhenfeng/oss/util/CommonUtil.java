package pers.zhenfeng.oss.util;

import com.aliyun.oss.OSSClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pers.zhenfeng.core.util.FileUtil;
import pers.zhenfeng.core.util.NumberUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author Grow-Worm
 * @date 2018/09/29
 */
public class CommonUtil {

    private final static Logger LOGGER = LoggerFactory.getLogger(CommonUtil.class);

    // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
    private static String accessKeyId = "accessKeyId";
    private static String accessKeySecret = "accessKeySecret";
    private static String bucketName = "bucketName";

    static {
        try {
            Properties properties = FileUtil.getPropertiesFile(FileUtil.PATH, FileUtil.CHARSET_NAME);
            accessKeyId = properties.getProperty(accessKeyId);
            accessKeySecret = properties.getProperty(accessKeySecret);
            bucketName = properties.getProperty(bucketName);

        } catch (Exception e) {
            LOGGER.error("初始化文件失败", e);
            e.printStackTrace();
        }
    }

    public static String uploadFile(InputStream inputStream, String fileName) {
        String filePath = "https://recycle-thing.oss-cn-hangzhou.aliyuncs.com/" + fileName;

        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = "https://oss-cn-hangzhou.aliyuncs.com";

        // 创建OSSClient实例。
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        // 上传网络流。
        ossClient.putObject(bucketName, fileName, inputStream);
        // 关闭OSSClient。
        ossClient.shutdown();

        return filePath;
    }

    public static void main(String[] args) throws Exception {
        InputStream inputStream = new FileInputStream(new File("/Users/Grow-Worm/Downloads/default-avatar.jpeg"));

        String filePath = uploadFile(inputStream, NumberUtil.generateImageName() + ".jpeg");

        inputStream.close();

        System.out.println(filePath);
    }

}
