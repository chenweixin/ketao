package com.springmvc.ketao.service.impl;

import java.util.List;

import com.springmvc.ketao.dao.ICoscollectDao;
import com.springmvc.ketao.entity.Coscollect;
import com.springmvc.ketao.service.ICoscollectManager;

public class CoscollectManager implements ICoscollectManager {

	private ICoscollectDao coscollectDao;

	public ICoscollectDao getCoscollectDao() {
		return coscollectDao;
	}

	public void setCoscollectDao(ICoscollectDao coscollectDao) {
		this.coscollectDao = coscollectDao;
	}

	public void add(Coscollect coscollect) throws Exception {
		coscollectDao.add(coscollect);
	}

	public Coscollect get(String id) {
		return coscollectDao.get(id);
	}

	public Coscollect isExist(String course_id, String student_id) {
		return coscollectDao.isExist(course_id, student_id);
	}

	public boolean del(String id) {
		return coscollectDao.del(id);
	}

	public List<Coscollect> getMy(String student_id, int pageSize, int pageIndex) {
		return coscollectDao.getMy(student_id, pageSize, pageIndex);
	}
}
