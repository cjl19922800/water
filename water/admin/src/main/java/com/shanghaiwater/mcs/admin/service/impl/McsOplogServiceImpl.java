package com.shanghaiwater.mcs.admin.service.impl;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shanghaiwater.mcs.db.entity.McsOplog;
import com.shanghaiwater.mcs.db.mapper.McsOplogMapper;
import com.shanghaiwater.mcs.admin.model.LogListResponse;
import com.shanghaiwater.mcs.admin.model.LogPageResponse;
import com.shanghaiwater.mcs.admin.service.McsOplogService;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author MCS
 * 
 */
@Service
public class McsOplogServiceImpl extends ServiceImpl<McsOplogMapper, McsOplog> implements McsOplogService {
	
	@Autowired
	private McsOplogMapper mcsOplogMapper;
	
	@Autowired
	private McsOplogService mcsOplogService;
	
	//获取日志详情
	public McsOplog detail(Map<String, Object> map) {
		
		QueryWrapper<McsOplog> queryWrapper = new QueryWrapper<McsOplog>();
		String id=(String) map.get("id");
		if(!StringUtils.isEmpty(id)) {
			queryWrapper
			.eq("id", id);
		}
		
		
		List<McsOplog> list=mcsOplogMapper.selectList(queryWrapper);
		
		
		
		return list.get(0);
		
	}
	
	
	 //根据事件id获取事件类型
	 public LogListResponse getEventTypeByEventId(Map<String,Object> map) {
	  
		 LogListResponse logListRespons=new LogListResponse();
		 QueryWrapper<McsOplog> queryWrapper=new QueryWrapper<McsOplog>();
	  
		 String eventId=(String) map.get("eventId");
	  
		 if(!StringUtils.isEmpty(eventId)) { 
			 
			 queryWrapper
			 .select("event_id","count(event_type) as num","event_type")
			 .eq("event_id", eventId).groupBy("event_id","event_type"); 
			 
		 }
		 
		 List<McsOplog> list=mcsOplogMapper.selectList(queryWrapper);
		 
		 logListRespons.setCode(0);
		 logListRespons.setTotal(list.size());
		 logListRespons.setCount(String.valueOf(list.size()));
		 logListRespons.setSuccess(true);
		 logListRespons.setData(list);
		 return logListRespons;
	  
	 }
	 
	
	
	 //分页查询 <T>
	 public LogPageResponse findByPage(Map<String,Object> map) {
		 int current=Integer.parseInt((String) map.get("page")); 
		 int limit=Integer.parseInt((String) map.get("limit")); 
		 Page<McsOplog> page=new Page<>(current,limit); 
		 LogPageResponse logPageRespons=new LogPageResponse();
		 QueryWrapper<McsOplog> queryWrapper=new QueryWrapper<McsOplog>();
		 
		 queryWrapper.select("EVENTID",
				 			"FIELD1",
				 			"ID",
				 			"FIELD2",
				 			"OPERATION",
				 			"FIELD6",
				 			"START_TIME",
				 			"END_TIME",
				 			"RESULT",
				 			"(SELECT ITEM_VALUE FROM SYS_DICT_ITEM WHERE DICT_CODE = 'McsEventType' AND EVENTID = ITEM_CODE AND STATUS = 'Enable') AS EVENT_NAME");
		 SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm");
		 String startTime=(String) map.get("startTime");
		 String endTime=(String)map.get("endTime");
		 String  result=(String)map.get("result");
	  
		 if(!StringUtils.isEmpty(result)) {
			 queryWrapper.eq("result",result);
		 }
		 if(!StringUtils.isEmpty(map.get("eventId"))) {
			 queryWrapper.eq("EVENT_ID",map.get("eventId"));
		 }
		 if(!StringUtils.isEmpty(map.get("field1"))) {
			 queryWrapper.eq("FIELD1",map.get("field1"));
		 }
		 if(!StringUtils.isEmpty(map.get("field2"))) {
			 queryWrapper.eq("FIELD2",map.get("field2"));
		 }
		 if(!StringUtils.isEmpty(map.get("operation"))) {
			 queryWrapper.eq("OPERATION",map.get("operation"));
		 }
		 if(!StringUtils.isEmpty(map.get("field3"))) {
			 queryWrapper.eq("FIELD6",map.get("field3"));
		 }
		 if(!StringUtils.isEmpty(startTime)&&!StringUtils.isEmpty(endTime)) {
			 try { 
					 queryWrapper.gt("START_TIME", df.parse(startTime));
					 queryWrapper.lt("END_TIME", df.parse(endTime));
				 
			 } catch (ParseException e) { 
				 e.printStackTrace(); 
			 }
		 }
		 queryWrapper.orderByDesc("START_TIME");
		 
		 
//		 queryWrapper.apply("rownum<1000");
	  
		 IPage<McsOplog> list=mcsOplogMapper.selectPage(page,queryWrapper);
		 logPageRespons.setCode(0); 
		 logPageRespons.setTotal(list.getSize());
		 logPageRespons.setCount(String.valueOf(list.getSize()));
		 logPageRespons.setSuccess(true); 
		 logPageRespons.setData(list);
		 return  logPageRespons;
	 
	  } 
	 
}
