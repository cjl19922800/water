package com.shanghaiwater.mcs.admin.service.impl;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shanghaiwater.mcs.admin.model.McsTasklogPageResponse;
import com.shanghaiwater.mcs.admin.service.McsTasklogService;
import com.shanghaiwater.mcs.db.entity.McsAulog;
import com.shanghaiwater.mcs.db.entity.McsOplog;
import com.shanghaiwater.mcs.db.entity.McsTasklog;
import com.shanghaiwater.mcs.db.mapper.McsTasklogMapper;

@Service
public class McsTasklogServiceImpl extends ServiceImpl<McsTasklogMapper,McsTasklog> implements McsTasklogService{

	@Autowired
	private McsTasklogMapper mcsTasklogMapper;
	
	//查询task日志
	public McsTasklogPageResponse getTask(Map<String,Object> map) {
		
		QueryWrapper<McsTasklog> queryWrapper=new QueryWrapper<McsTasklog>();
		McsTasklogPageResponse mcsTasklogPageResponse=new McsTasklogPageResponse();
		SimpleDateFormat df=new SimpleDateFormat("yyyy:MM:dd hh:mm");
		
		String eventId=(String) map.get("eventId");
		String optarget=(String) map.get("optarget");
		String result=(String) map.get("result");
		String startTime=(String) map.get("startTime");
		String endTime=(String) map.get("endTime");
	    Integer current=Integer.parseInt((String)map.get("page"));
	    Integer size=Integer.parseInt((String)map.get("limit"));
		
		Page<McsTasklog> page=new Page<>(current,size);
		
		if(!StringUtils.isEmpty(eventId)) {
			queryWrapper.eq("EVENTID", eventId);
		}
		
		if(!StringUtils.isEmpty(optarget)) {
			queryWrapper.eq("OPTARGET", optarget);
		}
		
		if(!StringUtils.isEmpty(result)) {
			queryWrapper.eq("RESULT", result);
		}
		
		if(!StringUtils.isEmpty(startTime)&&!StringUtils.isEmpty(endTime)) {
			try {
				queryWrapper.ge("START_TIME", df.parse(startTime));
				queryWrapper.lt("START_TIME", df.parse(endTime));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		IPage<McsTasklog> data=mcsTasklogMapper.selectPage(page, queryWrapper);
		
		 mcsTasklogPageResponse.setCode(0); mcsTasklogPageResponse.setData(data);
		 mcsTasklogPageResponse.setSuccess(true);
		 mcsTasklogPageResponse.setCount(String.valueOf(data.getSize()));
		 mcsTasklogPageResponse.setTotal(data.getSize());
		 
		return mcsTasklogPageResponse;
			
	}
	
	//获取task日志详情
	public McsTasklog detail(Map<String,Object> map) {
		
		String taskId=(String) map.get("taskId");
		
		return mcsTasklogMapper.selectById(taskId);
	}
	
	
}
