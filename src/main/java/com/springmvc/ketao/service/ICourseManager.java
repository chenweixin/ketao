package com.springmvc.ketao.service;

import java.util.List;

import com.springmvc.ketao.entity.Course;

public interface ICourseManager {

	public void add(Course course) throws Exception;
	public boolean delByIds(String []ids);
	public boolean update(Course course);
	public Course get(String id);
	public List<Course> getBySearch(String search);
	public List<Course> getAll();
}
