package pers.zhenfeng.zuul.base;

import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.cloud.netflix.eureka.EurekaClientConfigBean;
import org.springframework.stereotype.Component;
import pers.zhenfeng.core.util.FileUtil;

import java.util.Map;
import java.util.Properties;

/**
 * @author Grow-Worm
 * @date 2018/11/07
 */
@Component
public class ConfigBeanInit implements BeanFactoryPostProcessor {

    private final static Logger LOGGER = LoggerFactory.getLogger(ConfigBeanInit.class);

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

        Properties properties;
        try {
            properties = FileUtil.getPropertiesFile(FileUtil.PATH, FileUtil.CHARSET_NAME);
        } catch (Exception ex) {
            LOGGER.error("Throw exception when load properties.", ex);
            return;
        }

        // 设置服务中心地址
        initEurekaClientConfigBean(beanFactory, properties);
    }

    private void initEurekaClientConfigBean(ConfigurableListableBeanFactory beanFactory, Properties properties) {
        EurekaClientConfigBean eurekaClientConfigBean = beanFactory.getBean(EurekaClientConfigBean.class);

        Map<String, String> serviceUrl = Maps.newHashMap();
        serviceUrl.put(FileUtil.DEFAULT_ZONE, properties.getProperty(FileUtil.DEFAULT_ZONE));
        eurekaClientConfigBean.setServiceUrl(serviceUrl);
    }

}
