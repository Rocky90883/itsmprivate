package com.koron.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SSOUtil {

    //开发登陆地址
    public static String itsmLoginUrl;

    //获取ticket地址
    public static String ticketUrl;

    //获取用户名称地址
    public static String serverCas;


    public static String getItsmLoginUrl() {
        return itsmLoginUrl;
    }

    @Value("${itsm.login.url}")
    public void setItsmLoginUrl(String itsmLoginUrl) {
        SSOUtil.itsmLoginUrl = itsmLoginUrl;
    }

    public static String getTicketUrl() {
        return ticketUrl;
    }

    @Value("${ticket.url}")
    public void setTicketUrl(String ticketUrl) {
        SSOUtil.ticketUrl = ticketUrl;
    }

    public static String getServerCas() {
        return serverCas;
    }

    @Value("${server.cas}")
    public void setServerCas(String serverCas) {
        SSOUtil.serverCas = serverCas;
    }


    public static String getUmaAndCas() {
        return ticketUrl+serverCas;
    }

}
