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
		String hql = "update Course s set s.name=?,s.location=?,s.credit=?,s.type=?,s.teacher_id=?,"
				+ "s.introduction=?,s.create_time=?,s.num_collect=?,s.num_evaluate=?,s.score=?,"
				+ "s.avg_score=? where s.id=?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, course.getName());
		query.setString(1, course.getLocation());
		query.setInteger(2, course.getCredit());
		query.setInteger(3, course.getType());
		query.setString(4, course.getTeacher_id());
		query.setString(5, course.getIntroduction());
		query.setString(6, course.getCreate_time());
		query.setInteger(7, course.getNum_collect());
		query.setInteger(8, course.getNum_evaluate());
		query.setInteger(9, course.getScore());
		query.setDouble(10, course.getAvg_score());
		query.setString(11, course.getId());
		
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

	public List<Course> getRankingCourses(int pageSize, int pageIndex) {
		String hql = "from Course s order by s.avg_score desc";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setFirstResult(pageSize * pageIndex);
		query.setMaxResults(pageSize);
		return query.list();
	}

	public List<Course> getByType(int type, int pageSize, int pageIndex) {
		String hql = "from Course s where s.type=?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setInteger(0, type);
		query.setFirstResult(pageSize * pageIndex);
		query.setMaxResults(pageSize);
		return query.list();
	}

}
