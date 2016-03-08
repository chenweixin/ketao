package com.springmvc.ketao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Student {

	@Id
	@Column(length=32)
	private String id;
	
	@Column(length=32)
	private String password;
	
	@Column(length=32)
	private String name;
	
	@Column(length=32)
	private boolean sex;
	
	/**
	 * 学院
	 */
	@Column(length=32)
	private String college;
	
	/**
	 * 专业
	 */
	@Column(length=32)
	private String major;
	
	/**
	 * 入学年份
	 */
	@Column(length=32)
	private String period;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isSex() {
		return sex;
	}

	public void setSex(boolean sex) {
		this.sex = sex;
	}

	public String getCollege() {
		return college;
	}

	public void setCollege(String college) {
		this.college = college;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}
	
}
