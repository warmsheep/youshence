package com.chanpay.cloud.common.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.apache.ibatis.type.JdbcType;

import tk.mybatis.mapper.annotation.ColumnType;

/**
 * 基础信息
 *
 */
public class CustomBaseEntity {
	
	@Id	
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ColumnType(jdbcType=JdbcType.BIGINT)
	private Long id;

	@Column(name="delete_status")
	@ColumnType(jdbcType=JdbcType.INTEGER)
	private Integer deleteStatus = 0;
	
	/**
	 * ID
	 * @return
	 */
	public Long getId() {
		return id;
	}
	/**
	 * ID
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
