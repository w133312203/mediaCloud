package com.hm.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hm.base.dao.BaseDao;
import com.hm.domain.Picture;

public interface PictureDao extends BaseDao<Picture>{
	
	/**
	 * 查看图片列表
	 * @param passportId 账号ID
	 */
	List<Map> list(@Param("passportId")Integer passportId, @Param("type")Integer type, @Param("groupId")Integer groupId, @Param("propertyId")Integer propertyId, @Param("first")Integer first, @Param("max")Integer max);
	
	/**
	 * 查看图片总数
	 * @param passportId 账号Id
	 */
	Integer count(@Param("passportId")Integer passportId, @Param("type")Integer type, @Param("groupId")Integer groupId, @Param("propertyId")Integer propertyId);
	
	/**
	 * 按Id数组删除
	 * @param id 图片id数组
	 */
	void deleteByArrayId(String[] idArray);
	
	/**
	 * 按Id数组编辑
	 * @param id 图片id数组
	 */
	void updateByArrayId(@Param("groupId")Integer groupId, @Param("propertyId")Integer propertyId, @Param("array")String[] idArray);
	
	/**
	 * 批量更新分组
	 * @param oldGroupId 老分组
	 * @param newGroupId 新分组
	 */
	void updateUpGroup(@Param("oldGroupId")Integer oldGroupId, @Param("newGroupId")Integer newGroupId);
	
	/**
	 * 批量删除属性
	 * @param propertyId 属性Id
	 */
	void deleteProperty(Integer propertyId);
}
