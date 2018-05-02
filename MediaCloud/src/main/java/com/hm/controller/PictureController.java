package com.hm.controller;  

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.hm.base.controller.BaseCotroller;
import com.hm.domain.Group;
import com.hm.domain.Picture;
import com.hm.domain.Property;
import com.hm.service.EnterpriseUserInfoService;
import com.hm.service.EnterpriseUserPassportService;
import com.hm.service.GroupService;
import com.hm.service.PictureService;
import com.hm.service.PropertyService;
import com.hm.utils.ApplicationUtil;
import com.hm.utils.ImageUtil;
import com.hm.utils.StringUtil;

@Controller
@RequestMapping("/picture")
public class PictureController extends BaseCotroller{ 
	
    @Resource  
    EnterpriseUserPassportService euserPassportService;
    
    @Resource  
    EnterpriseUserInfoService euserInfoService;
    
    @Resource
    PictureService pictureService;
    
    @Resource
    GroupService groupService;
    
    @Resource  
    PropertyService propertyService;  
	
    //图片页面
    @RequestMapping("/index/{groupId}")
  	public ModelAndView index(@PathVariable String groupId) {
    	ModelAndView mv = new ModelAndView();
    	if(StringUtil.isNumber(groupId)) {
    		Group group = groupService.findUpGroupById(0, Integer.parseInt(groupId));
    		if(group!=null) {
    			List<Map> groupList = groupService.findGroupByType(0, group.getType(), 1);
    	    	List<Property> propertyList = propertyService.listAll(0, group.getId(), 0);
    	    	List<Map> newGroupList = new ArrayList<Map>();
    	    	Map groupMap = new HashMap();
    	    	for(Map map:groupList) {
    	    		List<Map> list = new ArrayList<Map>();
    	    		if(groupMap.get(map.get("groupId").toString())!=null) {
    	    			list = (List<Map>) groupMap.get(map.get("groupId").toString());
    	    		}
    	    		list.add(map);
    				groupMap.put(map.get("groupId").toString(),list);
    	    	}
    	    	for(int i=4;i>=0;i--) {
    	    		String flag = "";
    	    		List<Map> list = new ArrayList<Map>();
    	    		for(Map map:groupList) {
    	    			if(Integer.parseInt(map.get("hierarchy").toString())==i-1) {
    	    				if(groupMap.get(map.get("id").toString())==null) {
    	    					map.put("groupList", new ArrayList());
    	    				}else {
    	    					map.put("groupList", groupMap.get(map.get("id").toString()));
    	    				}
    	    				if(!flag.equals(map.get("groupId").toString())) {
    	    					flag = map.get("groupId").toString();
    	    					list = new ArrayList<Map>();
    	    				}
    	    				list.add(map);
    	    				groupMap.put(map.get("groupId").toString(), list);
    	    			}
    	    		}
    	    	}
    	    	newGroupList = (List<Map>)groupMap.get("0");
    	    	newGroupList = (List<Map>)newGroupList.get(0).get("groupList");
    	    	mv.addObject("group",group);
    	    	mv.addObject("groupList",newGroupList);
    	    	mv.addObject("propertyList",propertyList);
    			mv.setViewName(ApplicationUtil.JSP_URL+"picture");
    		}else {
    			mv.setViewName(ApplicationUtil.JSP_URL+"error");
    		}
    	}else {
    		mv.setViewName(ApplicationUtil.JSP_URL+"error");
    	}
    	return mv;
  	}
    
