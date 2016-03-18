package com.springmvc.ketao.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import com.springmvc.ketao.dao.INoticeDao;
import com.springmvc.ketao.entity.Notice;

public class NoticeDao implements INoticeDao {

	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void add(Notice notice) throws Exception {
		sessionFactory.getCurrentSession().save(notice);
	}

	public boolean delByIds(String[] ids) {
		String hql = "delete Notice s where s.id in (:ids)";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameterList("ids", ids);
		return (query.executeUpdate() > 0);
	}

	public boolean update(Notice notice) {
		String hql = "update Notice s set s.title=?,s.content=?,s.course_id=?,s.create_time=? where s.id=?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, notice.getTitle());
		query.setString(1, notice.getContent());
		query.setString(2, notice.getCourse_id());
		query.setString(3, notice.getCreate_time());
		query.setString(4, notice.getId());
		return (query.executeUpdate() > 0);
	}

	public Notice get(String id) {
		String hql = "from Notice s where s.id=?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, id);
		return (Notice) query.uniqueResult();
	}

	public List<Notice> getBySearch(String search) {
		String hql = "from Notice s where s.title like?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, "%"+search+"%");
		return query.list();
	}

	public List<Notice> getAll() {
		String hql = "from Notice";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}

}
