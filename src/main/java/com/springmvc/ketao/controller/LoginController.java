package com.springmvc.ketao.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.springmvc.ketao.config.Define;
import com.springmvc.ketao.entity.Admin;
import com.springmvc.ketao.service.IAdminManager;

@Controller
@RequestMapping("/login")
public class LoginController {

	@Resource(name="adminManager")
	private IAdminManager adminManager;
	
	@RequestMapping("")
	public String login_page(){
		return "/login";
	}
	
	@RequestMapping("/login")
	public void login(HttpServletRequest request, HttpServletResponse response){
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String urlReferrer = request.getParameter("urlReferrer");
		Admin admin = adminManager.getAdmin(username);
		JSONObject jsonObject = new JSONObject();
		HttpSession session = request.getSession(true);
		if(admin != null && admin.getPassword().equals(password)){
			jsonObject.put("success", "true");
			if(urlReferrer != null || !urlReferrer.equals("")){
				jsonObject.put("referrer", urlReferrer);	
			}
			else{
				jsonObject.put("referrer", "/ketao");
			}
		}
		else{
			jsonObject.put("success", "false");
			session.setAttribute(Define.SESSTION_LOGIN_NAME, null);
		}
		response.setContentType("application/json");
		try {
			PrintWriter writer = response.getWriter();
			writer.write(jsonObject.toJSONString());
			session.setAttribute(Define.SESSTION_LOGIN_NAME, username);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request){
		HttpSession session = request.getSession(true);
		session.setAttribute(Define.SESSTION_LOGIN_NAME, null);
		return "redirect:/login";
	}
}
