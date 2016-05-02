package com.springmvc.ketao.dao;

import java.util.List;

import com.springmvc.ketao.entity.Coscollect;

public interface ICoscollectDao {

	public void add(Coscollect coscollect) throws Exception;
	public boolean del(String id);
	public Coscollect get(String id);
	public Coscollect isExist(String course_id, String student_id);
	public List<Coscollect> getMy(String student_id, int pageSize, int pageIndex);
	public List<Coscollect> getMy(String student_id);
}
