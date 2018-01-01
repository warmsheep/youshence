package com.youshence.cloud.facade.enums.common;

import org.apache.commons.lang3.StringUtils;

/**
 * 有效状态
 * 
 * @author tianjing
 * @date 2015年6月30日 下午4:25:03
 * @version 2.0
 */
public enum ResultStatus
{
	/**
	 * 1、成功
	 */
	SUCCESS(1, "成功"),
	
	/**
	 * 0、失败
	 */
	FAILURE(0, "失败");
	
	private String name;
	
	private int index;
	
	private ResultStatus(int index, String name)
	{
		this.index = index;
		this.name = name;
	}
	
	/**
	 * 通过下标获得枚举
	 * 
	 * @param index
	 * @author tianjing
	 * @date 2015年7月5日 下午3:22:49
	 */
	public static ResultStatus getByIndex(Integer index)
	{
		if (null == index)
			return null;
		for (ResultStatus at : ResultStatus.values())
		{
			if (at.index == index)
				return at;
		}
		return null;
	}
	
	/**
	 * 通过名称获得枚举
	 * 
	 * @param name
	 * @author tianjing
	 * @date 2015年7月5日 下午3:21:49
	 */
	public static ResultStatus getByName(String name)
	{
		if (StringUtils.isBlank(name))
			return null;
		for (ResultStatus at : ResultStatus.values())
		{
			if (at.name.equals(name))
				return at;
		}
		return null;
	}
	
	@Override
	public String toString()
	{
		return this.index + ":" + this.name;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public int getIndex()
	{
		return index;
	}
	
	public void setIndex(int index)
	{
		this.index = index;
	}
}
