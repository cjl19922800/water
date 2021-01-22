package com.shanghaiwater.mcs.admin.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shanghaiwater.mcs.admin.common.AdminLoginInfo;
import com.shanghaiwater.mcs.admin.enums.ModuleEnums;
import com.shanghaiwater.mcs.admin.enums.OperationEnums;
import com.shanghaiwater.mcs.admin.model.CommonPageReponse;
import com.shanghaiwater.mcs.admin.service.SysAdminLogService;
import com.shanghaiwater.mcs.db.entity.McsOplog;
import com.shanghaiwater.mcs.db.entity.SysAdminLog;
import com.shanghaiwater.mcs.db.mapper.SysAdminLogMapper;

@Service
public class SysAdminLogServiceImpl implements SysAdminLogService{

	@Autowired
	private SysAdminLogMapper sysAdminLogMapper;
	
	@Autowired
	private HttpServletRequest request;
	
	@Override
	public void saveAdminLog(OperationEnums oe,ModuleEnums me,String msg,String tagetId) {
		SysAdminLog log = new SysAdminLog();
		AdminLoginInfo adminLoginInfo = (AdminLoginInfo) request.getSession().getAttribute("adminLoginInfo");
		log.setCreateId(adminLoginInfo.getId());
		log.setCreateTime(new Date());
		log.setId(UUID.randomUUID().toString());
		log.setTargetId(tagetId);
		log.setMsg(msg);
		log.setModule(me.getName());
		log.setModuleType(me.getCode());
		log.setOperationName(oe.getName());
		log.setOperationType(oe.getCode());
		sysAdminLogMapper.insert(log);
	}
	
	
	@Override
	public CommonPageReponse queryPagingLogList(Map<String,String> map) {
		 int current=Integer.parseInt((String) map.get("page")); 
		 int limit=Integer.parseInt((String) map.get("limit")); 
		 Page<SysAdminLog> page=new Page<SysAdminLog>(current,limit); 
		 CommonPageReponse reponse = new CommonPageReponse();
		 QueryWrapper<SysAdminLog> qw = new QueryWrapper<SysAdminLog>();
		 qw.select("ID"
				  ,"CREATE_ID"
				  ,"CREATE_TIME"
				  ,"MODULE_TYPE"
				  ,"OPERATION_TYPE"
				  ,"OPERATION_NAME"
				  ,"MODULE"
				  ,"TARGET_ID"
				  ,"MSG"
				  ,"(SELECT a.USER_NAME FROM SYS_ADMIN_USER a WHERE CREATE_ID = a.ID) AS USER_NAME");
		 if(StringUtils.isNotEmpty(map.get("moduleType"))) {
			 qw.eq("MODULE_TYPE", map.get("moduleType"));
		 }
		 if(StringUtils.isNotEmpty(map.get("operationType"))) {
			 qw.eq("OPERATION_TYPE", map.get("operationType"));
		 }
		 if(!StringUtils.isEmpty(map.get("startTime"))&&!StringUtils.isEmpty(map.get("endTime"))) {
			 try { 
				 qw.gt("CREATE_TIME", new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(map.get("startTime")));
				 qw.lt("CREATE_TIME", new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(map.get("endTime")));
			 } catch (ParseException e) { 
				 e.printStackTrace(); 
			 }
		 }
		 qw.orderByDesc("CREATE_TIME");
		 IPage<SysAdminLog> list=sysAdminLogMapper.selectPage(page,qw);
		 reponse.setCode(0); 
		 reponse.setTotal(list.getSize());
		 reponse.setCount(String.valueOf(list.getSize()));
		 reponse.setSuccess(true); 
		 reponse.setData(list);
		 return reponse;
	}
	
	
	
	
}
