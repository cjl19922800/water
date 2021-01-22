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
import com.shanghaiwater.mcs.admin.model.PageListBasicResponse;
import com.shanghaiwater.mcs.admin.model.ResidentApplyUpdateStatusRequest;
import com.shanghaiwater.mcs.admin.model.UswTransferListResponse;
import com.shanghaiwater.mcs.admin.model.UswTransferUpdateStatusResponse;
import com.shanghaiwater.mcs.admin.service.IMgtWaterCompanyService;
import com.shanghaiwater.mcs.admin.service.McsIncidentService;
import com.shanghaiwater.mcs.admin.service.UswCompanyTransferService;
import com.shanghaiwater.mcs.admin.service.UswTransferService;
import com.shanghaiwater.mcs.admin.service.inf.CisCompanyCodeService;
import com.shanghaiwater.mcs.admin.service.inf.ReginCodeService;
import com.shanghaiwater.mcs.common.util.SecurityUtil;
import com.shanghaiwater.mcs.common.vo.DictItem;
import com.shanghaiwater.mcs.common.vo.WaterCompany;
import com.shanghaiwater.mcs.db.entity.McsIncident;
import com.shanghaiwater.mcs.db.entity.McsUserImageDetail;
import com.shanghaiwater.mcs.db.entity.MgtWaterCompany;
import com.shanghaiwater.mcs.db.entity.UswTransfer;
import com.shanghaiwater.mcs.db.mapper.McsIncidentMapper;
import com.shanghaiwater.mcs.db.mapper.McsUserImageDetailMapper;
import com.shanghaiwater.mcs.db.mapper.UswTransferMapper;

/**
 * 页面跳转
 * 
 * @author Laipu
 *
 */
@RestController
@RequestMapping("/home/comtrans")
@PermissionIntercept
public class UswCompanyTransferController {

	@Autowired
	private UswCompanyTransferService uswTransferService;
	
	@Autowired
	private UswTransferService uswCommonTransferService;

	@Autowired
	private McsUserImageDetailMapper imageDetailMapper;
	
	@Autowired
	private IMgtWaterCompanyService mgtWaterCompanyService;
	
	@Autowired
	private UswTransferMapper uswTransferMapper;
	
	@Autowired
	private McsIncidentMapper mcsIncidentMapper;
	
	private final static String[] imgType = {"营业执照", "产权证"};

	@RequestMapping(value = "/listview", method = RequestMethod.GET)
	public ModelAndView listView(Model model) {
		return new ModelAndView("views/home/otherBusiness/unitTransfer", "model", model);
	}

	@RequestMapping("/detail")
	public ModelAndView detailPage(@RequestParam("incidentId") String incidentId, Model model) {
		UswTransfer info = uswTransferMapper.selectById(incidentId);
		McsIncident incident = mcsIncidentMapper.selectById(incidentId);
		
		incident.setCertNumber(SecurityUtil.decryptAES(incident.getCertNumber()));
		incident.setShwAddress(SecurityUtil.decryptAES(incident.getShwAddress()));
		
		info.setAddress(SecurityUtil.decryptAES(info.getAddress()));
		info.setIdNbr(SecurityUtil.decryptAES(info.getIdNbr()));
		info.setFczjhm(SecurityUtil.decryptAES(info.getFczjhm()));
		info.setContactNum(SecurityUtil.decryptAES(info.getContactNum()));
		info.setBillAddress(SecurityUtil.decryptAES(info.getBillAddress()));
		info.setContactValue(SecurityUtil.decryptAES(info.getContactValue()));
		
		
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
		return new ModelAndView("views/home/otherBusiness/unitTransferView", "model", model);
	}

	@RequestMapping("/list")
	public PageListBasicResponse list(HttpServletRequest request,
			@RequestParam(value = "status", required = false) String status,
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "phone", required = false) String phone,
			@RequestParam(value = "deal", required = false) String deal,
			@RequestParam(value = "userNo", required = false) String userNo,
			@RequestParam(value = "companyCode", required = false) String companyCode,
			@RequestParam(value = "eStatus", required = false) String eStatus,
			@RequestParam(value = "startdate", required = false) String startdate,
			@RequestParam(value = "type", required = false) String type,
			@RequestParam(value = "enddate", required = false) String enddate, Model model) {
		AdminLoginInfo adminLoginInfo = (AdminLoginInfo) request.getSession().getAttribute("adminLoginInfo");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("incidentType", type);
		map.put("status", status);
//		map.put("startTime", startdate);
		map.put("deal", deal);
		map.put("eStatus", eStatus);
//		map.put("endTime", enddate);
		map.put("page", page);
		map.put("limit", limit);
		map.put("companyCode", companyCode);
		map.put("userNo", userNo);
		if(StringUtils.isNotEmpty(startdate)) {
			map.put("startTime", startdate+" 00:00:00");
		}
		if(StringUtils.isNotEmpty(enddate)) {
			map.put("endTime", enddate+" 23:59:59");
		}
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
		return uswCommonTransferService.queryTransferList(map);
	}

	@RequestMapping("/updstatus")
	public UswTransferUpdateStatusResponse updateStatus(HttpServletRequest request,
			@RequestBody ResidentApplyUpdateStatusRequest residentApplyUpdateStatusRequest) {

		String incidentId = residentApplyUpdateStatusRequest.getIncidentId();
		String status = residentApplyUpdateStatusRequest.getStatus();

		UswTransferUpdateStatusResponse uswTransferUpdateStatusResponse = uswTransferService.updateStatus(incidentId,
				status);

		uswTransferUpdateStatusResponse.setRequestId(UUID.randomUUID().toString());
		uswTransferUpdateStatusResponse.setCode(0);
		uswTransferUpdateStatusResponse.setSuccess(true);
		uswTransferUpdateStatusResponse.setIncidentId(incidentId);

		return uswTransferUpdateStatusResponse;
	}

}
