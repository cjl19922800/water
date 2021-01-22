package com.shanghaiwater.mcs.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.shanghaiwater.mcs.admin.annotation.PermissionIntercept;
import com.shanghaiwater.mcs.admin.service.UswResidentApplyService;

/**
 * 页面跳转
 * 
 * @author Laipu
 *
 */
@RestController
@RequestMapping("/home")
@PermissionIntercept
public class OtherBusinessController {
	
	@Autowired
	private UswResidentApplyService uswResidentApplyService;

	@RequestMapping(value = "/otherBusiness/residentTransfer", method = RequestMethod.GET)
	public ModelAndView listViewresidentTransfer(Model model) {
		model.addAttribute("title", "Capi测试主页面");
		return new ModelAndView("views/home/otherBusiness/residentTransfer", "toolModel", model);
	}

	@RequestMapping(value = "/otherBusiness/unitTransfer", method = RequestMethod.GET)
	public ModelAndView listViewunitTransfer(Model model) {
		model.addAttribute("title", "Capi测试主页面");
		return new ModelAndView("views/home/otherBusiness/unitTransfer", "toolModel", model);
	}

	@RequestMapping(value = "/otherBusiness/summaryPacking", method = RequestMethod.GET)
	public ModelAndView listViewsummaryPacking(Model model) {
		model.addAttribute("title", "Capi测试主页面");
		return new ModelAndView("views/home/otherBusiness/summaryPacking", "toolModel", model);
	}

}
