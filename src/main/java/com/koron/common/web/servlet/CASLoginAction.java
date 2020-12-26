package com.koron.common.web.servlet;

import com.koron.common.type.CacheKey;
import com.koron.util.Constant;
import com.koron.util.HttpUtils;
import com.koron.util.JsonUtils;
import com.koron.web.systemmanger.model.SysModelService;
import com.koron.web.systemmanger.model.bean.SysModelVo;
import com.koron.web.systemmanger.roles.bean.FpModelVo;
import com.koron.web.systemmanger.roles.bean.SysRoleBean;
import com.koron.web.systemmanger.user.UserMapper;
import com.koron.web.systemmanger.user.bean.StaffAccount;
import com.koron.web.systemmanger.user.bean.StaffunBean;
import org.apache.log4j.Logger;
import org.koron.ebs.mybatis.ADOConnection;
import org.koron.ebs.mybatis.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.swan.bean.MessageBean;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
@Configuration
public class CASLoginAction {

	private static Logger log = Logger.getLogger(CASLoginAction.class);

	@Resource
	public JedisPool jedisPool;

	@Value("${sso_accurl}")
	private String sso_accurl;			//从sso获取用户地址（需要ticke）

	@RequestMapping("/umacas.htm")
	public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String ticket = request.getParameter("ticket");
		log.info("登陆ticket:"+ticket);

		if(ticket!=null && !ticket.equals("")) {
//			String url = "http://uma.gdhwater.com/cas/ticket?ticket=" + ticket + "&app=canteen";
			String url = sso_accurl + ticket + "&app=itsm";
			String accjson = HttpUtils.postData("", url);
			log.info("登录用户accjson:" + accjson);
			MessageBean messageBean = new MessageBean();
			try {
				messageBean = HttpUtils.jsonToPojo(accjson, MessageBean.class);
			} catch (Exception e) {
				e.printStackTrace();
			}
			String idsAccount = (String) messageBean.getData();

			try (SessionFactory factory = new SessionFactory();) {
				StaffunBean staffun = factory.getMapper(UserMapper.class).getStaffByLoginId(idsAccount);

				FpModelVo interfaces = ADOConnection.runTask(new LoginController(), "queryModelByStaffuncode", FpModelVo.class, staffun.getCode());
				List<SysModelVo> sysModelVo = ADOConnection.runTask(new SysModelService(), "getSysModelVo", List.class, staffun.getCode());
				List<SysRoleBean> roles = ADOConnection.runTask(new LoginController(), "getRoleBystaffuncode", List.class, staffun.getCode());
				staffun.setInterfaces(sysModelVo);  //返回人员权限

				StaffAccount staffAccount = new StaffAccount(staffun, roles, "my", interfaces);
				//it运维人员、信息中心、it经理可以看到全部部门
				if (roles.stream().anyMatch(r->r.getBroper().equals("all") || staffun.getCode().equals("admin"))) {
					staffAccount.setBroper("all");
				}

				HttpSession se = request.getSession(true);
				se.setMaxInactiveInterval(8*60*60);//8个小时
				se.setAttribute(CacheKey.LOGIN_USER, staffAccount);
				se.getServletContext().setAttribute(idsAccount,se.getId());
//            	String uuid = UUID.randomUUID().toString();
				MessageBean<String> sret = setRedis(se.getId(), staffAccount,"粤海供水itsm系统");
				request.getRequestDispatcher("index.htm").forward(request, response);
				return;
			}
		}

	}

	/**
	 * 记录缓存服务器
	 * @param token
	 * @param userInfo
	 * @return
	 */
	private MessageBean<String> setRedis(String token,StaffAccount userInfo,String moduleName) {
		// 连接redis
		Jedis resource = null;
		try {
			//写redis,因为要设置过期时间，所以这里直接存为顶级变量
			resource = jedisPool.getResource();
			if (resource != null) {
				resource.set(token, JsonUtils.objectToJson(userInfo));
				resource.expire(token, 30 * 60);
			}
		} catch (Exception e) {
			log.error("",e);
			return MessageBean.create(Constant.MESSAGE_DBFAIL, "缓存服务器异常，不能登陆", String.class);
		} finally {
			if (resource != null) {
				resource.close();
			}
		}
		return MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "", String.class);
	}


}