    //图片列表数据
    @ResponseBody
	@RequestMapping("/list")
	public Map<String,Object> list() {
		String maxresult = request.getParameter("limit");
		String offset = request.getParameter("offset");
		String type = request.getParameter("type");
		Integer groupId = null;
		Integer propertyId = null;
		if(StringUtil.isNumber(request.getParameter("groupId"))) {
			groupId = Integer.parseInt(request.getParameter("groupId"));
		}
		if(StringUtil.isNumber(request.getParameter("propertyId"))) {
			propertyId = Integer.parseInt(request.getParameter("propertyId"));
		}
		
		Group group = groupService.findUpGroup(0, Integer.parseInt(type));
		List<Property> propertyList = propertyService.listAll(0, group.getId(), 0);
		Map proMap = new HashMap();
		for(Property p:propertyList) {
			proMap.put(p.getId(), p.getTitle());
		}
		List<Map> pictureList = pictureService.list(0, Integer.parseInt(type), groupId, propertyId, Integer.parseInt(offset), Integer.parseInt(maxresult));
		for(Map pMap:pictureList) {
			if(pMap.get("propertyId")!=null) {
				pMap.put("proTitle", proMap.get(Integer.parseInt(pMap.get("propertyId").toString())));
			}else {
				pMap.put("proTitle", "暂无");
			}
		}
		Integer count = pictureService.count(0, Integer.parseInt(type), groupId, propertyId);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("rows", pictureList); 
		map.put("total", count);
		return map;
	}
    
    //上传图片
    @ResponseBody
  	@RequestMapping("/upload")
  	public Map<String,Object> upload(@RequestParam MultipartFile[] file, Integer groupId, Integer propertyId) {
    	for(MultipartFile m:file) {
    		//System.out.println(m.getOriginalFilename());
    		Picture picture = new Picture();
        	picture.setCreateTime(new Date());
        	if(groupId!=null) {
        		picture.setGroupId(groupId);
        	}
        	if(propertyId!=null) {
        		picture.setPropertyId(propertyId);
        	}
        	//picture.setPassportId(0);
        	picture.setSize(m.getSize());
        	picture.setUrl(ImageUtil.uploadImage(m));
        	pictureService.save(picture);
    	}
    	Map map = new HashMap();
    	map.put("CODE", 10001);
    	map.put("MSG", "SUCCESS");
    	return map;
  	}
    
    //删除图片
  	@RequestMapping("/delete")
  	public ModelAndView delete(String id, Integer groupId) {
  		ModelAndView mv = new ModelAndView();
  		if(!StringUtil.isEmpty(id)&&groupId!=null) {
  			id = id.trim().replace(" ", "");
  			if(id.indexOf(",")!=-1) {
  				pictureService.deleteByArrayId(id.split(","));
  				mv.setViewName("redirect:/picture/index/"+groupId);
  			}else {
  				Picture picture = pictureService.findById(Integer.parseInt(id));
  	  			if(picture!=null) {
  	  				mv.setViewName("redirect:/picture/index/"+groupId);
  	  				pictureService.deleteById(picture.getId());
  	  			}else {
  	  				mv.setViewName(ApplicationUtil.JSP_URL+"error");
  	  			}
  			}
  		}else {
  			mv.setViewName(ApplicationUtil.JSP_URL+"error");
  		}
      	return mv;
  	}
  	
  	//编辑图片信息
	@RequestMapping("/edit")
	public ModelAndView edit(Picture picture, String pictureId) {
		ModelAndView mv = new ModelAndView();
		if(!StringUtil.isEmpty(pictureId)) {
			pictureId = pictureId.trim().replace(" ", "");
			if(pictureId.indexOf(",")!=-1) {
				pictureService.updateByArrayId(picture.getGroupId(), picture.getPropertyId(), pictureId.split(","));
				mv.setViewName("redirect:/picture/index/"+picture.getGroupId());
			}else {
				Picture oldPicture = pictureService.findById(Integer.parseInt(pictureId));
				if(oldPicture!=null) {
					oldPicture.setGroupId(picture.getGroupId());
					oldPicture.setPropertyId(picture.getPropertyId());
					pictureService.update(oldPicture);
					mv.setViewName("redirect:/picture/index/"+oldPicture.getGroupId());
				}else {
					mv.setViewName(ApplicationUtil.JSP_URL+"error");
				}
			}
		}else {
			mv.setViewName(ApplicationUtil.JSP_URL+"error");
		}
		return mv;
	}
  	
}