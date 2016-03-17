package com.springmvc.ketao.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import com.springmvc.ketao.dao.ITeacherDao;
import com.springmvc.ketao.entity.Student;
import com.springmvc.ketao.entity.Teacher;

public class TeacherDao implements ITeacherDao {

	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public void addTeacher(Teacher teacher) throws Exception {
		sessionFactory.getCurrentSession().save(teacher);
	}

	public boolean delTeacher(String id) {
		String hql = "delete Teacher s where s.id=?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, id);
		return (query.executeUpdate() > 0);
	}

	public boolean delTeachersByIds(String[] ids) {
		String hql = "delete Teacher s where s.id in (:ids)";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameterList("ids", ids);
		return (query.executeUpdate() > 0);
	}

	public boolean updateTeacher(Teacher teacher) {
		String hql = "update Teacher s set s.password=?,s.name=?,s.sex=?"
				+ ",s.college=? where s.id=?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, teacher.getPassword());
		query.setString(1, teacher.getName());
		query.setBoolean(2, teacher.isSex());
		query.setString(3, teacher.getCollege());
		query.setString(4, teacher.getId());
		
		return (query.executeUpdate() > 0);
	}

	public Teacher getTeacher(String id) {
		String hql = "from Teacher s where s.id=?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, id);
		return (Teacher) query.uniqueResult();
	}

	public List<Teacher> getAllTeachers() {
		String hql = "from Teacher";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}

	public List<Teacher> getTeachersBySearch(String search) {
		String hql = "from Teacher s where s.id=? or s.name like?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, search);
		query.setString(1, "%"+search+"%");
		return query.list();
	}

}
