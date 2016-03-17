package com.springmvc.ketao.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import com.springmvc.ketao.dao.IInformDao;
import com.springmvc.ketao.entity.Inform;
import com.springmvc.ketao.entity.Student;

public class InformDao implements IInformDao {

	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public void addInform(Inform inform) throws Exception {
		sessionFactory.getCurrentSession().save(inform);
	}

	public boolean delInformsByIds(String[] ids) {
		String hql = "delete Inform s where s.id in (:ids)";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameterList("ids", ids);
		return (query.executeUpdate() > 0);
	}

	public boolean updateInform(Inform inform) {
		String hql = "update Inform s set s.title=?,s.content=?,s.create_time=? where s.id=?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, inform.getTitle());
		query.setString(1, inform.getContent());
		query.setString(2, inform.getCreate_time());
		query.setString(3, inform.getId());
		
		return (query.executeUpdate() > 0);
	}

	public Inform getInform(String id) {
		String hql = "from Inform s where s.id=?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, id);
		return (Inform) query.uniqueResult();
	}

	public List<Inform> getAllInforms() {
		String hql = "from Inform";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}

	public List<Inform> getInformsBySearch(String search) {
		String hql = "from Inform s where s.title like?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, "%"+search+"%");
		return query.list();
	}

}
