package com.hm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hm.base.dao.BaseDao;
import com.hm.domain.Property;

public interface PropertyDao extends BaseDao<Property>{
	
	/**
	 * 查看属性列表
	 * @param passportId 账号ID
	 */
	List<Property> list(@Param("passportId")Integer passportId, @Param("groupId")Integer groupId, @Param("type")Integer type, @Param("search")String search, @Param("first")Integer first, @Param("max")Integer max);
	
	/**
	 * 查看属性列表
	 * @param passportId 账号ID
	 */
	List<Property> listAll(@Param("passportId")Integer passportId, @Param("groupId")Integer groupId, @Param("type")Integer type);
	
	
	/**
	 * 查看列表总数
	 * @param passportId 账号Id
	 */
	Integer count(@Param("passportId")Integer passportId, @Param("groupId")Integer groupId, @Param("type")Integer type, @Param("search")String search);
	
}
