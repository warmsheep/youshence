package com.youshence.cloud.facade.exception;

import org.apache.commons.lang3.StringUtils;

import com.youshence.cloud.facade.constants.ServiceConstants;
import com.youshence.cloud.facade.exception.enums.BaseCoreExceptionEnums;

/**
 * 核心的异常类
 * 
 * @author Warmsheep
 * 
 */
public class BaseCoreException extends BaseException
{
	
	private static final long serialVersionUID = 1L;
	
	public BaseCoreException(String message){
		this.code = ServiceConstants.RES_SYSTEM_ERROR_CODE;
		this.msg = message;
	}
	
	public BaseCoreException(BaseCoreExceptionEnums ex){
		this.code = ex.getKey();
		this.msg = ex.getValue();
	}
	
	public String getCode() {
		return code;
	}
	
	public String getMsg() {
		if(StringUtils.isNotEmpty(this.msg)){
			return this.msg;
		} else {
			return BaseCoreExceptionEnums.getByKey(this.code).getValue();
		}	
	}
	
	@Override
	public String getMessage(){
		if(StringUtils.isNotEmpty(this.msg)){
			return this.msg;
		} else {
			return BaseCoreExceptionEnums.getByKey(this.code).getValue();
		}
	}
	
	
}
