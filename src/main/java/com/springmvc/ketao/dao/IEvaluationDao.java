package com.springmvc.ketao.dao;

import java.util.List;

import com.springmvc.ketao.entity.Evaluation;

public interface IEvaluationDao {

	public void evaluate(Evaluation evaluation) throws Exception;
	public Evaluation get(String id);
	public boolean update(Evaluation evaluation);
	public List<Evaluation> getListByCourseId(String course_id, int pageSize, int pageIndex);
	public List<Evaluation> getListByStudentId(String student_id, int pageSize, int pageIndex);
	public Evaluation isExist(String course_id, String student_id);
	public List<Evaluation> getHotList(int pageSize, int pageIndex);
	public List<Evaluation> getAll();
	public boolean del(String id);
}
