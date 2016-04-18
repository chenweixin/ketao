package com.springmvc.ketao.service.impl;

import com.springmvc.ketao.dao.IEvalikeDao;
import com.springmvc.ketao.entity.Evalike;
import com.springmvc.ketao.service.IEvalikeManager;

public class EvalikeManager implements IEvalikeManager {

	private IEvalikeDao evalikeDao;
	
	public IEvalikeDao getEvalikeDao() {
		return evalikeDao;
	}

	public void setEvalikeDao(IEvalikeDao evalikeDao) {
		this.evalikeDao = evalikeDao;
	}

	public void add(Evalike evalike) throws Exception {
		evalikeDao.add(evalike);
	}

	public Evalike isExist(String evaluation_id, String student_id) {
		return evalikeDao.isExist(evaluation_id, student_id);
	}

	public boolean del(String id) {
		return evalikeDao.del(id);
	}

}
