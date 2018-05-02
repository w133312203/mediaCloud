package com.hm.controller;  
  
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hm.base.controller.BaseCotroller;
import com.hm.domain.EnterpriseUserInfo;
import com.hm.domain.EnterpriseUserPassport;
import com.hm.service.EnterpriseUserInfoService;
import com.hm.service.EnterpriseUserPassportService;
import com.hm.utils.PSMD5;
import com.hm.utils.StringUtil;

@Controller
public class LoginController extends BaseCotroller{ 
	
    @Resource  
    private EnterpriseUserPassportService euserPassportService;
    
    @Resource  
    private EnterpriseUserInfoService euserInfoService;
	
    //登录
    @RequestMapping("/login")
  	public ModelAndView login() {
    	if(getSessionPassport()!=null) {
    		EnterpriseUserInfo ui = euserInfoService.findEnterpriseUserInfoByPassportId(getSessionPassport().getId());
    		ui.setLastLoginTime(new Date());
    		euserInfoService.update(ui);
    		return new ModelAndView("redirect:/picture/index/1");
		}else {
			return new ModelAndView("/login/login");
		}
  	}
    
    //登出
    @ResponseBody
    @RequestMapping("/logout")
    public ModelAndView logout(HttpSession httpSession, HttpServletResponse response) {
    	request.getSession().invalidate();
    	request.getSession().removeAttribute("euserPassport");
    	try {
    		response.sendRedirect("redirect:/login");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return null;
    }
    
    //查找企业用户是否存在
    @ResponseBody
    @RequestMapping(value = "/checkPassport",method = RequestMethod.POST, produces="application/json")
	public Map<String, Object> checkPassport(@RequestParam String passport, @RequestParam String password, HttpSession httpSession) {
    	Map<String, Object> map=new HashMap<String, Object>();
    	if(!StringUtil.isEmpty(passport)&&!StringUtil.isEmpty(password)) {
    		EnterpriseUserPassport euserPassport = euserPassportService.findEnterpriseUserPassport(passport, passport, PSMD5.MD5Encode(password));
    		if(euserPassport!=null) {
    			EnterpriseUserInfo euserInfo = euserInfoService.updateLastLoginTime(euserPassport.getId());
				httpSession.setAttribute("euserPassport", euserPassport);
				httpSession.setAttribute("euserInfo", euserInfo);
				map.put("CODE", 10001);
				map.put("MSG", "SUCCESS");
			}else {
				map.put("CODE", -200);
				map.put("MSG", "NO PASSPORT");
			}
    	}else {
    		map.put("CODE", -201);
    		map.put("MSG", "PARAM IS ERROR OR NULL");
    	}
    	return map;
	}
  	
}