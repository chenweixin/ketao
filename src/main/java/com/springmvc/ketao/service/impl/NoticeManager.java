package com.springmvc.ketao.service.impl;

import java.util.List;

import com.springmvc.ketao.dao.INoticeDao;
import com.springmvc.ketao.entity.Notice;
import com.springmvc.ketao.service.INoticeManager;

public class NoticeManager implements INoticeManager {

	private INoticeDao noticeDao;
	
	public INoticeDao getNoticeDao() {
		return noticeDao;
	}

	public void setNoticeDao(INoticeDao noticeDao) {
		this.noticeDao = noticeDao;
	}

	public void add(Notice notice) throws Exception {
		noticeDao.add(notice);
	}

	public boolean delByIds(String[] ids) {
		return noticeDao.delByIds(ids);
	}

	public boolean update(Notice notice) {
		return noticeDao.update(notice);
	}

	public Notice get(String id) {
		return noticeDao.get(id);
	}

	public List<Notice> getBySearch(String search) {
		return noticeDao.getBySearch(search);
	}

	public List<Notice> getAll() {
		return noticeDao.getAll();
	}

	public List<Notice> getNotices(int pageSize, int pageIndex, String []ids) {
		return noticeDao.getNotices(pageSize, pageIndex, ids);
	}

}
