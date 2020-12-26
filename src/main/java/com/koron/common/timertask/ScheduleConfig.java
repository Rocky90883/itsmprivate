package com.koron.common.timertask;

import java.util.Date;
import java.util.concurrent.ScheduledFuture;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * 定时任务配置类
 * 多例模式
 * @author Gaoyuan
 * @date 2018年9月26日
 *
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ScheduleConfig {

    private static Logger log = Logger.getLogger(ScheduleConfig.class);

    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;

    private ScheduledFuture<?> future;

    private String cron;

    public String getCron() {
        return cron;
    }

    /**
     * 停止任务
     */
    public void stopJob() {
        if (null != future) {
            future.cancel(true);
            log.info("Schedule job has stopped");
        }
    }

    /**
     * 开启任务
     * @param cron
     */
    public void startJob(String cron, Runnable task) {
        stopJob();

        this.cron = cron;
        future = threadPoolTaskScheduler.schedule(task, new Trigger() {
            @Override
            public Date nextExecutionTime(TriggerContext triggerContext) {
                if (StringUtils.isEmpty(cron)) {
                    return null;
                }

                CronTrigger trigger = new CronTrigger(cron);
                return trigger.nextExecutionTime(triggerContext);
            }
        });

        log.info("Schedule job has started");
    }


}
