package com.youshence.cloud.facade.dto;

import java.io.Serializable;

/**
 * API请求接口父类
 * @author warmsheep
 *
 */
public class ApiBaseReqDto implements Serializable {

	private String token;		//令牌
	private String clientNo;	//客户端编号
	private String sign;		//签名

	/**
	 * 令牌
	 * @return
	 */
	public String getToken() {
		return token;
	}
	/**
	 * 令牌
	 * @param token
	 */
	public void setToken(String token) {
		this.token = token;
	}
	/**
	 * 签名
	 * @return
	 */
	public String getSign() {
		return sign;
	}
	/**
	 * 签名
	 * @param sign
	 */
	public void setSign(String sign) {
		this.sign = sign;
	}
	/**
	 * 客户端编号
	 * @return
	 */
	public String getClientNo() {
		return clientNo;
	}
	/**
	 * 客户端编号
	 * @param clientNo
	 */
	public void setClientNo(String clientNo) {
		this.clientNo = clientNo;
	}
	
	
}
