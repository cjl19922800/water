package com.shanghaiwater.mcs.admin.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shanghaiwater.mcs.admin.common.AdminLoginInfo;
import com.shanghaiwater.mcs.admin.model.CommonPageReponse;
import com.shanghaiwater.mcs.admin.service.TransferIncidentService;
import com.shanghaiwater.mcs.db.entity.McsIncident;
import com.shanghaiwater.mcs.db.mapper.McsIncidentMapper;


@Service
public class TransferIncidentServiceImpl implements TransferIncidentService{

	@Autowired
	private McsIncidentMapper mcsIncidentMapper;
	
	@Autowired
	private HttpServletRequest request;
	
	@Override
	public CommonPageReponse queryPagingLogList(Map<String,String> map) {
		 int current=Integer.parseInt((String) map.get("page")); 
		 int limit=Integer.parseInt((String) map.get("limit")); 
		 Page<McsIncident> page=new Page<McsIncident>(current,limit); 
		 CommonPageReponse reponse = new CommonPageReponse();
		 QueryWrapper<McsIncident> qw = new QueryWrapper<McsIncident>();
		 qw.select("INCIDENT_ID",
				 "INCIDENT_TYPE",
				 "SHW_COMPANY",
				 "APPDATE",
				 "TRANSFER_COMPANY",
				 "APPLICANT",
				 "SHW_ADDRESS",
				 "APPLY_NO",
				 "SOURCE",
				 "USER_NO",
				 "(SELECT c.NAME FROM MGT_WATER_COMPANY c WHERE SHW_COMPANY = c.COMPANY_CODE) AS SHW_COMPANY_NAME",
				 "(SELECT t.NAME FROM MGT_WATER_COMPANY t WHERE TRANSFER_COMPANY = t.COMPANY_CODE) AS TRANSFER_COMPANY_NAME",
				 "(SELECT d.ITEM_VALUE FROM SYS_DICT_ITEM d WHERE d.DICT_CODE = 'McsItemType' AND d.STATUS = 'Enable' AND d.ITEM_CODE = INCIDENT_TYPE) AS INCIDENT_TYPE_NAME");
		 qw.eq("IS_TRANSFER", 1);
		 if(StringUtils.isNotEmpty(map.get("companyCode"))) {
			 qw.eq("TRANSFER_COMPANY", map.get("companyCode"));
		 }
		 if(!StringUtils.isEmpty(map.get("startTime"))&&!StringUtils.isEmpty(map.get("endTime"))) {
			 try { 
				 qw.gt("CDATE", new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(map.get("startTime")));
				 qw.lt("CDATE", new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(map.get("endTime")));
			 } catch (ParseException e) { 
				 e.printStackTrace(); 
			 }
		 }
		 AdminLoginInfo adminLoginInfo = (AdminLoginInfo) request.getSession().getAttribute("adminLoginInfo");
		 List<String> companyCodes = adminLoginInfo.getCompanyAuthorities();
		 if(companyCodes.size()>0) {
			 qw.in("SHW_COMPANY", companyCodes);
		 }else {
			 qw.isNull("INCIDENT_ID");
		 }
		 qw.orderByDesc("CDATE");
		 IPage<McsIncident> list = mcsIncidentMapper.selectPage(page,qw);
		 reponse.setCode(0); 
		 reponse.setTotal(list.getSize());
		 reponse.setCount(String.valueOf(list.getSize()));
		 reponse.setSuccess(true); 
		 reponse.setData(list);
		 return reponse;
	}
	
	
	
}
