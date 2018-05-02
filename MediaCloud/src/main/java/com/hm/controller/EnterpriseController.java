package com.hm.controller;  

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hm.base.controller.BaseCotroller;
import com.hm.domain.EnterpriseUserInfo;
import com.hm.domain.EnterpriseUserPassport;
import com.hm.service.EnterpriseUserInfoService;
import com.hm.service.EnterpriseUserPassportService;
import com.hm.utils.ApplicationUtil;
import com.hm.utils.PSMD5;
import com.hm.utils.StringUtil;

@Controller
@RequestMapping("/enterprise")
public class EnterpriseController extends BaseCotroller{ 
	
    @Resource  
    EnterpriseUserPassportService euserPassportService;
    
    @Resource  
    EnterpriseUserInfoService euserInfoService;
    
    //企业用户页面
    @RequestMapping("/index")
  	public ModelAndView index() {
    	ModelAndView mv = new ModelAndView(ApplicationUtil.JSP_URL+"enterpriseUser");
    	return mv;
  	}
    
    //列表数据
    @ResponseBody
	@RequestMapping("/list")
	public Map<String,Object> list() {
		String maxresult = request.getParameter("limit");
		String offset = request.getParameter("offset");
		String search = request.getParameter("search");
		if(StringUtil.isEmpty(search)) {
			search="";
		}else{
			search="%"+search+"%";
		}
		List<Map> list = euserPassportService.list(search, Integer.parseInt(offset), Integer.parseInt(maxresult));
		Integer count = euserPassportService.count(search);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("rows", list); 
		map.put("total", count);
		return map;
	}
    
    //保存企业用户
    @RequestMapping("/edit")
  	public ModelAndView edit(EnterpriseUserPassport ep, EnterpriseUserInfo ui) {
    	ModelAndView mv = new ModelAndView();
    	if(ep.getId()==null) {
    		euserPassportService.save(ep);
    		ui.setPassportId(ep.getId());
    		euserInfoService.save(ui);
    	}else {
    		EnterpriseUserPassport oldPassport = euserPassportService.findById(ep.getId());
    		if(!StringUtil.isEmpty(ep.getEmail())) {
    			oldPassport.setEmail(ep.getEmail());
    		}
    		if(!StringUtil.isEmpty(ep.getMobileNum())) {
    			oldPassport.setMobileNum(ep.getMobileNum());
    		}
    		if(!StringUtil.isEmpty(ep.getPassword())) {
    			oldPassport.setPassword(PSMD5.MD5Encode(ep.getPassword()));
    		}
    		if(ep.getType()!=null) {
    			oldPassport.setType(ep.getType());
    		}
    		euserPassportService.update(oldPassport);
    		EnterpriseUserInfo oldInfo = euserInfoService.findEnterpriseUserInfoByPassportId(oldPassport.getId());
    		if(!StringUtil.isEmpty(ui.getCompanyName())) {
    			oldInfo.setCompanyName(ui.getCompanyName());
    		}
    		if(!StringUtil.isEmpty(ui.getPosition())) {
    			oldInfo.setPosition(ui.getPosition());
    		}
    		
    		if(!StringUtil.isEmpty(ui.getRealName())) {
    			oldInfo.setRealName(ui.getRealName());
    		}
    		euserInfoService.update(oldInfo);
    	}
    	mv.setViewName("redirect:/enterprise/index");
    	return mv;
  	}
    
    //删除企业用户
    @RequestMapping("/delete")
  	public ModelAndView delete(Integer id) {
    	ModelAndView mv = new ModelAndView();
    	if(id!=null) {
    		EnterpriseUserPassport passport = euserPassportService.findById(id);
    		if(passport!=null) {
    			mv.setViewName("redirect:/enterprise/index");
    			euserPassportService.deleteById(passport.getId());
    			euserInfoService.deleteByPassportId(passport.getId());
    		}else {
    			mv.setViewName(ApplicationUtil.JSP_URL+"error");
    		}
    	}else {
    		mv.setViewName(ApplicationUtil.JSP_URL+"error");
    	}
    	return mv;
  	}
    
    //更新企业用户状态
    @RequestMapping("/changeStatus")
  	public ModelAndView changeStatus(Integer id) {
    	ModelAndView mv = new ModelAndView();
    	if(id!=null) {
    		EnterpriseUserPassport passport = euserPassportService.findById(id);
    		if(passport!=null) {
    			if(passport.getStatus()==null||passport.getStatus()==1) {
    				euserPassportService.updateStatus(0, passport.getId());
    			}else {
    				euserPassportService.updateStatus(1, passport.getId());
    			}
    			mv.setViewName("redirect:/enterprise/index");
    		}else {
    			mv.setViewName(ApplicationUtil.JSP_URL+"error");
    		}
    	}else {
    		mv.setViewName(ApplicationUtil.JSP_URL+"error");
    	}
    	return mv;
  	}
    
    //查找企业账户是否存在
    @ResponseBody
    @RequestMapping("/checkPassport")
	public Map<String, Object> checkPassport(String passport, Integer id, Integer type) {
    	Map<String, Object> map=new HashMap<String, Object>();
    	if(!StringUtil.isEmpty(passport)&&type!=null) {
    		List<EnterpriseUserPassport> passportList = new ArrayList<EnterpriseUserPassport>();
    		if(type==0) {
    			passportList = euserPassportService.checkEmail(passport, id);
        		if(passportList.size()>0) {
        			map.put("CODE", -200);
    				map.put("MSG", "IS EXISTS");
    			}else {
    				map.put("CODE", 10001);
					map.put("MSG", "NO EXISTS");
    			}
    		}else if(type==1){
    			passportList = euserPassportService.checkMobileNum(passport, id);
    			if(passportList.size()>0) {
        			map.put("CODE", -200);
    				map.put("MSG", "IS EXISTS");
    			}else {
    				map.put("CODE", 10001);
					map.put("MSG", "NO EXISTS");
    			}
    		}else {
    			map.put("CODE", -201);
        		map.put("MSG", "PARAM IS ERROR OR NULL");
    		}
    	}else {
    		map.put("CODE", -201);
    		map.put("MSG", "PARAM IS ERROR OR NULL");
    	}
    	return map;
	}
	
}