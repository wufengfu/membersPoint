package com.tianxiafen.entity;

import java.sql.Timestamp;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Pointrecord entity. @author MyEclipse Persistence Tools
 */

public class Pointrecord implements java.io.Serializable {

	// Fields

	private Integer id;
	private User user;
	private Airlinecompany airlinecompany;
	private String serialNum;
	private Integer dealPoint;
	private Integer dealStatus;
	private Integer comStatus;
	private Integer dealType;
	private Timestamp dealTime;

	// Constructors

	/** default constructor */
	public Pointrecord() {
	}

	/** full constructor */
	public Pointrecord(User user, Airlinecompany airlinecompany,
			String serialNum, Integer dealPoint, Integer dealStatus,
			Integer comStatus, Integer dealType, Timestamp dealTime) {
		this.user = user;
		this.airlinecompany = airlinecompany;
		this.serialNum = serialNum;
		this.dealPoint = dealPoint;
		this.dealStatus = dealStatus;
		this.comStatus = comStatus;
		this.dealType = dealType;
		this.dealTime = dealTime;
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

	public Integer getDealPoint() {
		return this.dealPoint;
	}

	public void setDealPoint(Integer dealPoint) {
		this.dealPoint = dealPoint;
	}

	public Integer getDealStatus() {
		return this.dealStatus;
	}

	public void setDealStatus(Integer dealStatus) {
		this.dealStatus = dealStatus;
	}

	public Integer getComStatus() {
		return this.comStatus;
	}

	public void setComStatus(Integer comStatus) {
		this.comStatus = comStatus;
	}

	public Integer getDealType() {
		return this.dealType;
	}

	public void setDealType(Integer dealType) {
		this.dealType = dealType;
	}
	@Temporal(TemporalType.TIMESTAMP) 
	public Timestamp getDealTime() {
		return this.dealTime;
	}

	public void setDealTime(Timestamp dealTime) {
		this.dealTime = dealTime;
	}

	@Override
	public String toString() {
		return "Pointrecord [id=" + id + ", serialNum=" + serialNum
				+ ", dealPoint=" + dealPoint + ", dealStatus=" + dealStatus
				+ ", comStatus=" + comStatus + ", dealType=" + dealType
				+ ", dealTime=" + dealTime + "]";
	}

}