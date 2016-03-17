package com.springmvc.ketao.dao;

import java.util.List;

import com.springmvc.ketao.entity.Teacher;
public interface ITeacherDao {

	public void addTeacher(Teacher teacher) throws Exception;
	public boolean delTeacher(String id);
	public boolean delTeachersByIds(String []ids);
	public boolean updateTeacher(Teacher teacher);
	public Teacher getTeacher(String id);
	public List<Teacher> getAllTeachers();
	public List<Teacher> getTeachersBySearch(String search);
}
