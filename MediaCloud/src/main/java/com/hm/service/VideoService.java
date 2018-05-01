package com.hm.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hm.base.service.BaseService;
import com.hm.domain.Video;

public interface VideoService extends BaseService<Video>{
	
	/**
	 * 查看视频列表
	 * @param passportId 账号ID
	 */
	List<Map> list(Integer passportId, Integer type, Integer groupId, String search, Integer first, Integer max);
	
	/**
	 * 查看已经上传成功的视频列表
	 * @param passportId 账号ID
	 */
	List<Map> apiList(Integer type, Integer groupId, String search, Integer first, Integer max);
	
	/**
	 * 查看视频总数
	 * @param passportId 账号Id
	 */
	Integer count(Integer passportId, Integer type, Integer groupId, String search);
	
	/**
	 * 获取视频信息
	 * @param videoId 视频ID
	 */
	Video getVodInfo(String videoId);
	
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
	 * 更新视频信息并同步阿里云
	 * @param video 
	 * @return
	 */
	Video updateVideoInfo(Video video);
	
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
}
