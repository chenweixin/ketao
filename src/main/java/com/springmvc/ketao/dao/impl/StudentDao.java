package com.springmvc.ketao.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import com.springmvc.ketao.dao.IStudentDao;
import com.springmvc.ketao.entity.Student;

public class StudentDao implements IStudentDao {

	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void addStudent(Student student) throws Exception{
		sessionFactory.getCurrentSession().save(student);
	}

	public boolean delStudent(String id) {
		String hql = "delete Student s where s.id=?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, id);
		return (query.executeUpdate() > 0);
	}
	
	public boolean delStudentsByIds(String[] ids) {
		String hql = "delete Student s where s.id in (:ids)";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameterList("ids", ids);
		return (query.executeUpdate() > 0);
	}

	public boolean updateStudent(Student student) {
		String hql = "update Student s set s.password=?,s.name=?,s.sex=?"
				+ ",s.college=?,s.major=?,s.period=? where s.id=?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, student.getPassword());
		query.setString(1, student.getName());
		query.setBoolean(2, student.isSex());
		query.setString(3, student.getCollege());
		query.setString(4, student.getMajor());
		query.setString(5, student.getPeriod());
		query.setString(6, student.getId());
		
		return (query.executeUpdate() > 0);
	}

	public Student getStudent(String id) {
		String hql = "from Student s where s.id=?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, id);
		return (Student) query.uniqueResult();
	}

	public List<Student> getAllStudents() {
		String hql = "from Student";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}

	public List<Student> getStudentsBySearch(String search) {
		String hql = "from Student s where s.id=? or s.name like?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, search);
		query.setString(1, "%"+search+"%");
		return query.list();
	}

}
