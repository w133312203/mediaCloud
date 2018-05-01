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

@Controller
@RequestMapping("/api")
public class MenuAPI extends BaseCotroller{ 
    
    @Resource  
    GroupService groupService;
    
    @Resource  
    PropertyService propertyService;
    
    //二级页面接口
    @ResponseBody
	@RequestMapping("/items")
	public Map<String,Object> items(Integer groupId) {
    	Map map = new HashMap();
    	if(groupId!=null) {
    		Group group = groupService.findById(groupId);
    		if(group!=null) {
    			List<Map> groupList = groupService.findGroupByType(0, group.getType());
    	    	Map groupMap = new HashMap();
    	    	for(Map gMap:groupList) {
    	    		if(gMap.get("groupId")!=null) {
    	    			groupMap.put(gMap.get("groupId").toString(), gMap);
    	    		}
    	    	}
    	    	groupList = groupService.list(0, group.getId(), "", null, null);
    			for(Map gMap:groupList) {
    				if(groupMap.get(gMap.get("id").toString())!=null) {
    					gMap.put("hasList", true);
    				}else {
    					gMap.put("hasList", false);
    				}
    	    	}
    			map.put("CODE", 10001);
    			map.put("title", group.getTitle());
    			map.put("list", groupList);
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