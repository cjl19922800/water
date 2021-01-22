package com.shanghaiwater.mcs.admin.controller;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shanghaiwater.mcs.admin.model.SearchDbResponse;
import com.shanghaiwater.mcs.admin.service.SearchDbService;


@RequestMapping("/right/db")
@RestController
public class SearchDbController {
	
	@Autowired
	private SearchDbService searchDb;
	
	
	@RequestMapping(value="/listview",method = RequestMethod.GET)
	public ModelAndView ListView(Model model) {
		
		return new ModelAndView("views/right/db/db", "Model", model);
	}
	
	
	@RequestMapping(value="/query",method = RequestMethod.GET)
	public SearchDbResponse DataQuery(@RequestParam(value ="sql", required = true) String sql,
			@RequestParam(value="name",required = true) String name,
			@RequestParam(value="pass",required = true) String pass,
			@RequestParam(value="page",defaultValue="1") int page,
			@RequestParam(value="limit",defaultValue="10") int limit){
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("page", page);
		map.put("limit", limit);
		map.put("sql", sql);
		map.put("name", name);
		map.put("pass", pass);
		
		System.out.println("进入了controller");
		return searchDb.search(map);
		
	}
	
	

}
