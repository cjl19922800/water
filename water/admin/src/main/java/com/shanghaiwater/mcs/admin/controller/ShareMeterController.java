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
import com.shanghaiwater.mcs.admin.annotation.PermissionIntercept;
import com.shanghaiwater.mcs.admin.common.AdminLoginInfo;
import com.shanghaiwater.mcs.admin.enums.BusinessTypeEnums;
import com.shanghaiwater.mcs.admin.model.PageListBasicResponse;
import com.shanghaiwater.mcs.admin.model.PayRemailResponse;
import com.shanghaiwater.mcs.admin.service.IMgtWaterCompanyService;
import com.shanghaiwater.mcs.admin.service.UswMeterMoveService;
import com.shanghaiwater.mcs.admin.service.UswOpenMeterService;
import com.shanghaiwater.mcs.admin.service.UswPayRemailService;
import com.shanghaiwater.mcs.admin.service.UswShareMeterService;
import com.shanghaiwater.mcs.common.util.SecurityUtil;
import com.shanghaiwater.mcs.db.entity.McsIncident;
import com.shanghaiwater.mcs.db.entity.McsUserImageDetail;
import com.shanghaiwater.mcs.db.entity.MgtWaterCompany;
import com.shanghaiwater.mcs.db.entity.UswOpenMeter;
import com.shanghaiwater.mcs.db.entity.UswShareMeter;
import com.shanghaiwater.mcs.db.mapper.McsIncidentMapper;
import com.shanghaiwater.mcs.db.mapper.McsUserImageDetailMapper;
import com.shanghaiwater.mcs.db.mapper.UswMeterMoveMapper;
import com.shanghaiwater.mcs.db.mapper.UswOpenMeterMapper;
import com.shanghaiwater.mcs.db.mapper.UswShareMeterMapper;

@RestController
@RequestMapping("/home/shareMeter")
@PermissionIntercept
public class ShareMeterController {
	
	@Autowired
	private UswShareMeterService uswShareMeterService;
	
	@Autowired
	private McsIncidentMapper mcsIncidentMapper;
	
	@Autowired
	private UswShareMeterMapper uswShareMeterMapper;
	
	@Autowired
	private IMgtWaterCompanyService mgtWaterCompanyService;
	
	@Autowired
	private McsUserImageDetailMapper imageDetailMapper;
	
	
	private final static String[] imgType = { "身份证", "房产证" };
	private final static String[] imgTypeTwo = { "身份证", "户口本"};
	
	

	@RequestMapping(value = "/listView", method = RequestMethod.GET)
	public ModelAndView listMeterMoveIndex(Model model) {
		
		return new ModelAndView("views/home/shareMeter/shareMeter", "model", model);
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
		return uswShareMeterService.queryShareMeterList(map);
	}
	
	@RequestMapping("/detail")
	public ModelAndView detailPage(@RequestParam("incidentId") String incidentId, Model model) {
		McsIncident incident = mcsIncidentMapper.selectById(incidentId);
		UswShareMeter info = uswShareMeterMapper.selectById(incidentId);
		
		incident.setCertNumber(SecurityUtil.decryptAES(incident.getCertNumber()));
		incident.setShwAddress(SecurityUtil.decryptAES(incident.getShwAddress()));
		
		info.setAddress(SecurityUtil.decryptAES(info.getAddress()));
		info.setCertNumber(SecurityUtil.decryptAES(info.getCertNumber()));
		info.setHouseCertNo(SecurityUtil.decryptAES(info.getHouseCertNo()));
		info.setContactNum(SecurityUtil.decryptAES(info.getContactNum()));
		
		
		model.addAttribute("info", info);
		model.addAttribute("incident", incident);
		List<MgtWaterCompany> companys = mgtWaterCompanyService.findByCompanyCode(incident.getShwCompany());
		if(companys.size()>0) {
			model.addAttribute("companyName", companys.get(0).getName());
		}else {
			model.addAttribute("companyName", "");
		}
		Map<String,List<McsUserImageDetail>> map = new HashMap<String, List<McsUserImageDetail>>();
		for (int x = 0; x < imgType.length; x++) {
			QueryWrapper<McsUserImageDetail> queWrapper = new QueryWrapper<McsUserImageDetail>();
			queWrapper.eq("apply_no", incident.getApplyNo());
			
			if (info.getAppType().equals("01")) {
				queWrapper.eq("cert_type", imgType[x]);
			} else {
				queWrapper.eq("cert_type", imgTypeTwo[x]);
			}
			
			List<McsUserImageDetail> selectList = imageDetailMapper.selectList(queWrapper);
			
			if (info.getAppType().equals("01")) {
				map.put(imgType[x], selectList);
			} else {
				map.put(imgTypeTwo[x], selectList);
			}
			
		}
		model.addAttribute("imagemaps", map);
		return new ModelAndView("views/home/shareMeter/shareMeterView", "model", model);
	}
}
