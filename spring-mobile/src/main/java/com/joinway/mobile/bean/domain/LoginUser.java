package com.joinway.mobile.bean.domain;

import java.util.Date;

import com.joinway.bean.db.annotation.DomainField;
import com.joinway.bean.db.type.CaseFormat;
import com.joinway.bean.logging.annotation.LogIgnore;
import com.joinway.bean.logging.annotation.LogMask;
import com.joinway.db.bean.domain.DomainEntity;
import com.joinway.db.constant.DBValueConstants;

public class LoginUser extends DomainEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2938665189298158123L;

	@DomainField(CaseFormat.Lower)
	@LogMask
	String loginName;
	
	@LogIgnore
	String password;
	
	int loginCount;
	
	String enableStatus = DBValueConstants.YES;
	
	String loginStatus = DBValueConstants.NO;
	
	Date createTime;
	
	Date lastLoginTime;

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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

	public String getEnableStatus() {
		return enableStatus;
	}

	public void setEnableStatus(String status) {
		this.enableStatus = status;
	}

	public String getLoginStatus() {
		return loginStatus;
	}

	public void setLoginStatus(String loginStatus) {
		this.loginStatus = loginStatus;
	}

}
