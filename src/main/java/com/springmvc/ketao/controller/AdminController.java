package com.springmvc.ketao.controller;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springmvc.ketao.entity.Admin;
import com.springmvc.ketao.service.IAdminManager;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Resource(name="adminManager")
	private IAdminManager adminManager;
	
	@RequestMapping("/add")
	public String addAdmin(){
		Admin admin = new Admin();
		admin.setId("admin");
		admin.setPassword("admin");
		adminManager.addAdmin(admin);
		return "/success";
	}
	
	@RequestMapping("/del")
	public String delAdmin(){
		String id = "admin";
		adminManager.delAdmin(id);
		return "/success";
	}
	
	@RequestMapping("/update")
	public String updateAdmin(){
		Admin admin = new Admin();
		admin.setId("admin");
		admin.setName("username");
		System.out.println(adminManager.updateAdmin(admin));
		return "/success";
	}
	
	@RequestMapping("/get")
	public String getAdmin(String id){
		adminManager.getAdmin(id);
		return "/success";
	}
	
	@RequestMapping("/getall")
	public String getAllAdmins(){
		List<Admin> admins = adminManager.getAllAdmins();
		return "/success";
	}
}
