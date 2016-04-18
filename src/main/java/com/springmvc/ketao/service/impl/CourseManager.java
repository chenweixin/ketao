package com.springmvc.ketao.service.impl;

import java.util.List;

import com.springmvc.ketao.dao.ICourseDao;
import com.springmvc.ketao.entity.Course;
import com.springmvc.ketao.service.ICourseManager;

public class CourseManager implements ICourseManager {

	private ICourseDao courseDao;
	
	public ICourseDao getCourseDao() {
		return courseDao;
	}

	public void setCourseDao(ICourseDao courseDao) {
		this.courseDao = courseDao;
	}

	public void add(Course course) throws Exception{
		courseDao.add(course);
	}

	public boolean delByIds(String[] ids) {
		return courseDao.delByIds(ids);
	}

	public boolean update(Course course) {
		return courseDao.update(course);
	}

	public List<Course> getBySearch(String search) {
		return courseDao.getBySearch(search);
	}

	public List<Course> getAll() {
		return courseDao.getAll();
	}

	public Course get(String id) {
		return courseDao.get(id);
	}

	public List<Course> getRankingCourses(int pageSize, int pageIndex) {
		return courseDao.getRankingCourses(pageSize, pageIndex);
	}

	public List<Course> getByType(int type, int pageSize, int pageIndex) {
		return courseDao.getByType(type, pageSize, pageIndex);
	}

}
