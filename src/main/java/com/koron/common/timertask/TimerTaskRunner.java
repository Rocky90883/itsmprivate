package com.koron.common.timertask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class TimerTaskRunner implements ApplicationRunner {

    @Autowired
    TestThreadTask testThreadTask;

    @Autowired
    TestTimeThreadTask testTimeThreadTask;

    @Autowired
    SynOrgTimeThreadTask synOrgTimeThreadTask;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        start();
        startJob();
    }

    /**
     * 开机启动项(只执行一次)
     */
    public void start(){
        testThreadTask.start();
    }

    /**
     * 定时任务启动项（重复执行）
     */
    private void startJob(){
//        testThreadTask.startJob();
        testTimeThreadTask.startJob();
        synOrgTimeThreadTask.startJob();
    }


}
