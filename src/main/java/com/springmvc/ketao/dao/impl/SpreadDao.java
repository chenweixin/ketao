package com.springmvc.ketao.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import com.springmvc.ketao.dao.ISpreadDao;
import com.springmvc.ketao.entity.Spread;

public class SpreadDao implements ISpreadDao {

	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void add(Spread spread) throws Exception {
		sessionFactory.getCurrentSession().save(spread);
	}

	public boolean delByIds(String[] ids) {
		String hql = "delete Spread s where s.id in (:ids)";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameterList("ids", ids);
		return (query.executeUpdate() > 0);
	}

	public boolean update(Spread spread) {
		String hql = "update Spread s set s.title=?,s.sponsor=?,s.event_date=?,s.event_address=?,s.event_describe=?,s.poster_url=?"
				+ ",s.create_time=? where s.id=?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, spread.getTitle());
		query.setString(1, spread.getSponsor());
		query.setString(2, spread.getEvent_date());
		query.setString(3, spread.getEvent_address());
		query.setString(4, spread.getEvent_describe());
		query.setString(5, spread.getPoster_url());
		query.setString(6, spread.getCreate_time());
		query.setString(7, spread.getId());
		
		return (query.executeUpdate() > 0);
	}

	public Spread get(String id) {
		String hql = "from Spread s where s.id=?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, id);
		return (Spread) query.uniqueResult();
	}

	public List<Spread> getBySearch(String search) {
		String hql = "from Spread s where s.title like?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, "%"+search+"%");
		return query.list();
	}

	public List<Spread> getAll() {
		String hql = "from Spread s order by s.create_time desc";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}

	/**
	 * 分页接口 从0页开始算
	 */
	public List<Spread> getByPage(int pageSize, int pageIndex) {
		// TODO Auto-generated method stub
		String hql = "from Spread s order by s.create_time desc";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setFirstResult(pageIndex * pageSize); 
		query.setMaxResults(pageSize); 
		return query.list();
	}

}
