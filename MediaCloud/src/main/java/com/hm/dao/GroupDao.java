package com.hm.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hm.base.dao.BaseDao;
import com.hm.domain.Group;

public interface GroupDao extends BaseDao<Group>{
	
	/**
	 * 查看组别列表
	 * @param passportId 账号ID
	 */
	List<Map> list(@Param("passportId")Integer passportId, @Param("id")Integer id, @Param("search")String search, @Param("first")Integer first, @Param("max")Integer max);
	
	/**
	 * 查看组别总数
	 * @param passportId 账号Id
	 */
	Integer count(@Param("passportId")Integer passportId, @Param("id")Integer id, @Param("search")String search);
	
	/**
	 * 根据分组层级/类型查找分组列表
	 * @param passportId 账号Id
	 */
	List<Map> findListByHier(@Param("passportId")Integer passportId, @Param("type")Integer type, @Param("hierarchy")Integer hierarchy);
	
	/**
	 * 查找最大层级
	 * @param type 分组类型
	 */
	Integer findMaxHier(@Param("type")Integer type);
	
	/**
	 * 根据类型查找第二层级分组列表
	 * @param passportId 账号Id
	 */
	List<Group> findListByHierSec(@Param("passportId")Integer passportId, @Param("type")Integer type);
	
	/**
	 * 根据分组类型查找顶层分组
	 * @param passportId 账号Id
	 */
	Group findUpGroup(@Param("passportId")Integer passportId, @Param("type")Integer type);
	
	/**
	 * 根据分组类型查找分组
	 * @param passportId 账号Id
	 */
	List<Map> findGroupByType(@Param("passportId")Integer passportId, @Param("type")Integer type);
	
	/**
	 * 根据分组类型/上传类型查找分组
	 * @param passportId 账号Id
	 */
	List<Map> findGroupByUploadType(@Param("passportId")Integer passportId, @Param("type")Integer type, @Param("uploadType")Integer uploadType);
	
	/**
	 * 根据Id数组删除分组
	 * @param idArray id数组
	 */
	void deleteByArrayId(@Param("array")String[] idArray);
		
}
