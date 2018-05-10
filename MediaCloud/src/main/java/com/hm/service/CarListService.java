package com.hm.service;

import java.util.List;

import com.hm.base.service.BaseService;
import com.hm.domain.CarList;

public interface CarListService extends BaseService<CarList>{
	
	/**
	 * 查看车单列表
	 * @param passportId 账号ID
	 */
	List<CarList> list(String search, Integer first, Integer max);
	
	/**
	 * 查看车单列表
	 * @param passportId 账号ID
	 */
	List<CarList> listAll();
	
	/**
	 * 查看车单总数
	 * @param passportId 账号Id
	 */
	Integer count(String search);
	
}
