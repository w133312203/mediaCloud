package com.hm.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hm.base.dao.BaseDao;
import com.hm.domain.Video;

public interface VideoDao extends BaseDao<Video>{
	
	/**
	 * 查看视频列表
	 * @param passportId 账号ID
	 */
	List<Map> list(@Param("passportId")Integer passportId, @Param("type")Integer type, @Param("groupId")Integer groupId, @Param("search")String search, @Param("first")Integer first, @Param("max")Integer max);
	
	/**
	 * 查看已经上传成功的视频列表
	 * @param passportId 账号ID
	 */
	List<Map> apiList(@Param("type")Integer type, @Param("groupId")Integer groupId, @Param("search")String search, @Param("first")Integer first, @Param("max")Integer max);
	
	/**
	 * 查看视频总数
	 * @param passportId 账号Id
	 */
	Integer count(@Param("passportId")Integer passportId, @Param("type")Integer type, @Param("groupId")Integer groupId, @Param("search")String search);
	
	/**
	 * 根据videoId查找Video
	 * @param videoId 阿里云videoId
	 * @return
	 */
	Video findByVideoId(String videoId);
	
	/**
	 * 根据videoId删除视频
	 * @param videoId 阿里云videoId
	 * @return
	 */
	void deleteByVideoId(String videoId);
	
	/**
	 * 批量更新分组
	 * @param oldGroupId 老分组
	 * @param newGroupId 新分组
	 */
	void updateUpGroup(@Param("oldGroupId")Integer oldGroupId, @Param("newGroupId")Integer newGroupId);
	
	/**
	 * 批量更新分组
	 * @param Array 老分组数组
	 * @param newGroupId 新分组
	 */
	void updateUpGroupByArray(@Param("array")String[] idArray, @Param("newGroupId")Integer newGroupId);
	
	/**
	 * 批量删除属性
	 * @param propertyId 属性Id
	 */
	void deleteProperty(Integer propertyId);
}
