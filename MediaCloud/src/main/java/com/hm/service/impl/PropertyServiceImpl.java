package com.hm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hm.dao.PictureDao;
import com.hm.dao.PropertyDao;
import com.hm.dao.VideoDao;
import com.hm.domain.Property;
import com.hm.service.PropertyService;

@Service("propertyService")
public class PropertyServiceImpl implements PropertyService{
	
	@Autowired
	PropertyDao propertyDao;
	
	@Autowired
	VideoDao videoDao;
	
	@Autowired
	PictureDao pictureDao;
	
	
	@Override
	public List<Property> list(Integer passportId, Integer groupId, Integer type, String search, Integer first, Integer max) {
		return propertyDao.list(passportId, groupId, type, search, first, max);
	}
	
	@Override
	public List<Property> listAll(Integer passportId, Integer groupId, Integer type) {
		return propertyDao.listAll(passportId, groupId, type);
	}

	@Override
	public Integer count(Integer passportId, Integer groupId, Integer type, String search) {
		return propertyDao.count(passportId, groupId, type, search);
	}
	
	@Override
	public void save(Property t) {
		propertyDao.save(t);
	}

	@Override
	public void update(Property t) {
		propertyDao.update(t);
	}

	@Override
	public void deleteById(Integer id) {
		pictureDao.deleteProperty(id);
		videoDao.deleteProperty(id);
		propertyDao.deleteById(id);
	}

	@Override
	public Property findById(Integer id) {
		return propertyDao.findById(id);
	}
	
}
