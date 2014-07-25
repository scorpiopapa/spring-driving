package com.joinway.admin.bean;

import java.util.Date;

import com.joinway.bean.BaseBean;
import com.joinway.bean.logging.annotation.LogMask;

public class UserContext extends BaseBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = -319274419703518931L;

	int userId;
	
	@LogMask
	String userName;

	Date lastLoginTime;
	
	int loginCount;
	
	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public int getLoginCount() {
		return loginCount;
	}

	public void setLoginCount(int loginCount) {
		this.loginCount = loginCount;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public void setUserId(String userId) {
		this.userId = Integer.valueOf(userId);
	}
}
