package com.springmvc.ketao.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSONObject;
import com.springmvc.ketao.entity.Student;
import com.springmvc.ketao.service.IStudentManager;

import net.sf.ehcache.CacheOperationOutcomes.GetAllOutcome;

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
	public void addStudent(Student student, HttpServletResponse response){
		JSONObject jsonObject = new JSONObject();
		try {
			studentManager.addStudent(student);
			jsonObject.put("success", "true");
		} catch (Exception e1) {
			jsonObject.put("success", "false");
		} finally {
			response.setContentType("application/json");
			try {
				PrintWriter writer = response.getWriter();
				writer.write(jsonObject.toJSONString());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}
	
	@RequestMapping("/delbyids")
	public void delStudentsByIds(@RequestParam(value = "ids[]") String[] ids, HttpServletResponse response){
		JSONObject jsonObject = new JSONObject();
		if(studentManager.delStudentsByIds(ids)){
			jsonObject.put("success", "true");
		}
		else{
			jsonObject.put("success", "false");
		}
		response.setContentType("application/json");
		try {
			PrintWriter writer = response.getWriter();
			writer.write(jsonObject.toJSONString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/update")
	public void updateStudent(Student student, HttpServletResponse response){
		JSONObject jsonObject = new JSONObject();
		if(studentManager.updateStudent(student)){
			jsonObject.put("success", "true");
		}
		else{

			jsonObject.put("success", "false");
		}
		response.setContentType("application/json");
		try {
			PrintWriter writer = response.getWriter();
			writer.write(jsonObject.toJSONString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/get")
	public String getStudent(HttpServletRequest request){
		String student_id = request.getParameter("studentid");
		Student student = studentManager.getStudent(student_id);
		request.setAttribute("student", student);
		return "/view/student_info";
	}
	
	@RequestMapping("/getstudents")
	public String getStudentsBySearch(HttpServletRequest request){
		String search = request.getParameter("search");
		List<Student> students = studentManager.getStudentsBySearch(search);
		request.setAttribute("students", students);
		request.setAttribute("search", search);
		return "/view/student_result";
	}
	
	@RequestMapping("/getall")
	public String getAllStudents(HttpServletRequest request){
		List<Student> students = studentManager.getAllStudents();
		request.setAttribute("students", students);
		request.setAttribute("search", "全部学生");
		return "/view/student_result";
	}
}
