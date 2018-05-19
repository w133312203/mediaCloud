package com.hm.controller;  

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import jxl.Sheet;
import jxl.Workbook;
import net.sf.json.JSON;
import net.sf.json.JSONSerializer;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.hm.base.controller.BaseCotroller;
import com.hm.domain.Materiel;
import com.hm.service.EnterpriseUserInfoService;
import com.hm.service.EnterpriseUserPassportService;
import com.hm.service.MaterielService;
import com.hm.utils.ApplicationUtil;
import com.hm.utils.ImageUtil;
import com.hm.utils.StringUtil;

@Controller
@RequestMapping("/materiel")
public class MaterielController extends BaseCotroller{ 
	
    @Resource  
    EnterpriseUserPassportService euserPassportService;
    
    @Resource  
    EnterpriseUserInfoService euserInfoService;
    
    @Resource
    MaterielService materielService;
    
    //物料页面
    @RequestMapping("/index")
  	public ModelAndView index() {
    	ModelAndView mv = new ModelAndView(ApplicationUtil.JSP_URL+"materiel");
    	return mv;
  	}
    
    //物料列表数据
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
		List<Materiel> materiel = materielService.list(search, Integer.parseInt(offset), Integer.parseInt(maxresult));
		Integer count = materielService.count(search);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("rows", materiel); 
		map.put("total", count);
		return map;
	}
    
    //保存物料
    @RequestMapping("/edit")
  	public ModelAndView edit(@RequestParam MultipartFile file, Materiel materiel) {
    	ModelAndView mv = new ModelAndView();
    	if(file!=null&&!StringUtil.isEmpty(file.getOriginalFilename())) {
    		materiel.setImageUrl(ImageUtil.uploadImage(file));
    	}
    	if(materiel.getId()==null) {
    		materielService.save(materiel);
    		mv.setViewName("redirect:/materiel/index");
    	}else {
    		Materiel oldMateriel = materielService.findById(materiel.getId());
    		if(oldMateriel!=null) {
    			materielService.update(materiel);
    			mv.setViewName("redirect:/materiel/index");
    		}else {
    			mv.setViewName(ApplicationUtil.JSP_URL+"error");
    		}
    	}
    	return mv;
  	}
    
    //删除物料
    @RequestMapping("/delete")
  	public ModelAndView delete(Integer id) {
    	ModelAndView mv = new ModelAndView();
    	if(id!=null) {
    		Materiel materiel = materielService.findById(id);
    		if(materiel!=null) {
    			mv.setViewName("redirect:/materiel/index");
    			materielService.deleteById(materiel.getId());
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
  						Materiel materiel = new Materiel();
  		            	String[] fields = new String[colums];
  		            	for (int y = 0; y < colums; y++) { //遍历出一条用户的所有记录
  							fields[y] = sheet.getCell(y, j).getContents().trim();
  						}
  		            	materiel.setItems(fields[0].trim());
  		            	materiel.setNums(fields[1].trim());
  		            	materiel.setBz(fields[2].trim());
  		            	materielService.save(materiel);
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