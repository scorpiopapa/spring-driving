package com.joinway.mobile.bean.form;

import org.hibernate.validator.constraints.Length;
import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

import com.joinway.bean.form.Form;
import com.joinway.bean.logging.annotation.LogIgnore;
import com.joinway.bean.logging.annotation.LogMask;

@ApiObject(name = "AuthForm", show = false)
public abstract class AuthForm extends Form {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6643400074363702461L;

	@ApiObjectField(description = "用户登录账号")
	@Length(min=6,max=20)
	@LogMask
	String name;
	
	@ApiObjectField(description = "用户密码")
	@Length(min=6,max=20)
	@LogIgnore
	String password;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


}
