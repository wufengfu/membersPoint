package com.tianxiafen.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * Airlinecompany entity. @author MyEclipse Persistence Tools
 */

public class Airlinecompany implements java.io.Serializable {

	// Fields

	private Integer id;
	private String companyName;
	private String shortForm;
	private Set formulas = new HashSet(0);

	// Constructors

	/** default constructor */
	public Airlinecompany() {
	}

	/** full constructor */
	public Airlinecompany(String companyName, String shortForm, Set formulas) {
		this.companyName = companyName;
		this.shortForm = shortForm;
		this.formulas = formulas;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCompanyName() {
		return this.companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getShortForm() {
		return this.shortForm;
	}

	public void setShortForm(String shortForm) {
		this.shortForm = shortForm;
	}

	public Set getFormulas() {
		return this.formulas;
	}

	public void setFormulas(Set formulas) {
		this.formulas = formulas;
	}

	@Override
	public String toString() {
		return "Airlinecompany [id=" + id + ", companyName=" + companyName
				+ ", shortForm=" + shortForm + "]";
	}

}