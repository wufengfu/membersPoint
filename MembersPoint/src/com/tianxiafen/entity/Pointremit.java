package com.tianxiafen.entity;

import java.sql.Timestamp;

/**
 * Pointremit entity. @author MyEclipse Persistence Tools
 */

public class Pointremit implements java.io.Serializable {

	// Fields

	private Integer id;
	private User user;
	private Airlinecompany airlinecompany;
	private String serialNum;
	private String cardNum;
	private String familyName;
	private String familyNameSpell;
	private String realName;
	private String realNameSpell;
	private String remitName;
	private String phoneNum;
	private Integer mileage;
	private Integer points;
	private Timestamp addTime;
	private Timestamp passTime;
	private Integer statues;

	// Constructors

	/** default constructor */
	public Pointremit() {
	}

	/** minimal constructor */
	public Pointremit(String cardNum) {
		this.cardNum = cardNum;
	}

	/** full constructor */
	public Pointremit(User user, Airlinecompany airlinecompany,
			String serialNum, String cardNum, String familyName,
			String familyNameSpell, String realName, String realNameSpell,
			String remitName, String phoneNum, Integer mileage, Integer points,
			Timestamp addTime, Timestamp passTime, Integer statues) {
		this.user = user;
		this.airlinecompany = airlinecompany;
		this.serialNum = serialNum;
		this.cardNum = cardNum;
		this.familyName = familyName;
		this.familyNameSpell = familyNameSpell;
		this.realName = realName;
		this.realNameSpell = realNameSpell;
		this.remitName = remitName;
		this.phoneNum = phoneNum;
		this.mileage = mileage;
		this.points = points;
		this.addTime = addTime;
		this.passTime = passTime;
		this.statues = statues;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Airlinecompany getAirlinecompany() {
		return this.airlinecompany;
	}

	public void setAirlinecompany(Airlinecompany airlinecompany) {
		this.airlinecompany = airlinecompany;
	}

	public String getSerialNum() {
		return this.serialNum;
	}

	public void setSerialNum(String serialNum) {
		this.serialNum = serialNum;
	}

	public String getCardNum() {
		return this.cardNum;
	}

	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}

	public String getFamilyName() {
		return this.familyName;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	public String getFamilyNameSpell() {
		return this.familyNameSpell;
	}

	public void setFamilyNameSpell(String familyNameSpell) {
		this.familyNameSpell = familyNameSpell;
	}

	public String getRealName() {
		return this.realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getRealNameSpell() {
		return this.realNameSpell;
	}

	public void setRealNameSpell(String realNameSpell) {
		this.realNameSpell = realNameSpell;
	}

	public String getRemitName() {
		return this.remitName;
	}

	public void setRemitName(String remitName) {
		this.remitName = remitName;
	}

	public String getPhoneNum() {
		return this.phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public Integer getMileage() {
		return this.mileage;
	}

	public void setMileage(Integer mileage) {
		this.mileage = mileage;
	}

	public Integer getPoints() {
		return this.points;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}

	public Timestamp getAddTime() {
		return this.addTime;
	}

	public void setAddTime(Timestamp addTime) {
		this.addTime = addTime;
	}

	public Timestamp getPassTime() {
		return this.passTime;
	}

	public void setPassTime(Timestamp passTime) {
		this.passTime = passTime;
	}

	public Integer getStatues() {
		return this.statues;
	}

	public void setStatues(Integer statues) {
		this.statues = statues;
	}

}