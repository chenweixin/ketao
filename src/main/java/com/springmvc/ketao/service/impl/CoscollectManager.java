package com.springmvc.ketao.service.impl;

import java.util.ArrayList;
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

	public String[] getMy(String student_id) {
		List<Coscollect> coscollects = coscollectDao.getMy(student_id);
		List<String> course_ids = new ArrayList<String>();
		for(int i = 0; i < coscollects.size(); i++){
			course_ids.add(coscollects.get(i).getCourse_id());
		}
		String []ids = (String[]) course_ids.toArray(new String[course_ids.size()]);
		return ids;
	}
}
