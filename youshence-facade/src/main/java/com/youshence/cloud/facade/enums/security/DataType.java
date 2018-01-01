package com.youshence.cloud.facade.enums.security;

import org.apache.commons.lang3.StringUtils;

/**
 * 数据格式
 * @author linxuan
 *
 */
public enum DataType {

	/**
	 * 0、ASCII
	 */
	ASCII(0,"ASCII"),
	/**
	 * 1、16进制
	 */
	HEX(1,"16进制");
	
	private String name;
	
	private int index;
	
	private DataType(int index, String name)
	{
		this.index = index;
		this.name = name;
	}

	/**
	 * 通过下标获得枚举
	 * @param index
	 * @return
	 */
	public static DataType getByIndex(Integer index) {
		if (null == index)
			return null;
		for (DataType at : DataType.values()) {
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
	public static DataType getByName(String name) {
		if (StringUtils.isBlank(name))
			return null;
		for (DataType at : DataType.values()) {
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
