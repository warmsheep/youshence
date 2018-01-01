package com.youshence.cloud.facade.enums.security;

import org.apache.commons.lang3.StringUtils;

/**
 * 密钥使用方式
 * @author linxuan
 *
 */
public enum KeyUseType {

	/**
	 * 1、使用密钥名称加密
	 */
	KEY_NAME_ENCRYPT(1,"密钥名称加密"),
	/**
	 * 2、使用密钥密文加密
	 */
	KEY_VALUE_ENCRYPT(2,"密钥密文加密");
	
	private String name;
	
	private int index;
	
	private KeyUseType(int index, String name)
	{
		this.index = index;
		this.name = name;
	}

	/**
	 * 通过下标获得枚举
	 * @param index
	 * @return
	 */
	public static KeyUseType getByIndex(Integer index) {
		if (null == index)
			return null;
		for (KeyUseType at : KeyUseType.values()) {
			if (at.index == index)
				return at;
		}
		return null;
	}
	
	/**
	 * 通过名称获得枚举
	 * @param name
	 * @return
	 */
	public static KeyUseType getByName(String name) {
		if (StringUtils.isBlank(name))
			return null;
		for (KeyUseType at : KeyUseType.values()) {
			if (at.name.equals(name))
				return at;
		}
		return null;
	}
	
	@Override
	public String toString() {
		return this.index + ":" + this.name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getIndex() {
		return index;
	}
	
	public void setIndex(int index) {
		this.index = index;
	}
	
}
