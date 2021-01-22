package com.shanghaiwater.mcs.admin.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shanghaiwater.mcs.admin.model.AulogPageResponse;
import com.shanghaiwater.mcs.admin.model.AulogResponse;
import com.shanghaiwater.mcs.admin.service.McsAulogService;
import com.shanghaiwater.mcs.db.entity.McsAulog;
import com.shanghaiwater.mcs.db.mapper.McsAulogMapper;


@Service
public class McsAulogServiceImpl extends ServiceImpl<McsAulogMapper,McsAulog> implements McsAulogService{

	@Autowired
	private McsAulogMapper mcsAulogMapper;
	
	public AulogPageResponse getByPage(Map<String,Object> map) {
		
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-DD hh:mm");
		QueryWrapper<McsAulog> queryWrapper=new QueryWrapper<McsAulog>();
		AulogPageResponse aulogPageResponse=new AulogPageResponse();
		
		int page=Integer.parseInt((String) map.get("page"));
		int size=Integer.parseInt((String) map.get("limit"));
		Page<McsAulog> pages=new Page<>(page,size); 
		
		String startTime=(String) map.get("startTime");
		String endTime=(String) map.get("endTime");
		String result=(String) map.get("result");
		
		if(!StringUtils.isEmpty(result)) {
			queryWrapper.eq("result", result); 
		}
		
		if(!StringUtils.isEmpty(startTime)&&!StringUtils.isEmpty(endTime)) {
			try { 
				 queryWrapper.ge("start_time", df.parse(startTime));
				 queryWrapper.lt("end_time", df.parse(endTime));
			 
		 } catch (ParseException e) { // TODO Auto-generated catch block
			 e.printStackTrace(); 
		 }
			
		}
		
		/*
		 * if(!StringUtils.isEmpty(status)) { queryWrapper.eq("res", status); }
		 */
		queryWrapper.orderByDesc("start_time");
		IPage<McsAulog> mcsAulogs=mcsAulogMapper.selectPage(pages, queryWrapper);
		
		aulogPageResponse.setCode(0);
		aulogPageResponse.setCount(String.valueOf(mcsAulogs.getSize()));
		aulogPageResponse.setTotal(mcsAulogs.getSize());
		aulogPageResponse.setSuccess(true); 
		aulogPageResponse.setData(mcsAulogs);
		
		return aulogPageResponse;
	}
	
	public McsAulog findById(Map<String,Object> map) {
		AulogResponse aulogResponse = new AulogResponse();
		String id = (String) map.get("id");
		
		McsAulog mcsAulog=mcsAulogMapper.selectById(id);
		return mcsAulog;
		
		
	}
}
