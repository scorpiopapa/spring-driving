package com.joinway.admin.bean.view;

import java.util.Date;

import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

import com.joinway.bean.logging.annotation.LogMask;
import com.joinway.bean.view.View;

@ApiObject(name = "LoginView", description = "登录成功返回结果")
public class LoginView extends View {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7556348699529664324L;

	@ApiObjectField(description = "用户id")
	String userId;
	
	@ApiObjectField(description = "登录用户名")
	@LogMask
	String userName;

	@ApiObjectField(description = "最后一次登录时间")
	Date lastLoginTime;
	
	@ApiObjectField(description = "当前登录次数")
	int loginCount;
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
//	public void setUserId(int userId) {
//		this.userId = String.valueOf(userId);
//	}
}
