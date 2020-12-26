package com.koron.util;

import com.koron.common.bean.StaffBean;
import com.koron.common.web.bean.DepartmentTreeBean;
import org.koron.ebs.util.field.EnumElement;

import java.util.HashMap;
import java.util.Map;

public class Constant {
	/**
	 * 登录用户在SESSION里的储存KEY
	 */
public static final String USER = "_user";
	/**
	 * 登陆微信号在SESSION里的储存KEY
	 */
	public static final String OPENID = "_openid";
	/**
	 * 验证码在Session里的存储KEY
	 */
	public static final String VALIDATECODE = "_validate_code";
	// ////////////////////////接口错误说明/////////////
	/**
	 * 操作成功
	 */
	public static final int MESSAGE_INT_SUCCESS = 0;
	/**
	 * 未登录
	 */
	public static final int MESSAGE_INT_NOLOGIN = 10000;
	/**
	 * 无操作权限
	 */
	public static final int MESSAGE_INT_NOMODULE = 10001;
	/**
	 * 密码错误
	 */
	public static final int MESSAGE_INT_PWDERROR = 10002;
	/**
	 * 职员信息不存在
	 */
	public static final int MESSAGE_INT_STAFF_NO_EXIST = 11003;
	/**
	 * 账号已停用
	 */
	public static final int MESSAGE_INT_STAFF_DISABLE = 11004;
	/**
	 * 参数校验异常
	 */
	public static final int MESSAGE_INT_PARAMS = 11005;
	/**MESSAGE_VALIDATION_ERROR
	 * 操作异常（比如不符合删除条件）
	 */
	public static final int MESSAGE_INT_OPERATION=11006;
	/**
	 * 未知错误
	 */
	public static final int MESSAGE_INT_UNKNOW_ERROR = 11007;
	/**
	 * 分配100个错误码给文件转换
	 */
	public static final int MESSAGE_INT_FILE_TRANSFER_ERROR = 20001;
	/**
	 * 分配100个错误吗给数据库
	 */
	public static final int MESSAGE_DBFAIL = 20101;
	/**
	 * 自定义表单增加辅助单位功能(在前台要自动加上辅助功能的脚本)
	 */
	public static final int HAS_ASSIST = 1000;
	// ////////////////////////接口错误说明/////////////

	/**
	 * 台账导入异常
	 */
	public static final int MESSAGE_ASSTETS_PARAMS = 10500;
	/**
	 * 已存在工作流 不允许删除
	 */
	public static final int MESSAGE_WORKDELERROR = 30000;

	public static final Map<String,StaffBean> STAFF = new HashMap<>();
	public static final Map<String,DepartmentTreeBean> DEPARTMENT = new HashMap<>();

	/**
	 * 枚举缓存
	 */
	public final static HashMap<String, EnumElement<Object>> enumCache = new HashMap<>();

	/**
	 * 远程同步数据的数据源名称(工作流)
	 */
	public static final String DB_COMMLIB_DATASOURCE = "_common";

	//now时间
	public static final String noewTime = DateAndTimeUtils.getnow();


	/**
	 * 自定义层别缓存
	 */
//	public static final HashMap<String, Integer> layerCache = new HashMap<>();
	/**
	 * 自定义层别下的所有字段
	 */
//	public static final HashMap<Integer, List<DefineFieldBean>> layerFieldCache = new HashMap<>();
	/**
	 * 自定义字段缓存
	 */
//	public final static HashMap<String, DefineFieldBean> fieldCache = new HashMap<>();
}
