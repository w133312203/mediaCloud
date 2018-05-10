package com.hm.service;

import java.util.List;

import com.hm.base.service.BaseService;
import com.hm.domain.Materiel;

public interface MaterielService extends BaseService<Materiel>{
	
	/**
	 * 查看物料列表
	 * @param passportId 账号ID
	 */
	List<Materiel> list(String search, Integer first, Integer max);
	
	/**
	 * 查看物料列表
	 * @param passportId 账号ID
	 */
	List<Materiel> listAll();
	
	/**
	 * 查看车单总数
	 * @param passportId 账号Id
	 */
	Integer count(String search);
	
}
