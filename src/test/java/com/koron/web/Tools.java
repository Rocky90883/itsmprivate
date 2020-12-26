//package com.koron.web;
//
//
//import java.beans.Introspector;
//import java.beans.PropertyDescriptor;
//import java.io.File;
//import java.lang.reflect.Method;
//import java.math.BigDecimal;
//import java.security.MessageDigest;
//import java.security.NoSuchAlgorithmException;
//import java.sql.Timestamp;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.LinkedHashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Map.Entry;
//import java.util.Objects;
//import java.util.Random;
//import java.util.Set;
//import java.util.UUID;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//import java.util.stream.Collectors;
//
//import javax.servlet.ServletContext;
//import javax.servlet.http.HttpServletRequest;
//
//import org.bson.types.ObjectId;
//import org.koron.ebs.businessLog.LogBean;
//import org.koron.ebs.common.KVBean;
//import org.koron.ebs.module.ModuleService;
//import org.koron.ebs.mybatis.MybatisInfo;
//import org.koron.ebs.mybatis.SessionFactory;
//import org.koron.ebs.mybatis.TaskAnnotation;
//import org.koron.ebs.util.field.EnumElement;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.web.context.ContextLoader;
//import org.springframework.web.context.WebApplicationContext;
//import org.springframework.web.context.support.WebApplicationContextUtils;
//import org.springframework.web.servlet.ModelAndView;
//import org.swan.bean.MessageBean;
//
//
//
//public class Tools {
//    public static final ServletContext context = ContextLoader.getCurrentWebApplicationContext().getServletContext();
//
//    private static Logger logger = LoggerFactory.getLogger(Tools.class);
//
//    private static ModuleService service = null;
//
//
//
//    /**
//     * 获取模块服务组件
//     *
//     * @return
//     */
//    public static final ModuleService getModuleService() {
//        if (service == null) {
//            WebApplicationContext context2 = WebApplicationContextUtils.getWebApplicationContext(context);
//            return (ModuleService) context2.getBean("moduleService");
//        }
//        return service;
//    }
//
//    /**
//     * 获取数据库连接
//     *
//     * @return
//     */
//    public static final SessionFactory getSessionFactory() {
//        MessageBean<SessionFactory> msgBean = getModuleService().invoke(MybatisInfo.MODULENAME, "getSessionFactory",
//                SessionFactory.class);
//        return msgBean.getCode() == 0 ? msgBean.getData() : null;
//    }
//
//    /**
//     * 取得调用该函数的类的包下name对应的文件. 使用 {@link #getView(String, Class)}代替
//     *
//     * @param name
//     *            名称
//     * @param layer
//     *            线程中的层数，默认为3
//     * @return
//     */
//    @Deprecated
//    public static final ModelAndView getView(String name, int layer) {
//        StackTraceElement[] els = Thread.currentThread().getStackTrace();
//        if (els == null || els.length < layer + 1)
//            return new ModelAndView("/" + name);
//        StackTraceElement el = els[layer];
//        String pkgStr = el.getClassName();
//        if (pkgStr.indexOf('.') != -1) {
//            pkgStr = pkgStr.substring(0, pkgStr.lastIndexOf('.'));
//            return new ModelAndView("/" + pkgStr.replace('.', '/') + '/' + name);
//        } else {
//            return new ModelAndView("/" + name);
//        }
//    }
//
//    /**
//     * 取得调用该函数的类的包下name对应的文件. 使用 {@link #getView(String, Class)}代替
//     *
//     * @param name
//     *            名称
//     * @param clazz
//     *            对应类
//     * @return 类对应目录下的name文件作为视图
//     */
//    public static final ModelAndView getView(String name, Class<?> clazz) {
//        String pkgStr = clazz.getPackage().getName();
//        return new ModelAndView("/" + pkgStr.replace('.', '/') + '/' + name);
//    }
//
//    /**
//     * 取得调用该函数的类的包下name对应的文件
//     *
//     * @param name
//     *            名称
//     * @return
//     */
//    public static final ModelAndView getView(String name) {
//        return getView(name, 3);
//    }
//
//    /**
//     * 获取MD5后的字符串
//     *
//     * @param source
//     *            进行加密的字符串
//     * @return
//     */
//    public static String MD5(String source) {
//        try {
//            MessageDigest md = MessageDigest.getInstance("MD5");
//            md.update(source.getBytes());
//            byte[] b = md.digest();
//            StringBuffer sb = new StringBuffer();
//            for (byte c : b) {
//                int val = ((int) c) & 0xff;
//                if (val < 16)
//                    sb.append("0");
//                sb.append(Integer.toHexString(val));
//            }
//            return sb.toString().toUpperCase();
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//
//
//
//
//
////    /**
////     * 通过code 获取数据字典集合
////     *
////     * @param factory
////     * @param code
////     * @return 数据字典集合
////     */
////    public static List<DictionaryVO> listDicByCode(SessionFactory factory, String code) {
////        Objects.requireNonNull(code, "数据字典code参数不能为空");
////        DictionaryMapper mapper = factory.getMapper(DictionaryMapper.class);
////        return mapper.findDictionaryParamName(code);
////    }
//
////    /**
////     * 通过数据字典code以及字典value获取数据字典的name
////     *
////     * @param factory
////     * @param code
////     * @param key
////     * @return 数据字典的value
////     */
////    public static String getDicNameByCodeAndValue(SessionFactory factory, String code, String value) {
////        List<DictionaryVO> dics = listDicByCode(factory, code);
////        DictionaryVO dic = dics.stream().filter(t -> t.getValue().equals(value)).findFirst().orElse(new DictionaryVO());
////        return dic.getName();
////    }
//
////    /**
////     * 获取表字段中英文键值对
////     *
////     * @param factory
////     * @param table
////     * @return 数据字典集合
////     */
////    public static Map<String, String> getFiledName(SessionFactory factory, String table) {
////        FieldComparisonMapper fieldMapper = factory.getMapper(FieldComparisonMapper.class);
////        // 整合键值对
////        List<FieldComparisonBean> list = fieldMapper.selectFieldComparison(table);
////        if (list == null) {
////            return null;
////        }
////        Map<String, String> map = new HashMap<String, String>();
////        for (FieldComparisonBean field : list) {
////            map.put(field.getColumnEn(), field.getColumnZh());
////        }
////        return map;
////    }
//
//    /**
//     * 获取枚举
//     *
//     * @param key
//     *            枚举的key
//     * @return
//     */
//    @SuppressWarnings("unchecked")
//    public static final EnumElement<Object> getEnumByKey(String key, Object... parameter) {
//        try (SessionFactory factory = new SessionFactory()) {
//            return factory.runTask(new Tools(), "getEnum", EnumElement.class, key, parameter);
//        }
//    }
//
//    /**
//     *
//     * @param factory
//     * @param key
//     * @return
//     */
//    @TaskAnnotation("getEnum")
//    private EnumElement<Object> getEnumByKey(SessionFactory factory, String key, Object... parameter) {
//        EnumElement<Object> ret = Constant.enumCache.get(key);
//        if (parameter == null || parameter.length == 0) {
//            if (ret != null)
//                return ret;
//        }
//        logger.debug("获取指定的枚举值：" + key);
//        UserDefineMapper mapper = factory.getMapper(UserDefineMapper.class);
//        EnumDBBean bean = mapper.getEnumByKey(key);
//        ret = new EnumElement<>();
//        ret.setBit(bean.getIsbit());
//        ret.setType(bean.getType());
//        if (bean.getParam() != null && !bean.getParam().isEmpty()) {
//            Map<String, String> map = getDyn(bean.getParam(), parameter);
//            for (Entry<String, String> item : map.entrySet()) {
//                switch (bean.getType()) {
//                    case 0:
//                        ret.put(item.getKey(), item.getValue());
//                        break;
//                    case 1:
//                        ret.put(Integer.parseInt(item.getKey()), item.getValue());
//                        break;
//                    case 2:
//                        ret.put(Long.parseLong(item.getKey()), item.getValue());
//                }
//            }
//        } else {
//            List<EnumDetailDBBean> list = mapper.getEnumDetailByEnumId(bean.getId());
//            for (EnumDetailDBBean enumDetailDBBean : list) {
//                switch (bean.getType()) {
//                    case 0:
//                        ret.put(enumDetailDBBean.getKey(), enumDetailDBBean.getValue());
//                        break;
//                    case 1:
//                        ret.put(Integer.parseInt(enumDetailDBBean.getKey()), enumDetailDBBean.getValue());
//                        break;
//                    case 2:
//                        ret.put(Long.parseLong(enumDetailDBBean.getKey()), enumDetailDBBean.getValue());
//                }
//            }
//        }
//        if (parameter == null || parameter.length == 0)
//            Constant.enumCache.put(key, ret);
//        return ret;
//    }
//
//    /**
//     * 根据类名#方法的方式获取得KVBean数组，然后转换成map
//     *
//     * @param url
//     *            mybatis的接口名加方法名,方法不带参数，返回必须为List<KVBean>
//     * @param parameter
//     *            参数，调用接口用的参数
//     *
//     * @return
//     */
//    public static final LinkedHashMap<String, String> getDyn(String url, Object... parameter) {
//        if (url == null || url.indexOf('#') == -1)
//            return null;
//        String className = url.substring(0, url.indexOf('#'));
//        String methodName = url.substring(url.indexOf('#') + 1);
//        try {
//            SessionFactory factory = new SessionFactory();
//            Class<?> clazz = Class.forName(className);
//            Class<?>[] param = null;
//            if (parameter != null) {
//                param = new Class[parameter.length];
//                for (int i = 0; i < parameter.length; i++) {
//                    param[i] = parameter[i].getClass();
//                }
//            }
//            Method m = clazz.getMethod(methodName, param);
//            @SuppressWarnings("unchecked")
//            List<KVBean> list = (List<KVBean>) m.invoke(factory.getMapper(clazz), parameter);
//            LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
//            for (KVBean kvBean : list) {
//                map.put(kvBean.getKey(), kvBean.getValue());
//            }
//            factory.close();
//            return map;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    /**
//     * 生成16位key
//     *
//     * @return
//     */
//    public static String get16Key() {
//        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
//        char s;
//        String key = "";
//        Random ran = new Random();
//        for (int i = 0; i < 16; i++) {
//            int index = ran.nextInt(32);
//            char[] chars = uuid.toCharArray();
//            s = chars[index];
//            key += s;
//        }
//        return key.toUpperCase();
//    }
//
//    /**
//     * 获取web目录的文件
//     *
//     * @param path
//     *            文件路径
//     * @return
//     */
//    public static final File getWebFile(String path) {
//        String url = context.getRealPath(path);
//        File f = new File(url);
//        if (f.exists())
//            return f;
//        return null;
//    }
//
//    /**
//     * 转换单据状态字段
//     *
//     * @param status
//     * @return
//     */
//    public static String transProcessStatus(String status) {
//        if (StringUtils.isNullOrEmpty(status)) {
//            return "草稿";
//        } else if (Objects.equals(status, "END")) {
//            return "已办结";
//        } else if (Objects.equals(status, "CANCEL")) {
//            return "已废弃";
//        } else {
//            return "进行中";
//        }
//    }
//
//    /**
//     * 获取ObjectId
//     *
//     * @return
//     */
//    public static String getObjectId() {
//        return ObjectId.get().toHexString();
//    }
//
//    /**
//     * 补0
//     *
//     * @return
//     */
//    public static String lpad(int num, int digit) {
//        return String.format("%0" + digit + "d", num);
//    }
//
//    /**
//     * 提供精确的加法运算。
//     *
//     * @param value1
//     *            被加数
//     * @param value2
//     *            加数
//     * @return 两个参数的和
//     */
//    public static Double add(Double value1, Double value2) {
//        BigDecimal b1 = new BigDecimal(Double.toString(value1));
//        BigDecimal b2 = new BigDecimal(Double.toString(value2));
//        return b1.add(b2).doubleValue();
//    }
//
//    /**
//     * 获取当前日期（yyyy-MM-dd）
//     *
//     * @return
//     */
//    public static String getCurrentDate() {
//        Calendar cal = Calendar.getInstance();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        return sdf.format(cal.getTime());
//    }
//
//    /**
//     * 比较两个对象字段值
//     * @param obj1
//     * 		对象1
//     * @param obj2
//     * 		对象2
//     * @param fields
//     * 		对比字段
//     * @return
//     */
//    public static Map<String, List<Object>> compareFields(Object obj1, Object obj2, Map<String, String> fields) {
//        Map<String, List<Object>> map = new HashMap<String, List<Object>>();
//        try {
//            // 只有两个对象都是同一类型的才有可比性
//            if (obj1.getClass() == obj2.getClass()) {
//                @SuppressWarnings("rawtypes")
//                Class clazz = obj1.getClass();
//                // 获取object的属性描述
//                PropertyDescriptor[] pds = Introspector.getBeanInfo(clazz, Object.class).getPropertyDescriptors();
//                // 这里就是所有的属性了
//                for (PropertyDescriptor pd : pds) {
//                    // 属性名
//                    String name = pd.getName();
//                    // 如果当前属性选择进行比较，跳到下一次循环
//                    if (fields != null && fields.containsKey(name)) {
//                        // get方法
//                        Method readMethod = pd.getReadMethod();
//                        // 在obj1上调用get方法等同于获得obj1的属性值
//                        Object objBefore = readMethod.invoke(obj1);
//                        // 在obj2上调用get方法等同于获得obj2的属性值
//                        Object objAfter = readMethod.invoke(obj2);
//                        if (objBefore instanceof Timestamp) {
//                            objBefore = new Date(((Timestamp) objBefore).getTime());
//                        }
//                        if (objAfter instanceof Timestamp) {
//                            objAfter = new Date(((Timestamp) objAfter).getTime());
//                        }
//                        if (objBefore == null && objAfter == null) {
//                            continue;
//                        } else if (objBefore == null && objAfter != null) {
//                            List<Object> list = new ArrayList<Object>();
//                            list.add("''");
//                            list.add(objAfter);
//                            map.put(name, list);
//                            continue;
//                        }
//                        // 比较这两个值是否相等,不等则放入map
//                        if (!objBefore.equals(objAfter)) {
//                            List<Object> list = new ArrayList<Object>();
//                            list.add(objBefore);
//                            list.add(objAfter);
//                            map.put(name, list);
//                        }
//                    }
//                }
//            }
//        } catch (Exception e) {
//            logger.error(e.getMessage());
//            e.printStackTrace();
//        }
//        return map;
//    }
//    /**
//     * 比较两个对象变更字段并且拼接成字符串
//     * @param obj1
//     * 		对象1
//     * @param obj2
//     * 		对象2
//     * @param index
//     * 		变更个数
//     * @param fields
//     * 		对比字段
//     * @return
//     */
//    public static Map<String, String> getChangeItems(Object obj1, Object obj2, int index, Map<String, String> fields) {
//        String arrs = "";
//        Map<String, String> map = new HashMap<String, String>();
//        // 比较对象变更字段值
//        Map<String, List<Object>> changes = compareFields(obj1, obj2, fields);
//        if (changes != null && changes.size() != 0) {
//            // 取出所有的变更项的主键
//            Set<String> set = changes.keySet(); // 取出所有的key值
//            for (String key : set) {
//                if (!fields.containsKey(key)) {
//                    continue;
//                }
//                index++;
//                arrs += index + "、" + fields.get(key) + ":" + changes.get(key).get(0) + "->" + changes.get(key).get(1)
//                        + " ";
//            }
//        }
//        map.put("arrs", arrs);
//        map.put("index", index + "");
//        return map;
//    }
//    /**
//     * 获取虚拟公式中的用户编号
//     * @param formulas
//     * @return
//     */
//    public static List<String> getUserNoFromFormulas(String formulas) {
//        List<String> codes=new ArrayList<String>();
//        //String line = "[000002]+300+[000003]*50%";
//        String regex ="\\[(.*?)]";
//        // 创建 Pattern 对象
//        Pattern r = Pattern.compile(regex);
//        // 现在创建 matcher 对象
//        Matcher m = r.matcher(formulas);
//        while (m.find()) {
//            codes.add(m.group(1));
//        }
//        return codes;
//
//    }
//    /**
//     * 获取当前月份（yyyyMM）
//     *
//     * @return
//     */
//    public static String getYearMonth() {
//        Calendar cal = Calendar.getInstance();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
//        return sdf.format(cal.getTime());
//    }
//
//    /**
//     * 求最小值
//     * @param valus 传入的参数，多个
//     * @return
//     */
//    public static double getMin(double... values) {
//        Double min = Double.MAX_VALUE;
//        for (Double d : values) {
//            if (d < min) {
//                min = d;
//            }
//        }
//        return min;
//    }
//
//}