package com.springmvc.ketao.dao;

import java.util.List;

import com.springmvc.ketao.entity.Inform;

public interface IInformDao {

	public void addInform(Inform inform) throws Exception;
	public boolean delInformsByIds(String []ids);
	public boolean updateInform(Inform inform);
	public Inform getInform(String id);
	public List<Inform> getAllInforms();
	public List<Inform> getInformsBySearch(String search);
}
