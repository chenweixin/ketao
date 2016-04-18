package com.springmvc.ketao.dao;

import com.springmvc.ketao.entity.Evalike;

public interface IEvalikeDao {

	public void add(Evalike evalike) throws Exception;
	public Evalike isExist(String evaluation_id, String student_id);
	public boolean del(String id);
}
