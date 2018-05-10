package com.hm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hm.base.dao.BaseDao;
import com.hm.domain.Materiel;

public interface MaterielDao extends BaseDao<Materiel>{
	
	/**
	 * 查看物料列表
	 * @param passportId 账号ID
	 */
	List<Materiel> list(@Param("search")String search, @Param("first")Integer first, @Param("max")Integer max);
	
	/**
	 * 查看物料列表
	 * @param passportId 账号ID
	 */
	List<Materiel> listAll();
	
	
	/**
	 * 查看列表总数
	 * @param passportId 账号Id
	 */
	Integer count(@Param("search")String search);
	
}
