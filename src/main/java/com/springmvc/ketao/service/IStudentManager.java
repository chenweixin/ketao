package com.springmvc.ketao.service;

import java.util.List;

import com.springmvc.ketao.entity.Student;

public interface IStudentManager {

	public void addStudent(Student student) throws Exception;
	public boolean delStudent(String id);
	public boolean delStudentsByIds(String []ids);
	public boolean updateStudent(Student student);
	public Student getStudent(String id);
	public List<Student> getStudentsBySearch(String search);
	public List<Student> getAllStudents();
}
