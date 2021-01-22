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

import com.google.gson.Gson;
import com.shanghaiwater.mcs.admin.annotation.PermissionIntercept;
import com.shanghaiwater.mcs.admin.common.AdminLoginInfo;
import com.shanghaiwater.mcs.admin.model.CompanyApplyListResponse;
import com.shanghaiwater.mcs.admin.model.CompanyApplyUpdateStatusResponse;
import com.shanghaiwater.mcs.admin.model.ResidentApplyUpdateStatusRequest;
import com.shanghaiwater.mcs.admin.service.McsIncidentService;
import com.shanghaiwater.mcs.admin.service.UswCompanyApplyService;
import com.shanghaiwater.mcs.admin.service.inf.CisCompanyCodeService;
import com.shanghaiwater.mcs.admin.service.inf.ReginCodeService;
import com.shanghaiwater.mcs.common.util.GsonUtil;
import com.shanghaiwater.mcs.common.vo.AreaTown;
import com.shanghaiwater.mcs.common.vo.DictItem;
import com.shanghaiwater.mcs.common.vo.WaterCompany;
import com.shanghaiwater.mcs.db.entity.McsIncident;
import com.shanghaiwater.mcs.db.entity.UswCompanyApply;

/**
 * 页面跳转
 * 
 * @author Laipu
 *
 */
@RestController
@RequestMapping("/comapply")
@PermissionIntercept
public class CompanyApplyController {

	private Gson gson = GsonUtil.getInstance();

	@Autowired
	private UswCompanyApplyService uswCompanyApplyService;

	@Autowired
	private McsIncidentService mcsIncidentService;

	@Autowired
	private ReginCodeService reginCodeService;

	@Autowired
	private CisCompanyCodeService cisCompanyCodeService;

	@RequestMapping(value = "/listview", method = RequestMethod.GET)
	public ModelAndView listView(Model model) {

		return new ModelAndView("views/usewater/company_apply/company_apply", "toolModel", model);
	}

	@RequestMapping("/detail")
	public ModelAndView detailPage(@RequestParam("incidentId") String incidentId, Model model) {
		System.out.println(incidentId);

		UswCompanyApply uswCompanyApply = uswCompanyApplyService.getById(incidentId);
		McsIncident mcsIncident = mcsIncidentService.getById(incidentId);

		List<WaterCompany> itemCode1 = null;// cisCompanyCodeService
											// .selectCisCompanyByCompanyCode(uswCompanyApply.getCisCompany());
		String code1 = itemCode1.get(0).getName();
		uswCompanyApply.setCisCompany(code1);
		List<DictItem> itemCode11 = reginCodeService.selectDictByItemCode(mcsIncident.getStatus());
		String code11 = itemCode11.get(0).getItemValue();
		mcsIncident.setStatus(code11);
		List<DictItem> itemCode3 = reginCodeService.selectDictByItemCode(mcsIncident.getIncidentType());
		String code3 = itemCode3.get(0).getItemValue();
		mcsIncident.setIncidentType(code3);
		List<DictItem> itemCode2 = reginCodeService.selectDictByItemCode(uswCompanyApply.getApplyContent());
		String code2 = itemCode2.get(0).getItemValue();
		uswCompanyApply.setApplyContent(code2);
		List<AreaTown> itemCode = reginCodeService.selectTownByArea(uswCompanyApply.getXzq());
		String code = itemCode.get(0).getQxmc();
		uswCompanyApply.setXzq(code);
		List<AreaTown> itemCode4 = reginCodeService.selectOneTown(uswCompanyApply.getJdz());
		String code4 = itemCode4.get(0).getJdzmc();
		uswCompanyApply.setJdz(code4);
		List<DictItem> itemCode12 = reginCodeService.selectDictByItemCode(uswCompanyApply.getQyzjlx());
		String code12 = itemCode12.get(0).getItemValue();
		uswCompanyApply.setQyzjlx(code12);
		List<DictItem> itemCode5 = reginCodeService.selectDictByItemCode(uswCompanyApply.getBuildType());
		String code5 = itemCode5.get(0).getItemValue();
		uswCompanyApply.setBuildType(code5);
		List<DictItem> itemCode6 = reginCodeService.selectDictByItemCode(uswCompanyApply.getKhxz());
		String code6 = itemCode6.get(0).getItemValue();
		uswCompanyApply.setKhxz(code6);
		List<DictItem> itemCode7 = reginCodeService.selectDictByItemCode(uswCompanyApply.getYdxz());
		String code7 = itemCode7.get(0).getItemValue();
		uswCompanyApply.setYdxz(code7);
		List<DictItem> itemCode8 = reginCodeService.selectDictByItemCode(uswCompanyApply.getSqlb());
		String code8 = itemCode8.get(0).getItemValue();
		uswCompanyApply.setSqlb(code8);

		List<List<String>> list = uswCompanyApplyService.queryImages(incidentId);

		model.addAttribute("url1", list.get(0));
		model.addAttribute("url2", list.get(1));
		model.addAttribute("url3", list.get(2));
		model.addAttribute("url4", list.get(3));
		model.addAttribute("url5", list.get(4));
		model.addAttribute("url6", list.get(5));
		model.addAttribute("url7", list.get(6));
		model.addAttribute("url8", list.get(7));
		model.addAttribute("url9", list.get(8));
		model.addAttribute("url10", list.get(9));
		model.addAttribute("info", uswCompanyApply);
		model.addAttribute("incident", mcsIncident);
		return new ModelAndView("views/usewater/company_apply/iframe", "model", model);
	}

	@RequestMapping("/list")
	public CompanyApplyListResponse list(HttpServletRequest request,
			@RequestParam(value = "status", required = false) String status,
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "startdate", required = false) String startdate,
			@RequestParam(value = "enddate", required = false) String enddate, Model model) {

		AdminLoginInfo adminLoginInfo = (AdminLoginInfo) request.getSession().getAttribute("adminLoginInfo");
		boolean isNormalAdmin = true;

		if (adminLoginInfo.getIsRootAdmin() || adminLoginInfo.getIsSHWAdmin()) {
			isNormalAdmin = false;
		}

		String companyCode = adminLoginInfo.getCompanyCode();

		Map<String, Object> map = new HashMap<String, Object>();

		page = (page - 1) * limit;
		map.put("status", status);
		map.put("startdate", startdate);
		map.put("enddate", enddate);
		map.put("page", page);
		map.put("limit", limit);
		map.put("isNormalAdmin", isNormalAdmin);
		map.put("companyCode", companyCode);

		return uswCompanyApplyService.queryList(map);
	}

	@RequestMapping("/updstatus")
	public CompanyApplyUpdateStatusResponse updateStatus(HttpServletRequest request, @RequestBody String payload) {

		ResidentApplyUpdateStatusRequest residentApplyUpdateStatusRequest = gson.fromJson(payload,
				ResidentApplyUpdateStatusRequest.class);

		String incidentId = residentApplyUpdateStatusRequest.getIncidentId();
		String status = residentApplyUpdateStatusRequest.getStatus();

//		reqParam.setRequestId(requestId);
//		jsonResponse = groupAction.listGroups(reqParam);

		CompanyApplyUpdateStatusResponse response = uswCompanyApplyService.updateStatus(incidentId, status);

		response.setRequestId(UUID.randomUUID().toString());
		response.setCode(0);
		response.setSuccess(true);
		response.setIncidentId(incidentId);

		return response;
	}
}
