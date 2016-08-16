package com.tianxiafen.entity;

/**
 * Systemutil entity. @author MyEclipse Persistence Tools
 */

public class Systemutil implements java.io.Serializable {

	// Fields

	private Integer id;
	private String showName;
	private String name;
	private String content;

	// Constructors

	/** default constructor */
	public Systemutil() {
	}

	/** full constructor */
	public Systemutil(String showName, String name, String content) {
		this.showName = showName;
		this.name = name;
		this.content = content;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getShowName() {
		return this.showName;
	}

	public void setShowName(String showName) {
		this.showName = showName;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}