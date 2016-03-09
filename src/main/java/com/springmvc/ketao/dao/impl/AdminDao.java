package com.springmvc.ketao.dao.impl;

import java.util.List;

import org.hibernate.Query;
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
		String hql = "delete Admin a where a.id=?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, id);
		return (query.executeUpdate() > 0);
	}

	public boolean updateAdmin(Admin admin) {
		String hql = "update Admin a set a.password=?,a.name=?,a.sex=? where a.id=?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, admin.getPassword());
		query.setString(1, admin.getName());
		query.setBoolean(2, admin.isSex());
		query.setString(3, admin.getId());
		
		return (query.executeUpdate() > 0);
	}

	public Admin getAdmin(String id) {
		String hql = "from Admin a where a.id=?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, id);
		return (Admin) query.uniqueResult();
	}

	public List<Admin> getAllAdmins() {
		String hql = "from Admin";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}

}
