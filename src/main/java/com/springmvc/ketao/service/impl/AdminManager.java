package com.springmvc.ketao.service.impl;

import java.util.List;

import com.springmvc.ketao.dao.IAdminDao;
import com.springmvc.ketao.dao.impl.AdminDao;
import com.springmvc.ketao.entity.Admin;
import com.springmvc.ketao.service.IAdminManager;

public class AdminManager implements IAdminManager {

	private IAdminDao adminDao;
	
	public IAdminDao getAdminDao() {
		return adminDao;
	}

	public void setAdminDao(IAdminDao adminDao) {
		this.adminDao = adminDao;
	}

	public void addAdmin(Admin admin) {
		adminDao.addAdmin(admin);
	}

	public boolean delAdmin(String id) {
		return adminDao.delAdmin(id);
	}

	public boolean updateAdmin(Admin admin) {
		return adminDao.updateAdmin(admin);
	}

	public Admin getAdmin(String id) {
		return adminDao.getAdmin(id);
	}

	public List<Admin> getAllAdmins() {
		return adminDao.getAllAdmins();
	}

}
