package com.tianxiafen.entity;

import java.sql.Timestamp;

/**
 * Pointabouchemenet entity. @author MyEclipse Persistence Tools
 */

public class Pointabouchemenet implements java.io.Serializable {

	// Fields

	private Integer id;
	private User user;
	private String serialNum;
	private Double moneyAmount;
	private Integer points;
	private String postscript;
	private Integer statues;
	private Timestamp addTime;
	private Timestamp passTime;

	// Constructors

	/** default constructor */
	public Pointabouchemenet() {
	}

	/** minimal constructor */
	public Pointabouchemenet(Integer statues) {
		this.statues = statues;
	}

	/** full constructor */
	public Pointabouchemenet(User user, String serialNum, Double moneyAmount,
			Integer points, String postscript, Integer statues,
			Timestamp addTime, Timestamp passTime) {
		this.user = user;
		this.serialNum = serialNum;
		this.moneyAmount = moneyAmount;
		this.points = points;
		this.postscript = postscript;
		this.statues = statues;
		this.addTime = addTime;
		this.passTime = passTime;
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

	public String getSerialNum() {
		return this.serialNum;
	}

	public void setSerialNum(String serialNum) {
		this.serialNum = serialNum;
	}

	public Double getMoneyAmount() {
		return this.moneyAmount;
	}

	public void setMoneyAmount(Double moneyAmount) {
		this.moneyAmount = moneyAmount;
	}

	public Integer getPoints() {
		return this.points;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}

	public String getPostscript() {
		return this.postscript;
	}

	public void setPostscript(String postscript) {
		this.postscript = postscript;
	}

	public Integer getStatues() {
		return this.statues;
	}

	public void setStatues(Integer statues) {
		this.statues = statues;
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

	@Override
	public String toString() {
		return "Pointabouchemenet [id=" + id + ", serialNum=" + serialNum
				+ ", moneyAmount=" + moneyAmount + ", points=" + points
				+ ", postscript=" + postscript + ", statues=" + statues
				+ ", addTime=" + addTime + ", passTime=" + passTime + "]";
	}

}