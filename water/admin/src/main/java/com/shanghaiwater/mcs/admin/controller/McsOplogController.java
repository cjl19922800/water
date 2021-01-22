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
import com.shanghaiwater.mcs.admin.enums.OperationEnums;
import com.shanghaiwater.mcs.admin.model.LogListResponse;
import com.shanghaiwater.mcs.admin.model.LogPageResponse;
import com.shanghaiwater.mcs.admin.service.McsOplogService;
import com.shanghaiwater.mcs.db.entity.McsOplog;

@RequestMapping("/right/log")
@RestController
@PermissionIntercept
public class McsOplogController {
	
	@Autowired
	private McsOplogService mcsOplogService;
	
	@RequestMapping(value="/listview",method = RequestMethod.GET)
	public ModelAndView ListView(Model model) {
		return new ModelAndView("views/right/log/log", "Model", model);
	}
	
	//获取日志详情列表
	@RequestMapping(value="/detail",method=RequestMethod.GET)
	public ModelAndView detail(@RequestParam(value="id",required=false)String id,Model model) {
		Map<String,Object> map=new HashMap<>();
		map.put("id", id);
		McsOplog log = mcsOplogService.detail(map);
		model.addAttribute("operationName", OperationEnums.getOperationNameByCode(log.getOperation()));
		model.addAttribute("log",log);
		return new ModelAndView("views/right/log/logform","model",model);
	}
	
	//根据此事件id获取事件类型
	@RequestMapping(value="/getEventTypeByEventId",method=RequestMethod.GET)
	public LogListResponse getEventTypeByEventId(
			@RequestParam(value="eventId",required=false) String eventId) {
		Map<String,Object> map=new HashMap<>();
		map.put("eventId", eventId);
		return mcsOplogService.getEventTypeByEventId(map);
	}
	
	//分页查询
	@RequestMapping(value="/findByPage")
	public LogPageResponse findByPage(
		@RequestParam(defaultValue = "1")String page,
		@RequestParam(defaultValue = "10")String limit,
		@RequestParam(value="result",required=false) String result,
		@RequestParam(value="eventId",required=false) String eventId,
		@RequestParam(value="operation",required=false) String operation,
		@RequestParam(value="field1",required=false) String field1,
		@RequestParam(value="field2",required=false) String field2,
		@RequestParam(value="field3",required=false) String field3,
		@RequestParam(value ="startTime", required = false) String startTime,
		@RequestParam(value ="endTime", required = false) String endTime,Model model){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("page", page);
		map.put("operation", operation);
		map.put("eventId", eventId);
		map.put("field1", field1);
		map.put("field2", field2);
		map.put("field3", field3);
		map.put("limit", limit);
        map.put("result", result);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		return mcsOplogService.findByPage(map);
	}



}
