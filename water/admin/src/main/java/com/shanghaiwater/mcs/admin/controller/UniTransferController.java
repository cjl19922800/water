package com.shanghaiwater.mcs.admin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.shanghaiwater.mcs.admin.annotation.PermissionIntercept;
import com.shanghaiwater.mcs.admin.common.AdminLoginInfo;
import com.shanghaiwater.mcs.admin.model.PageListBasicResponse;
import com.shanghaiwater.mcs.admin.service.UswTransferService;

@RestController
@RequestMapping("/home/uniTransfer/")
@PermissionIntercept
public class UniTransferController {

	@Autowired
	private UswTransferService uswTransferService;
	
	/**
	 * 列表跳转
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/listview", method = RequestMethod.GET)
	public ModelAndView listView(Model model) {
		return new ModelAndView("views/home/otherBusiness/uniTransfer", "model", model);
	}
	
	
	/**
	 * 列表查询
	 * @param request
	 * @param status
	 * @param page
	 * @param limit
	 * @param name
	 * @param phone
	 * @param deal
	 * @param companyCode
	 * @param userNo
	 * @param eStatus
	 * @param startdate
	 * @param type
	 * @param enddate
	 * @param model
	 * @return
	 */
	@RequestMapping("/list")
	public PageListBasicResponse list(HttpServletRequest request,
			@RequestParam(value = "status", required = false) String status,
			@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "limit", defaultValue = "10") Integer limit,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "phone", required = false) String phone,
			@RequestParam(value = "deal", required = false) String deal,
			@RequestParam(value = "companyCode", required = false) String companyCode,
			@RequestParam(value = "userNo", required = false) String userNo,
			@RequestParam(value = "eStatus", required = false) String eStatus,
			@RequestParam(value = "startdate", required = false) String startdate,
			@RequestParam(value = "type", required = false) String type,
			@RequestParam(value = "enddate", required = false) String enddate, Model model) {
		AdminLoginInfo adminLoginInfo = (AdminLoginInfo) request.getSession().getAttribute("adminLoginInfo");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", status);
//		map.put("startTime", startdate);
//		map.put("endTime", enddate);
		if(StringUtils.isNotEmpty(startdate)) {
			map.put("startTime", startdate+" 00:00:00");
		}
		if(StringUtils.isNotEmpty(enddate)) {
			map.put("endTime", enddate+" 23:59:59");
		}
		map.put("page", page);
		map.put("limit", limit);
		map.put("eStatus", eStatus);
		map.put("incidentType", type);
		map.put("companyCode", companyCode);
		map.put("userNo", userNo);
		map.put("deal", deal);
		if(StringUtils.isEmpty(phone)) {
			map.put("phone", phone);
		}else {
			map.put("phone", "%"+phone+"%");
		}
		if(StringUtils.isEmpty(name)) {
			map.put("name", name);
		}else {
			map.put("name", "%"+name+"%");
		}
		List<String> companyCodes = adminLoginInfo.getCompanyAuthorities();
		map.put("companys", companyCodes.toArray(new String[companyCodes.size()]));
		return uswTransferService.queryTransferList(map);
	}
	
	
	
	
	
	
	
}
