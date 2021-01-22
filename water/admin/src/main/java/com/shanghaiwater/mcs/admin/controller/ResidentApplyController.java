package com.shanghaiwater.mcs.admin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.shanghaiwater.mcs.admin.annotation.PermissionIntercept;
import com.shanghaiwater.mcs.admin.common.AdminLoginInfo;

import lombok.extern.slf4j.Slf4j;

import com.shanghaiwater.mcs.admin.model.PageListBasicResponse;
import com.shanghaiwater.mcs.admin.model.ResidentApplyListResponse;
import com.shanghaiwater.mcs.admin.model.ResidentApplyUpdateStatusRequest;
import com.shanghaiwater.mcs.admin.model.ResidentApplyUpdateStatusResponse;
import com.shanghaiwater.mcs.admin.service.IMgtWaterCompanyService;
import com.shanghaiwater.mcs.admin.service.UswResidentApplyService;
import com.shanghaiwater.mcs.admin.service.inf.CisCompanyCodeService;
import com.shanghaiwater.mcs.admin.service.inf.ReginCodeService;
import com.shanghaiwater.mcs.common.util.SecurityUtil;
import com.shanghaiwater.mcs.common.vo.AreaTown;
import com.shanghaiwater.mcs.common.vo.DictItem;
import com.shanghaiwater.mcs.common.vo.WaterCompany;
import com.shanghaiwater.mcs.db.entity.McsIncident;
import com.shanghaiwater.mcs.db.entity.McsUserImageDetail;
import com.shanghaiwater.mcs.db.entity.MgtWaterCompany;
import com.shanghaiwater.mcs.db.entity.UswResidentApply;
import com.shanghaiwater.mcs.db.mapper.McsIncidentMapper;
import com.shanghaiwater.mcs.db.mapper.McsUserImageDetailMapper;
import com.shanghaiwater.mcs.db.mapper.UswResidentApplyMapper;

@Slf4j
@RestController
@RequestMapping("/resiapply")
@PermissionIntercept
public class ResidentApplyController {

	@Autowired
	private UswResidentApplyService uswResidentApplyService;

	@Autowired
	private ReginCodeService reginCodeService;

	@Autowired
	private CisCompanyCodeService cisCompanyCodeService;
	
	@Autowired
	private McsIncidentMapper mcsIncidentMapper;
	
	@Autowired
	private UswResidentApplyMapper uswResidentApplyMapper;
	
	@Autowired
	private IMgtWaterCompanyService mgtWaterCompanyService;
	
	@Autowired
	private McsUserImageDetailMapper imageDetailMapper;
	
	private final static String[] imgType = {"身份证", "产权证"};
	
	@RequestMapping(value = "/listview", method = RequestMethod.GET)
	public ModelAndView listView(Model model) {
		return new ModelAndView("views/usewater/resident_apply/resident_apply", "toolModel", model);
	}

	@RequestMapping("/detail")
	public ModelAndView detailPage(@RequestParam("incidentId") String incidentId, Model model) {
		McsIncident incident = mcsIncidentMapper.selectById(incidentId);
		UswResidentApply info = uswResidentApplyMapper.selectById(incidentId);
		
		incident.setCertNumber(SecurityUtil.decryptAES(incident.getCertNumber()));
		incident.setShwAddress(SecurityUtil.decryptAES(incident.getShwAddress()));
		
		info.setAddress(SecurityUtil.decryptAES(info.getAddress()));
		info.setPhone(SecurityUtil.decryptAES(info.getPhone()));
		info.setHourseCertNo(SecurityUtil.decryptAES(info.getHourseCertNo()));
		info.setContactAddress(SecurityUtil.decryptAES(info.getContactAddress()));
		
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
			queWrapper.eq("cert_type", imgType[x]);
			List<McsUserImageDetail> selectList = imageDetailMapper.selectList(queWrapper);
			map.put(imgType[x], selectList);
		}
		model.addAttribute("imagemaps", map);
		return new ModelAndView("views/usewater/resident_apply/iframe", "res", model);
	}

	@RequestMapping("/list")
	public PageListBasicResponse list(HttpServletRequest request,
			@RequestParam(value = "status", required = false) String status,
			@RequestParam(value = "companyCode", required = false) String companyCode,
			@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "limit", defaultValue = "10") Integer limit,
			@RequestParam(value = "startdate", required = false) String startdate,
			@RequestParam(value = "deal", required = false) String deal,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "eStatus", required = false) String eStatus,
			@RequestParam(value = "phone", required = false) String phone,
			@RequestParam(value = "enddate", required = false) String enddate, Model model) {

		AdminLoginInfo adminLoginInfo = (AdminLoginInfo) request.getSession().getAttribute("adminLoginInfo");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", status);
		map.put("startdate", startdate);
		map.put("enddate", enddate);
		map.put("eStatus", eStatus);
		map.put("page", page);
		map.put("companyCode", companyCode);
		map.put("limit", limit);
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
		return uswResidentApplyService.queryWaterList(map);
	}

	@RequestMapping("/updstatus")
	public ResidentApplyUpdateStatusResponse updateStatus(HttpServletRequest request,
			@RequestBody ResidentApplyUpdateStatusRequest residentApplyUpdateStatusRequest) {
		String incidentId = residentApplyUpdateStatusRequest.getIncidentId();
		String status = residentApplyUpdateStatusRequest.getStatus();
		ResidentApplyUpdateStatusResponse residentApplyUpdateStatusResponse = uswResidentApplyService
				.updateStatus(incidentId, status);

		residentApplyUpdateStatusResponse.setRequestId(UUID.randomUUID().toString());
		residentApplyUpdateStatusResponse.setCode(0);
		residentApplyUpdateStatusResponse.setSuccess(true);
		residentApplyUpdateStatusResponse.setIncidentId(incidentId);

		return residentApplyUpdateStatusResponse;
	}

}
