package com.springmvc.ketao.service;

import java.util.List;

import com.springmvc.ketao.entity.Teacher;

public interface ITeacherManager {

	public void addTeacher(Teacher teacher) throws Exception;
	public boolean delTeacher(String id);
	public boolean delTeachersByIds(String []ids);
	public boolean updateTeacher(Teacher teacher);
	public Teacher getTeacher(String id);
	public List<Teacher> getTeachersBySearch(String search);
	public List<Teacher> getAllTeachers();
}
