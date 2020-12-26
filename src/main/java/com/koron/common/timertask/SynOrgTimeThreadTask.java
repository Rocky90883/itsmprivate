package com.koron.common.timertask;

import com.koron.common.thread.SynOrgTimeThread;
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
public class SynOrgTimeThreadTask {

    private static Logger log = Logger.getLogger(TestThread.class);

    @Autowired
    private ScheduleConfig scheduleConfig;      //时间


    boolean startJob() {
        String switchFlag = SysConfig.getValue(SysConfigKey.SyncOrganize_Thread_SWITCH);
        if (!Const.SWITCH_ON.equalsIgnoreCase(switchFlag)) {
            // 定时任务开关没开
            log.warn("同步组织人员-定时线程未启用");
            return false;
        }
        //由定时器分配
        String cron = PropertiesUtil.getProperty("timer.sync.org.user");
        scheduleConfig.startJob(cron, new SynOrgTimeThread());

        log.info("同步组织人员-定时线程已启用");
        return true;
    }

}
