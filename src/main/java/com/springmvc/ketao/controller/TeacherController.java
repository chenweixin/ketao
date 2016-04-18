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
import com.springmvc.ketao.entity.Teacher;
import com.springmvc.ketao.service.ITeacherManager;

@Controller
@RequestMapping("/teacher")
public class TeacherController {

	@Resource(name="teacherManager")
	private ITeacherManager teacherManager;
	
	@RequestMapping("")
	public String index(){
		return "/view/teacher";
	}
	
	@RequestMapping("/add")
	public void addTeacher(Teacher teacher, HttpServletResponse response){
		JSONObject jsonObject = new JSONObject();
		try {
			teacherManager.addTeacher(teacher);
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
	public void delTeachersByIds(@RequestParam(value = "ids[]") String[] ids, HttpServletResponse response){
		JSONObject jsonObject = new JSONObject();
		if(teacherManager.delTeachersByIds(ids)){
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
	public void updateTeacher(Teacher teacher, HttpServletResponse response){
		JSONObject jsonObject = new JSONObject();
		if(teacherManager.updateTeacher(teacher)){
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
	public String getTeacher(HttpServletRequest request){
		String teacher_id = request.getParameter("teacherid");
		Teacher teacher = teacherManager.getTeacher(teacher_id);
		request.setAttribute("teacher", teacher);
		return "/view/teacher_info";
	}
	
	@RequestMapping("/getteachers")
	public String getTeachersBySearch(HttpServletRequest request){
		String search = request.getParameter("search");
		List<Teacher> teachers = teacherManager.getTeachersBySearch(search);
		request.setAttribute("teachers", teachers);
		request.setAttribute("search", search);
		return "/view/teacher_result";
	}
	
	@RequestMapping("/getall")
	public String getAllTeachers(HttpServletRequest request){
		List<Teacher> teachers = teacherManager.getAllTeachers();
		request.setAttribute("teachers", teachers);
		request.setAttribute("search", "全部教师");
		return "/view/teacher_result";
	}
	
	@RequestMapping("/checkid")
	public void checkId(HttpServletRequest request, HttpServletResponse response){
		String teacher_id = request.getParameter("teacher_id");
		Teacher teacher = teacherManager.getTeacher(teacher_id);
		JSONObject jsonObject = new JSONObject();
		if(teacher != null){
			jsonObject.put("success", "true");
			jsonObject.put("teacher_name", teacher.getName());
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
}
