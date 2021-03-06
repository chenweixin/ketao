package com.springmvc.ketao.dao;

import java.util.List;

import com.springmvc.ketao.entity.Notice;

public interface INoticeDao {

	public void add(Notice notice) throws Exception;
	public boolean delByIds(String []ids);
	public boolean update(Notice notice);
	public Notice get(String id);
	public List<Notice> getBySearch(String search);
	public List<Notice> getAll();
	public List<Notice> getNotices(int pageSize, int pageIndex, String []ids);
}
