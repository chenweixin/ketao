package com.springmvc.ketao.service.impl;

import java.util.List;

import com.springmvc.ketao.dao.IEvaluationDao;
import com.springmvc.ketao.entity.Evaluation;
import com.springmvc.ketao.service.IEvaluationManager;

public class EvaluationManager implements IEvaluationManager {

	private IEvaluationDao evaluationDao;
	
	public IEvaluationDao getEvaluationDao() {
		return evaluationDao;
	}

	public void setEvaluationDao(IEvaluationDao evaluationDao) {
		this.evaluationDao = evaluationDao;
	}

	public void evaluate(Evaluation evaluation) throws Exception {
		evaluationDao.evaluate(evaluation);
	}

	public Evaluation get(String id) {
		return evaluationDao.get(id);
	}

	public boolean update(Evaluation evaluation) {
		return evaluationDao.update(evaluation);
	}

	public List<Evaluation> getListByCourseId(String course_id, int pageSize, int pageIndex) {
		return evaluationDao.getListByCourseId(course_id, pageSize, pageIndex);
	}

	public Evaluation isExist(String course_id, String student_id) {
		return evaluationDao.isExist(course_id, student_id);
	}

	public List<Evaluation> getListByStudentId(String student_id, int pageSize, int pageIndex) {
		return evaluationDao.getListByStudentId(student_id, pageSize, pageIndex);
	}

	public List<Evaluation> getHotList(int pageSize, int pageIndex) {
		return evaluationDao.getHotList(pageSize, pageIndex);
	}

	public List<Evaluation> getAll() {
		return evaluationDao.getAll();
	}

	public boolean del(String id) {
		return evaluationDao.del(id);
	}

}
