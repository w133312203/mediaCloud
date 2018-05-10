package com.hm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hm.dao.CarListDao;
import com.hm.domain.CarList;
import com.hm.service.CarListService;

@Service("carListService")
public class CarListServiceImpl implements CarListService{
	
	@Autowired
	CarListDao carListDao;
	
	@Override
	public List<CarList> list(String search, Integer first, Integer max) {
		return carListDao.list(search, first, max);
	}
	
	@Override
	public List<CarList> listAll() {
		return carListDao.listAll();
	}

	@Override
	public Integer count(String search) {
		return carListDao.count(search);
	}

	@Override
	public void save(CarList t) {
		carListDao.save(t);
	}

	@Override
	public void update(CarList t) {
		carListDao.update(t);
	}

	@Override
	public void deleteById(Integer id) {
		carListDao.deleteById(id);
	}

	@Override
	public CarList findById(Integer id) {
		return carListDao.findById(id);
	}
	
}
