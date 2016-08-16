package com.tianxiafen.entity;

import java.sql.Timestamp;

/**
 * Messagerecord entity. @author MyEclipse Persistence Tools
 */

public class Messagerecord implements java.io.Serializable {

	// Fields

	private Integer id;
	private User user;
	private String phoneNum;
	private String content;
	private String ext;
	private String reference;
	private Timestamp addTime;

	// Constructors

	/** default constructor */
	public Messagerecord() {
	}

	/** full constructor */
	public Messagerecord(User user, String phoneNum, String content,
			String ext, String reference, Timestamp addTime) {
		this.user = user;
		this.phoneNum = phoneNum;
		this.content = content;
		this.ext = ext;
		this.reference = reference;
		this.addTime = addTime;
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

	public String getPhoneNum() {
		return this.phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getExt() {
		return this.ext;
	}

	public void setExt(String ext) {
		this.ext = ext;
	}

	public String getReference() {
		return this.reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public Timestamp getAddTime() {
		return this.addTime;
	}

	public void setAddTime(Timestamp addTime) {
		this.addTime = addTime;
	}

}