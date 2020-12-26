package com.koron.common.timertask;

import com.koron.common.thread.TestThread;
import com.koron.common.thread.TestTimeThread;
import com.koron.common.type.Const;
import com.koron.common.type.SysConfigKey;
import com.koron.common.web.SysConfig;
import com.koron.util.PropertiesUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TestTimeThreadTask {

    private static Logger log = Logger.getLogger(TestThread.class);

    @Autowired
    private ScheduleConfig scheduleConfig;      //时间


    boolean startJob() {
        String switchFlag = SysConfig.getValue(SysConfigKey.Test_Thread_SWITCH);
        if (!Const.SWITCH_ON.equalsIgnoreCase(switchFlag)) {
            // 定时任务开关没开
            log.warn("测试定时线程未启用");
            return false;
        }
        //由定时器分配
        String cron = PropertiesUtil.getProperty("timer.accu.flow.sync");
        scheduleConfig.startJob(cron, new TestTimeThread());

        log.info("测试定时线程已启用");
        return true;
    }

}
