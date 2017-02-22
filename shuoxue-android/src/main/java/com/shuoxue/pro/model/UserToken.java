package com.shuoxue.pro.model;


import java.io.Serializable;

public class UserToken implements Serializable{
	private static final long serialVersionUID = 6619075415631956801L;

	private String userId;
	private String token;
	private String logName;
	private String groupName;
	private String groupId;
	private String role;//角色。
	private int expires;

	
	public UserToken() {
		super();
	}
	public UserToken(String userId, String token, String logName, String role,
			int expires) {
		super();
		this.userId = userId;
		this.token = token;
		this.logName = logName;
		this.role = role;
		this.expires = expires;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getLogName() {
		return logName;
	}

	public void setLogName(String logName) {
		this.logName = logName;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}


	public int getExpires() {
		return expires;
	}

	public void setExpires(int expires) {
		this.expires = expires;
	}

	@Override
	public String toString() {
		return "UserToken{" +
				"userId='" + userId + '\'' +
				", token='" + token + '\'' +
				", logName='" + logName + '\'' +
				", groupName='" + groupName + '\'' +
				", groupId='" + groupId + '\'' +
				", role='" + role + '\'' +
				", expires=" + expires +
				'}';
	}
}
