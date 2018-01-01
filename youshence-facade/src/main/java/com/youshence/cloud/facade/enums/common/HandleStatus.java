package com.youshence.cloud.facade.enums.common;

import org.apache.commons.lang3.StringUtils;

/**
 * 处理状态
 * 
 * @author Liugl
 * @version 1.0,2015年10月28日 下午2:40:38,Liugl,Ins
 */
public enum HandleStatus {

	/**
	 * 0、未处理
	 */
	PROCESS_NOT(0, "未处理"),

	/**
	 * 1、部分处理
	 */
	PROCESS_ING(1, "部分处理"),

	/**
	 * 2、已处理
	 */
	PROCESS_FINISH(2, "已处理"),
	/**
	 * 3、无需处理
	 */
	PROCESS_NO_NEED(3,"无需处理");

	private String name;
	private int index;

	private HandleStatus(int index, String name) {
		this.index = index;
		this.name = name;
	}

	/**
	 * 通过下标获得枚举
	 */
	public static HandleStatus getByIndex(Integer index) {
		if (null == index)
			return null;
		for (HandleStatus at : HandleStatus.values()) {
			if (at.index == index)
				return at;
		}
		return null;
	}

	/**
	 * 通过名称获得枚举
	 */
	public static HandleStatus getByName(String name) {
		if (StringUtils.isBlank(name))
			return null;
		for (HandleStatus at : HandleStatus.values()) {
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
