package com.hm.service;

import java.util.List;

import com.hm.base.service.BaseService;
import com.hm.domain.Property;

public interface PropertyService extends BaseService<Property>{
	
	/**
	 * 查看属性列表
	 * @param passportId 账号ID
	 */
	List<Property> list(Integer passportId, Integer groupId, Integer type, String search, Integer first, Integer max);
	
	/**
	 * 查看属性列表
	 * @param passportId 账号ID
	 */
	List<Property> listAll(Integer passportId, Integer groupId, Integer type);
	
	/**
	 * 查看组别总数
	 * @param passportId 账号Id
	 */
	Integer count(Integer passportId, Integer groupId, Integer type, String search);
	
}
