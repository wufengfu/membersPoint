package com.tianxiafen.entity;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * User entity. @author MyEclipse Persistence Tools
 */

public class User implements java.io.Serializable {

	// Fields

	private Integer id;
	private String openId;
	private String nickname;
	private Integer subscribe;
	private Integer sex;
	private String country;
	private String province;
	private String city;
	private String language;
	private String headImageUrl;
	private Timestamp subScribeTime;
	private String unionId;
	private String remark;
	private Integer groupId;
	private String taglibList;
	private String familyName;
	private String familyNameSpell;
	private String realName;
	private String realNameSpell;
	private String fullName;
	private String phoneNum;
	private Integer pointBalance;
	private String transactionPassword;
	private Integer statues;
	private Set messagerecords = new HashSet(0);
	private Set pointrecords = new HashSet(0);
	private Set pointremits = new HashSet(0);
	private Set pointabouchemenets = new HashSet(0);

	// Constructors

	/** default constructor */
	public User() {
	}

	/** minimal constructor */
	public User(Integer pointBalance, Integer statues) {
		this.pointBalance = pointBalance;
		this.statues = statues;
	}

	/** full constructor */
	public User(String openId, String nickname, Integer sex, String country,
			String province, String city, String language, String headImageUrl,
			Timestamp subScribeTime, String unionId, String remark,
			Integer groupId, String taglibList, String familyName,
			String familyNameSpell, String realName, String realNameSpell,
			String fullName, String phoneNum, Integer pointBalance,
			String transactionPassword, Integer statues, Set messagerecords,
			Set pointrecords, Set pointremits, Set pointabouchemenets) {
		this.openId = openId;
		this.nickname = nickname;
		this.sex = sex;
		this.country = country;
		this.province = province;
		this.city = city;
		this.language = language;
		this.headImageUrl = headImageUrl;
		this.subScribeTime = subScribeTime;
		this.unionId = unionId;
		this.remark = remark;
		this.groupId = groupId;
		this.taglibList = taglibList;
		this.familyName = familyName;
		this.familyNameSpell = familyNameSpell;
		this.realName = realName;
		this.realNameSpell = realNameSpell;
		this.fullName = fullName;
		this.phoneNum = phoneNum;
		this.pointBalance = pointBalance;
		this.transactionPassword = transactionPassword;
		this.statues = statues;
		this.messagerecords = messagerecords;
		this.pointrecords = pointrecords;
		this.pointremits = pointremits;
		this.pointabouchemenets = pointabouchemenets;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOpenId() {
		return this.openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public Integer getSubscribe() {
		return subscribe;
	}

	public void setSubscribe(Integer subscribe) {
		this.subscribe = subscribe;
	}

	public String getNickname() {
		return this.nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Integer getSex() {
		return this.sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getProvince() {
		return this.province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getLanguage() {
		return this.language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getHeadImageUrl() {
		return this.headImageUrl;
	}

	public void setHeadImageUrl(String headImageUrl) {
		this.headImageUrl = headImageUrl;
	}

	public Timestamp getSubScribeTime() {
		return this.subScribeTime;
	}

	public void setSubScribeTime(Timestamp subScribeTime) {
		this.subScribeTime = subScribeTime;
	}

	public String getUnionId() {
		return this.unionId;
	}

	public void setUnionId(String unionId) {
		this.unionId = unionId;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getGroupId() {
		return this.groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public String getTaglibList() {
		return this.taglibList;
	}

	public void setTaglibList(String taglibList) {
		this.taglibList = taglibList;
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

	public String getFullName() {
		return this.fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPhoneNum() {
		return this.phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public Integer getPointBalance() {
		return this.pointBalance;
	}

	public void setPointBalance(Integer pointBalance) {
		this.pointBalance = pointBalance;
	}

	public String getTransactionPassword() {
		return this.transactionPassword;
	}

	public void setTransactionPassword(String transactionPassword) {
		this.transactionPassword = transactionPassword;
	}

	public Integer getStatues() {
		return this.statues;
	}

	public void setStatues(Integer statues) {
		this.statues = statues;
	}

	public Set getMessagerecords() {
		return this.messagerecords;
	}

	public void setMessagerecords(Set messagerecords) {
		this.messagerecords = messagerecords;
	}

	public Set getPointrecords() {
		return this.pointrecords;
	}

	public void setPointrecords(Set pointrecords) {
		this.pointrecords = pointrecords;
	}

	public Set getPointremits() {
		return this.pointremits;
	}

	public void setPointremits(Set pointremits) {
		this.pointremits = pointremits;
	}

	public Set getPointabouchemenets() {
		return this.pointabouchemenets;
	}

	public void setPointabouchemenets(Set pointabouchemenets) {
		this.pointabouchemenets = pointabouchemenets;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", openId=" + openId + ", nickname="
				+ nickname + ", sex=" + sex + ", country=" + country
				+ ", province=" + province + ", city=" + city + ", language="
				+ language + ", headImageUrl=" + headImageUrl
				+ ", subScribeTime=" + subScribeTime + ", unionId=" + unionId
				+ ", remark=" + remark + ", groupId=" + groupId
				+ ", taglibList=" + taglibList + ", familyName=" + familyName
				+ ", familyNameSpell=" + familyNameSpell + ", realName="
				+ realName + ", realNameSpell=" + realNameSpell + ", fullName="
				+ fullName + ", phoneNum=" + phoneNum + ", pointBalance="
				+ pointBalance + ", transactionPassword=" + transactionPassword
				+ ", statues=" + statues + "]";
	}

}