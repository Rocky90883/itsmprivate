package com.koron.common;


import com.koron.common.type.CacheKey;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

//@Component
public class SSOFilter implements Filter {

    private Logger log = Logger.getLogger(SSOFilter.class);

    private String ticketurl="";       //获取ticke地址
    private String servercas="";      //项目单点地址
//    private String ticketurl="http://uma.gdhwater.com/ekp/wanhu?url=";       //获取ticke地址
//    private String servercas="http://localhost:8080/canteen/umacas.htm";     //项目单点地址

    private static List<String> existenceList = Arrays.asList("/login.htm","/login/login.htm");

    /**
     * 验证是否通过sso登录过来的。验证是则解密后直接进入登录页面
     */
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());

        log.info(request.getRequestURI());
        log.info(SSOUtil.getItsmLoginUrl());
        log.info(SSOUtil.getTicketUrl());
        log.info(SSOUtil.getServerCas());


//        if(existenceList.contains(request.getRequestURI())){
//            filterChain.doFilter(request, response);
//            return;
//        }

        if(request.getSession().getAttribute(CacheKey.LOGIN_USER)==null){
            //开发地址
//            response.sendRedirect(SSOUtil.getItsmLoginUrl());
            //正式单点
            System.out.println(SSOUtil.getUmaAndCas());
            response.sendRedirect(SSOUtil.getUmaAndCas());
            return;
        }else{
            filterChain.doFilter(request, response);
        }

    }

    public void destroy() {

    }

    public void init(FilterConfig arg0) throws ServletException {

//        if(arg0.getInitParameter("ticketurl")!=null){
//            ticketurl = arg0.getInitParameter("ticketurl");
//        }
//        if(arg0.getInitParameter("servercas")!=null){
//            servercas = arg0.getInitParameter("servercas");
//        }


    }
}
