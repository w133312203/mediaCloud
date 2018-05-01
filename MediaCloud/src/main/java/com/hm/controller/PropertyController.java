package com.hm.controller;  

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hm.base.controller.BaseCotroller;
import com.hm.domain.Property;
import com.hm.service.EnterpriseUserInfoService;
import com.hm.service.EnterpriseUserPassportService;
import com.hm.service.PropertyService;
import com.hm.utils.ApplicationUtil;
import com.hm.utils.StringUtil;

@Controller
@RequestMapping("/property")
public class PropertyController extends BaseCotroller{ 
	
    @Resource  
    EnterpriseUserPassportService euserPassportService;
    
    @Resource  
    EnterpriseUserInfoService euserInfoService;
    
    @Resource
    PropertyService propertyService;
    
    //属性页面
    @RequestMapping("/index/{groupId}/{type}")
  	public ModelAndView index(@PathVariable String groupId, @PathVariable String type) {
    	ModelAndView mv = new ModelAndView();
    	if(StringUtil.isNumber(groupId)&&StringUtil.isNumber(type)) {
    		mv.addObject("type",type);
        	mv.addObject("groupId",groupId);
        	mv.setViewName(ApplicationUtil.JSP_URL+"property");
    	}else {
    		mv.setViewName(ApplicationUtil.JSP_URL+"error");
    	}
    	return mv;
  	}
    
    //属性列表数据
    @ResponseBody
	@RequestMapping("/list")
	public Map<String,Object> list() {
		String maxresult = request.getParameter("limit");
		String offset = request.getParameter("offset");
		String search = request.getParameter("search");
		String groupId = request.getParameter("groupId");
		String type = request.getParameter("type");
		if(StringUtil.isEmpty(search)) {
			search="";
		}else{
			search="%"+search+"%";
		}
		List<Property> groupList = propertyService.list(0, Integer.parseInt(groupId), Integer.parseInt(type), search, Integer.parseInt(offset), Integer.parseInt(maxresult));
		Integer count = propertyService.count(0,  Integer.parseInt(groupId), Integer.parseInt(type), search);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("rows", groupList); 
		map.put("total", count);
		return map;
	}
    
    //保存属性
    @RequestMapping("/edit")
  	public ModelAndView edit(Property property) {
    	ModelAndView mv = new ModelAndView();
    	if(property.getId()==null) {
    		//property.setPassportId(passportId);
    		if(property.getLevel()==null) {
    			property.setLevel(0);
    		}
    		propertyService.save(property);
    		mv.setViewName("redirect:/property/index/"+property.getGroupId()+"/"+property.getType());
    	}else {
    		Property oldProperty = propertyService.findById(property.getId());
    		if(oldProperty!=null&&!StringUtil.isEmpty(property.getTitle())) {
    			oldProperty.setTitle(property.getTitle());
    			if(property.getLevel()!=null) {
    				oldProperty.setLevel(property.getLevel());
    			}
    			propertyService.update(oldProperty);
    			mv.setViewName("redirect:/property/index/"+property.getGroupId()+"/"+property.getType());
    		}else {
    			mv.setViewName(ApplicationUtil.JSP_URL+"error");
    		}
    	}
    	return mv;
  	}
    
    //删除属性
    @RequestMapping("/delete")
  	public ModelAndView deleteGroup(Integer id) {
    	ModelAndView mv = new ModelAndView();
    	if(id!=null) {
    		Property property = propertyService.findById(id);
    		if(property!=null) {
    			mv.setViewName("redirect:/property/index/"+property.getGroupId()+"/"+property.getType());
    			propertyService.deleteById(property.getId());
    		}else {
    			mv.setViewName(ApplicationUtil.JSP_URL+"error");
    		}
    	}else {
    		mv.setViewName(ApplicationUtil.JSP_URL+"error");
    	}
    	return mv;
  	}
	
  	
}