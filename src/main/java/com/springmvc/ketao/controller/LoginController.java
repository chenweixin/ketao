package com.springmvc.ketao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {

	@RequestMapping("")
	public String login_page(){
		return "/login.jsp";
	}
	
	@RequestMapping("/login")
	public void login(){
		
	}
	
	@RequestMapping("/logout")
	public void logout(){
		
	}
}
