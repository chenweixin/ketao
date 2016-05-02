package com.springmvc.ketao.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.springmvc.ketao.config.Define;
import com.springmvc.ketao.entity.Admin;
import com.springmvc.ketao.entity.Coscollect;
import com.springmvc.ketao.entity.Course;
import com.springmvc.ketao.entity.Evalike;
import com.springmvc.ketao.entity.Evaluation;
import com.springmvc.ketao.entity.Inform;
import com.springmvc.ketao.entity.Notice;
import com.springmvc.ketao.entity.Spread;
import com.springmvc.ketao.entity.Student;
import com.springmvc.ketao.entity.Teacher;
import com.springmvc.ketao.service.ICoscollectManager;
import com.springmvc.ketao.service.ICourseManager;
import com.springmvc.ketao.service.IEvalikeManager;
import com.springmvc.ketao.service.IEvaluationManager;
import com.springmvc.ketao.service.IInformManager;
import com.springmvc.ketao.service.INoticeManager;
import com.springmvc.ketao.service.ISpreadManager;
import com.springmvc.ketao.service.IStudentManager;
import com.springmvc.ketao.service.ITeacherManager;
import com.springmvc.ketao.service.impl.CoscollectManager;

@Controller
@RequestMapping("/mobile")
public class MobileController {

	@Resource(name="spreadManager")
	private ISpreadManager spreadManager;
	@Resource(name="courseManager")
	private ICourseManager courseManager;
	@Resource(name="evaluationManager")
	private IEvaluationManager evaluationManager;
	@Resource(name="evalikeManager")
	private IEvalikeManager evalikeManager;
	@Resource(name="teacherManager")
	private ITeacherManager teacherManager;
	@Resource(name="studentManager")
	private IStudentManager studentManager;
	@Resource(name="coscollectManager")
	private ICoscollectManager coscollectManager;
	@Resource(name="noticeManager")
	private INoticeManager noticeManager;
	@Resource(name="informManager")
	private IInformManager informManager;
	
	public void rankCourses(){
		List<Course> courses = courseManager.getAll();
		int total_num = 0, total_score = 0;
		for(int i = 0; i < courses.size(); i++){
			total_num += courses.get(i).getNum_evaluate();
			total_score += courses.get(i).getScore();
		}
		double C = total_num / courses.size();//每个课程的平均评论数量
		double m = ((double)total_score) / total_num;//总体平均分
		for(int i = 0; i < courses.size(); i++){
			int n = courses.get(i).getNum_evaluate();//评论数量
			int X = courses.get(i).getScore();//评论总分
			double weight = (C * m + X) / (n + C);
			courses.get(i).setAvg_score(weight);
			courseManager.update(courses.get(i));
		}
	}
	
