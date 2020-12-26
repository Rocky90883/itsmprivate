package com.koron.common.authentication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.koron.common.SSOUtil;
import com.koron.common.bean.ResponseBean;
import com.koron.common.type.CacheKey;
import com.koron.common.web.servlet.DevData;
import com.koron.util.Constant;
import com.koron.util.Tools;
import com.koron.util.JsonUtils;
import com.koron.util.SessionUtil;
import com.koron.web.systemmanger.model.SysModelService;
import com.koron.web.systemmanger.model.bean.ModelTreeBean;
import com.koron.web.systemmanger.roles.SysRoleMapper;
import com.koron.web.systemmanger.roles.bean.FpModelVo;
import com.koron.web.systemmanger.roles.bean.RoleModelVo;
import com.koron.web.systemmanger.roles.bean.SysRoleQueryBean;
import com.koron.web.systemmanger.user.UserMapper;
import com.koron.web.systemmanger.user.bean.StaffunBean;
import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.koron.ebs.mybatis.SessionFactory;
import org.koron.ebs.mybatis.TaskAnnotation;
import org.springframework.http.MediaType;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


import redis.clients.jedis.JedisPool;

//@Slf4j
public class AuthInterceptor extends HandlerInterceptorAdapter {

    private static Logger log = Logger.getLogger(AuthInterceptor.class);

//    @Autowired
//    ModelService modelService;

	@Resource
	private JedisPool jedisPool; // redis缓存池

