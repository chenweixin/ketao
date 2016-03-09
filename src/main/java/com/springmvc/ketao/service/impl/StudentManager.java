package com.springmvc.ketao.service.impl;

import java.util.List;

import com.springmvc.ketao.dao.IStudentDao;
import com.springmvc.ketao.entity.Student;
import com.springmvc.ketao.service.IStudentManager;

public class StudentManager implements IStudentManager {

	private IStudentDao studentDao;
	
	public IStudentDao getStudentDao() {
		return studentDao;
	}

	public void setStudentDao(IStudentDao studentDao) {
		this.studentDao = studentDao;
	}

	public void addStudent(Student student) {
		studentDao.addStudent(student);
	}

	public boolean delStudent(String id) {
		return studentDao.delStudent(id);
	}

	public boolean updateStudent(Student student) {
		return studentDao.updateStudent(student);
	}

	public Student getStudent(String id) {
		return studentDao.getStudent(id);
	}

	public List<Student> getAllStudents() {
		return studentDao.getAllStudents();
	}

}
