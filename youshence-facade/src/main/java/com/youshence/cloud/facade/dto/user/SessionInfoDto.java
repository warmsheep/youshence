package com.youshence.cloud.facade.dto.user;

import java.util.Date;
import java.util.UUID;

import com.youshence.cloud.facade.dto.CustomBaseEntityDto;

/**
 * 会话信息实体
 * @author warmsheep
 *
 */
public class SessionInfoDto extends CustomBaseEntityDto{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String 	sessionId;	//会话ID
	private UUID	userUuid;	//用户UUID
	private Date 	createTime;	//创建时间
	private Date	expireTime;	//过期时间
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public UUID getUserUuid() {
		return userUuid;
	}
	public void setUserUuid(UUID userUuid) {
		this.userUuid = userUuid;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getExpireTime() {
		return expireTime;
	}
	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SessionInfoDto [sessionId=");
		builder.append(sessionId);
		builder.append(", userUuid=");
		builder.append(userUuid);
		builder.append(", createTime=");
		builder.append(createTime);
		builder.append(", expireTime=");
		builder.append(expireTime);
		builder.append(", getId()=");
		builder.append(getId());
		builder.append(", getDeleteStatus()=");
		builder.append(getDeleteStatus());
		builder.append("]");
		return builder.toString();
	}
}
