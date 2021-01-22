package com.shanghaiwater.mcs.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shanghaiwater.mcs.admin.annotation.PermissionIntercept;
import com.shanghaiwater.mcs.admin.model.CommonPageReponse;
import com.shanghaiwater.mcs.db.entity.AccDiffDuizhangDetail;
import com.shanghaiwater.mcs.db.entity.AccFlowsheetDetail;
import com.shanghaiwater.mcs.db.mapper.AccDiffDuizhangDetailMapper;
import com.shanghaiwater.mcs.db.mapper.AccFlowsheetDetailMapper;


@RestController
@RequestMapping("/reconciliation")
@PermissionIntercept
public class ReconciliationController {

	@Autowired
	private AccFlowsheetDetailMapper flowsheetDetailMapper;
	
	@Autowired
	private AccDiffDuizhangDetailMapper accDiffDuizhangDetailMapper;
	
	//对账
	@RequestMapping(value = "/duizhang/index", method = RequestMethod.GET)
	public ModelAndView duizhangView(Model model) {
		return new ModelAndView("views/reconciliation/duizhang/duizhangIndex", "model", model);
	}
	
	//差异对账
	@RequestMapping(value = "/diff/index", method = RequestMethod.GET)
	public ModelAndView diffduizhangView(Model model) {
		return new ModelAndView("views/reconciliation/diff/diffIndex", "model", model);
	}
	
	//回盘
	@RequestMapping(value = "/counteroffer/index", method = RequestMethod.GET)
	public ModelAndView huipanView(Model model) {
		return new ModelAndView("views/reconciliation/counteroffer/huipanIndex", "model", model);
	}
	
	//订单
	@RequestMapping(value = "/collectfees/index", method = RequestMethod.GET)
	public ModelAndView collectfeesView(Model model) {
		return new ModelAndView("views/reconciliation/collectfees/index", "model", model);
	}
	
	//对账查询
	@RequestMapping("/duizhang/query")
	public CommonPageReponse queryDuizhangList(
			@RequestParam(value="startTime",required=false)String startTime,
			@RequestParam(value="endTime",required=false)String endTime,
			@RequestParam(value="orderId",required=false)String orderId,
			@RequestParam(value="payDate",required=false)String payDate,
			@RequestParam(value="sbh",required=false)String sbh,
			@RequestParam(value="payChannel",required=false)String payChannel,
			@RequestParam(defaultValue = "1")String page,
			@RequestParam(defaultValue = "10")String limit) {
		CommonPageReponse reponse = new CommonPageReponse();
		int current = Integer.parseInt(page); 
		int limitNum = Integer.parseInt(limit); 
		Page<AccFlowsheetDetail> pageData = new Page<AccFlowsheetDetail>(current,limitNum); 
		QueryWrapper<AccFlowsheetDetail> qw = new QueryWrapper<AccFlowsheetDetail>();
		if(StringUtils.isNotEmpty(payDate)) {
			qw.eq("DAYSTRING", payDate);
		}
		if(StringUtils.isNotEmpty(orderId)) {
			qw.eq("ORDER_ID", orderId);
		}
		if(StringUtils.isNotEmpty(payChannel)) {
			qw.eq("PAY_CHANNEL", payChannel);
		}
		if(StringUtils.isNotEmpty(sbh)) {
			qw.eq("SBH", sbh);
		}
		qw.orderByAsc("CDATE");
		IPage<AccFlowsheetDetail> list= flowsheetDetailMapper.selectPage(pageData,qw);
		reponse.setCode(0); 
		reponse.setTotal(list.getSize());
		reponse.setCount(String.valueOf(list.getSize()));
		reponse.setSuccess(true); 
		reponse.setData(list);
		return reponse;
	}
	
	//差异对账查询
	@RequestMapping("/diff/query")
	public CommonPageReponse queryDiffDuizhangList(
			@RequestParam(value="orderId",required=false)String orderId,
			@RequestParam(value="status",required=false)String status,
			@RequestParam(value="payDate",required=false)String payDate,
			@RequestParam(value="userNo",required=false)String userNo,
			@RequestParam(defaultValue = "1")String page,
			@RequestParam(defaultValue = "10")String limit) {
		CommonPageReponse reponse = new CommonPageReponse();
		int current = Integer.parseInt(page); 
		int limitNum = Integer.parseInt(limit); 
		Page<AccDiffDuizhangDetail> pageData = new Page<AccDiffDuizhangDetail>(current,limitNum); 
		QueryWrapper<AccDiffDuizhangDetail> qw = new QueryWrapper<AccDiffDuizhangDetail>();
		
		if(StringUtils.isNotEmpty(payDate)) {
			qw.and(wrapper -> wrapper.eq("SFRQ", payDate).or().eq("PAY_DATE", payDate));
		}
		if(StringUtils.isNotEmpty(orderId)) {
			qw.and(wrapper -> wrapper.eq("FLOW_NO", orderId).or().eq("ORDER_ID", orderId));
		}
		if(StringUtils.isNotEmpty(userNo)) {
			qw.and(wrapper -> wrapper.eq("USER_NO", userNo).or().eq("ACCT_ID", userNo));
		}
		if(StringUtils.isNotEmpty(status)) {
			qw.and(wrapper -> wrapper.eq("STATUS", status).or().eq("QS_STATUS", status));
		}
		qw.orderByAsc("CDATE");
		IPage<AccDiffDuizhangDetail> list= accDiffDuizhangDetailMapper.selectPage(pageData,qw);
		reponse.setCode(0); 
		reponse.setTotal(list.getSize());
		reponse.setCount(String.valueOf(list.getSize()));
		reponse.setSuccess(true); 
		reponse.setData(list);
		return reponse;
	}
	
	//回盘查询
	@RequestMapping("/huipan/query")
	public CommonPageReponse queryHuipanList(
			@RequestParam(value="startTime",required=false)String startTime,
			@RequestParam(value="endTime",required=false)String endTime,
			@RequestParam(value="orderId",required=false)String orderId,
			@RequestParam(value="payDate",required=false)String payDate,
			@RequestParam(value="sbh",required=false)String sbh,
			@RequestParam(value="payChannel",required=false)String payChannel,
			@RequestParam(defaultValue = "1")String page,
			@RequestParam(defaultValue = "10")String limit) {
		CommonPageReponse reponse = new CommonPageReponse();
		
		
		
		
		return reponse;
	}
	
	
	
}
