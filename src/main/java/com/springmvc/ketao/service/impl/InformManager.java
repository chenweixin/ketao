package com.springmvc.ketao.service.impl;

import java.util.List;

import com.springmvc.ketao.dao.IInformDao;
import com.springmvc.ketao.entity.Inform;
import com.springmvc.ketao.service.IInformManager;

public class InformManager implements IInformManager {

	private IInformDao informDao;

	public IInformDao getInformDao() {
		return informDao;
	}

	public void setInformDao(IInformDao informDao) {
		this.informDao = informDao;
	}

	public void addInform(Inform inform) throws Exception {
		informDao.addInform(inform);
	}

	public boolean delInformsByIds(String[] ids) {
		return informDao.delInformsByIds(ids);
	}

	public boolean updateInform(Inform inform) {
		return informDao.updateInform(inform);
	}

	public Inform getInform(String id) {
		return informDao.getInform(id);
	}

	public List<Inform> getAllInforms() {
		return informDao.getAllInforms();
	}

	public List<Inform> getInformsBySearch(String search) {
		return informDao.getInformsBySearch(search);
	}

}
