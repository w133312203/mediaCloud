package com.hm.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hm.dao.PictureDao;
import com.hm.domain.Picture;
import com.hm.service.PictureService;

@Service("pictureService")
public class PictureServiceImpl implements PictureService{
	
	@Autowired
	PictureDao pictureDao;

	@Override
	public void save(Picture t) {
		pictureDao.save(t);
	}

	@Override
	public void update(Picture t) {
		pictureDao.update(t);
	}

	@Override
	public void deleteById(Integer id) {
		pictureDao.deleteById(id);
	}

	@Override
	public Picture findById(Integer id) {
		return pictureDao.findById(id);
	}

	@Override
	public List<Map> list(Integer passportId, Integer type, Integer groupId, Integer propertyId,
			Integer first, Integer max) {
		return pictureDao.list(passportId, type, groupId, propertyId, first, max);
	}

	@Override
	public Integer count(Integer passportId, Integer type, Integer groupId, Integer propertyId) {
		return pictureDao.count(passportId, type, groupId, propertyId);
	}
	
	@Override
	public void deleteByArrayId(String[] idArray) {
		pictureDao.deleteByArrayId(idArray);
	}

	@Override
	public void updateByArrayId(Integer groupId, Integer propertyId, String[] idArray) {
		pictureDao.updateByArrayId(groupId, propertyId, idArray);
	}
	
	@Override
	public void updateUpGroup(Integer oldGroupId, Integer newGroupId) {
		pictureDao.updateUpGroup(oldGroupId, newGroupId);
	}
	
	@Override
	public void deleteProperty(Integer propertyId) {
		pictureDao.deleteProperty(propertyId);
	}
	
}
