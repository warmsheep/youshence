package com.youshence.cloud.facade.enums.security;

import org.apache.commons.lang3.StringUtils;

/**
 * 算法标识
 * @author linxuan
 *
 */
public enum AlgorithmId {

	/**
	 * 0、ECB
	 */
	ECB(0,"ECB"),
	/**
	 * 1、CBC
	 */
	CBC(1,"CBC");
	
	private String name;
	
	private int index;
	
	private AlgorithmId(int index, String name)
	{
		this.index = index;
		this.name = name;
	}

	/**
	 * 通过下标获得枚举
	 * @param index
	 * @return
	 */
	public static AlgorithmId getByIndex(Integer index) {
		if (null == index)
			return null;
		for (AlgorithmId at : AlgorithmId.values()) {
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
	public static AlgorithmId getByName(String name) {
		if (StringUtils.isBlank(name))
			return null;
		for (AlgorithmId at : AlgorithmId.values()) {
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
