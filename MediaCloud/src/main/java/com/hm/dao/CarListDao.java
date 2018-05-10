package com.hm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hm.base.dao.BaseDao;
import com.hm.domain.CarList;

public interface CarListDao extends BaseDao<CarList>{
	
	/**
	 * 查看车单列表
	 * @param passportId 账号ID
	 */
	List<CarList> list(@Param("search")String search, @Param("first")Integer first, @Param("max")Integer max);
	
	/**
	 * 查看车单列表
	 * @param passportId 账号ID
	 */
	List<CarList> listAll();
	
	
	/**
	 * 查看列表总数
	 * @param passportId 账号Id
	 */
	Integer count(@Param("search")String search);
	
}
