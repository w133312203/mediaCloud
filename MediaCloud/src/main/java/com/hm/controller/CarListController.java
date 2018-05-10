package com.hm.controller;  

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;

import jxl.Sheet;
import jxl.Workbook;
import net.sf.json.JSON;
import net.sf.json.JSONSerializer;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.hm.base.controller.BaseCotroller;
import com.hm.domain.CarList;
import com.hm.service.CarListService;
import com.hm.service.EnterpriseUserInfoService;
import com.hm.service.EnterpriseUserPassportService;
import com.hm.utils.ApplicationUtil;
import com.hm.utils.StringUtil;

@Controller
@RequestMapping("/carList")
public class CarListController extends BaseCotroller{ 
	
    @Resource  
    EnterpriseUserPassportService euserPassportService;
    
    @Resource  
    EnterpriseUserInfoService euserInfoService;
    
    @Resource
    CarListService carListService;
    
    //用车车单页面
    @RequestMapping("/index")
  	public ModelAndView index() {
    	ModelAndView mv = new ModelAndView(ApplicationUtil.JSP_URL+"carList");
    	return mv;
  	}
    
    //车单列表数据
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
		List<CarList> carList = carListService.list(search, Integer.parseInt(offset), Integer.parseInt(maxresult));
		Integer count = carListService.count(search);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("rows", carList); 
		map.put("total", count);
		return map;
	}
    
    //保存车单
    @RequestMapping("/edit")
  	public ModelAndView edit(CarList carList) {
    	ModelAndView mv = new ModelAndView();
    	if(carList.getId()==null) {
    		carListService.save(carList);
    		mv.setViewName("redirect:/carList/index");
    	}else {
    		CarList oldCarList = carListService.findById(carList.getId());
    		if(oldCarList!=null) {
    			carListService.update(carList);
    			mv.setViewName("redirect:/carList/index");
    		}else {
    			mv.setViewName(ApplicationUtil.JSP_URL+"error");
    		}
    	}
    	return mv;
  	}
    
    //删除车单
    @RequestMapping("/delete")
  	public ModelAndView delete(Integer id) {
    	ModelAndView mv = new ModelAndView();
    	if(id!=null) {
    		CarList carList = carListService.findById(id);
    		if(carList!=null) {
    			mv.setViewName("redirect:/carList/index");
    			carListService.deleteById(carList.getId());
    		}else {
    			mv.setViewName(ApplicationUtil.JSP_URL+"error");
    		}
    	}else {
    		mv.setViewName(ApplicationUtil.JSP_URL+"error");
    	}
    	return mv;
  	}
    
    //导入
  	@RequestMapping(produces="text/html;charset=UTF-8",value="/importExcel")
  	@ResponseBody
  	public String importExcel(MultipartFile file) {
  		Integer count = 0;
  		List<Map<String,Object>> importFailureMap=new ArrayList<Map<String,Object>>();
  		if(file!=null){
  			try {
  				InputStream is = file.getInputStream();
  				jxl.Workbook rwb = Workbook.getWorkbook(is);
  				final Sheet[] sheets = rwb.getSheets();
  				for(int i=0;i<sheets.length;i++){
  					Sheet sheet = sheets[i];
  					int colums = sheet.getColumns();
  					int rows = sheet.getRows();
  					for (int j = 1; j < rows; j++) {
  						CarList carList = new CarList();
  		            	String[] fields = new String[colums];
  		            	for (int y = 0; y < colums; y++) { //遍历出一条用户的所有记录
  							fields[y] = sheet.getCell(y, j).getContents().trim();
  						}
  		            	carList.setModel(fields[0].trim());
  		            	carList.setVin(fields[1].trim());
  		            	carList.setComm(fields[2].trim());
  		            	carList.setColor(fields[3].trim());
  		            	carList.setIns(fields[4].trim());
  		            	carList.setOuts(fields[5].trim());
  		            	carList.setStatus(fields[6].trim());
  		            	carList.setLocation(fields[7].trim());
  		            	carListService.save(carList);
  		            	count++;
  					}
  				}
  			}catch (Exception e) {
  				count--;
  				e.printStackTrace();
  			}
  		} 
  		Map<String,Object> map = new HashMap<String, Object>();
  		map.put("rows", importFailureMap);
  		map.put("sucCount", count);
  		JSON object = JSONSerializer.toJSON(map);
  		return object.toString();
  	}
  	
}