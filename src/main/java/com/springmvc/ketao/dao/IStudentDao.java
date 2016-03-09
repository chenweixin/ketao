package com.springmvc.ketao.dao;

import java.util.List;

import com.springmvc.ketao.entity.Student;

public interface IStudentDao {

	public void addStudent(Student student);
	public boolean delStudent(String id);
	public boolean updateStudent(Student student);
	public Student getStudent(String id);
	public List<Student> getAllStudents();
}
