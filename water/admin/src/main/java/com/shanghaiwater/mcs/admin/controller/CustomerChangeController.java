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

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.shanghaiwater.mcs.admin.common.AdminLoginInfo;
import com.shanghaiwater.mcs.admin.model.PageListBasicResponse;
import com.shanghaiwater.mcs.admin.service.IMgtWaterCompanyService;
import com.shanghaiwater.mcs.admin.service.UswCustomerChangeService;
import com.shanghaiwater.mcs.common.util.SecurityUtil;
import com.shanghaiwater.mcs.db.entity.McsConInfo;
import com.shanghaiwater.mcs.db.entity.McsIncident;
import com.shanghaiwater.mcs.db.entity.McsUserImageDetail;
import com.shanghaiwater.mcs.db.entity.MgtWaterCompany;
import com.shanghaiwater.mcs.db.entity.UswUpdateInf;
import com.shanghaiwater.mcs.db.mapper.McsConInfoMapper;
import com.shanghaiwater.mcs.db.mapper.McsIncidentMapper;
import com.shanghaiwater.mcs.db.mapper.McsUserImageDetailMapper;
import com.shanghaiwater.mcs.db.mapper.UswUpdateInfMapper;

@RestController
@RequestMapping("/home/cusChange")
public class CustomerChangeController {
	
	@Autowired
	private UswCustomerChangeService uswCustomerChangeService;
	
	
	@Autowired
	private McsIncidentMapper mcsIncidentMapper;
	
	@Autowired
	private UswUpdateInfMapper uswUpdateInfMapper;
	
	@Autowired
	private IMgtWaterCompanyService mgtWaterCompanyService;
	
	@Autowired
	private McsConInfoMapper mcsConInfoMapper;
	
	@Autowired
	private McsUserImageDetailMapper imageDetailMapper;
	
	private final static String[] imgType = {"身份证"};
	
	
	@RequestMapping(value = "listView", method = RequestMethod.GET)
	public ModelAndView listView(Model model) {
		return new ModelAndView("views/home/customerChange/customerChange", "model", model);
	}
	
	
	
	
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
			@RequestParam(value = "incidentType", required = false) String type,
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
		return uswCustomerChangeService.queryCusChangeList(map);
	}
	
	@RequestMapping("/detail")
	public ModelAndView detailPage(@RequestParam("incidentId") String incidentId, Model model) {
		McsIncident incident = mcsIncidentMapper.selectById(incidentId);
		UswUpdateInf info = uswUpdateInfMapper.selectById(incidentId);
		
		incident.setCertNumber(SecurityUtil.decryptAES(incident.getCertNumber()));
		incident.setShwAddress(SecurityUtil.decryptAES(incident.getShwAddress()));
		info.setContactAddress1(SecurityUtil.decryptAES(info.getContactAddress1()));
		info.setContactAddress2(SecurityUtil.decryptAES(info.getContactAddress2()));
		info.setContactNumber2(SecurityUtil.decryptAES(info.getContactNumber2()));
		info.setContactNumber1(SecurityUtil.decryptAES(info.getContactNumber1()));
		info.setWaterAddress(SecurityUtil.decryptAES(info.getWaterAddress()));
		info.setMailAddress(SecurityUtil.decryptAES(info.getMailAddress()));
		
		
		model.addAttribute("info", info);
		model.addAttribute("incident", incident);
		List<MgtWaterCompany> companys = mgtWaterCompanyService.findByCompanyCode(incident.getShwCompany());
		if(companys.size()>0) {
			model.addAttribute("companyName", companys.get(0).getName());
		}else {
			model.addAttribute("companyName", "");
		}
		QueryWrapper<McsConInfo> queryWrapper1 = new QueryWrapper<McsConInfo>();
		queryWrapper1.eq("incident_id", incidentId);
		List<McsConInfo> conInfos = mcsConInfoMapper.selectList(queryWrapper1);
		for(McsConInfo conInfo:conInfos) {
			conInfo.setContactMail(SecurityUtil.decryptAES(conInfo.getContactMail()));
			conInfo.setContactNum(SecurityUtil.decryptAES(conInfo.getContactNum()));
		}
		model.addAttribute("conList", conInfos);
		
		
		Map<String,List<McsUserImageDetail>> map = new HashMap<String, List<McsUserImageDetail>>();
		for (int x = 0; x < imgType.length; x++) {
			QueryWrapper<McsUserImageDetail> queWrapper = new QueryWrapper<McsUserImageDetail>();
			queWrapper.eq("apply_no", incident.getApplyNo());
			queWrapper.eq("cert_type", imgType[x]);
			List<McsUserImageDetail> selectList = imageDetailMapper.selectList(queWrapper);
			map.put(imgType[x], selectList);
		}
		model.addAttribute("imagemaps", map);
		return new ModelAndView("views/home/customerChange/customerChangeView", "model", model);
	}
	
}
