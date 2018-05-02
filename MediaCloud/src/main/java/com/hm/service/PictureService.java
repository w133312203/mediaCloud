package com.hm.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hm.base.service.BaseService;
import com.hm.domain.Picture;

public interface PictureService extends BaseService<Picture>{
	
	/**
	 * 查看图片列表
	 * @param passportId 账号ID
	 */
	List<Map> list(Integer passportId, Integer type, Integer groupId, Integer propertyId, Integer first, Integer max);
	
	/**
	 * 查看图片总数
	 * @param passportId 账号Id
	 */
	Integer count(Integer passportId, Integer type, Integer groupId, Integer propertyId);
	
	/**
	 * 按Id数组删除
	 * @param idArray 图片id数组
	 */
	void deleteByArrayId(String[] idArray);
	
	/**
	 * 批量更新分组
	 * @param oldGroupId 老分组
	 * @param newGroupId 新分组
	 */
	void updateUpGroup(Integer oldGroupId, Integer newGroupId);
	
	/**
	 * 批量删除属性
	 * @param propertyId 属性Id
	 */
	void deleteProperty(Integer propertyId);
	
	/**
	 * 按Id数组编辑
	 * @param id 图片id数组
	 */
	void updateByArrayId(Integer groupId, Integer propertyId, String[] idArray);
}
