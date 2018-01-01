package com.youshence.cloud.facade.exception.enums;

import org.apache.commons.lang3.StringUtils;

public enum BaseCoreExceptionEnums {
	
	DB_OBJECT_NOT_NULL("00000001", "数据库记录对象不能为空"),
	PARAMS_NOT_NULL("00000002","参数不能为空"),
	INSERT_FAILED("00000003","插入失败"),
	UPDATE_FAILED("00000004","更新失败"),
	DELETE_FAILED("00000005","删除失败"),
	SELECT_FAILED("00000006","查询失败"),
	USER_ID_NOT_NULL("00000007","用户ID不能为空"),
	ENTITY_OBJECT_NOT_NULL("00000008","实体对象不能为空"),
	ENTITY_OBJECT_ID_NOT_NULL("00000009","实体对象ID不能为空"),
	USER_NOT_EQUALS("00000010","非法操作（用户不一致）"),
	HSM_FAILURE("00000011","加解密失败!");
	
	private String value;
	private String key;
	
	private BaseCoreExceptionEnums(String index, String name)
	{
		this.key = index;
		this.value = name;
	}
	
	/**
	 * 通过下标获得枚举
	 * 
	 * @param key
	 */
	public static BaseCoreExceptionEnums getByKey(String key) {
		if (null == key)
			return null;
		for (BaseCoreExceptionEnums at : BaseCoreExceptionEnums.values()) {
			if (at.key.equals(key))
				return at;
		}
		return null;
	}
	
	/**
	 * 通过名称获得枚举
	 * 
	 * @param value
	 */
	public static BaseCoreExceptionEnums getByValue(String value) {
		if (StringUtils.isBlank(value))
			return null;
		for (BaseCoreExceptionEnums at : BaseCoreExceptionEnums.values()) {
			if (at.value.equals(value))
				return at;
		}
		return null;
	}
	
	@Override
	public String toString() {
		return this.key + ":" + this.value;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
	
}
