package com.shanghaiwater.mcs.admin.controller;

import java.util.ArrayList;
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
import com.shanghaiwater.mcs.admin.model.MultiPopBenReponse;
import com.shanghaiwater.mcs.admin.model.MultiPopReponse;
import com.shanghaiwater.mcs.admin.model.PageListBasicResponse;
import com.shanghaiwater.mcs.admin.service.UswMultiPopService;
import com.shanghaiwater.mcs.common.util.SecurityUtil;
import com.shanghaiwater.mcs.db.entity.McsIncident;
import com.shanghaiwater.mcs.db.entity.McsUserImageDetail;
import com.shanghaiwater.mcs.db.entity.UswMulitiPop;
import com.shanghaiwater.mcs.db.entity.UswMulitiPopAppCert;
import com.shanghaiwater.mcs.db.entity.UswMulitiPopBen;
import com.shanghaiwater.mcs.db.entity.UswMulitiPopBenCert;
import com.shanghaiwater.mcs.db.mapper.McsIncidentMapper;
import com.shanghaiwater.mcs.db.mapper.McsUserImageDetailMapper;
import com.shanghaiwater.mcs.db.mapper.UswMulitiPopAppCertMapper;
import com.shanghaiwater.mcs.db.mapper.UswMulitiPopBenCertMapper;
import com.shanghaiwater.mcs.db.mapper.UswMulitiPopBenMapper;
import com.shanghaiwater.mcs.db.mapper.UswMulitiPopMapper;

@RestController
@RequestMapping("/home/multi")
@PermissionIntercept
public class MultiPopController {

	@Autowired
	private UswMultiPopService uswMultiPopService;
	
	@Autowired
	private McsIncidentMapper mcsIncidentMapper;
	
	@Autowired
	private UswMulitiPopMapper uswMulitiPopMapper;
	
	@Autowired
	private UswMulitiPopAppCertMapper uswMulitiPopAppCertMapper;

	@Autowired
	private UswMulitiPopBenMapper uswMulitiPopBenMapper;

	@Autowired
	private UswMulitiPopBenCertMapper uswMulitiPopBenCertMapper;
	
	@Autowired
	private McsUserImageDetailMapper mcsUserImageDetailMapper;
	
	/**
	 * 列表页面跳转
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/listview", method = RequestMethod.GET)
	public ModelAndView listView(Model model) {
		return new ModelAndView("views/home/otherBusiness/multi", "model", model);
	}
	
	/**
	 * 列表页面查询
	 * @param request
	 * @param status
	 * @param page
	 * @param limit
	 * @param name
	 * @param phone
	 * @param eStatus
	 * @param companyCode
	 * @param userNo
	 * @param deal
	 * @param startdate
	 * @param enddate
	 * @param model
	 * @return
	 */
	@RequestMapping("/list")
	public PageListBasicResponse list(
			HttpServletRequest request,
			@RequestParam(value = "status", required = false) String status,
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "phone", required = false) String phone,
			@RequestParam(value = "eStatus", required = false) String eStatus,
			@RequestParam(value = "companyCode", required = false) String companyCode,
			@RequestParam(value = "userNo", required = false) String userNo,
			@RequestParam(value = "deal", required = false) String deal,
			@RequestParam(value = "startdate", required = false) String startdate,
			@RequestParam(value = "enddate", required = false) String enddate, Model model) {
		AdminLoginInfo adminLoginInfo = (AdminLoginInfo) request.getSession().getAttribute("adminLoginInfo");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", status);
		
		if(StringUtils.isNotEmpty(startdate)) {
			map.put("startTime", startdate+" 00:00:00");
		}
		if(StringUtils.isNotEmpty(enddate)) {
			map.put("endTime", enddate+" 23:59:59");
		}
		
		map.put("page", page);
		map.put("eStatus", eStatus);
		map.put("limit", limit);
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
		return uswMultiPopService.queryList(map);
	}
	
	
	@RequestMapping("/detail")
	public ModelAndView detail(@RequestParam("incidentId") String incidentId,Model model) {
		MultiPopReponse reponse = new MultiPopReponse();
		List<MultiPopBenReponse> benListReponse = new ArrayList<MultiPopBenReponse>();
		McsIncident incident = mcsIncidentMapper.selectById(incidentId);
		incident.setCertNumber(SecurityUtil.decryptAES(incident.getCertNumber()));
		incident.setShwAddress(SecurityUtil.decryptAES(incident.getShwAddress()));
		UswMulitiPop pop = uswMulitiPopMapper.selectById(incidentId);
		pop.setApplyAddress(SecurityUtil.decryptAES(pop.getApplyAddress()));
		pop.setPhone(SecurityUtil.decryptAES(pop.getPhone()));
		pop.setCertNum(SecurityUtil.decryptAES(pop.getCertNum()));
		
		reponse.setIncident(incident);
		reponse.setPop(pop);
		//受益人列表
		QueryWrapper<UswMulitiPopBen> qwBen = new QueryWrapper<UswMulitiPopBen>();
		qwBen.eq("INCIDENT_ID", incidentId);
		List<UswMulitiPopBen> benList = uswMulitiPopBenMapper.selectList(qwBen);
		if(benList.size()>0) {
			for(UswMulitiPopBen ben:benList) {
				MultiPopBenReponse benReponse = new MultiPopBenReponse();
				benReponse.setBen(ben);
				QueryWrapper<UswMulitiPopBenCert> certBenQw = new QueryWrapper<UswMulitiPopBenCert>();
				certBenQw.eq("INCIDENT_ID", incidentId);
				certBenQw.eq("BEN_ID", ben.getId());
				List<UswMulitiPopBenCert> benCertList = uswMulitiPopBenCertMapper.selectList(certBenQw);
				if(benCertList.size()>0) {
					for(UswMulitiPopBenCert benCert:benCertList) {
						QueryWrapper<McsUserImageDetail> fileQw = new QueryWrapper<McsUserImageDetail>();
						fileQw.eq("APPLY_NO", incident.getApplyNo());
						fileQw.eq("RECORD_ID", benCert.getId());
						List<McsUserImageDetail> imageDetails = mcsUserImageDetailMapper.selectList(fileQw);
						if(imageDetails.size()>0) {
							benCert.setUri(imageDetails.get(0).getWebUri());
						}
					}
				}
				benReponse.setCertList(benCertList);
				benListReponse.add(benReponse);
			}
		}
		reponse.setBenList(benListReponse);
		//申请人证件
		QueryWrapper<UswMulitiPopAppCert> qwAppCert = new QueryWrapper<UswMulitiPopAppCert>();
		qwAppCert.eq("INCIDENT_ID", incidentId);
		List<UswMulitiPopAppCert> appCertList = uswMulitiPopAppCertMapper.selectList(qwAppCert);
		if(appCertList.size()>0) {
			for(UswMulitiPopAppCert appCert:appCertList) {
				QueryWrapper<McsUserImageDetail> fileQw = new QueryWrapper<McsUserImageDetail>();
				fileQw.eq("APPLY_NO", incident.getApplyNo());
				fileQw.eq("RECORD_ID", appCert.getId());
				List<McsUserImageDetail> imageDetails = mcsUserImageDetailMapper.selectList(fileQw);
				if(imageDetails.size()>0) {
					appCert.setUri(imageDetails.get(0).getWebUri());
				}
			}
		}
		reponse.setAppCertList(appCertList);
		model.addAttribute("data", reponse);
		return new ModelAndView("views/home/otherBusiness/multiView", "model", model);
	}
	
	
	
}
