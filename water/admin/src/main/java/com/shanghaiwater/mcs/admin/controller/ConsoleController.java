package com.shanghaiwater.mcs.admin.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shanghaiwater.mcs.admin.annotation.PermissionIntercept;
import com.shanghaiwater.mcs.admin.common.AdminLoginInfo;
import com.shanghaiwater.mcs.admin.model.CommonPageReponse;
import com.shanghaiwater.mcs.common.constants.IncidentStatus;
import com.shanghaiwater.mcs.db.entity.McsIncident;
import com.shanghaiwater.mcs.db.mapper.McsIncidentMapper;

/**
 * 页面跳转
 * 
 * @author Laipu
 *
 */
@RestController
@RequestMapping("/home/console")
public class ConsoleController {
	
	@Autowired
	private McsIncidentMapper incidentMapper;

	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public ModelAndView listView(HttpServletRequest request,Model model) {
		AdminLoginInfo adminLoginInfo = (AdminLoginInfo) request.getSession().getAttribute("adminLoginInfo");
		//待办事项 获取新申请、处理中、退单、办结的数量
		int newApply = incidentMapper.selectCount(new QueryWrapper<McsIncident>().eq("STATUS", IncidentStatus.NEW).in("SHW_COMPANY", adminLoginInfo.getCompanyAuthorities()).eq("YWXT_SUCCESS", 1));
		int processApply = incidentMapper.selectCount(new QueryWrapper<McsIncident>().eq("STATUS", IncidentStatus.PROCESSING).in("SHW_COMPANY", adminLoginInfo.getCompanyAuthorities()).eq("YWXT_SUCCESS", 1));
		int returnApply = incidentMapper.selectCount(new QueryWrapper<McsIncident>().eq("STATUS", IncidentStatus.RETURN).in("SHW_COMPANY", adminLoginInfo.getCompanyAuthorities()).eq("YWXT_SUCCESS", 1));
		int finishApply = incidentMapper.selectCount(new QueryWrapper<McsIncident>().eq("STATUS", IncidentStatus.FINISH).in("SHW_COMPANY", adminLoginInfo.getCompanyAuthorities()).eq("YWXT_SUCCESS", 1));
		model.addAttribute("newApply", newApply);
		model.addAttribute("processApply", processApply);
		model.addAttribute("returnApply", returnApply);
		model.addAttribute("finishApply", finishApply);
		return new ModelAndView("views/home/console", "model", model);
	}

	
	@RequestMapping("/list")
//	@PermissionIntercept
	public CommonPageReponse list(HttpServletRequest request,
			@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "limit", defaultValue = "10") Integer limit,
			HttpServletResponse httpResponse) {
		AdminLoginInfo adminLoginInfo = (AdminLoginInfo) request.getSession().getAttribute("adminLoginInfo");
		
//		if(adminLoginInfo == null) {
//			
//			try {
//				httpResponse.getWriter().write("<script>window.location.reload();</script>");
//			} catch (IOException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//			return null;
//
//		}
		
		CommonPageReponse response = new CommonPageReponse();
		Page<McsIncident> pageData = new Page<McsIncident>(page,limit); 
		QueryWrapper<McsIncident> qw = new QueryWrapper<McsIncident>();
		List<String> statuslist = new ArrayList<String>();
		statuslist.add(IncidentStatus.NEW);
		statuslist.add(IncidentStatus.PROCESSING);
		statuslist.add(IncidentStatus.DEFER);
		qw.select("(SELECT c.NAME FROM MGT_WATER_COMPANY c WHERE SHW_COMPANY = c.COMPANY_CODE) AS SHW_COMPANY_NAME",
				  "INCIDENT_ID",
				  "INCIDENT_TYPE",
				  "APPDATE",
				  "SHW_ADDRESS",
				  "APPLY_NO",
				  "YWXT_SUCCESS",
				  "DEAL",
				  "SOURCE",
				  "USER_NO",
				  "(SELECT d.ITEM_VALUE FROM SYS_DICT_ITEM d WHERE d.DICT_CODE = 'McsItemType' AND d.STATUS = 'Enable' AND d.ITEM_CODE = INCIDENT_TYPE) AS INCIDENT_TYPE_NAME",
				  "APPLICANT",
				  "STATUS");
		qw.in("SHW_COMPANY", adminLoginInfo.getCompanyAuthorities());
		qw.in("STATUS", statuslist);
		qw.eq("YWXT_SUCCESS", 1);
		qw.orderByDesc("CDATE");
		IPage<McsIncident> list = incidentMapper.selectPage(pageData,qw);
		response.setCode(0); 
		response.setTotal(list.getSize());
		response.setCount(String.valueOf(list.getSize()));
		response.setSuccess(true); 
		response.setData(list);
		return response;
	}
	
	
	
}
