package com.springmvc.ketao.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import com.springmvc.ketao.dao.IEvaluationDao;
import com.springmvc.ketao.entity.Course;
import com.springmvc.ketao.entity.Evaluation;

public class EvaluationDao implements IEvaluationDao {

	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void evaluate(Evaluation evaluation) throws Exception {
		sessionFactory.getCurrentSession().save(evaluation);
	}

	public Evaluation get(String id) {
		String hql = "from Evaluation s where s.id=?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, id);
		return (Evaluation) query.uniqueResult();
	}

	public boolean update(Evaluation evaluation) {
		String hql = "update Evaluation s set s.course_id=?,s.student_id=?,s.score=?,s.comment=?,s.num_like=?,s.create_time=? where s.id=?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, evaluation.getCourse_id());
		query.setString(1, evaluation.getStudent_id());
		query.setInteger(2, evaluation.getScore());
		query.setString(3, evaluation.getComment());
		query.setInteger(4, evaluation.getNum_like());
		query.setString(5, evaluation.getCreate_time());
		query.setString(6, evaluation.getId());
		
		return (query.executeUpdate() > 0);
	}

	public List<Evaluation> getListByCourseId(String course_id, int pageSize, int pageIndex) {
		String hql = "from Evaluation s where s.course_id=? order by s.create_time desc";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, course_id);
		query.setFirstResult(pageIndex * pageSize);
		query.setMaxResults(pageSize);
		return query.list();
	}

	public Evaluation isExist(String course_id, String student_id) {
		String hql = "from Evaluation s where s.course_id=? and s.student_id=?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, course_id);
		query.setString(1, student_id);
		return (Evaluation) query.uniqueResult();
	}

	public List<Evaluation> getListByStudentId(String student_id, int pageSize, int pageIndex) {
		String hql = "from Evaluation s where s.student_id=? order by s.create_time desc";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, student_id);
		query.setFirstResult(pageIndex * pageSize);
		query.setMaxResults(pageSize);
		return query.list();
	}

	public List<Evaluation> getHotList(int pageSize, int pageIndex) {
		String hql = "from Evaluation s order by s.num_like desc";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setFirstResult(pageSize * pageIndex);
		query.setMaxResults(pageSize);
		return query.list();
	}

	public List<Evaluation> getAll() {
		String hql = "from Evaluation";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}

	public boolean del(String id) {
		String hql = "delete Evaluation s where s.id=?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, id);
		return (query.executeUpdate() > 0);
	}

}
