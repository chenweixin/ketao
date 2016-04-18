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
import com.springmvc.ketao.entity.Notice;
import com.springmvc.ketao.service.INoticeManager;

@Controller
@RequestMapping("/notice")
public class NoticeController {

	@Resource(name="noticeManager")
	private INoticeManager noticeManager;
	
	@RequestMapping("")
	public String index(){
		return "/view/notice";
	}
	
	@RequestMapping("/add")
	public void add(Notice notice, HttpServletResponse response){
		JSONObject jsonObject = new JSONObject();
		try {
			noticeManager.add(notice);
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
		if(noticeManager.delByIds(ids)){
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
	public void update(Notice notice, HttpServletResponse response){
		JSONObject jsonObject = new JSONObject();
		if(noticeManager.update(notice)){
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
		String notice_id = request.getParameter("noticeid");
		Notice notice = noticeManager.get(notice_id);
		request.setAttribute("notice", notice);
		return "/view/notice_info";
	}
	
	@RequestMapping("/getnotices")
	public String getBySearch(HttpServletRequest request){
		String search = request.getParameter("search");
		List<Notice> notices = noticeManager.getBySearch(search);
		request.setAttribute("notices", notices);
		request.setAttribute("search", search);
		return "/view/notice_result";
	}
	
	@RequestMapping("/getall")
	public String getAll(HttpServletRequest request){
		List<Notice> notices = noticeManager.getAll();
		request.setAttribute("notices", notices);
		request.setAttribute("search", "全部课程通知");
		return "/view/notice_result";
	}
}
