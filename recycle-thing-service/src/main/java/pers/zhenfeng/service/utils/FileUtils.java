package pers.zhenfeng.service.utils;

import pers.zhenfeng.service.base.info.DataSourceInfo;
import pers.zhenfeng.service.constant.DataSourceConstant;

import java.io.*;
import java.util.Properties;

public class FileUtils {

    public static DataSourceInfo readDataSourceInfo() throws Exception {
        File file = new File(DataSourceConstant.CONFIG_FILE_PATH);
        if (!file.exists() || !file.isFile()) {
            throw new Exception("文件不存在:" + DataSourceConstant.CONFIG_FILE_PATH);
        }
        DataSourceInfo dataSourceInfo = new DataSourceInfo();
        Properties props = new Properties();
        props.load(new InputStreamReader(new FileInputStream(file), DataSourceConstant.CHARSET_NAME));

        dataSourceInfo.setUrl(props.getProperty(DataSourceConstant.URL));
        dataSourceInfo.setPassword(props.getProperty(DataSourceConstant.PASSWORD));
        dataSourceInfo.setDriver(props.getProperty(DataSourceConstant.DRIVER));
        dataSourceInfo.setUsername(props.getProperty(DataSourceConstant.USERNAME));

        return dataSourceInfo;
    }

}
