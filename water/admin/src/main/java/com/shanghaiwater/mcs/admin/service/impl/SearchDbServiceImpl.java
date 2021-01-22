package com.shanghaiwater.mcs.admin.service.impl;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.shanghaiwater.base.webapi.util.JdbcUtil;
import com.shanghaiwater.mcs.admin.model.SearchDbResponse;
import com.shanghaiwater.mcs.admin.service.SearchDbService;

@Service
public class SearchDbServiceImpl implements SearchDbService{
	
	
	public SearchDbResponse search(Map<String,Object> map) {
		
		int total=0;
		String sql=(String) map.get("sql");
		Integer page=(Integer) map.get("page");
		System.out.println("当前页"+page);
		Integer limit=(Integer) map.get("limit");
		System.out.println("每页数量"+limit);
		String name=(String) map.get("name");
		System.out.println("用户名"+name);
		String pass=(String) map.get("pass");
		System.out.println("密码"+pass);
		SearchDbResponse searchDbResponse=new SearchDbResponse();
		ArrayList<Map<String,String>> data=null; 
		List<String> cols=new ArrayList<>();
		List<Map<String,String>> nList=null;
		
		try {
			JdbcUtil  db=new JdbcUtil();
			data =db.dataQuery(sql,page,limit,name,pass);
			//获取总数量
			total=data.size();
			nList=data.subList(limit*(page-1), ((limit*page)>total?total:(limit*page)));
			Set set = data.get(0).keySet();
			Iterator iter = set.iterator();	
			while (iter.hasNext()) {
				String key = (String) iter.next();
				cols.add(key);
			}	
			
			System.out.println("表头为----"+cols);
			
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		searchDbResponse.setCode(0);
		searchDbResponse.setSuccess(true);
		searchDbResponse.setCols(cols);
		searchDbResponse.setData(nList);
		searchDbResponse.setCount(total);
		
		
		return searchDbResponse;
		
	}
	


}
