package com.hm.api;  

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hm.base.controller.BaseCotroller;
import com.hm.domain.CarList;
import com.hm.service.CarListService;

@Controller
@RequestMapping("/api")
public class CarListAPI extends BaseCotroller{
    
    @Resource  
    CarListService carListService;
    
    //用车车单接口
    @ResponseBody
	@RequestMapping("/carList")
	public Map<String,Object> carList(Integer first, Integer max, String search) {
    	Map map = new HashMap();
    	List<CarList> list = carListService.list(search, first, max);
    	map.put("CODE", 10001);
		map.put("list", list);
		map.put("MSG", "SUCCESS");
    	return map;
	}
}