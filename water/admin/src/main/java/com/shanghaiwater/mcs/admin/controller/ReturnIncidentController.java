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

import com.shanghaiwater.mcs.admin.annotation.PermissionIntercept;
import com.shanghaiwater.mcs.admin.common.AdminLoginInfo;
import com.shanghaiwater.mcs.db.entity.MgtWaterCompany;
import com.shanghaiwater.mcs.admin.model.IncidentListResponse;
import com.shanghaiwater.mcs.admin.service.IMgtWaterCompanyService;
import com.shanghaiwater.mcs.admin.service.McsIncidentService;

/**
 * 页面跳转
 * 
 * @author Laipu
 *
 */
@RestController
@RequestMapping("/home/retincident")
@PermissionIntercept
public class ReturnIncidentController {

	@Autowired
	private McsIncidentService mcsIncidentService;

	@Autowired
	private IMgtWaterCompanyService mgtWaterCompanyService;

	@RequestMapping(value = "/listview", method = RequestMethod.GET)
	public ModelAndView listView(Model model) {

		return new ModelAndView("views/home/incident/retincident", "toolModel", model);
	}

	@RequestMapping("/list")
	public IncidentListResponse list(HttpServletRequest request,
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
		map.put("status", "IncidentStatus.Return");
		map.put("startdate", startdate);
		map.put("enddate", enddate);
		map.put("page", page);
		map.put("limit", limit);
		map.put("isNormalAdmin", isNormalAdmin);
		map.put("companyCode", companyCode);
		map.put("incidentType", incidentType);

		return mcsIncidentService.queryList(map);
	}

	@RequestMapping(value = "/reassignview", method = RequestMethod.GET)
	public ModelAndView reAssignView(@RequestParam("incidentId") String incidentId, Model model) {
		List<MgtWaterCompany> mgtWaterCompanyList = mgtWaterCompanyService.list();
		model.addAttribute("waterCompanyList", mgtWaterCompanyList);
		model.addAttribute("incidentId", incidentId);

		return new ModelAndView("views/home/incident/reassign", "model", model);
	}
}
