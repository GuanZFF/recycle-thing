package pers.zhenfeng.core.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * @author Grow-Worm
 * @date 2018/10/29
 */
public class FileUtil {

    public static final String PATH = "/data/server.properties";
    public static final String CHARSET_NAME = "utf-8";
    public static final String DEFAULT_ZONE = "defaultZone";

    /**
     * 读取配置文件
     *
     * @param path        文件路径
     * @param charsetName 字符编码
     *
     * @return 配置文件
     *
     * @throws Exception 可能出现的异常（1、给定的文件路径不存在）
     */
    public static Properties getPropertiesFile(String path, String charsetName) throws Exception {
        if (path == null || charsetName == null) {
            throw new Exception("文件路径和编码不能为空");
        }

        File file = new File(path);
        if (!file.exists() || !file.isFile()) {
            throw new Exception("文件不存在:" + path);
        }

        Properties props = new Properties();
        props.load(new InputStreamReader(new FileInputStream(file), charsetName));

        return props;
    }

}
