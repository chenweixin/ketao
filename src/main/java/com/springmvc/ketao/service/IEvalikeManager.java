package com.springmvc.ketao.service;

import com.springmvc.ketao.entity.Evalike;

public interface IEvalikeManager {


	public void add(Evalike evalike) throws Exception;
	public Evalike isExist(String evaluation_id, String student_id);
	public boolean del(String id);
}
