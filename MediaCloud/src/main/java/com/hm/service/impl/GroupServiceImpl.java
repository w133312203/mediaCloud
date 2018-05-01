package com.hm.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hm.dao.GroupDao;
import com.hm.dao.PictureDao;
import com.hm.dao.VideoDao;
import com.hm.domain.Group;
import com.hm.service.GroupService;

@Service("groupService")
public class GroupServiceImpl implements GroupService{
	
	@Autowired
	GroupDao groupDao;
	
	@Autowired
	VideoDao videoDao;
	
	@Autowired
	PictureDao pictureDao;
	
	
	@Override
	public List<Map> list(Integer passportId, Integer id, String search, Integer first, Integer max) {
		return groupDao.list(passportId, id, search, first, max);
	}

	@Override
	public Integer count(Integer passportId, Integer id, String search) {
		return groupDao.count(passportId, id, search);
	}
	
	@Override
	public List<Map> findListByHier(Integer passportId, Integer type, Integer hierarchy) {
		return groupDao.findListByHier(passportId, type, hierarchy);
	}
	
	@Override
	public List<Group> findListByHierSec(Integer passportId, Integer type) {
		return groupDao.findListByHierSec(passportId, type);
	}
	
	@Override
	public Group findUpGroup(Integer passportId, Integer type) {
		return groupDao.findUpGroup(passportId, type);
	}

	@Override
	public Group findUpGroupById(Integer passportId, Integer id) {
		Group group = findById(id);
		return groupDao.findUpGroup(passportId, group.getType());
	}
	
	@Override
	public List<Map> findGroupByType(Integer passportId, Integer type) {
		return groupDao.findGroupByType(passportId, type);
	}
	
	@Override
	public List<Map> findGroupByType(Integer passportId, Integer type, Integer uploadType) {
		return groupDao.findGroupByUploadType(passportId, type, uploadType);
	}
	
	@Override
	public void save(Group t) {
		groupDao.save(t);
	}

	@Override
	public void update(Group t) {
		groupDao.update(t);
	}

	@Override
	public void deleteById(Integer id) {
		Group group =findUpGroupById(null, id);
		pictureDao.updateUpGroup(id, group.getId());
		videoDao.updateUpGroup(id, group.getId());
		groupDao.deleteById(id);
	}

	@Override
	public Group findById(Integer id) {
		return groupDao.findById(id);
	}
	
}
