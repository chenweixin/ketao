package com.springmvc.ketao.service.impl;

import java.util.List;

import com.springmvc.ketao.dao.ITeacherDao;
import com.springmvc.ketao.entity.Teacher;
import com.springmvc.ketao.service.ITeacherManager;

public class TeacherManager implements ITeacherManager {
	
	private ITeacherDao teacherDao;

	public ITeacherDao getTeacherDao() {
		return teacherDao;
	}

	public void setTeacherDao(ITeacherDao teacherDao) {
		this.teacherDao = teacherDao;
	}

	public void addTeacher(Teacher teacher) throws Exception {
		teacherDao.addTeacher(teacher);
	}

	public boolean delTeacher(String id) {
		return teacherDao.delTeacher(id);
	}

	public boolean delTeachersByIds(String[] ids) {
		return teacherDao.delTeachersByIds(ids);
	}

	public boolean updateTeacher(Teacher teacher) {
		return teacherDao.updateTeacher(teacher);
	}

	public Teacher getTeacher(String id) {
		return teacherDao.getTeacher(id);
	}

	public List<Teacher> getTeachersBySearch(String search) {
		return teacherDao.getTeachersBySearch(search);
	}

	public List<Teacher> getAllTeachers() {
		return teacherDao.getAllTeachers();
	}

}
