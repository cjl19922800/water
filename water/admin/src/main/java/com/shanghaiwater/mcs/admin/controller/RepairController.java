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
import com.shanghaiwater.mcs.admin.common.ResponseSuccessModel;
import com.shanghaiwater.mcs.admin.enums.BusinessTypeEnums;
import com.shanghaiwater.mcs.admin.model.RepairListResponse;
import com.shanghaiwater.mcs.admin.model.RprUsewaterUpdateStatusRequest;
import com.shanghaiwater.mcs.admin.service.BusinessWaterRepairService;
import com.shanghaiwater.mcs.admin.service.IMgtWaterCompanyService;
import com.shanghaiwater.mcs.admin.service.McsIncidentService;
import com.shanghaiwater.mcs.admin.service.inf.ReginCodeService;
import com.shanghaiwater.mcs.common.util.SecurityUtil;
import com.shanghaiwater.mcs.db.entity.McsIncident;
import com.shanghaiwater.mcs.db.entity.McsUserImageDetail;
import com.shanghaiwater.mcs.db.entity.MgtWaterCompany;
import com.shanghaiwater.mcs.db.entity.RprUsewaterRepair;
import com.shanghaiwater.mcs.db.mapper.McsIncidentMapper;
import com.shanghaiwater.mcs.db.mapper.McsUserImageDetailMapper;
import com.shanghaiwater.mcs.db.mapper.RprUsewaterRepairMapper;

/**
 * 页面跳转
 * 
 * @author Laipu
 *
 */
@RestController
@RequestMapping("/home")
@PermissionIntercept
public class RepairController {

	@Autowired
	private BusinessWaterRepairService businessWaterRepairService;

	@Autowired
	private ReginCodeService reginCodeService;

	@Autowired
	private McsIncidentService mcsIncidentService;

	@Autowired
	private IMgtWaterCompanyService mgtWaterCompanyService;

	@Autowired
	private McsIncidentMapper mcsIncidentMapper;
	
	@Autowired
	private RprUsewaterRepairMapper mcsRepairMapper;
	
	@Autowired
	private McsUserImageDetailMapper imageDetailMapper;

	@RequestMapping(value = "/repairs/plumbingLeakIndex", method = RequestMethod.GET)
	public ModelAndView listView(Model model) {
		model.addAttribute("itemType", "RepairLeakWater");
		model.addAttribute("itemTypeName", "报修（水管漏水）");
		return new ModelAndView("views/home/repairs/plumbingLeakIndex", "model", model);
	}

	@RequestMapping(value = "/repairs/waterProblemsIndex", method = RequestMethod.GET)
	public ModelAndView listViewwaterProblemsIndex(Model model) {
		model.addAttribute("itemType", "RepairUseWater");
		model.addAttribute("itemTypeName", "报修（用水问题）");
		return new ModelAndView("views/home/repairs/plumbingLeakIndex", "model", model);
	}

	@RequestMapping(value = "/repairs/waterQualityProblemsIndex", method = RequestMethod.GET)
	public ModelAndView listViewwaterQualityProblemsIndex(Model model) {
		model.addAttribute("itemType", "RepairWaterQuality");
		model.addAttribute("itemTypeName", "报修（水质问题）");
		return new ModelAndView("views/home/repairs/plumbingLeakIndex", "model", model);
	}

	@RequestMapping(value = "/repairs/tableForIndex", method = RequestMethod.GET)
	public ModelAndView listViewtableForIndex(Model model) {
		model.addAttribute("itemType", "RepairWaterMeter");
		model.addAttribute("itemTypeName", "报修（表务问题）");
		return new ModelAndView("views/home/repairs/plumbingLeakIndex", "model", model);
	}

