package com.springmvc.ketao.service;

import java.util.List;

import com.springmvc.ketao.entity.Notice;

public interface INoticeManager {

	public void add(Notice notice) throws Exception;
	public boolean delByIds(String []ids);
	public boolean update(Notice notice);
	public Notice get(String id);
	public List<Notice> getBySearch(String search);
	public List<Notice> getAll();
}
