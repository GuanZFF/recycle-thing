package pers.zhenfeng.zuul.base;

import com.google.common.collect.Maps;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.cloud.netflix.eureka.EurekaClientConfigBean;
import org.springframework.stereotype.Component;
import pers.zhenfeng.core.util.FileUtil;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Properties;

/**
 * @author Grow-Worm
 * @date 2018/10/29
 */
@Component
public class ApplicationConfig implements InitializingBean {

    @Resource
    private EurekaClientConfigBean eurekaClientConfigBean;

    @Override
    public void afterPropertiesSet() throws Exception {
        // 读取配置文件设置
        Properties properties = FileUtil.getPropertiesFile(FileUtil.PATH, FileUtil.CHARSET_NAME);

        // 设置服务中心地址
        Map<String, String> serviceUrl = Maps.newHashMap();
        serviceUrl.put(FileUtil.DEFAULT_ZONE, properties.getProperty(FileUtil.DEFAULT_ZONE));
        eurekaClientConfigBean.setServiceUrl(serviceUrl);
    }
}
