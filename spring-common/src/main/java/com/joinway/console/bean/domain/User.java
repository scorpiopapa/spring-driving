package com.joinway.console.bean.domain;

import java.util.Date;

import com.joinway.bean.db.annotation.DomainField;
import com.joinway.bean.db.type.CaseFormat;
import com.joinway.db.bean.domain.DomainEntity;

public class User extends DomainEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1622195219006896158L;

	String cellPhone;
	
	@DomainField(CaseFormat.Upper)
	String cellPhoneType;
	
	String phone;
	
	String name;
	
	@DomainField(CaseFormat.Upper)
	String gender;
	
	int age;
	
	String idCard;
	
	String coach;
	
	String testStatus;
	
	String imId;
	
	String zone;
	
	String address;
	
	String comment;

	Date registerTime;
	
	public String getCellPhone() {
		return cellPhone;
	}

	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getImId() {
		return imId;
	}

	public void setImId(String imId) {
		this.imId = imId;
	}

	public String getZone() {
		return zone;
	}

	public void setZone(String zone) {
		this.zone = zone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getCellPhoneType() {
		return cellPhoneType;
	}

	public void setCellPhoneType(String cellPhoneType) {
		this.cellPhoneType = cellPhoneType;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getCoach() {
		return coach;
	}

	public void setCoach(String teacher) {
		this.coach = teacher;
	}

	public String getTestStatus() {
		return testStatus;
	}

	public void setTestStatus(String testStatus) {
		this.testStatus = testStatus;
	}

	public Date getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}
	
}
