package com.koron.common.type;

public enum LogType {
	
	LOGIN_LOG(1, "登录日志"),
	
	FUNCTION_LOG(2, "功能日志"),
	
	SYSTEM_LOG(4, "系统后台日志");
	
	private Integer code;
	private String name;

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private LogType(Integer code, String name) {
		this.code = code;
		this.name = name;
	}

}