	@RequestMapping(value = "/repairs/otherProblemsIndex", method = RequestMethod.GET)
	public ModelAndView listViewotherProblemsIndex(Model model) {
		model.addAttribute("itemType", "RepairOther");
		model.addAttribute("itemTypeName", "报修（其他）");
		return new ModelAndView("views/home/repairs/plumbingLeakIndex", "model", model);
	}

	
	/**
	 * 详情页
	 * @param incidentId
	 * @param model
	 * @return
	 */
	@RequestMapping("/repairs/detail")
	public ModelAndView detailPage(@RequestParam("incidentId") String incidentId, Model model) {
		McsIncident incident = mcsIncidentMapper.selectById(incidentId);
		RprUsewaterRepair reapirInfo = mcsRepairMapper.selectById(incidentId);
		
		incident.setCertNumber(SecurityUtil.decryptAES(incident.getCertNumber()));
		incident.setShwAddress(SecurityUtil.decryptAES(incident.getShwAddress()));
		
		reapirInfo.setContactValue(SecurityUtil.encryptAES(reapirInfo.getContactValue()));
		reapirInfo.setAddress(SecurityUtil.encryptAES(reapirInfo.getAddress()));
		
		List<MgtWaterCompany> companys = mgtWaterCompanyService.findByCompanyCode(incident.getShwCompany());
		if(companys.size()>0) {
			model.addAttribute("companyName", companys.get(0).getName());
		}else {
			model.addAttribute("companyName", "");
		}
		QueryWrapper<McsUserImageDetail> queryWrapper = new QueryWrapper<McsUserImageDetail>();
		queryWrapper.eq("apply_no", incident.getApplyNo());
		List<McsUserImageDetail> imageList = imageDetailMapper.selectList(queryWrapper);
		model.addAttribute("imageList", imageList);
		model.addAttribute("info", reapirInfo);
		model.addAttribute("incident", incident);
		return new ModelAndView("views/home/repairs/plumbingLeakView", "model", model);
	}

	@RequestMapping("/repairs/list")
	public RepairListResponse list(HttpServletRequest request, @RequestParam(value = "itemType",required = false) String itemType,
			@RequestParam("itemTypeTemp") String itemTypeTemp,
			@RequestParam(value = "status", required = false) String status,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "phone", required = false) String phone,
			@RequestParam(value = "companyCode", required = false) String companyCode,
			@RequestParam(value = "userNo", required = false) String userNo,
			@RequestParam(value = "deal", required = false) String deal,
			@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "limit", defaultValue = "10") Integer limit,
			@RequestParam(value = "startdate", required = false) String startdate,
			@RequestParam(value = "eStatus", required = false) String eStatus,
			@RequestParam(value = "enddate", required = false) String enddate, Model model) {
		Map<String, Object> map = new HashMap<String, Object>();
		AdminLoginInfo adminLoginInfo = (AdminLoginInfo) request.getSession().getAttribute("adminLoginInfo");
		if(org.springframework.util.StringUtils.isEmpty(itemType)) {
			itemType = itemTypeTemp;
		}
		List<String> incidentTypes = BusinessTypeEnums.getIncidentTypes(itemType);
		String[] types = incidentTypes.toArray(new String[incidentTypes.size()]);
		
		List<String> companyCodes = adminLoginInfo.getCompanyAuthorities();
		map.put("companys", companyCodes.toArray(new String[companyCodes.size()]));
		
		map.put("types", types);
		map.put("status", status);
		map.put("startTime", startdate);
		map.put("endTime", enddate);
		map.put("page", page);
		map.put("limit", limit);
		map.put("eStatus", eStatus);
		map.put("companyCode", companyCode);
		map.put("userNo", userNo);
		map.put("deal", deal);
		map.put("incidentType", itemType);
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
		return businessWaterRepairService.queryRepairList(map);
	}

	@RequestMapping("/repairs/updstatus")
	public ResponseSuccessModel updateStatus(HttpServletRequest request,
			@RequestBody RprUsewaterUpdateStatusRequest residentApplyUpdateStatusRequest) {

		String incidentId = residentApplyUpdateStatusRequest.getIncidentId();
		String status = residentApplyUpdateStatusRequest.getStatus();

//		reqParam.setRequestId(requestId);
//		jsonResponse = groupAction.listGroups(reqParam);

		ResponseSuccessModel updateStatus = businessWaterRepairService.updateStatus(incidentId, status);

		updateStatus.setRequestId(UUID.randomUUID().toString());
		updateStatus.setCode(0);
		updateStatus.setSuccess(true);
		updateStatus.setIncidentId(incidentId);

		return updateStatus;
	}
}
