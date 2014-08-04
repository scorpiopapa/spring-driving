package com.joinway.console.bean.domain;

import com.joinway.bean.db.annotation.DomainField;
import com.joinway.bean.db.type.CaseFormat;
import com.joinway.db.bean.domain.DomainEntity;

public class Coach extends DomainEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1622195219006896158L;

	/**
	 * 姓名
	 */
	String name;
	
	/**
	 * 性别
	 */
	@DomainField(CaseFormat.Upper)
	String gender;
	
	String carBand;

	/**
	 * 固话
	 */
	String phone;
	
	String cellPhone;

	int carType;
	
	int classType;
	
	int trainClass;
	
	int department;
	
	/**
	 * 初次领证
	 */
	String license;
	
	/**
	 * 参加工作
	 */
	String workDetail;
	
	String idCard;
	
	/**
	 * 准教范围
	 */
	String coachContent;
	
	/**
	 * 准教证号
	 */
	String coachLiscense;
	
	String address;
	
	String comment;

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

	public String getCarBand() {
		return carBand;
	}

	public void setCarBand(String carBand) {
		this.carBand = carBand;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCellPhone() {
		return cellPhone;
	}

	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}

	public String getLicense() {
		return license;
	}

	public void setLicense(String license) {
		this.license = license;
	}

	public String getWorkDetail() {
		return workDetail;
	}

	public void setWorkDetail(String workDetail) {
		this.workDetail = workDetail;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getCoachContent() {
		return coachContent;
	}

	public void setCoachContent(String coachContent) {
		this.coachContent = coachContent;
	}

	public String getCoachLiscense() {
		return coachLiscense;
	}

	public void setCoachLiscense(String coachLiscense) {
		this.coachLiscense = coachLiscense;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getCarType() {
		return carType;
	}

	public void setCarType(int carType) {
		this.carType = carType;
	}

	public int getClassType() {
		return classType;
	}

	public void setClassType(int classType) {
		this.classType = classType;
	}

	public int getTrainClass() {
		return trainClass;
	}

	public void setTrainClass(int trainClass) {
		this.trainClass = trainClass;
	}

	public int getDepartment() {
		return department;
	}

	public void setDepartment(int department) {
		this.department = department;
	}

}
