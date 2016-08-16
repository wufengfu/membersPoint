package com.tianxiafen.entity;

/**
 * Formula entity. @author MyEclipse Persistence Tools
 */

public class Formula implements java.io.Serializable {

	// Fields

	private Integer id;
	private Airlinecompany airlinecompany;
	private Integer levelValue;
	private Double levelRatio;

	// Constructors

	/** default constructor */
	public Formula() {
	}

	/** minimal constructor */
	public Formula(Airlinecompany airlinecompany) {
		this.airlinecompany = airlinecompany;
	}

	/** full constructor */
	public Formula(Airlinecompany airlinecompany, Integer levelValue,
			Double levelRatio) {
		this.airlinecompany = airlinecompany;
		this.levelValue = levelValue;
		this.levelRatio = levelRatio;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Airlinecompany getAirlinecompany() {
		return this.airlinecompany;
	}

	public void setAirlinecompany(Airlinecompany airlinecompany) {
		this.airlinecompany = airlinecompany;
	}

	public Integer getLevelValue() {
		return this.levelValue;
	}

	public void setLevelValue(Integer levelValue) {
		this.levelValue = levelValue;
	}

	public Double getLevelRatio() {
		return this.levelRatio;
	}

	public void setLevelRatio(Double levelRatio) {
		this.levelRatio = levelRatio;
	}

}