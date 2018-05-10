package com.hm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hm.dao.MaterielDao;
import com.hm.domain.Materiel;
import com.hm.service.MaterielService;

@Service("materielService")
public class MaterielServiceImpl implements MaterielService{
	
	@Autowired
	MaterielDao materielDao;
	
	@Override
	public List<Materiel> list(String search, Integer first, Integer max) {
		return materielDao.list(search, first, max);
	}
	
	@Override
	public List<Materiel> listAll() {
		return materielDao.listAll();
	}

	@Override
	public Integer count(String search) {
		return materielDao.count(search);
	}

	@Override
	public void save(Materiel t) {
		materielDao.save(t);
	}

	@Override
	public void update(Materiel t) {
		materielDao.update(t);
	}

	@Override
	public void deleteById(Integer id) {
		materielDao.deleteById(id);
	}

	@Override
	public Materiel findById(Integer id) {
		return materielDao.findById(id);
	}
	
}
