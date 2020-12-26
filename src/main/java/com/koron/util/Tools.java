package com.koron.util;

import com.koron.common.type.CacheKey;
import com.koron.common.web.bean.DepartmentTreeBean;
import com.koron.common.web.mapper.DepartmentMapper;
import com.koron.web.systemmanger.dictionary.DictionaryMapper;
import com.koron.web.systemmanger.dictionary.bean.Parameter;

import com.koron.web.systemmanger.user.UserBean;
import com.koron.web.systemmanger.user.UserMapper;
import com.koron.web.systemmanger.user.UserQueryBean;
import com.koron.web.systemmanger.user.bean.StaffAccount;
import com.koron.web.systemmanger.user.bean.StaffunBean;
import com.mysql.jdbc.StringUtils;
import org.apache.log4j.Logger;
import org.koron.ebs.mybatis.SessionFactory;
import org.springframework.web.servlet.ModelAndView;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Tools {

    private  static Logger log = Logger.getLogger(Tools.class);

    /**
     * 获取用户登录信息(包括用户信息，用户可选水司信息，用户当前水司信息) redis可用从redis中获取，不可用或者redis中没数据，从数据库获取
     *
     * @param token
     * @param jedisPool
     * @return
     */
    public static StaffunBean getLoginBean(String token, JedisPool jedisPool) {
        if (StringUtils.isNullOrEmpty(token)) {
            return null;
        }
        StaffAccount staffAccount;
        // TODO 测试专用
//        if (true) {
//            userInfo.setName("李丹");
//            userInfo.setCode("lidan");
//            userInfo.setWorkflowCode("b44f8dac7e36443c89235ae6390003e6");
//            userInfo.setId(851);
//            userInfo.setOrgNodeCode("5e3af83d-eda2-4a32-b4b5-9e26f194bd4c");//it运维部
//            userInfo.setOrgNodeName("科荣运维部");
//        }
        boolean redisAavailable = true;
        Jedis resource = null;
        try {
            resource = jedisPool.getResource();
            try {
                String pong = resource.ping();
                if (resource == null || pong == "PONG") {
                    redisAavailable = false;
                }
            } catch (Exception e) {
                redisAavailable = false;
                log.error("redis服务连接错误", e);
            }
            // reids可用从redis获取
            if (redisAavailable) {
//                SessionUtil.setAttribute(CacheKey.LOGIN_USER,userInfo);     //开发放开  redis里的用户对象放session里面
                String user = resource.get(token);
                if (!StringUtils.isNullOrEmpty(user)) {
                    // redis用户缓存，登录状态，则顺延12小时
                    resource.expire(token, 8 * 60 * 60);
                    //延长session时长
                    SessionUtil.getSession().setMaxInactiveInterval(12 * 60 * 60);
                    staffAccount = JsonUtils.jsonToPojo(user, StaffAccount.class);
                    SessionUtil.setAttribute(CacheKey.LOGIN_USER,staffAccount);     //开发放开  redis里的用户对象放session里面
                } else {
                    return null;
                }
            } else {
                return null;// 缓存服务器异常，不允许操作
            }
            return staffAccount.getStaffunBean();
        } catch (Exception e) {
            log.error("服务器异常", e);
            return null;
        } finally {
            if (resource != null) {
                resource.close();
            }
        }
    }

    /**
     * 获取登陆用户名
     * @return
     */
//    public static String getloginName(){
//        String loginName = "";
//        if(org.jasig.cas.client.util.AssertionHolder.getAssertion()!=null){
//            loginName = org.jasig.cas.client.util.AssertionHolder.getAssertion().getPrincipal().getName();
//        }
//        return loginName;
//    }

//    public static UserBean getUserInfo(){
//        List<UserBean> list = null;
//        try(SessionFactory factory = new SessionFactory()) {
//            UserQueryBean queryBean = new UserQueryBean();
//            queryBean.setLoginName(Tools.getloginName());
//            list = factory.getMapper(UserMapper.class).list(queryBean);
//            System.out.println(list.get(0).getUserName());
//        }
//        return list.get(0);
//    }

//    /**
//     * 获取所有 部门
//     *
//     * @param factory
//     * @return 数据字典集合
//     */
    public static List<DepartmentTreeBean> listAllDept(SessionFactory factory) {
        DepartmentMapper mapper = factory.getMapper(DepartmentMapper.class);
        return mapper.getAllDepartment();
    }

    /**
     * 获取 部门 id，name 键值对
     * @param factory
     * @return
     */
    public static Map<Integer,String> getDeptMap(SessionFactory factory) {
        List<DepartmentTreeBean> deptList = listAllDept(factory);
        Map<Integer, String> depMap = deptList.stream().collect(Collectors.toMap(DepartmentTreeBean::getId, DepartmentTreeBean::getName));
        return depMap;
    }

//    /**
//     * 根据 部门 id 获取部门 deptName
//     * @param factory
//     * @return
//     */
    public static String getDeptName(SessionFactory factory,Integer deptId) {
            List<DepartmentTreeBean> deptList = listAllDept(factory);
            Map<Integer, String> depMap = deptList.stream().collect(Collectors.toMap(DepartmentTreeBean::getId, DepartmentTreeBean::getName));
            return depMap.get(deptId);
    }


    /**
    * 生成固定长度随机码
    * @param n 长度
    */
    private static long getRandom(long n) {
        long min = 1,max = 9;
        for (int i = 1; i < n; i++) {
        min *= 10;
        max *= 10;
        }
        long rangeLong = (((long) (new Random().nextDouble() * (max - min)))) + min ;
        return rangeLong;
    }


    /**
     * 生成订单号连号
     * @param prefix
     * @param serial
     * @return
     */
    public static String getOrderNo(String prefix,String serial) {
        String today = LocalDate.now().toString().replace("-","").substring(2,6);
        System.out.println(today);
        serial = serial.replaceAll("[a-zA-Z]","" );
        return prefix+today+String.format("%04d",Integer.valueOf(serial)+1);
    }

    /**
     * 获得一个UUID
     * @return String UUID
     */
    public static String get16Key(){
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        return uuid;
    }

    /**
     * @param factory
     * @return 数据字典集合
     */
    public static List<Parameter> getParamList(SessionFactory factory, String regionCode) {
        DictionaryMapper mapper = factory.getMapper(DictionaryMapper.class);
        return mapper.paramListByCode(regionCode);
    }

    /**
     * 根据数据字典组code、参数值获取名称
     */
    public static String getParameterName(SessionFactory factory, String regionCode,String parameterValue) {
        Objects.requireNonNull(regionCode, "数据字典loginId参数不能为空");
        List<Parameter> parameters = getParamList(factory,regionCode);
        return parameters.stream().filter(p->p.getParameterValue().equals(parameterValue)).findFirst().get().getParameterName();
    }

    /**
     * 取得调用该函数的类的包下name对应的文件
     *
     * @param name 名称
     * @return
     */
    public static final ModelAndView getView(String name) {
        return getView(name, 3);
    }

    /**
     * 取得调用该函数的类的包下name对应的文件. 使用 {@link # getView(String, Class)}代替
     *
     * @param name 名称
     * @param layer 线程中的层数，默认为3
     * @return
     */
    @Deprecated
    public static final ModelAndView getView(String name, int layer) {
        StackTraceElement[] els = Thread.currentThread().getStackTrace();
        if (els == null || els.length < layer + 1)
            return new ModelAndView("/" + name);
        StackTraceElement el = els[layer];
        String pkgStr = el.getClassName();
        if (pkgStr.indexOf('.') != -1) {
            pkgStr = pkgStr.substring(0, pkgStr.lastIndexOf('.'));
            return new ModelAndView("/" + pkgStr.replace('.', '/') + '/' + name);
        } else {
            return new ModelAndView("/" + name);
        }
    }


    /**
     * 获取MD5后的字符串
     *
     * @param source 进行加密的字符串
     * @return
     */
    public static String MD5(String source) {
        System.out.println(source);
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(source.getBytes());
            byte[] b = md.digest();
            StringBuffer sb = new StringBuffer();
            for (byte c : b) {
                int val = ((int) c) & 0xff;
                if (val < 16)
                    sb.append("0");
                sb.append(Integer.toHexString(val));
            }
            return sb.toString().toUpperCase();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据 code,code,code 转换 中文、中文、中文
     * @param factory
     * @param workflowCode
     * @return
     */
    public static String getDsrName(SessionFactory factory,String workflowCode){
        String str = "";
        if(org.apache.commons.lang3.StringUtils.isEmpty(workflowCode)){
            return str;
        }
        UserMapper mapper = factory.getMapper(UserMapper.class);
        String[] codes = workflowCode.split(",");
        for (String code:codes){
            String name = mapper.getStaffByWorkCode(code).getName();
            str = str + name + ";";
        }
        return str;
    }



}
