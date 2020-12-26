package com.koron.common.timertask;

import com.koron.common.thread.TestThread;
import com.koron.common.type.Const;
import com.koron.common.type.SysConfigKey;
import com.koron.common.web.SysConfig;
import com.koron.util.PropertiesUtil;
import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

@Component
public class TestThreadTask {

    private static Logger log = Logger.getLogger(TestThread.class);

    @Autowired
    private ThreadPoolTaskExecutor executor;

    @Autowired
    private ScheduleConfig scheduleConfig;      //时间

    public boolean start() {
        String switchFlag = SysConfig.getValue(SysConfigKey.Test_Thread_SWITCH);
        if (!Const.SWITCH_ON.equalsIgnoreCase(switchFlag)) {
            // 定时任务开关没开
            log.warn("测试未启用");
            return false;
        }

        int interval = Integer.valueOf(SysConfig.getValue(SysConfigKey.Test_Thread, "1"));

        executor.execute(new TestThread(interval));
        log.info("测试已启用");
        return true;
    }

//    boolean startJob() {
//        String switchFlag = SysConfig.getValue(SysConfigKey.Test_Thread_SWITCH);
//        if (!Const.SWITCH_ON.equalsIgnoreCase(switchFlag)) {
//            // 定时任务开关没开
//            log.warn("测试未启用");
//            return false;
//        }
//
//        int interval = Integer.valueOf(SysConfig.getValue(SysConfigKey.Test_Thread, "1"));
//
//
//        //直接开启
//        executor.execute(new TestThread(interval));
//
//        //由定时器分配
//        String cron = PropertiesUtil.getProperty("timer.accu.flow.sync");
//        scheduleConfig.startJob(cron, new TestThread(interval));
//
//        log.info("测试已启用");
//        return true;
//    }

}