	/**
	 * 获取课程通知
	 * @param request
	 * @param response
	 */
	@RequestMapping("/notice/get")
	public void getNotices(HttpServletRequest request, HttpServletResponse response){
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));
		int pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
		String student_id = request.getParameter("student_id");
		String []ids = coscollectManager.getMy(student_id);
		JSONArray jsonArray = new JSONArray();
		List<Notice> notices = noticeManager.getNotices(pageSize, pageIndex, ids);
		for(int i = 0; i < notices.size(); i++){
			JSONObject object = JSONObject.parseObject(JSON.toJSONString(notices.get(i)));
			jsonArray.add(object);
		}
		response.setContentType("application/json");
		try {
			PrintWriter writer = response.getWriter();
			writer.write(jsonArray.toJSONString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取系统通告
	 * @param request
	 * @param response
	 */
	@RequestMapping("/inform/get")
	public void getInforms(HttpServletRequest request, HttpServletResponse response){
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));
		int pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
		JSONArray jsonArray = new JSONArray();
		List<Inform> informs = informManager.getInforms(pageSize, pageIndex);
		for(int i = 0; i < informs.size(); i++){
			JSONObject object = JSONObject.parseObject(JSON.toJSONString(informs.get(i)));
			jsonArray.add(object);
		}
		response.setContentType("application/json");
		try {
			PrintWriter writer = response.getWriter();
			writer.write(jsonArray.toJSONString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 推荐课程
	 * @param request
	 * @param response
	 */
	@RequestMapping("/course/getbysearch")
	public void getCoursesBySearch(HttpServletRequest request, HttpServletResponse response){
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));
		int pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
		String student_id = request.getParameter("student_id");
		String search = request.getParameter("search");
		JSONArray jsonArray = new JSONArray();
		List<Course> courses = courseManager.getBySearch(search, pageSize, pageIndex);
		for(int i = 0; i < courses.size(); i++){
			JSONObject object = JSONObject.parseObject(JSON.toJSONString(courses.get(i)));

			//根据studentid 和 courseid查找CosCollect 若存在 则设置已收藏标识
			String course_id = courses.get(i).getId();
			Coscollect coscollect = coscollectManager.isExist(course_id, student_id);
			if(coscollect != null){
				object.put("iscollect", "true");
			}
			else {
				object.put("iscollect", "false");
			}
			Evaluation evaluation = evaluationManager.isExist(course_id, student_id);
			if(evaluation != null){
				object.put("isevaluate", "true");
			}
			else{
				object.put("isevaluate", "false");
			}
			jsonArray.add(object);
		}
		response.setContentType("application/json");
		try {
			PrintWriter writer = response.getWriter();
			writer.write(jsonArray.toJSONString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 受欢迎教师
	 * @param request
	 * @param response
	 */
	@RequestMapping("/teacher/getwelcome")
	public void getWelcomedTea(HttpServletRequest request, HttpServletResponse response){
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));
		int pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
		List<Teacher> teachers = new ArrayList<Teacher>();
		List<Course> courses = courseManager.getRankingCourses(1000, 0);
		Map<String, Object> teacherMap = new HashMap<String, Object>();
		for(int i = 0; i < courses.size(); i++){
			String teacher_id = courses.get(i).getTeacher_id(); 
			if(!teacherMap.containsKey(teacher_id)){
				teacherMap.put(teacher_id, teacherManager.getTeacher(teacher_id));
				teachers.add(teacherManager.getTeacher(teacher_id));
			}
		}
		JSONArray jsonArray;
		if(pageIndex * pageSize >= teachers.size()){
			jsonArray = new JSONArray();
		}
		else{
			int toIndex = ((pageIndex+1) * pageSize)>teachers.size()?teachers.size():((pageIndex+1) * pageSize);
			jsonArray = JSONArray.parseArray(JSON.toJSONString(
					teachers.subList(pageIndex * pageSize, toIndex)));
		}
		response.setContentType("application/json");
		try {
			PrintWriter writer = response.getWriter();
			writer.write(jsonArray.toJSONString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取学生信息
	 * @param request
	 * @param response
	 */
	@RequestMapping("/student/get")
	public void getStudent(HttpServletRequest request, HttpServletResponse response){
		String student_id = request.getParameter("student_id");
		Student student = studentManager.getStudent(student_id);
		JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(student));
		response.setContentType("application/json");
		try {
			PrintWriter writer = response.getWriter();
			writer.write(jsonObject.toJSONString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 根据type获取课程列表
	 * @param request
	 * @param response
	 */
	@RequestMapping("/course/getbytype")
	public void getCourseByType(HttpServletRequest request, HttpServletResponse response){
		int type = Integer.parseInt(request.getParameter("type"));
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));
		int pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
		String student_id = request.getParameter("student_id");
		JSONArray jsonArray = new JSONArray();
		List<Course> courses = courseManager.getByType(type, pageSize, pageIndex);
		for(int i = 0; i < courses.size(); i++){
			JSONObject object = JSONObject.parseObject(JSON.toJSONString(courses.get(i)));

			//根据studentid 和 courseid查找CosCollect 若存在 则设置已收藏标识
			String course_id = courses.get(i).getId();
			Coscollect coscollect = coscollectManager.isExist(course_id, student_id);
			if(coscollect != null){
				object.put("iscollect", "true");
			}
			else {
				object.put("iscollect", "false");
			}
			Evaluation evaluation = evaluationManager.isExist(course_id, student_id);
			if(evaluation != null){
				object.put("isevaluate", "true");
			}
			else{
				object.put("isevaluate", "false");
			}
			jsonArray.add(object);
		}
		response.setContentType("application/json");
		try {
			PrintWriter writer = response.getWriter();
			writer.write(jsonArray.toJSONString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 获取热门评论
	 * @param request
	 * @param response
	 */
	@RequestMapping("/evaluation/gethot")
	public void getHotEva(HttpServletRequest request, HttpServletResponse response){
		String student_id = request.getParameter("student_id");
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));
		int pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
		JSONArray jsonArray = new JSONArray();
		List<Evaluation> evaluations = evaluationManager.getHotList(pageSize, pageIndex);
		for(int i = 0; i < evaluations.size(); i++){
			JSONObject object = JSONObject.parseObject(JSON.toJSONString(evaluations.get(i)));
			JSONObject student = JSONObject.parseObject(JSON.toJSONString(
					studentManager.getStudent(evaluations.get(i).getStudent_id())));
			object.put("student", student);
			JSONObject course = JSONObject.parseObject(JSON.toJSONString(
					courseManager.get(evaluations.get(i).getCourse_id())));
			object.put("course", course);
			
			//根据evaluation_id student_id 判断是否已经点赞
			String evaluation_id = evaluations.get(i).getId();
			Evalike evalike = evalikeManager.isExist(evaluation_id, student_id);
			if(evalike != null){
				object.put("islike", "true");
			}
			else{
				object.put("islike", "false");
			}
			jsonArray.add(object);
		}
		response.setContentType("application/json");
		try {
			PrintWriter writer = response.getWriter();
			writer.write(jsonArray.toJSONString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 我的评论列表
	 * @param request
	 * @param response
	 */
	@RequestMapping("/evaluation/getmy")
	public void getMyEva(HttpServletRequest request, HttpServletResponse response){
		String student_id = request.getParameter("student_id");
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));
		int pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
		JSONArray jsonArray = new JSONArray();
		List<Evaluation> evaluations = evaluationManager.getListByStudentId(student_id, pageSize, pageIndex);
		for(int i = 0; i < evaluations.size(); i++){
			JSONObject object = JSONObject.parseObject(JSON.toJSONString(evaluations.get(i)));
			JSONObject student = JSONObject.parseObject(JSON.toJSONString(
					studentManager.getStudent(evaluations.get(i).getStudent_id())));
			object.put("student", student);
			JSONObject course = JSONObject.parseObject(JSON.toJSONString(
					courseManager.get(evaluations.get(i).getCourse_id())));
			object.put("course", course);
			
			//根据evaluation_id student_id 判断是否已经点赞
			String evaluation_id = evaluations.get(i).getId();
			Evalike evalike = evalikeManager.isExist(evaluation_id, student_id);
			if(evalike != null){
				object.put("islike", "true");
			}
			else{
				object.put("islike", "false");
			}
			jsonArray.add(object);
		}
		response.setContentType("application/json");
		try {
			PrintWriter writer = response.getWriter();
			writer.write(jsonArray.toJSONString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 我的课程关注列表
	 * @param request
	 * @param response
	 */
	@RequestMapping("/coscollect/getmy")
	public void getMyCosCollect(HttpServletRequest request, HttpServletResponse response){
		String student_id = request.getParameter("student_id");
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));
		int pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
		JSONArray jsonArray = new JSONArray();
		List<Coscollect> coscollects = coscollectManager.getMy(student_id, pageSize, pageIndex);
		for(int i = 0; i < coscollects.size(); i++){
			String course_id = coscollects.get(i).getCourse_id();
			JSONObject object = JSONObject.parseObject(JSON.toJSONString(courseManager.get(course_id)));
			
			//根据studentid 和 courseid查找CosCollect 若存在 则设置已收藏标识
			Coscollect coscollect = coscollectManager.isExist(course_id, student_id);
			if(coscollect != null){
				object.put("iscollect", "true");
			}
			else {
				object.put("iscollect", "false");
			}
			Evaluation evaluation = evaluationManager.isExist(course_id, student_id);
			if(evaluation != null){
				object.put("isevaluate", "true");
			}
			else{
				object.put("isevaluate", "false");
			}
			jsonArray.add(object);
		}
		response.setContentType("application/json");
		try {
			PrintWriter writer = response.getWriter();
			writer.write(jsonArray.toJSONString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 获取评论
	 * @param request
	 * @param response
	 */
	@RequestMapping("/evaluation/geteva")
	public void getEvaluation(HttpServletRequest request, HttpServletResponse response){
		String course_id = request.getParameter("courseid");
		String student_id = request.getParameter("studentid");
		Evaluation evaluation = evaluationManager.isExist(course_id, student_id);
		JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(evaluation));
		response.setContentType("application/json");
		try {
			PrintWriter writer = response.getWriter();
			writer.write(jsonObject.toJSONString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 获取课程评论列表
	 * @param request
	 * @param response
	 */
	@RequestMapping("/evaluation/get")
	public void getComments(HttpServletRequest request, HttpServletResponse response){
		String course_id = request.getParameter("courseid");
		String student_id = request.getParameter("studentid");
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));
		int pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
		JSONArray jsonArray = new JSONArray();
		List<Evaluation> evaluations = evaluationManager.getListByCourseId(course_id, pageSize, pageIndex);
		for(int i = 0; i < evaluations.size(); i++){
			JSONObject object = JSONObject.parseObject(JSON.toJSONString(evaluations.get(i)));
			JSONObject student = JSONObject.parseObject(JSON.toJSONString(
					studentManager.getStudent(evaluations.get(i).getStudent_id())));
			object.put("student", student);
			
			//根据evaluation_id student_id 判断是否已经点赞
			String evaluation_id = evaluations.get(i).getId();
			Evalike evalike = evalikeManager.isExist(evaluation_id, student_id);
			if(evalike != null){
				object.put("islike", "true");
			}
			else{
				object.put("islike", "false");
			}
			jsonArray.add(object);
		}
		response.setContentType("application/json");
		try {
			PrintWriter writer = response.getWriter();
			writer.write(jsonArray.toJSONString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 收藏课程
	 * @param coscollect
	 * @param response
	 */
	@RequestMapping("/coscollect/add")
	public void collect(HttpServletRequest request, HttpServletResponse response){
		Coscollect coscollect = new Coscollect();
		coscollect.setCourse_id(request.getParameter("course_id"));
		coscollect.setStudent_id(request.getParameter("student_id"));
		long time = System.currentTimeMillis();
		coscollect.setCreate_time(time+"");
		
		
		JSONObject jsonObject = new JSONObject();
		try {
			coscollectManager.add(coscollect);
			
			String course_id = coscollect.getCourse_id();
			Course course = courseManager.get(course_id);
			int num_collect = course.getNum_collect() + 1;
			course.setNum_collect(num_collect);

			if(courseManager.update(course))
				jsonObject.put("success", "true");
			else
				jsonObject.put("success", "false");
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
	/**
	 * 取消收藏
	 * @param request
	 * @param response
	 */
	@RequestMapping("/coscollect/cancle")
	public void cancleCollect(HttpServletRequest request, HttpServletResponse response){
		String course_id = request.getParameter("course_id");
		String student_id = request.getParameter("student_id");
		JSONObject jsonObject = new JSONObject();
		try {
			Coscollect coscollect = coscollectManager.isExist(course_id, student_id);
			
			coscollectManager.del(coscollect.getId());
			
			Course course = courseManager.get(course_id);
			int num_collect = course.getNum_collect() - 1;
			course.setNum_collect(num_collect);
			if(courseManager.update(course))
				jsonObject.put("success", "true");
			else
				jsonObject.put("success", "false");
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
	
	/**
	 * 推荐课程
	 * @param request
	 * @param response
	 */
	@RequestMapping("/course/getranking")
	public void getRankingCourses(HttpServletRequest request, HttpServletResponse response){
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));
		int pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
		String student_id = request.getParameter("student_id");
		if(pageIndex == 0) rankCourses();
		JSONArray jsonArray = new JSONArray();
		List<Course> courses = courseManager.getRankingCourses(pageSize, pageIndex);
		for(int i = 0; i < courses.size(); i++){
			JSONObject object = JSONObject.parseObject(JSON.toJSONString(courses.get(i)));

			//根据studentid 和 courseid查找CosCollect 若存在 则设置已收藏标识
			String course_id = courses.get(i).getId();
			Coscollect coscollect = coscollectManager.isExist(course_id, student_id);
			if(coscollect != null){
				object.put("iscollect", "true");
			}
			else {
				object.put("iscollect", "false");
			}
			Evaluation evaluation = evaluationManager.isExist(course_id, student_id);
			if(evaluation != null){
				object.put("isevaluate", "true");
			}
			else{
				object.put("isevaluate", "false");
			}
			jsonArray.add(object);
		}
		response.setContentType("application/json");
		try {
			PrintWriter writer = response.getWriter();
			writer.write(jsonArray.toJSONString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 学生评论课程
	 * @param evaluation
	 * @param response
	 */
	@RequestMapping("/evaluation/add")
	public void evaluate(HttpServletRequest request, HttpServletResponse response){
		Evaluation evaluation = new Evaluation();
		evaluation.setComment(request.getParameter("comment"));
		evaluation.setCourse_id(request.getParameter("course_id"));
		long time = System.currentTimeMillis();
		evaluation.setCreate_time(time+"");
		evaluation.setScore(Integer.parseInt(request.getParameter("score")));
		evaluation.setStudent_id(request.getParameter("student_id"));
		JSONObject jsonObject = new JSONObject();
		try {
			evaluationManager.evaluate(evaluation);
			
			String course_id = evaluation.getCourse_id();
			Course course = courseManager.get(course_id);
			int num_eva = course.getNum_evaluate() + 1;
			int score = course.getScore() + evaluation.getScore() * 2;
			double avg_score = ((double) score) / num_eva;
			course.setNum_evaluate(num_eva);
			course.setScore(score);
			course.setAvg_score(avg_score);
			courseManager.update(course);
			
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
	
	/**
	 * 修改评论
	 * @param evaluation
	 * @param response
	 */
	@RequestMapping("/evaluation/update")
	public void updatevaluate(HttpServletRequest request, HttpServletResponse response){
		String course_id = request.getParameter("course_id");
		String student_id = request.getParameter("student_id");
		Evaluation evaluation = evaluationManager.isExist(course_id, student_id);
		int score_before = evaluation.getScore();
		evaluation.setComment(request.getParameter("comment"));
		long time = System.currentTimeMillis();
		evaluation.setCreate_time(time+"");
		evaluation.setScore(Integer.parseInt(request.getParameter("score")));
		JSONObject jsonObject = new JSONObject();
		try {
			evaluationManager.evaluate(evaluation);
			
			Course course = courseManager.get(course_id);
			int num_eva = course.getNum_evaluate();
			int score = course.getScore() + evaluation.getScore() * 2 - score_before * 2;
			double avg_score = ((double) score) / num_eva;
			course.setScore(score);
			course.setAvg_score(avg_score);
			courseManager.update(course);
			
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
	
	/**
	 * 对评论点赞
	 * @param evaLike
	 * @param response
	 */
	@RequestMapping("/evalike/add")
	public void evalike(HttpServletRequest request, HttpServletResponse response){
		Evalike evaLike = new Evalike();
		evaLike.setEvaluation_id(request.getParameter("evaluation_id"));
		evaLike.setStudent_id(request.getParameter("student_id"));
		long time = System.currentTimeMillis();
		evaLike.setCreate_time(time+"");
		JSONObject jsonObject = new JSONObject();
		try {
			evalikeManager.add(evaLike);
			
			String evaluation_id = evaLike.getEvaluation_id();
			Evaluation evaluation = evaluationManager.get(evaluation_id);
			int num_like = evaluation.getNum_like() + 1;
			evaluation.setNum_like(num_like);
			evaluationManager.update(evaluation);
			
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
	/**
	 * 取消点赞
	 * @param request
	 * @param response
	 */
	@RequestMapping("/evalike/cancle")
	public void cancleEvalike(HttpServletRequest request, HttpServletResponse response){
		String evaluation_id = request.getParameter("evaluation_id");
		String student_id = request.getParameter("student_id");
		JSONObject jsonObject = new JSONObject();
		try {
			Evalike evaLike = evalikeManager.isExist(evaluation_id, student_id);
			evalikeManager.del(evaLike.getId());
			
			Evaluation evaluation = evaluationManager.get(evaluation_id);
			int num_like = evaluation.getNum_like() - 1;
			evaluation.setNum_like(num_like);
			evaluationManager.update(evaluation);
			
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
	
	/**
	 * 登录接口
	 * @param request
	 * @param response
	 */
	@RequestMapping("/login")
	public void teacherLogin(HttpServletRequest request, HttpServletResponse response){
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		Student student = studentManager.getStudent(username);
		Teacher teacher = teacherManager.getTeacher(username);
		
		JSONObject jsonObject = new JSONObject();
		if((teacher != null && teacher.getPassword().equals(password)) || 
				(student != null && student.getPassword().equals(password))){
			jsonObject.put("success", "true");
			if(student != null){
				jsonObject.put("usertype", "student");
			}
			else{
				jsonObject.put("usertype", "teacher");
			}
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
	
	/**
	 * 根据id获取课程
	 * @param request
	 * @param response
	 */
	@RequestMapping("/course/get")
	public void getCourseById(HttpServletRequest request, HttpServletResponse response){
		String id = request.getParameter("courseid");
		Course course = courseManager.get(id);
		JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(course));
		response.setContentType("application/json");
		try {
			PrintWriter writer = response.getWriter();
			writer.write(jsonObject.toJSONString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 根据id获取教师
	 * @param request
	 * @param response
	 */
	@RequestMapping("/teacher/get")
	public void getTeacherById(HttpServletRequest request, HttpServletResponse response){
		String id = request.getParameter("teacherid");
		Teacher teacher = teacherManager.getTeacher(id);
		JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(teacher));
		response.setContentType("application/json");
		try {
			PrintWriter writer = response.getWriter();
			writer.write(jsonObject.toJSONString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取推广列表
	 * @param response
	 */
	@RequestMapping("/spread/getall")
	public void getAllSpreads(HttpServletRequest request, HttpServletResponse response){
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));
		int pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
		JSONArray jsonArray = new JSONArray();
		List<Spread> spreads = spreadManager.getByPage(pageSize, pageIndex);
		for(int i = 0; i < spreads.size(); i++){
			JSONObject object = JSONObject.parseObject(JSON.toJSONString(spreads.get(i)));
			jsonArray.add(object);
		}
		response.setContentType("application/json");
		try {
			PrintWriter writer = response.getWriter();
			writer.write(jsonArray.toJSONString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 生成一个随机数
	 * @param n
	 * @return
	 */
	public int getRandom(int n){
		return (int)(Math.random()*n);
	}
	public String getNum(int min_d, int max_d, int n){
		NumberFormat nf = NumberFormat.getInstance();  
		nf.setMinimumIntegerDigits(min_d);
		nf.setMaximumIntegerDigits(max_d);
		return nf.format(n);
	}
	/**
	 * 添加测试数据
	 */
//	@RequestMapping("/test")
	public void addTest(HttpServletResponse response){
//		String colleges[] = {"建筑学院","土木与交通学院","材料科学与工程学院","电力学院","自动化科学与工程学院","电子与信息学院",
//				"化学与化工学院","轻工与食品学院","理学院","机械与汽车工程学院","工商管理学院","公共管理学院","思想政治学院",
//				"外国语学院","体育学院","生物科学与工程学院","计算机科学与工程学院","软件学院","环境与能源学院","新闻与传播学院",
//				"艺术学院","法学院","经济与贸易学院","设计学院","继续教育学院"};
//		String arts[] = {"钢琴基础弹奏", "音乐剧欣赏", "世界民族音乐", "民族舞蹈", "外国舞蹈史及作品欣赏", "西方音乐作品欣赏"};
//		String englishs[] = {"英语短篇小说选读","科技交流英语","中国文化概论","英语写作技巧与训练","雅思英语","西班牙语初级","俄语"};
//		String news[] = {"中国传统文化","大学语文","岭南文化","中国古代名著导读","中国文学与文化","影视赏析","新媒体文化"};
//		String gong[] = {"营销学原理","创业学","创业法律实务","新领域营销"};
//		String default_avatar = "/ketao_img/default.jpg";
		JSONObject jsonObject = new JSONObject();
//		List<Course> courses = courseManager.getAll();
//		for(int i = 0; i < courses.size();i++){
//			courses.get(i).setNum_evaluate(0);
//			courses.get(i).setScore(0);
//			courses.get(i).setAvg_score(0);
//			courseManager.update(courses.get(i));
//		}
		
//		List<Evaluation> evaluations = evaluationManager.getAll();
//		for(int i = 0; i < evaluations.size(); i++){
//			String student_id = evaluations.get(i).getStudent_id();
//			String course_id = evaluations.get(i).getCourse_id();
//			Student student = studentManager.getStudent(student_id);
//			if(student == null){
//				evaluationManager.del(evaluations.get(i).getId());
//				Course course = courseManager.get(course_id);
//				course.setNum_evaluate(course.getNum_evaluate() - 1);
//				courseManager.update(course);
//			}
//		}
		//评论点赞
		List<Course> courses = courseManager.getAll();
		for(int k = 0; k < courses.size(); k++){
			String course_id = courses.get(k).getId();
			List<Evaluation> evaluations = evaluationManager.getListByCourseId(course_id, 400, 0);
			int t = 300;
			for(int i = 0; i < evaluations.size(); i++){
				int n = getRandom(t);
				for(int j = 0; j < n; j++){
					int c = getRandom(25);
					int p = getRandom(300);
					String id = "2012" + getNum(2, 2, c) + getNum(3, 3, p);
					
					Evalike evalike = new Evalike();
					evalike.setEvaluation_id(evaluations.get(i).getId());
					evalike.setStudent_id(id);
					try {
						String evaluation_id = evaluations.get(i).getId();
						if(evalikeManager.isExist(evaluation_id, id) == null){
							evalikeManager.add(evalike);
							Evaluation evaluation = evaluationManager.get(evaluation_id);
							int num_like = evaluation.getNum_like() + 1;
							evaluation.setNum_like(num_like);
							evaluationManager.update(evaluation);
						}
						
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				t -= (20 + getRandom(30));
				if(t <= 0) break;
			}
		}
//		List<Evaluation> evaluations = evaluationManager.getAll();
//		int k = 300;
//		for(int i = 0; i < evaluations.size(); i++){
//			int n = getRandom(300);
//			for(int j = 0; j < n; j++){
//				int c = getRandom(25);
//				int p = getRandom(300);
//				String id = "2012" + getNum(2, 2, c) + getNum(3, 3, p);
//				
//				Evalike evalike = new Evalike();
//				evalike.setEvaluation_id(evaluations.get(i).getId());
//				evalike.setStudent_id(id);
//				try {
//					String evaluation_id = evaluations.get(i).getId();
//					if(evalikeManager.isExist(evaluation_id, id) == null){
//						evalikeManager.add(evalike);
//						Evaluation evaluation = evaluationManager.get(evaluation_id);
//						int num_like = evaluation.getNum_like() + 1;
//						evaluation.setNum_like(num_like);
//						evaluationManager.update(evaluation);
//					}
//					
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				
//				
//			}
//		}
		//添加评论
//		List<Course> courses = courseManager.getAll();
//		for(int i = 0; i < courses.size(); i++){
//			int n = getRandom(60);
//			for(int j = 0; j < n; j++){
//				int c = getRandom(25);
//				int p = getRandom(300);
//				String id = "2012" + getNum(2, 2, c) + getNum(3, 3, p);
//				if(studentManager.getStudent(id) == null) continue;
//				
//				Evaluation evaluation = new Evaluation();
//				evaluation.setComment("evaluation_"+i+"_"+j);
//				evaluation.setCourse_id(courses.get(i).getId());
//				long create_time = System.currentTimeMillis() - getRandom(1000000000);
//				evaluation.setCreate_time(create_time+"");
//				evaluation.setScore(1+getRandom(5));
//				evaluation.setStudent_id(id);
//				try {
//					if(evaluationManager.isExist(courses.get(i).getId(), id) == null){
//						evaluationManager.evaluate(evaluation);
//						String course_id = evaluation.getCourse_id();
//						Course course = courseManager.get(course_id);
//						int num_eva = course.getNum_evaluate() + 1;
//						int score = course.getScore() + evaluation.getScore() * 2;
////						double avg_score = ((double) score) / num_eva;
//						course.setNum_evaluate(num_eva);
//						course.setScore(score);
////						course.setAvg_score(avg_score);
//						courseManager.update(course);
//					}
//				} catch (Exception e1) {
//				}
//			}
//		}
		
		//课程通知
//		List<Course> courses = courseManager.getAll();
//		for(int i = 0; i < courses.size(); i++){
//			int n = getRandom(10);
//			for(int j = 0; j < n; j++){
//				Notice notice = new Notice();
//				notice.setCourse_id(courses.get(i).getId());
//				long create_time = System.currentTimeMillis() - getRandom(1000000000);
//				notice.setCreate_time(create_time+"");
//				notice.setTitle("notice_title_"+i+"_"+j);
//				notice.setContent("notice_content"+i+"_"+j);
//				try {
//					noticeManager.add(notice);
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//		}
		//收藏课程
//		List<Course> courses = courseManager.getAll();
//		for(int i = 0; i < courses.size(); i++){
//			int num = getRandom(200);
//			for(int j = 0; j < num;j++){
//				int c = getRandom(25);
//				int p = getRandom(300);
//				String id = "2012" + getNum(2, 2, c) + getNum(3, 3, p);
//				Coscollect coscollect = new Coscollect();
//				coscollect.setCourse_id(courses.get(i).getId());
//				coscollect.setStudent_id(id);
//				long time = System.currentTimeMillis();
//				coscollect.setCreate_time(time+"");
//				try {
//					coscollectManager.add(coscollect);
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				
//				Course course = courses.get(i);
//				int num_collect = course.getNum_collect() + 1;
//				course.setNum_collect(num_collect);
//				courseManager.update(course);
//			}
//		}
		//添加课程
//		for(int i = 0; i < 10; i++){
//			Course course = new Course();
//			course.setCreate_time(System.currentTimeMillis()+"");
//			course.setCredit(2);
//			course.setId("16"+"10"+getNum(2, 2, i));
//			course.setLocation("A"+(1+getRandom(5))+"0"+(1+getRandom(6)));
//			course.setName(gong[getRandom(gong.length)]);
//			course.setTeacher_id("199"+"10"+getNum(2, 2, i%4));
//			course.setTeacher_name(teacherManager.getTeacher(course.getTeacher_id()).getName());
//			course.setType(2);
//			try {
//				courseManager.add(course);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
		//添加教师
//		for(int i = 0; i < colleges.length; i++){
//			for(int j = 0; j < 4; j++){
//				Teacher teacher = new Teacher();
//				String id = "199" + getNum(2, 2, i) + getNum(2, 2, j);
//				String college = colleges[i];
//				teacher.setAvatar_url(default_avatar);
//				teacher.setCollege(college);
//				teacher.setId(id);
//				teacher.setName("tea_name" + j);
//				teacher.setPassword("123");
//				teacher.setSex((getRandom(2)) % 2==0);
//				try {
//					teacherManager.addTeacher(teacher);
//					jsonObject.put("addteacher", "successs");
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//		}
		//添加学生
//		for(int i = 0; i < colleges.length; i++){
//			for(int j = 0; j < 200; j++){
//				Student student = new Student();
//				String id = "2012" + getNum(2, 2, i) + getNum(3, 3, j);
//				String college = colleges[i];
//				student.setId(id);
//				student.setCollege(college);
//				student.setMajor("test_major" + getRandom(6));
//				student.setName("test_name" + j);
//				student.setAvatar_url(default_avatar);
//				student.setPassword("123");
//				student.setPeriod("2012");
//				student.setSex((getRandom(2)) % 2==0);
//				try {
//					studentManager.addStudent(student);
//					jsonObject.put("addstudent", "successs");
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//		}
		
		
		
		//添加评论
		//评论点赞
		
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
