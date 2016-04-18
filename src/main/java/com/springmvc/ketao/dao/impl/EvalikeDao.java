package com.springmvc.ketao.dao.impl;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import com.springmvc.ketao.dao.IEvalikeDao;
import com.springmvc.ketao.entity.Evalike;

public class EvalikeDao implements IEvalikeDao {

	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void add(Evalike evalike) throws Exception {
		sessionFactory.getCurrentSession().save(evalike);
	}

	public Evalike isExist(String evaluation_id, String student_id) {
		String hql = "from Evalike s where s.evaluation_id=? and s.student_id=?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, evaluation_id);
		query.setString(1, student_id);
		return (Evalike) query.uniqueResult();
	}

	public boolean del(String id) {
		String hql = "delete Evalike s where s.id=?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, id);
		return (query.executeUpdate() > 0);
	}

}
