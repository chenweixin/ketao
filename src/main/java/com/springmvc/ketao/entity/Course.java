package com.springmvc.ketao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Course {

	@Id
	@Column(length=32)
	private String id;
	
	@Column(length=32)
	private String name;
	
	@Column(length=32)
	private String teacher_id;
	
	@Column(length=32)
	private String teacher_name;

	@Column(length=32)
	private String location;
	
	@Column
	private Integer credit;
	
	/**
	 * 0：专业选修
	 * 1：人文科学
	 * 2：人文科学核心
	 * 3：社会科学
	 * 4：社会科学核心
	 * 5：科学技术
	 * 6：科学技术核心
	 */
	@Column
	private Integer type;
	
	@Column(length=32)
	private String create_time;
	
	@Column(columnDefinition="INT default 0", nullable=false, updatable=false, insertable=false)
	private Integer num_collect;
	
	@Column(columnDefinition="INT default 0", nullable=false, updatable=false, insertable=false)
	private Integer num_evaluate;
	
	@Column(columnDefinition="INT default 0", nullable=false, updatable=false, insertable=false)
	private Integer score;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Integer getCredit() {
		return credit;
	}

	public void setCredit(Integer credit) {
		this.credit = credit;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

	public Integer getNum_collect() {
		return num_collect;
	}

	public void setNum_collect(Integer num_collect) {
		this.num_collect = num_collect;
	}

	public Integer getNum_evaluate() {
		return num_evaluate;
	}

	public void setNum_evaluate(Integer num_evaluate) {
		this.num_evaluate = num_evaluate;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}
	
	public String getTeacher_id() {
		return teacher_id;
	}

	public void setTeacher_id(String teacher_id) {
		this.teacher_id = teacher_id;
	}

	public String getTeacher_name() {
		return teacher_name;
	}

	public void setTeacher_name(String teacher_name) {
		this.teacher_name = teacher_name;
	}
	
}
