package com.hm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hm.dao.RoleDao;
import com.hm.domain.Role;
import com.hm.service.RoleService;

@Service("roleService")
public class RoleServiceImpl implements RoleService{
	
	@Autowired
	RoleDao roleDao;

	@Override
	public void save(Role t) {
		roleDao.save(t);
	}

	@Override
	public void update(Role t) {
		roleDao.update(t);
	}

	@Override
	public void deleteById(Integer id) {
		roleDao.deleteById(id);
	}

	@Override
	public Role findById(Integer id) {
		return roleDao.findById(id);
	}
	
}
