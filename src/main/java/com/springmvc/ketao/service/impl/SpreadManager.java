package com.springmvc.ketao.service.impl;

import java.util.List;

import com.springmvc.ketao.dao.ISpreadDao;
import com.springmvc.ketao.entity.Spread;
import com.springmvc.ketao.service.ISpreadManager;

public class SpreadManager implements ISpreadManager {

	private ISpreadDao spreadDao;
	
	public ISpreadDao getSpreadDao() {
		return spreadDao;
	}

	public void setSpreadDao(ISpreadDao spreadDao) {
		this.spreadDao = spreadDao;
	}

	public void add(Spread spread) throws Exception {
		spreadDao.add(spread);
	}

	public boolean delByIds(String[] ids) {
		return spreadDao.delByIds(ids);
	}

	public boolean update(Spread spread) {
		return spreadDao.update(spread);
	}

	public Spread get(String id) {
		return spreadDao.get(id);
	}

	public List<Spread> getBySearch(String search) {
		return spreadDao.getBySearch(search);
	}

	public List<Spread> getAll() {
		return spreadDao.getAll();
	}

	public List<Spread> getByPage(int pageSize, int pageIndex) {
		return spreadDao.getByPage(pageSize, pageIndex);
	}

}
