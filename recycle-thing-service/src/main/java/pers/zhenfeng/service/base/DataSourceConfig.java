package pers.zhenfeng.service.base;

import org.apache.http.util.Asserts;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.stereotype.Component;
import pers.zhenfeng.service.base.info.DataSourceInfo;
import pers.zhenfeng.service.utils.FileUtils;

import javax.annotation.Resource;

@Component
public class DataSourceConfig implements InitializingBean {

    @Resource
    private DataSourceProperties dataSourceProperties;

    @Override
    public void afterPropertiesSet() throws Exception {
        DataSourceInfo dataSourceInfo = FileUtils.readDataSourceInfo();

        Asserts.notBlank(dataSourceInfo.getPassword(), "数据库连接密码不为空");
        Asserts.notBlank(dataSourceInfo.getUrl(), "数据库连接路径不为空");

        dataSourceProperties.setUsername(dataSourceInfo.getUsername());
        dataSourceProperties.setPassword(dataSourceInfo.getPassword());
        dataSourceProperties.setDriverClassName(dataSourceInfo.getDriver());
        dataSourceProperties.setUrl(dataSourceInfo.getUrl());
    }

}
