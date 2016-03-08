package com.springmvc.ketao.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;

import com.springmvc.ketao.dao.IAdminDao;
import com.springmvc.ketao.entity.Admin;

public class AdminDao implements IAdminDao {

	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void addAdmin(Admin admin) {
		sessionFactory.getCurrentSession().save(admin);
	}

	public boolean delAdmin(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean updateAdmin(Admin admin) {
		// TODO Auto-generated method stub
		return false;
	}

	public Admin getAdmin(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Admin> getAllAdmins() {
		// TODO Auto-generated method stub
		return null;
	}

}
