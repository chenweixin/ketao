package com.springmvc.ketao.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springmvc.ketao.entity.Student;
import com.springmvc.ketao.service.IStudentManager;

@Controller
@RequestMapping("/student")
public class StudentContrller {
	
	@Resource(name="studentManager")
	private IStudentManager studentManager;
	
	@RequestMapping("")
	public String index(){
		return "/view/student";
	}
	
	@RequestMapping("/add")
	public String addStudent(){
		Student student = new Student();
		student.setId("stu1");
		student.setName("stu1");
		studentManager.addStudent(student);
		return "/success";
	}
	
	@RequestMapping("/del")
	public String delStudent(){
		studentManager.delStudent("stu1");
		return "/success";
	}
	
	@RequestMapping("/update")
	public String updateStudent(){
		Student student = new Student();
		student.setId("stu1");
		student.setMajor("cs");
		student.setName("stu1111");
		System.out.println(studentManager.updateStudent(student));
		return "/success";
	}
	
	@RequestMapping("/get")
	public String getStudent(){
		System.out.println(studentManager.getStudent("stu1"));
		return "/success";
	}
	
	@RequestMapping("/getall")
	public String getAllStudents(){
		System.out.println(studentManager.getAllStudents().size());
		return "/success";
	}
}
