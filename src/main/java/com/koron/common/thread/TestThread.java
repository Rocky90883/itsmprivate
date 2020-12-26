package com.koron.common.thread;

import org.apache.log4j.Logger;

public class TestThread implements Runnable{

    private static Logger log = Logger.getLogger(TestThread.class);

    /**
     * 执行间隔，单位：分钟
     */
    private int interval;

    /**
     * 标识线程是否在运行
     */
    private boolean isRun;

    public TestThread(int interval) {
        this.interval = interval;
    }

    /**
     * 停止线程
     */
    public void stop() {
        isRun = false;
    }

    @Override
    public void run() {
        isRun = true;

        while(isRun) {
            log.info("开始测试线程（间隔{"+interval+"}分钟）");
            scan();
            try {
                //睡眠interval分钟
                Thread.sleep(interval * 60 * 1000);
            } catch (InterruptedException e) {
                log.error("测试线程被打断"+e.getMessage());
            }
        }
    }
    /**
     * 简单打印一下 测试
     */
    public void scan() {
        System.out.println("测试线程");

    }

}
