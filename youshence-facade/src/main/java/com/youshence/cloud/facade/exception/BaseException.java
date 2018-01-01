package com.youshence.cloud.facade.exception;

/**
 * 基础异常类
 * @author warmsheep
 *
 */
public class BaseException extends Exception {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected String code;
	protected String msg;
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getCode() {
		return code;
	}
	public String getMsg() {
		return msg;
	}
}
