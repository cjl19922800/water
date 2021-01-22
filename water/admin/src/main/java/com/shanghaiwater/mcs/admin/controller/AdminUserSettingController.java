package com.shanghaiwater.mcs.admin.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.shanghaiwater.mcs.admin.annotation.PermissionIntercept;
import com.shanghaiwater.mcs.admin.model.ResidentApplyListResponse;
import com.shanghaiwater.mcs.admin.model.ResidentApplyUpdateStatusRequest;
import com.shanghaiwater.mcs.admin.model.ResidentApplyUpdateStatusResponse;
import com.shanghaiwater.mcs.admin.service.UswResidentApplyService;
import com.shanghaiwater.mcs.common.util.GsonUtil;

/**
 * 页面跳转
 * 
 * @author Laipu
 *
 */
@RestController
@RequestMapping("/set/adminuser")
@PermissionIntercept
public class AdminUserSettingController {

	private Gson gson = GsonUtil.getInstance();

	@Autowired
	private UswResidentApplyService uswResidentApplyService;

	@RequestMapping("/detail")
	public ModelAndView detailPage(@RequestParam(value = "incidentId", required = false) String incidentId,
			Model model) {
		System.out.println(incidentId);

//		UswResidentApply uswResidentApply = uswResidentApplyService.getById(incidentId);
//		model.addAttribute("info", uswResidentApply);

		return new ModelAndView("views/set/user/info", "model", model);
	}

	@RequestMapping("/list")
	public ResidentApplyListResponse list(@RequestParam(value = "status", required = false) String status,
			@RequestParam(value = "startdate", required = false) String startdate,
			@RequestParam(value = "enddate", required = false) String enddate, Model model) {

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("status", status);
		map.put("startdate", startdate);
		map.put("enddate", enddate);

		return uswResidentApplyService.queryList(map);
	}

	@RequestMapping("/updstatus")
	public ResidentApplyUpdateStatusResponse updateStatus(HttpServletRequest request, @RequestBody String payload) {

		ResidentApplyUpdateStatusRequest residentApplyUpdateStatusRequest = gson.fromJson(payload,
				ResidentApplyUpdateStatusRequest.class);

		String incidentId = residentApplyUpdateStatusRequest.getIncidentId();
		String status = residentApplyUpdateStatusRequest.getStatus();

//		reqParam.setRequestId(requestId);
//		jsonResponse = groupAction.listGroups(reqParam);

		ResidentApplyUpdateStatusResponse residentApplyUpdateStatusResponse = uswResidentApplyService
				.updateStatus(incidentId, status);

		residentApplyUpdateStatusResponse.setRequestId(UUID.randomUUID().toString());
		residentApplyUpdateStatusResponse.setCode(0);
		residentApplyUpdateStatusResponse.setSuccess(true);
		residentApplyUpdateStatusResponse.setIncidentId(incidentId);

		return residentApplyUpdateStatusResponse;
	}

}
