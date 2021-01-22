package com.shanghaiwater.mcs.admin.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.shanghaiwater.mcs.admin.common.AdminLoginInfo;
import com.shanghaiwater.mcs.db.mapper.SysRoleResourcesMapper;
import com.shanghaiwater.mcs.db.vo.RoleResourcesPowerVO;

/**
 * 页面跳转
 * 
 * @author Laipu
 *
 */
@Controller
public class HomeController {

	@Autowired
	private SysRoleResourcesMapper sysRoleResourcesMapper;
	
	
	@RequestMapping("/admin")
	public ModelAndView userUsewaterApplyPage(HttpServletRequest request, Model model) {
		AdminLoginInfo adminLoginInfo = (AdminLoginInfo) request.getSession().getAttribute("adminLoginInfo");
		if (adminLoginInfo == null) {
			return new ModelAndView("views/user/login", "model", model);
		}
		//查询权限导航资源
		List<RoleResourcesPowerVO> powers = sysRoleResourcesMapper.selectHomeResourcesByRoleId(adminLoginInfo.getRoleId());
		LinkedHashMap<String,List<RoleResourcesPowerVO>>  map2 = new LinkedHashMap<String,List<RoleResourcesPowerVO>>();
		for(RoleResourcesPowerVO power:powers) {
			List<RoleResourcesPowerVO> powerList = map2.get(power.getParnavName());
			if(null != powerList) {
				powerList.add(power);
				map2.put(power.getParnavName(), powerList);
			}else {
				List<RoleResourcesPowerVO> newList = new ArrayList<RoleResourcesPowerVO>();
				newList.add(power);
				map2.put(power.getParnavName(), newList);
			}
		}
		
		model.addAttribute("powersMapList", map2);
		return new ModelAndView("views/index", "model", model);
	}

}
