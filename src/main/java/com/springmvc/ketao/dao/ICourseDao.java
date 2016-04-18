package com.springmvc.ketao.dao;

import java.util.List;

import com.springmvc.ketao.entity.Course;

public interface ICourseDao {
	
	public void add(Course course) throws Exception;
	public boolean delByIds(String []ids);
	public boolean update(Course course);
	public Course get(String id);
	public List<Course> getBySearch(String search);
	public List<Course> getAll();
	public List<Course> getRankingCourses(int pageSize, int pageIndex);
	public List<Course> getByType(int type, int pageSize, int pageIndex);
}
