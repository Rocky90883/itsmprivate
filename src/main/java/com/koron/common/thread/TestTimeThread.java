package com.koron.common.thread;

import org.apache.log4j.Logger;

public class TestTimeThread implements Runnable{

    Logger log = Logger.getLogger(TestTimeThread.class);

    @SuppressWarnings("unchecked")
    @Override
    public void run() {

        log.info("开始线程每天19点0份进行）");

    }

}
