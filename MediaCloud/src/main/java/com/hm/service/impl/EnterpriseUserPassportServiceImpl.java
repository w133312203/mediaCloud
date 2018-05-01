package com.hm.service.impl;

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

}
