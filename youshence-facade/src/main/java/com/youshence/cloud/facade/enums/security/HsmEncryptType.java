package com.youshence.cloud.facade.enums.security;

import org.apache.commons.lang3.StringUtils;

/**
 * 加密方式
 * @author linxuan
 *
 */
public enum HsmEncryptType {

	/**
	 * 1、加密机加密
	 */
	HSM_ENCRYPT(1,"加密机加密"),
	/**
	 * 2、软加密
	 */
	SOFTWARE_ENCRYPT(2,"软加密");
	
	private String name;
	
	private int index;
	
	private HsmEncryptType(int index, String name)
	{
		this.index = index;
		this.name = name;
	}

	/**
	 * 通过下标获得枚举
	 * @param index
	 * @return
	 */
	public static HsmEncryptType getByIndex(Integer index) {
		if (null == index)
			return null;
		for (HsmEncryptType at : HsmEncryptType.values()) {
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
	public static HsmEncryptType getByName(String name) {
		if (StringUtils.isBlank(name))
			return null;
		for (HsmEncryptType at : HsmEncryptType.values()) {
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
