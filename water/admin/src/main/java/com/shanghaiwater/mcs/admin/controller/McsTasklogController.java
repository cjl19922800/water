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
import com.shanghaiwater.mcs.admin.model.McsTasklogPageResponse;
import com.shanghaiwater.mcs.admin.service.McsTasklogService;
import com.shanghaiwater.mcs.db.entity.McsTasklog;

@RestController
@RequestMapping("right/taskLog")
@PermissionIntercept
public class McsTasklogController {
	
	
	@Autowired
	private McsTasklogService mcsTasklogService;
	
	
	@RequestMapping(value="/listview",method = RequestMethod.GET)
	public ModelAndView ListView(Model model) {
		
		return new ModelAndView("views/right/task/task", "Model", model);
	}
	
	@RequestMapping(value="/getTask",method=RequestMethod.GET)
	public McsTasklogPageResponse getTask(@RequestParam(value="eventid",required=false)String eventId,
			@RequestParam(value="optarget",required=false)String optarget,
			@RequestParam(value="result",required=false)String result,
			@RequestParam(value="startTime",required=false)String startTime,
			@RequestParam(value="endTime",required=false)String endTime,
			@RequestParam(value="page",defaultValue="1")String page,
			@RequestParam(value="limit",defaultValue="10")String limit) {
		
		Map<String,Object> map= new HashMap();
		map.put("eventId", eventId);
		map.put("optarget", optarget);
		map.put("result", result);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("page", page);
		map.put("limit", limit);
		
		
		return mcsTasklogService.getTask(map);
	}

	@RequestMapping(value="/datail",method=RequestMethod.GET)
	public ModelAndView datail(@RequestParam(value="taskId")String taskId,Model model) {
		
		Map<String,Object> map=new HashMap();
		map.put("taskId", taskId);
		
		model.addAttribute("task", mcsTasklogService.detail(map));
		
		return new ModelAndView("views/right/task/taskform","model",model);
	}
	
}
