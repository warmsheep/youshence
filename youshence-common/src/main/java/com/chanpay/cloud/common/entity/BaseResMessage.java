package com.chanpay.cloud.common.entity;

import java.io.Serializable;

/**
 * 基础返回消息对象
 * @author warmsheep
 *
 */
public class BaseResMessage<T extends Object> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String code;		//状态码
	private String message;		//消息
	private T data;				//返回对象
	
	public BaseResMessage(){}
	
	public BaseResMessage(String code,String message){
		this.code = code;
		this.message = message;
	}
	
	public BaseResMessage(String code,String message,T data){
		this.code = code;
		this.message = message;
		this.data = data;
	}
	
	
	/**
	 * 状态码
	 * @return
	 */
	public String getCode() {
		return code;
	}
	/**
	 * 状态码
	 * @param code
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * 消息
	 * @return
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * 消息
	 * @param message
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	/**
	 * 数据
	 * @return
	 */
	public T getData() {
		return data;
	}
	/**
	 * 数据
	 * @param data
	 */
	public void setData(T data) {
		this.data = data;
	}
	
	
}
