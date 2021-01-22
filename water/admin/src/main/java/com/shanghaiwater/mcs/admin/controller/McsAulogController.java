package com.shanghaiwater.mcs.admin.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.shanghaiwater.mcs.admin.annotation.PermissionIntercept;
import com.shanghaiwater.mcs.admin.model.AulogPageResponse;
import com.shanghaiwater.mcs.admin.service.McsAulogService;
import com.shanghaiwater.mcs.db.entity.McsAulog;

@RestController
@RequestMapping("/right/aulog")
@PermissionIntercept
public class McsAulogController {
	
	@Autowired
	private McsAulogService mcsAulogService;
	
	@RequestMapping(value="/listview",method = RequestMethod.GET)
	public ModelAndView listView(Model model) {
		
		return new ModelAndView("views/right/aulog/aulog","Model", model);
	}
	
	
	@RequestMapping(value="/getByPage",method=RequestMethod.GET)
	public AulogPageResponse getbyPage(@RequestParam(value="startTime",required=false) String startTime,
			@RequestParam(value="endTime",required=false)String endTime,
			@RequestParam(value="result",required=false)String result,
			@RequestParam(value="page",required=false,defaultValue="1")String page,
			@RequestParam(value="limit",required=false,defaultValue="10")String limit) {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("result", result);
		map.put("page", page);
		map.put("limit", limit);
		return mcsAulogService.getByPage(map);
		
	}
	
	@RequestMapping(value="/detail",method=RequestMethod.GET)
	public ModelAndView detail(@RequestParam(value="id",required=true)String id,Model model) {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("id", id);
		McsAulog mcsAulog = mcsAulogService.findById(map);
		model.addAttribute("aulog",mcsAulog);
		return new ModelAndView("views/right/aulog/aulogform","model",model);
	}

}
