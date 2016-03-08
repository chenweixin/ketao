package com.springmvc.ketao.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springmvc.ketao.entity.Admin;
import com.springmvc.ketao.service.IAdminManager;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Resource(name="adminManager")
	private IAdminManager adminManager;
	
	@RequestMapping("/addAdmin")
	public String add(){
		Admin admin = new Admin();
		admin.setId("admin");
		admin.setPassword("admin");
		adminManager.addAdmin(admin);
		return "/success";
	}
	
	@RequestMapping("/getAdmin")
	public String get(){
		List<Admin> admins = adminManager.getAllAdmins();
		System.out.println(admins.size());
		return "/success";
	}
}
