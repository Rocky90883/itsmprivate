package com.koron.common.type;

public interface Const {
    String SWITCH_ON = "on";
    String SWITCH_OFF = "off";
    String OK = "OK";
    String ENV_DEFAULT = "_default";
    String ENV_IOT = "_iot";

    /**
     * 夜间结束时间，默认6点
     */
    int NIGHT_END = 6;

    /**
     * 分段数，恒等于1440，1天=1440分钟
     */
    int segmentCount = 60 * 24;
    //int segmentCount = Integer.valueOf(Calendar.getInstance().get(Calendar.YEAR)) % 4 == 0 ? 366 * 60 * 24 : 365 * 60 * 24;

    /**
     * 分段数，闰年 ：527040分钟， 平年：52560分钟
     */
    //Integer yearSegmentCount = Integer.valueOf(Calendar.getInstance().get(Calendar.YEAR)) % 4 == 0 ? 366 * 60 * 24 : 365 * 60 * 24;


    /**
     * 默认的时间间隔
     */
    int interval = 60;

    int STATUS_CANNOT_DELETE = 100;
}