    public static String token = "";
	/**
	 * 1. 校验是否通过了CAS认证 2. 校验session是否失效 3. 如果session已失效，检查用户是否存在 4.
	 * 校验是否有uri的访问权限
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
        ResponseBean responseBean = new ResponseBean();
        ObjectMapper mapper = new ObjectMapper();
        ServletOutputStream out = response.getOutputStream();
		// 是否通过校验
		boolean isOk = false;
        String path = request.getServletPath();
		String url = request.getRequestURI();
		log.info("拦截请求url:" + url);
		log.info("拦截请求path:" + path);
		url = url.substring(0,url.lastIndexOf("/"));


        //未通过CAS认证(旧)
//		if (null == org.jasig.cas.client.util.AssertionHolder.getAssertion()) {
//			log.error("未通过CAS认证");
//			return false;
//		}

        //获取登陆信息
        if(path.equals("/login/login.htm")){
            return true;
        }
        if(SessionUtil.getLoginUser()==null){
            DevData.devdatainit(request);
        }

        if (SessionUtil.getLoginUser()==null) {
            rspMsg(out,Constant.MESSAGE_INT_NOLOGIN,"登录信息过期，重新登陆");
            return false;
        }

		List<String> userModel = SessionUtil.getUserModel();


//        if(request.getSession().getAttribute(CacheKey.LOGIN_USER)==null){
//            //开发地址
////            response.sendRedirect(SSOUtil.getItsmLoginUrl());
//            response.sendRedirect("/login.htm");
//            return false;
//        }


		// session未失效
//		if (null != userModel && userModel.size()>0) {
//			// 检查是否有权限
//			HandlerMethod handlerMethod = (HandlerMethod) handler;
//			Method method = handlerMethod.getMethod();
//			NoAuth noAuth = method.getAnnotation(NoAuth.class);
//			if (null != noAuth || (CollectionUtils.isNotEmpty(userModel) && userModel.contains(url))) {
//				// 有权限
//				isOk = true;
//			}
//		}
//		// session为空或已失效
//		else {
//		    userModel = new ArrayList<>();
//			// 检查loginName是否存在
////			String loginName = org.jasig.cas.client.util.AssertionHolder.getAssertion().getPrincipal().getName();
////            String loginName = DBUtil.getloginName();
//            UserBean userInfo = ADOConnection.runTask(factory -> userService.getUserInfo(factory, new UserBean(Tools.getloginName())), UserBean.class);
////            UserBean userInfo = ADOConnection.runTask(userService, "getUserInfo", UserBean.class, new UserBean(DBUtil.getloginName()));
//
//			// 用户不存在
//			if (null == userInfo) {
//				// 用户不存在，将提示信息扔给前端
//				throw new ServiceException(ErrorCode.NO_USER);
//			}
//			// 用户存在
//			else {
//				// 查询用户已授权的功能列表
//				List<ModelBean> modelList = ADOConnection.runTask(faction -> modelService.listAllMsgByLoginId(faction,userInfo.getLoginId()) ,List.class);
//				// 拆分出codes和urls
//				if (CollectionUtils.isNotEmpty(modelList)) {
//					for (ModelBean bean : modelList) {
////                        System.out.println(bean.getmRequest());
//                        if (!StringUtils.isEmpty(bean.getmRequest())) {
//                            userModel.add(bean.getmRequest());
//						}
//					}
//				}
//				// 用户登陆信息入缓存
//				SessionUtil.setAttribute(CacheKey.LOGIN_USER, userInfo);
//				// 用户权限信息入缓存
//				SessionUtil.setAttribute(CacheKey.USER_MODEL, userModel);
//
//
//			}
//		}

//        String bodyParameter = getBodyParameter(request);
//        RequestWrapper requestWrapper = new RequestWrapper(request);
//        String bodyParameter = new RequestWrapper(request).getBodyString();
//        System.out.println(bodyParameter);
//        RequestBean requestBean = JsonUtils.jsonToPojoIgnoreNone(bodyParameter, RequestBean.class);
//        log.info("前端token为"+requestBean.getToken());
//
//        requestBean.setToken(token);            //tem    后端独立开发启动，拦截器默认給每个请求写上登陆的token    部署注释
//        log.info("token为"+requestBean.getToken());

        HttpSession se = request.getSession(true);
        log.info("sessionId为:"+se.getId());

        StaffunBean userInfo = Tools.getLoginBean(se.getId(), jedisPool);//sessionId当token

        if(userInfo!=null){
            log.info("缓存服务器登陆id为："+userInfo.getCode());
            log.info("缓存服务器登录用户为："+userInfo.getName());
        }


        if (userInfo != null) {
//            log.info("登陆id为:"+SessionUtil.getUseerInfoCode());
//            log.info("登陆用户为:"+SessionUtil.getLoginUser().getName());
            isOk = true;
        }else{
            //响应前端登陆过期
//            rspMsg(out,Constant.MESSAGE_INT_NOLOGIN,"登陆过期");
            return true;
        }

        // 检查是否有权限
//		HandlerMethod handlerMethod = (HandlerMethod) handler;
//		Method method = handlerMethod.getMethod();
//		NoAuth noAuth = method.getAnnotation(NoAuth.class);
//		// 不用鉴权或有权限
//		if (null != noAuth || (CollectionUtils.isNotEmpty(userModel) && userModel.contains(url))) {
//			isOk = true;
//		}

        isOk = true;
		if (isOk) {
			return true;
		}
//		if (true) {
//			return true;
//		}

        rspMsg(out,Constant.MESSAGE_INT_NOMODULE,"用户无权限访问此接口");
		return  false;
	}

    /**
     * 获取post请求的参数
     * @param request
     * @return
     */
	private String getBodyParameter(HttpServletRequest request){
        String body = "";
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;
        InputStream inputStream = null;
        try {
            inputStream = request.getInputStream();
            if (inputStream != null) {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                char[] charBuffer = new char[128];
                int bytesRead = -1;
                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                    stringBuilder.append(charBuffer, 0, bytesRead);
                }
            } else {
                stringBuilder.append("");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        body = stringBuilder.toString();
        return body;
    }

    /**
     * 判断本次请求的数据类型是否为json
     *
     * @param request request
     * @return boolean
     */
    private boolean isJson(HttpServletRequest request) {
        if (request.getContentType() != null) {
            return request.getContentType().equals(MediaType.APPLICATION_JSON_VALUE) ||
                    request.getContentType().equals(MediaType.APPLICATION_JSON_UTF8_VALUE);
        }

        return false;
    }

    private void rspMsg(ServletOutputStream out,Integer code,String description) throws IOException {

        try {
            ResponseBean bean = new ResponseBean();
            bean.setCode(code);
            bean.setDescription(description);
            String jsonStr = JsonUtils.objectToJson(bean);
            out.write(jsonStr.getBytes("UTF-8"));
            out.flush();
        } catch (Exception e) {
            log.error("拦截失败", e);
            throw new RuntimeException(e.getMessage());
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

}
