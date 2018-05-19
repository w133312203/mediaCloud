package com.hm.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hm.base.service.BaseService;
import com.hm.domain.Group;

public interface GroupService extends BaseService<Group>{
	
	/**
	 * 查看组别列表
	 * @param passportId 账号ID
	 */
	List<Map> list(Integer passportId, Integer id, String search, Integer first, Integer max);
	
	/**
	 * 查看组别总数
	 * @param passportId 账号Id
	 */
	Integer count(Integer passportId, Integer id, String search);
	
	/**
	 * 根据分组层级/类型查找分组列表
	 * @param passportId 账号Id
	 */
	List<Map> findListByHier(Integer passportId, Integer type, Integer hierarchy);
	
	/**
	 * 根据类型查找第二层级分组列表
	 * @param passportId 账号Id
	 */
	List<Group> findListByHierSec(Integer passportId, Integer type);
	
	/**
	 * 根据分组类型查找顶层分组
	 * @param passportId 账号Id
	 */
	Group findUpGroup(Integer passportId, Integer type);
	
	/**
	 * 根据分组ID查找顶层分组
	 * @param passportId 账号Id
	 */
	Group findUpGroupById(Integer passportId, Integer id);
	
	/**
	 * 根据分组类型查找分组
	 * @param passportId 账号Id
	 */
	List<Map> findGroupByType(Integer passportId, Integer type);
	
	/**
	 * 根据分组类型/上传类型查找分组
	 * @param passportId 账号Id
	 */
	List<Map> findGroupByType(Integer passportId, Integer type, Integer uploadType);
	
	/**
	 * 查找最大层级
	 * @param type 分组类型
	 */
	Integer findMaxHier(Integer type);
	
}
