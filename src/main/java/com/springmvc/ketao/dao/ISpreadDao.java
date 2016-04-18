package com.springmvc.ketao.dao;

import java.util.List;

import com.springmvc.ketao.entity.Spread;

public interface ISpreadDao {

	public void add(Spread spread) throws Exception;
	public boolean delByIds(String []ids);
	public boolean update(Spread spread);
	public Spread get(String id);
	public List<Spread> getBySearch(String search);
	public List<Spread> getAll();
	public List<Spread> getByPage(int pageSize, int pageIndex);
}
