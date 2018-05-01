package com.hm.controller;  

import java.util.ArrayList;
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
import com.hm.domain.Group;
import com.hm.service.EnterpriseUserInfoService;
import com.hm.service.EnterpriseUserPassportService;
import com.hm.service.GroupService;
import com.hm.utils.ApplicationUtil;
import com.hm.utils.StringUtil;

@Controller
@RequestMapping("/group")
public class GroupController extends BaseCotroller{ 
	
    @Resource  
    EnterpriseUserPassportService euserPassportService;
    
    @Resource  
    EnterpriseUserInfoService euserInfoService;
    
    @Resource
    GroupService groupService;
    
    //组别页面
    @RequestMapping("/index/{groupId}")
  	public ModelAndView index(@PathVariable String groupId) {
    	ModelAndView mv = new ModelAndView();
    	if(StringUtil.isNumber(groupId)) {
    		Group group = groupService.findById(Integer.parseInt(groupId));
    		if(group!=null) {
    			List<Map> groupList = groupService.findGroupByType(0, group.getType());
    	    	List<Map> newGroupList = new ArrayList<Map>();
    	    	Map groupMap = new HashMap();
    	    	for(Map map:groupList) {
    	    		groupMap.put(map.get("id").toString(), map);
    	    	}
    	    	Map flagGroup = new HashMap();
    	    	flagGroup.put("id", group.getId());
    	    	flagGroup.put("title", group.getTitle());
    	    	flagGroup.put("groupId", group.getGroupId());
    	    	while(flagGroup!=null) {
    	    		newGroupList.add(0, flagGroup);
    	    		Map map = (Map) groupMap.get(flagGroup.get("groupId").toString());
    	    		flagGroup = map;
    	    	}
    	    	mv.addObject("groupList",newGroupList);
    	    	mv.addObject("group",group);
    			mv.setViewName(ApplicationUtil.JSP_URL+"group");
    		}else {
    			mv.setViewName(ApplicationUtil.JSP_URL+"error");
    		}
    	}else {
    		mv.setViewName(ApplicationUtil.JSP_URL+"error");
    	}
    	return mv;
  	}
    
    //组别列表数据
    @ResponseBody
	@RequestMapping("/list")
	public Map<String,Object> list() {
		String maxresult = request.getParameter("limit");
		String offset = request.getParameter("offset");
		String search = request.getParameter("search");
		String id = request.getParameter("id");
		if(StringUtil.isEmpty(search)) {
			search="";
		}else{
			search="%"+search+"%";
		}
		List<Map> groupList = groupService.list(0, Integer.parseInt(id), search, Integer.parseInt(offset), Integer.parseInt(maxresult));
		Integer count = groupService.count(0,  Integer.parseInt(id), search);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("rows", groupList); 
		map.put("total", count);
		return map;
	}
    
    //保存组别
    @RequestMapping("/edit")
  	public ModelAndView edit(Group group) {
    	ModelAndView mv = new ModelAndView();
    	if(group.getId()==null) {
    		//group.setPassportId(passportId);
    		group.setHierarchy(group.getHierarchy()+1);
    		if(group.getLevel()==null) {
    			group.setLevel(0);
    		}
    		groupService.save(group);
    		mv.setViewName("redirect:/group/index/"+group.getGroupId());
    	}else {
    		Group oldGroup = groupService.findById(group.getId());
    		if(oldGroup!=null&&!StringUtil.isEmpty(group.getTitle())) {
    			oldGroup.setTitle(group.getTitle());
    			if(group.getLevel()!=null) {
    				oldGroup.setLevel(group.getLevel());
    			}
    			if(group.getUploadType()!=null) {
    				oldGroup.setUploadType(group.getUploadType());
    			}else {
    				oldGroup.setUploadType(0);
    			}
    			groupService.update(oldGroup);
    			mv.setViewName("redirect:/group/index/"+group.getGroupId());
    		}else {
    			mv.setViewName(ApplicationUtil.JSP_URL+"error");
    		}
    	}
    	return mv;
  	}
    
    //删除组别
    @RequestMapping("/delete")
  	public ModelAndView delete(Integer id) {
    	ModelAndView mv = new ModelAndView();
    	if(id!=null) {
    		Group group = groupService.findById(id);
    		if(group!=null) {
    			mv.setViewName("redirect:/group/index/"+group.getGroupId());
    			groupService.deleteById(group.getId());
    		}else {
    			mv.setViewName(ApplicationUtil.JSP_URL+"error");
    		}
    	}else {
    		mv.setViewName(ApplicationUtil.JSP_URL+"error");
    	}
    	return mv;
  	}
	
  	
}