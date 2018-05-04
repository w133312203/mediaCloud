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
import com.hm.domain.Property;
import com.hm.service.GroupService;
import com.hm.service.PictureService;
import com.hm.service.PropertyService;
import com.hm.utils.ApplicationUtil;
import com.hm.utils.BaseUtil;

@Controller
@RequestMapping("/api")
public class PictureAPI extends BaseCotroller{ 
    
    @Resource  
    GroupService groupService;
    
    @Resource  
    PropertyService propertyService;
    
    @Resource
    PictureService pictureService;
    
    //图片列表接口
    @ResponseBody
	@RequestMapping("/picture")
	public Map<String,Object> picture(Integer propertyId, Integer groupId, Integer first, Integer max) {
    	Map map = new HashMap();
    	if(groupId!=null&&first!=null&&max!=null) {
    		Group group = groupService.findById(groupId);
    		if(group!=null) {
    			//Group upGroup = groupService.findUpGroupById(0, group.getId());
    			List<Property> propertyList = propertyService.listAll(0, groupId, 0);
    			if(propertyList.size()>0) {
    				Property p = new Property();
        			p.setId(0);
        			p.setTitle("全部");
        			p.setGroupId(propertyList.get(0).getGroupId());
        			propertyList.add(0,p);
    			}
    			if(propertyList.size()>0&&(propertyId==null||propertyId==0)) {
    				propertyId = null;
    			}
    			List<Map> pictureList = pictureService.list(0, group.getType(), groupId, propertyId, first, max);
    			for(Map pMap:pictureList) {
    				pMap.put("size", BaseUtil.getPrintSize(Long.parseLong(pMap.get("size").toString())));
    				pMap.put("slt", ApplicationUtil.PICTURE_DOMAIN+pMap.get("url").toString()+ApplicationUtil.PICTURE_SLT);
    				pMap.put("url", ApplicationUtil.PICTURE_DOMAIN+pMap.get("url").toString());
    			}
    			map.put("CODE", 10001);
    			map.put("propertyList", propertyList);
    			map.put("pictureList", pictureList);
    			map.put("MSG", "SUCCESS");
    		}else {
    			map.put("CODE", -200);
        		map.put("MSG", "GROUP IS NULL");
    		}
    	}else {
    		map.put("CODE", -200);
    		map.put("MSG", "PARAM IS ERROR OR NULL");
    	}
    	return map;
	}
}