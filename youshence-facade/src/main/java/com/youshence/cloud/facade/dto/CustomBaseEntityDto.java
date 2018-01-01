package com.youshence.cloud.facade.dto;

import java.io.Serializable;

/**
 * 基础实体的传输对象
 * @author warmsheep
 *
 */
public class CustomBaseEntityDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;				//主键
	private Integer deleteStatus=0;	//删除状态
	
	/**
	 * 主键
	 * @return
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 主键
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 删除状态
	 * @return
	 */
	public Integer getDeleteStatus() {
		return deleteStatus;
	}
	/**
	 * 删除状态
	 * @param deleteStatus
	 */
	public void setDeleteStatus(Integer deleteStatus) {
		this.deleteStatus = deleteStatus;
	}
	
	
}
