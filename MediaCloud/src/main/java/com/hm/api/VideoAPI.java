package com.hm.api;  

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hm.base.controller.BaseCotroller;
import com.hm.domain.Group;
import com.hm.service.GroupService;
import com.hm.service.PropertyService;
import com.hm.service.VideoService;
import com.hm.utils.DateUtil;

@Controller
@RequestMapping("/api")
public class VideoAPI extends BaseCotroller{ 
    
    @Resource  
    VideoService videoService;
    
    @Resource  
    GroupService groupService;
    
    @Resource  
    PropertyService propertyService;
    
    //视频列表接口
    @ResponseBody
	@RequestMapping("/video")
	public Map<String,Object> video(Integer groupId, Integer first, Integer max) {
    	Map map = new HashMap();
    	if(groupId!=null&&first!=null&&max!=null) {
    		Group group = groupService.findById(groupId);
    		if(group!=null) {
    			List<Map> videoList = videoService.apiList(group.getType(), group.getId(), "", first, max);
    			for(Map vMap:videoList) {
    				if(vMap.get("time")!=null) {
    					vMap.put("time", DateUtil.getTimeByMin(vMap.get("time").toString()));
    				}
    			}
    			map.put("CODE", 10001);
    			map.put("title", group.getTitle());
    			map.put("list", videoList);
    			map.put("MSG", "SUCCESS");
    		}else {
    			map.put("CODE", -200);
        		map.put("MSG", "NO GROUP");
    		}
    	}else {
    		map.put("CODE", -200);
    		map.put("MSG", "PARAM IS ERROR OR NULL");
    	}
    	return map;
	}
}