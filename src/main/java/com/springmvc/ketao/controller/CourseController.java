package com.springmvc.ketao.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.Index;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSONObject;
import com.springmvc.ketao.entity.Course;
import com.springmvc.ketao.service.ICourseManager;

@Controller
@RequestMapping("/course")
public class CourseController {

	@Resource(name="courseManager")
	private ICourseManager courseManager;
	
	@RequestMapping("")
	public String Index(){
		return "/view/course";
	}
	
	@RequestMapping("/add")
	public void add(Course course, HttpServletResponse response){
		JSONObject jsonObject = new JSONObject();
		try {
			courseManager.add(course);
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
	public void delByIds(@RequestParam(value="ids[]") String []ids, HttpServletResponse response){
		JSONObject jsonObject = new JSONObject();
		if(courseManager.delByIds(ids)){
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
	public void update(Course course, HttpServletResponse response){
		JSONObject jsonObject = new JSONObject();
		if(courseManager.update(course)){
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
	public String get(HttpServletRequest request){
		String id = request.getParameter("courseid");
		Course course = courseManager.get(id);
		request.setAttribute("course", course);
		return "/view/course_info";
	}
	
	@RequestMapping("/getcourses")
	public String getBySearch(HttpServletRequest request){
		String search = request.getParameter("search");
		List<Course> courses = courseManager.getBySearch(search);
		request.setAttribute("courses", courses);
		request.setAttribute("search", search);
		return "/view/course_result";
	}
	
	@RequestMapping("/getall")
	public String getAll(HttpServletRequest request){
		List<Course> courses = courseManager.getAll();
		request.setAttribute("courses", courses);
		request.setAttribute("search", "全部课程");
		return "/view/course_result";
	}
}
