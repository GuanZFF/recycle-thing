package pers.zhenfeng.service.base;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import pers.zhenfeng.service.service.CommonService;
import pers.zhenfeng.service.utils.LogUtil;

import javax.annotation.Resource;

/**
 * @author Grow-Worm
 * @date 2018/10/10
 */
@Component
@EnableScheduling
public class SpringTask {

    @Resource
    private CommonService commonService;

    @Scheduled(cron = "*/5 * * * * ?")
    private void logTask() {
        if (CollectionUtils.isEmpty(LogUtil.recycleLogPOS)) {
            return;
        }

        commonService.insertRecycleLog(LogUtil.recycleLogPOS);

        LogUtil.recycleLogPOS.clear();
    }

}
