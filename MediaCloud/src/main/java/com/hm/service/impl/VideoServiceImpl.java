package com.hm.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hm.dao.VideoDao;
import com.hm.domain.Video;
import com.hm.service.VideoService;
import com.hm.utils.StringUtil;
import com.hm.utils.VideoStatus;
import com.hm.utils.VodUtil;

@Service("videoService")
public class VideoServiceImpl implements VideoService{
	
	@Autowired
	VideoDao videoDao;

	@Override
	public void save(Video t) {
		videoDao.save(t);
	}

	@Override
	public void update(Video t) {
		videoDao.update(t);
	}
	
	@Override
	public Video updateVideoInfo(Video video) {
		Video oldVideo = findById(video.getId());
		if(!StringUtil.isEmpty(video.getTitle())) {
			oldVideo.setTitle(video.getTitle());
		}
		if(!StringUtil.isEmpty(video.getHeadImage())) {
			oldVideo.setHeadImage(video.getHeadImage());
		}
		if(video.getGroupId()!=null) {
			oldVideo.setGroupId(video.getGroupId());
		}
		if(VodUtil.editVideo(oldVideo)) {
			videoDao.update(oldVideo);
		}
		return oldVideo;
	}

	@Override
	public void deleteById(Integer id) {
		videoDao.deleteById(id);
	}

	@Override
	public Video findById(Integer id) {
		return videoDao.findById(id);
	}

	@Override
	public List<Map> list(Integer passportId, Integer type, Integer groupId, String search,
			Integer first, Integer max) {
		return videoDao.list(passportId, type, groupId, search, first, max);
	}
	
	@Override
	public List<Map> apiList(Integer type, Integer groupId, String search, Integer first, Integer max) {
		return videoDao.apiList(type, groupId, search, first, max);
	}

	@Override
	public Integer count(Integer passportId, Integer type, Integer groupId, String search) {
		return videoDao.count(passportId, type, groupId, search);
	}
	
	public Video findByVideoId(String videoId) {
		Video video = videoDao.findByVideoId(videoId);
		return video;
	}
	
	public void deleteByVideoId(String videoId) {
		videoDao.deleteByVideoId(videoId);
	}

	@Override
	public Video getVodInfo(String videoId) {
		Video video = findByVideoId(videoId);
		Map resultMap = VodUtil.getVideoInfo(videoId);
		while (true) {
			if(resultMap.get("Code")!=null) {
				if(video.getStatus()==null||video.getStatus()==0) {
					video.setStatus(3);
					update(video);
				}
				try {
					Thread.sleep(15000);
					resultMap = VodUtil.getVideoInfo(videoId);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else {
				Map baseMap = (Map) resultMap.get("VideoBase");
				if(baseMap.get("CoverURL")==null) {
					if(video.getStatus()==null||video.getStatus()==0) {
						video.setStatus(3);
						update(video);
					}
					try {
						Thread.sleep(15000);
						resultMap = VodUtil.getVideoInfo(videoId);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else {
					break;
				}
			}
		}
		Map odMap = VodUtil.getODVideo(videoId);
		if(video!=null) {
			Map baseMap = (Map) resultMap.get("VideoBase");
			String status = baseMap.get("Status").toString();
			status = status.toUpperCase();
			VideoStatus videoStat = VideoStatus.valueOf(status);
			if(video.getStatus()==3) {
				video.setTime(baseMap.get("Duration").toString());
				video.setHeadImage(baseMap.get("CoverURL").toString());
				video.setStatus(videoStat.ordinal());
				video.setOd_address(odMap.get("FileURL").toString());
				Map playMap = (Map) resultMap.get("PlayInfoList");
				List<Map> playList = (List<Map>) playMap.get("PlayInfo");
				for(Map map:playList) {
					String Definition = map.get("Definition").toString();
					if(Definition.equals("LD")) {
						video.setLd_address(map.get("PlayURL").toString());
					}
				}
				update(video);
			}else {
				if(video.getStatus()!=videoStat.ordinal()) {
					video.setStatus(videoStat.ordinal());
					update(video);
				}
			}
		}
		return video;
	}
	
	@Override
	public void updateUpGroup(Integer oldGroupId, Integer newGroupId) {
		videoDao.updateUpGroup(oldGroupId, newGroupId);
	}
	
	@Override
	public void deleteProperty(Integer propertyId) {
		videoDao.deleteProperty(propertyId);
	}
	
}
