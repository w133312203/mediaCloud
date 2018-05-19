package com.hm.api;  

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hm.base.controller.BaseCotroller;
import com.hm.domain.Materiel;
import com.hm.service.MaterielService;

@Controller
@RequestMapping("/api")
public class MaterielAPI extends BaseCotroller{
    
    @Resource  
    MaterielService materielService;
    
    //物料接口
    @ResponseBody
	@RequestMapping("/materiel")
	public Map<String,Object> materiel(Integer first, Integer max, String search) {
    	Map map = new HashMap();
    	List<Materiel> list = materielService.list(search, first, max);
    	map.put("CODE", 10001);
		map.put("list", list);
		map.put("MSG", "SUCCESS");
    	return map;
	}
}