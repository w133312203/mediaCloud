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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.hm.base.controller.BaseCotroller;
import com.hm.domain.Group;
import com.hm.domain.Property;
import com.hm.domain.Video;
import com.hm.service.EnterpriseUserInfoService;
import com.hm.service.EnterpriseUserPassportService;
import com.hm.service.GroupService;
import com.hm.service.PropertyService;
import com.hm.service.VideoService;
import com.hm.utils.ApplicationUtil;
import com.hm.utils.ImageUtil;
import com.hm.utils.StringUtil;
import com.hm.utils.VodUtil;

@Controller
@RequestMapping("/video")
public class VideoController extends BaseCotroller{ 
	
    @Resource  
    EnterpriseUserPassportService euserPassportService;
    
    @Resource  
    EnterpriseUserInfoService euserInfoService;
    
    @Resource  
    VideoService videoService;
    
    @Resource  
    GroupService groupService;
    
    @Resource  
    PropertyService propertyService;
    
    //视频页面
    @RequestMapping("/index/{groupId}")
  	public ModelAndView index(@PathVariable String groupId) {
    	ModelAndView mv = new ModelAndView();
    	if(StringUtil.isNumber(groupId)) {
    		Group group = groupService.findUpGroupById(0, Integer.parseInt(groupId));
    		if(group!=null) {
    			List<Map> groupList = groupService.findGroupByType(0, group.getType(), 2);
    	    	List<Property> propertyList = propertyService.listAll(0, Integer.parseInt(groupId), 1);
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
    	    	mv.setViewName(ApplicationUtil.JSP_URL+"video");
    		}else {
    			mv.setViewName(ApplicationUtil.JSP_URL+"error");
    		}
    	}else {
    		mv.setViewName(ApplicationUtil.JSP_URL+"error");
    	}
    	return mv;
  	}
    
    //视频列表数据
    @ResponseBody
	@RequestMapping("/list")
	public Map<String,Object> list() {
		String maxresult = request.getParameter("limit");
		String offset = request.getParameter("offset");
		String search = request.getParameter("search");
		String type = request.getParameter("type");
		if(StringUtil.isEmpty(search)) {
			search="";
		}else{
			search="%"+search+"%";
		}
		Integer groupId = null;
		if(StringUtil.isNumber(request.getParameter("groupId"))) {
			groupId = Integer.parseInt(request.getParameter("groupId"));
		}
		List<Map> videoList = videoService.list(0, Integer.parseInt(type), groupId, search, Integer.parseInt(offset), Integer.parseInt(maxresult));
		Integer count = videoService.count(0, Integer.parseInt(type), groupId, search);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("rows", videoList); 
		map.put("total", count);
		return map;
	}
    
    //获取点播上传地址并保存视频
    @ResponseBody
	@RequestMapping("/getUploadUrl")
	public Map<String,Object> getUploadUrl(Video video) {
    	//video.setPassportId(getSessionPassport().getId());
    	if(StringUtil.isEmpty(video.getTitle())) {
    		video.setTitle(video.getFileName().replace("."+video.getFormats(), ""));
    	}
    	videoService.save(video);
    	Map urlMap = new HashMap();
    	try {
    		urlMap = VodUtil.getUploadConf(video.getTitle(), video.getFileName());
        	video.setVideoId(urlMap.get("videoId").toString());
        	videoService.update(video);
        	urlMap.put("CODE", 10001);
        	urlMap.put("MSG", "SUCCESS");
    	}catch (Exception e) {
    		urlMap.put("CODE", -200);
    		urlMap.put("MSG", "EXCEPTION");
    		e.printStackTrace();
    	}
     	return urlMap;
	}
    
    //获取视频信息
    @ResponseBody
	@RequestMapping("/getVideoInfo")
	public Map<String,Object> getVideoInfo(String videoId) {
    	Map map = new HashMap();
    	if(!StringUtil.isEmpty(videoId)) {
    		Video video = videoService.getVodInfo(videoId);
    		map.put("CODE", 10001);
    		map.put("OBJ", video);
    		map.put("MSG", "SUCCESS");
    	}else {
    		map.put("CODE", -200);
    		map.put("MSG", "PARAM ERROR OR IS NULL");
    	}
    	return map;
	}
    
    //编辑视频信息
	@RequestMapping("/edit")
	public ModelAndView edit(Video video, MultipartFile imageFile) {
		ModelAndView mv = new ModelAndView();
    	if(video.getId()!=null) {
    		if(imageFile!=null&&!StringUtil.isEmpty(imageFile.getOriginalFilename())) {
    			video.setHeadImage(ImageUtil.uploadImage(imageFile));
    		}
    		video = videoService.updateVideoInfo(video);
    		mv.setViewName("redirect:/video/index/"+video.getGroupId());
    	}else {
    		mv.setViewName(ApplicationUtil.JSP_URL+"error");
    	}
    	return mv;
	}
	
	//删除视频信息
	@RequestMapping("/delete")
	public ModelAndView delete(Integer id) {
		ModelAndView mv = new ModelAndView();
		if(id!=null) {
			Video video = videoService.findById(id);
			if(video!=null) {
				mv.setViewName("redirect:/video/index/"+video.getGroupId());
				videoService.deleteById(video.getId());
			}else {
				mv.setViewName(ApplicationUtil.JSP_URL+"error");
			}
		}else {
			mv.setViewName(ApplicationUtil.JSP_URL+"error");
		}
    	return mv;
	}
  	
}