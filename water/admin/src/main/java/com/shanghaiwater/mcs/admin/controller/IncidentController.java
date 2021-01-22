package com.shanghaiwater.mcs.admin.controller;

import java.util.HashMap;
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
import com.shanghaiwater.mcs.admin.common.ResponseSuccessModel;
import com.shanghaiwater.mcs.admin.model.IncidentCompanyUpdateRequest;
import com.shanghaiwater.mcs.admin.model.IncidentListResponse;
import com.shanghaiwater.mcs.admin.service.McsIncidentService;
import com.shanghaiwater.mcs.admin.service.UswResidentApplyService;
import com.shanghaiwater.mcs.common.util.GsonUtil;
import com.shanghaiwater.mcs.db.entity.McsIncident;
import com.shanghaiwater.mcs.db.entity.UswResidentApply;

/**
 * 页面跳转
 * 
 * @author Laipu
 *
 */
@RestController
@RequestMapping("/home")
@PermissionIntercept
public class IncidentController {

	private Gson gson = GsonUtil.getInstance();

	@Autowired
	private UswResidentApplyService uswResidentApplyService;

	@Autowired
	private McsIncidentService mcsIncidentService;

	@RequestMapping(value = "/incident/listview", method = RequestMethod.GET)
	public ModelAndView listView(Model model) {

		return new ModelAndView("views/home/incident/incident", "toolModel", model);
	}

	@RequestMapping("/detail")
	public ModelAndView detailPage(@RequestParam("incidentId") String incidentId, Model model) {
		System.out.println(incidentId);

		UswResidentApply uswResidentApply = uswResidentApplyService.getById(incidentId);
		model.addAttribute("info", uswResidentApply);

		return new ModelAndView("views/usewater/company_apply/iframe", "res", model);
	}

	@RequestMapping("/incident/list")
	public IncidentListResponse list(HttpServletRequest request,
			@RequestParam(value = "status", required = false) String status,
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "startdate", required = false) String startdate,
			@RequestParam(value = "enddate", required = false) String enddate,
			@RequestParam(value = "incidentType", required = false) String incidentType, Model model) {

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
		map.put("incidentType", incidentType);

		return mcsIncidentService.queryList(map);
	}

	@RequestMapping("/incident/updcompany")
	public ResponseSuccessModel updateCompany(HttpServletRequest request, @RequestBody String payload) {

		IncidentCompanyUpdateRequest incidentCompanyUpdateRequest = gson.fromJson(payload,
				IncidentCompanyUpdateRequest.class);

		String incidentId = incidentCompanyUpdateRequest.getIncidentId();
		String companyCode = incidentCompanyUpdateRequest.getCompanyCode();

		McsIncident incident = new McsIncident();
		incident.setIncidentId(incidentId);
		incident.setShwCompany(companyCode);
		incident.setStatus("IncidentStatus.Processing");

		mcsIncidentService.updateById(incident);

		ResponseSuccessModel responseModel = new ResponseSuccessModel();
		responseModel.setRequestId(UUID.randomUUID().toString());
		responseModel.setCode(0);
		responseModel.setSuccess(true);
		responseModel.setIncidentId(incidentId);

		return responseModel;
	}

	@RequestMapping("/incident/updstatus")
	public ResponseSuccessModel updateStatus(HttpServletRequest request, @RequestBody String payload) {

		McsIncident incident = gson.fromJson(payload, McsIncident.class);

		mcsIncidentService.updateById(incident);

		ResponseSuccessModel responseModel = new ResponseSuccessModel();
		responseModel.setRequestId(UUID.randomUUID().toString());
		responseModel.setCode(0);
		responseModel.setSuccess(true);
		responseModel.setIncidentId(incident.getIncidentId());

		return responseModel;
	}
}
