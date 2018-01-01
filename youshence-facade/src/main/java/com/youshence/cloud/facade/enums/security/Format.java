package com.youshence.cloud.facade.enums.security;

import org.apache.commons.lang3.StringUtils;

/**
 * 密文数据格式
 * @author linxuan
 *
 */
public enum Format {

	/**
	 * 0、不填充
	 */
	NO_PADDING(0,"不填充"),
	/**
	 * 1、4字节明文长度+明文+补位0
	 */
	PADDING_0(1,"补位0"),
	/**
	 * 2、填充0x00
	 */
	PADDING_00(2,"填充0x00"),
	/**
	 * 3、填充0x80
	 */
	PADDING_80(3,"填充0x80");
	
	private String name;
	
	private int index;
	
	private Format(int index, String name)
	{
		this.index = index;
		this.name = name;
	}

	/**
	 * 通过下标获得枚举
	 * @param index
	 * @return
	 */
	public static Format getByIndex(Integer index) {
		if (null == index)
			return null;
		for (Format at : Format.values()) {
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
	public static Format getByName(String name) {
		if (StringUtils.isBlank(name))
			return null;
		for (Format at : Format.values()) {
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
