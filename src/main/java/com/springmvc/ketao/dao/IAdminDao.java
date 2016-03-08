package com.springmvc.ketao.dao;

import java.util.List;

import com.springmvc.ketao.entity.Admin;

public interface IAdminDao {

	public void addAdmin(Admin admin);
	public boolean delAdmin(String id);
	public boolean updateAdmin(Admin admin);
	public Admin getAdmin(String id);
	public List<Admin> getAllAdmins();
}
