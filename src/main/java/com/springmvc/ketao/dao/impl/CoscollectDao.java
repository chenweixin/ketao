package com.springmvc.ketao.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import com.springmvc.ketao.dao.ICoscollectDao;
import com.springmvc.ketao.entity.Coscollect;

public class CoscollectDao implements ICoscollectDao {

	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void add(Coscollect coscollect) throws Exception {
		sessionFactory.getCurrentSession().save(coscollect);
	}

	public Coscollect get(String id) {
		String hql = "from Coscollect s where s.id=?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, id);
		return (Coscollect) query.uniqueResult();
	}

	public Coscollect isExist(String course_id, String student_id) {
		String hql = "from Coscollect s where s.course_id=? and s.student_id=?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, course_id);
		query.setString(1, student_id);
		return (Coscollect) query.uniqueResult();
	}

	public boolean del(String id) {
		String hql = "delete Coscollect s where s.id=?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, id);
		return (query.executeUpdate() > 0);
	}

	public List<Coscollect> getMy(String student_id, int pageSize, int pageIndex) {
		String hql = "from Coscollect s where s.student_id=?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, student_id);
		query.setFirstResult(pageIndex * pageSize);
		query.setMaxResults(pageSize);
		return query.list();
	}

	public List<Coscollect> getMy(String student_id) {
		String hql = "from Coscollect s where s.student_id=?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, student_id);
		return query.list();
	}
}
