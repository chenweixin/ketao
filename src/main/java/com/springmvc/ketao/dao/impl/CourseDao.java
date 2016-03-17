package com.springmvc.ketao.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import com.springmvc.ketao.dao.ICourseDao;
import com.springmvc.ketao.entity.Course;

public class CourseDao implements ICourseDao {

	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void add(Course course) throws Exception{
		sessionFactory.getCurrentSession().save(course);
	}

	public boolean delByIds(String[] ids) {
		String hql = "delete Course s where s.id in (:ids)";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameterList("ids", ids);
		return (query.executeUpdate() > 0);
	}

	public boolean update(Course course) {
		String hql = "update Course s set s.name=?,s.location=?,s.credit=?,s.type=?,"
				+ "s.create_time=? where s.id=?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, course.getName());
		query.setString(1, course.getLocation());
		query.setInteger(2, course.getCredit());
		query.setInteger(3, course.getType());
		query.setString(4, course.getCreate_time());
		query.setString(5, course.getId());
		
		return (query.executeUpdate() > 0);
	}

	public List<Course> getBySearch(String search) {
		String hql = "from Course s where s.name like?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, "%"+search+"%");
		return query.list();
	}

	public List<Course> getAll() {
		String hql = "from Course";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}

	public Course get(String id) {
		String hql = "from Course s where s.id=?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, id);
		return (Course) query.uniqueResult();
	}

}