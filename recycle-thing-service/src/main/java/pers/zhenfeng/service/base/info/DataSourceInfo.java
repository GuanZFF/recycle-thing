package pers.zhenfeng.service.base.info;

import org.springframework.util.StringUtils;

/**
 * 数据库连接信息
 */
public class DataSourceInfo {

    private String driver = "com.mysql.jdbc.Driver";

    private String url;

    private String username = "root";

    private String password;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        if (StringUtils.isEmpty(driver)) {
            return;
        }
        this.driver = driver;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        if (StringUtils.isEmpty(username)) {
            return;
        }
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
