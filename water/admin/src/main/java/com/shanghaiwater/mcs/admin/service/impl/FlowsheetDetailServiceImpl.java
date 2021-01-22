package com.shanghaiwater.mcs.admin.service.impl;

import java.text.SimpleDateFormat;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shanghaiwater.mcs.admin.model.FlowsheetDetailPageResponse;
import com.shanghaiwater.mcs.admin.service.FlowsheetDetailService;
import com.shanghaiwater.mcs.db.entity.AccFlowsheetDetail;
import com.shanghaiwater.mcs.db.mapper.AccFlowsheetDetailMapper;


@Service
public class FlowsheetDetailServiceImpl extends ServiceImpl<AccFlowsheetDetailMapper,AccFlowsheetDetail> implements FlowsheetDetailService{

	@Autowired
	private AccFlowsheetDetailMapper flowsheetDetailMapper;
	
	
	//查询失败的流水单
	public FlowsheetDetailPageResponse findFailed(Map<String,Object> map) {
		
		
		FlowsheetDetailPageResponse flowsheetDetailPageResponse=new FlowsheetDetailPageResponse();
		
		QueryWrapper queryWrapper=new QueryWrapper<AccFlowsheetDetail>();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-DD hh:mm");
		
		String startTime=(String) map.get("startTime");
		String endTime=(String) map.get("endTime");
		String payChannel=(String) map.get("payChannel");
		int current=(int) map.get("page");
		int  size=(int) map.get("limit");
		
		Page<AccFlowsheetDetail> page=new Page<>(current,size);
		
		if(!StringUtils.isEmpty(startTime)&&!StringUtils.isEmpty(endTime)) {
			try {
				
				queryWrapper.ge("CDATE", sdf.parse(startTime));
				queryWrapper.lt("CDATE", sdf.parse(endTime));
				
			} catch (java.text.ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(!StringUtils.isEmpty(payChannel)) {
			queryWrapper.eq("PAY_CHANNEL", payChannel);
		}
		
		queryWrapper.eq("STATUS", "支付失败");
		
		IPage<AccFlowsheetDetail> FlowsheetDetails=flowsheetDetailMapper.selectPage(page, queryWrapper);
		
		flowsheetDetailPageResponse.setCode(0);
		flowsheetDetailPageResponse.setData(FlowsheetDetails);
		flowsheetDetailPageResponse.setCount(String.valueOf(FlowsheetDetails.getSize()));
		flowsheetDetailPageResponse.setTotal(FlowsheetDetails.getSize());
		flowsheetDetailPageResponse.setSuccess(true);
		
		return flowsheetDetailPageResponse;
	}
	
	//获取流水单详情
	public AccFlowsheetDetail detail(Map<String,Object> map) {
		
		String flowsheetDetailId=(String) map.get("flowsheetDetailId");
		
		QueryWrapper queryWrapper=new QueryWrapper<AccFlowsheetDetail>();
		
		return  flowsheetDetailMapper.selectById(flowsheetDetailId);
	}
	
}
