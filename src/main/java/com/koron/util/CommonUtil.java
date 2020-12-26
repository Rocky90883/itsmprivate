package com.koron.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.koron.web.systemmanger.user.UserMapper;
import com.koron.web.systemmanger.user.bean.StaffunBean;
import com.koron.web.workflowUtils.mapper.BaseWorkflowMapper;
import org.apache.commons.lang3.StringUtils;
import org.koron.ebs.mybatis.SessionFactory;
import org.springframework.util.Assert;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class CommonUtil {
    private static final Gson gson = new Gson();
    private static final Gson gson2 = new GsonBuilder().serializeNulls().create();
    private static final ScriptEngine scriptEngine = new ScriptEngineManager().getEngineByName("JavaScript");

    /**
     * 对象转JSON字符串，属性为null时忽略该属性
     *
     * @param obj
     * @return
     */
    public static String toJson(Object obj) {
        return gson.toJson(obj);
    }

    /**
     * 对象转JSON字符串，属性为null时保留
     *
     * @param obj
     * @return
     */
    public static String toJsonIncludeNulls(Object obj) {
        return gson2.toJson(obj);
    }

    /**
     * 获取当前时间（24 yyyy-MM-dd HH:mm:ss）
     *
     * @return
     */
    public static String getCurrentTime() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(cal.getTime());
    }

    /**
     * 获取当前时间的时间戳
     *
     * @return
     */
    public static Date getNowTime() {
        Calendar cal = Calendar.getInstance();
        DateFormat dateFormatterChina = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM);// 格式化输出
        TimeZone timeZoneChina = TimeZone.getTimeZone("Asia/Shanghai");// 获取时区
        dateFormatterChina.setTimeZone(timeZoneChina);// 设置系统时区

        return cal.getTime();
    }

    /**
     * 获取当前时间的时间戳
     *
     * @return
     */
    public static String getNowTimechuo() {
        LocalDate localDate = LocalDate.now();
        Timestamp timestamp= Timestamp.valueOf(LocalDateTime.now());
;        return String.valueOf(timestamp.getTime());
    }


    /**
     * 获取当前日期（yyyy-MM-dd）
     *
     * @return
     */
    public static String getCurrentDate() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(cal.getTime());
    }

    /**
     * 判断是否为数字
     * @param str
     * @return
     */
    public static boolean isInteger(String str) throws Exception{
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }

    public static boolean isValidDate(String str) {
        boolean convertSuccess=true;
    // 指定日期格式为四位年/两位月份/两位日期，注意yyyy/MM/dd区分大小写；
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
    // 设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
            format.setLenient(false);
            format.parse(str);
        } catch (Exception e) {
            // e.printStackTrace();
// 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
            convertSuccess=false;
        }
        return convertSuccess;
    }
        /**
         * 生成16位key
         *
         * @return
         */
	public static String get16Key() {
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		char s;
		String key = "";
		Random ran = new Random();
		for (int i = 0; i < 16; i++) {
			int index = ran.nextInt(32);
			char[] chars = uuid.toCharArray();
			s = chars[index];
			key += s;
		}
		return key.toUpperCase();
	}
	/**
	 * 生成32位key
	 *
	 * @return
	 */
	public static String get32Key() {
		return UUID.randomUUID().toString().replace("-", "");
	}


    /**
     * 按日期生成流水
     * @param Prefix
     * @param suffix
     * @param streamStr
     * @return
     */
	public static String generateStream(String Prefix,String suffix,String streamStr) {
        StringBuffer str = new StringBuffer();
//        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyMMdd"));
        int stream = Integer.parseInt(streamStr)+1;

        str.append(Prefix);
        str.append(date);
        str.append(streamStr.format("%04d", stream));
        str.append(suffix);
        return str.toString();
    }


    /**
     * 根据codes 返回worlflowcode
     * @param factory
     * @param codes
     * @return
     */
    public static String getWorkFlowCodesBySplit(SessionFactory factory, String codes){
        BaseWorkflowMapper mapper = factory.getMapper(BaseWorkflowMapper.class);
        // 通过loginId获取code
        String code = null;
        if (!StringUtils.isEmpty(codes)) {
            String[] codearr = codes.split(",");
            List<String> codeList = mapper.getUserCode(codearr);
            code = String.join(",", codeList);
            return code;
        }else{
            return "xxx";
        }
    }

    /**
     * 根据code 返回worlflowcode
     * @param factory
     * @param code
     * @return
     */
    public static String getWorkFlowCode(SessionFactory factory, String code){
        String str = "";
        if(StringUtils.isEmpty(code)){
            return str;
        }
        StaffunBean staffunBean = factory.getMapper(UserMapper.class).getStaffByLoginId(code);
        if(staffunBean!=null){
            return  staffunBean.getWorkflowCode();
        }
        return str;
    }

    /**
     * 根据流程code 返回审核人名称
     * @param factory
     * @param workflowCode
     * @return
     */
    public static String getDsrName(SessionFactory factory, String workflowCode){
        String str = "";
        if(StringUtils.isEmpty(workflowCode)){
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

    /**
     * 根据流程code 返回审核人名称
     * @param staffunlist
     * @param workflowCode
     * @return
     */
    public static String getDsrNameNew(List<StaffunBean> staffunlist, String workflowCode){
        String str = "";
        if(StringUtils.isEmpty(workflowCode)){
            return str;
        }
        String[] codes = workflowCode.split(",");
        for (String code:codes){
            Optional<StaffunBean> first = staffunlist.stream().filter(s -> s.getWorkflowCode().equals(code)).findFirst();
            if(first.isPresent()){
                String name = staffunlist.stream().filter(s->s.getWorkflowCode().equals(code)).findFirst().get().getName();
                str = str + name + ";";
            }
        }
        return str;
    }

    /**
     * 根据人员code 返回人员名称
     * @param staffunlist
     * @param staffuncode
     * @return
     */
    public static String getStaffNameBycodepacke(List<StaffunBean> staffunlist, String staffuncode){
        String str = "";
        if(StringUtils.isEmpty(staffuncode)){
            return str;
        }
        String[] codes = staffuncode.split(",");
        for (String code:codes){
            Optional<StaffunBean> first = staffunlist.stream().filter(s -> s.getCode().equals(code)).findFirst();
            if(first.isPresent()){
                String name = first.get().getName();
                str = str + name + ";";
            }
        }
        return str;
    }

    /**
     * 检查表单是否有权限编辑
     * @param workflowCode      表单审核人字段
     * @return
     */
    public static int checkedit(String workflowCode){
        String str = "";
        if(StringUtils.isEmpty(workflowCode)){
            return 0;
        }
        String[] codes = workflowCode.split(",");
        String useerWorkFlowCode = SessionUtil.getUseerWorkFlowCode();
        //表单审核人是否包含 当前审核人
        if(Arrays.asList(codes).contains(useerWorkFlowCode)){
            return 1;
        }else{
            return 0;
        }
    }

    /**
     * 检查表单是否有权限编辑
     * @param status      表单审核人字段
     * @return
     */
    public static String converStatusName(Integer status){
        if(status==null){
            return "";
        }

        if(status==1){
            return "启用";
        }
        if(status==2){
            return "草稿";
        }
        if(status==3){
            return "审核中";
        }
        else if(status==4){
            return "驳回";
        }
        else if(status==5){
            return "废弃";
        }
        else if(status==6){
            return "转办中";
        }else{
            return "";
        }
    }

}
