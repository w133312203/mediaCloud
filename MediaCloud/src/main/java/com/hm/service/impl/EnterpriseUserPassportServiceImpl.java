package com.hm.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hm.dao.EnterpriseUserPassportDao;
import com.hm.domain.EnterpriseUserPassport;
import com.hm.service.EnterpriseUserPassportService;

@Service("euserPassportService")
public class EnterpriseUserPassportServiceImpl implements EnterpriseUserPassportService{
	
	@Autowired
	EnterpriseUserPassportDao euserPassportDao;

	@Override
	public EnterpriseUserPassport findEnterpriseUserPassport(String email,
			String mobileNum, String password) {
		return euserPassportDao.findEnterpriseUserPassport(email, mobileNum, password);
	}

	public Map<String, Object> findInfo(Integer id) {
		return euserPassportDao.findInfo(id);
	}

	@Override
	public void save(EnterpriseUserPassport t) {
		euserPassportDao.save(t);
	}

	@Override
	public void update(EnterpriseUserPassport t) {
		euserPassportDao.update(t);
	}

	@Override
	public void deleteById(Integer Id) {
		euserPassportDao.deleteById(Id);
	}

	@Override
	public EnterpriseUserPassport findById(Integer Id) {
		return euserPassportDao.findById(Id);
	}	
	
	@Override
	public List<Map> list(String search, Integer first, Integer max) {
		return euserPassportDao.list(search, first, max);
	}
	
	@Override
	public Integer count(String search) {
		return euserPassportDao.count(search);
	}

	@Override
	public List<EnterpriseUserPassport> checkEmail(String email, Integer id) {
		return euserPassportDao.checkEmail(email, id);
	}

	@Override
	public List<EnterpriseUserPassport> checkMobileNum(String mobileNum, Integer id) {
		return euserPassportDao.checkMobileNum(mobileNum, id);
	}

	@Override
	public void updateStatus(Integer status, Integer id) {
		euserPassportDao.updateStatus(status, id);
	}
}
