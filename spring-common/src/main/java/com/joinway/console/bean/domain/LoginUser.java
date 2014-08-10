package com.joinway.console.bean.domain;

import java.util.Date;

import com.joinway.bean.constant.DBValueConstants;
import com.joinway.bean.domain.DomainEntity;
import com.joinway.bean.domain.annotation.DomainField;
import com.joinway.bean.domain.type.CaseFormat;
import com.joinway.bean.logging.annotation.LogIgnore;
import com.joinway.bean.logging.annotation.LogMask;

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
	
	@DomainField(CaseFormat.Upper)
	String cellPhoneType;

	String imId;
	
	int loginCount;
	
	String enableStatus = DBValueConstants.YES;
	
	String loginStatus = DBValueConstants.NO;
	
	Date lastLoginTime;

	Date createTime;
	
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

	public String getCellPhoneType() {
		return cellPhoneType;
	}

	public void setCellPhoneType(String cellPhoneType) {
		this.cellPhoneType = cellPhoneType;
	}

	public String getImId() {
		return imId;
	}

	public void setImId(String imId) {
		this.imId = imId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
