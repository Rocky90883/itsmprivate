package com.koron.common.exception;


import com.koron.common.type.ErrorCode;

/**
 * 自定义异常类
 * @author ky01
 *
 */
@SuppressWarnings("serial")
public class ServiceException extends RuntimeException {
	private Integer code;

	public ServiceException(ErrorCode exceptionEnum) {
		super(exceptionEnum.getName());
		this.code = exceptionEnum.getCode();
	}

	public ServiceException(Integer code, String msg) {
		super(msg);
		this.code = code;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}
}
