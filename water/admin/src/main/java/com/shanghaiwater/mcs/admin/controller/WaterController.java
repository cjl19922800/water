package com.shanghaiwater.mcs.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shanghaiwater.mcs.admin.annotation.PermissionIntercept;


/**
 * 页面跳转
 * @author Laipu
 *
 */
@Controller
@RequestMapping("/water")
@PermissionIntercept
public class WaterController {

	
	/**
	 * 用水申请（居民用户申请）申请页面
	 * @return
	 */
	@RequestMapping("/apply/resident/user/apply")
	public String userUsewaterApplyPage() {
		return "residentUsewaterApply";
	}
	
	/**
	 * 用水申请（居民用户申请）上传页面
	 * @return
	 */
	@RequestMapping("/apply/resident/apply/upload")
	public String userUsewaterApplyUploadPage() {
		return "residentUsewaterUpload";
	}
	
	/**
	 * 用水申请（居民用户申请）结果页面
	 * @return
	 */
	@RequestMapping("/apply/resident/apply/result")
	public String userUsewaterApplyResultPage() {
		return "residentUsewaterResult";
	}
	
	
	
	@RequestMapping("/apply/company")
	public String companyUsewater() {
		return "companyUsewater";
	}
	/**
	 * 用水申请（单位接水申请）申请页面
	 * @return
	 */
	@RequestMapping("/apply/company/user/apply")
	public String companyUsewaterApply() {
		return "companyUsewaterApply";
	}
	
	/**
	 * 用水申请（单位接水申请）上传页面
	 * @return
	 */
	@RequestMapping("/apply/company/apply/upload")
	public String companyUsewaterUpload() {
		return "companyUsewaterUpload";
	}
	
	/**
	 * 用水申请（单位接水申请）结果页面
	 * @return
	 */
	@RequestMapping("/apply/company/apply/result")
	public String companyUsewaterResult() {
		return "companyUsewaterResult";
	}
	
	
	@RequestMapping("/transfer/resident")
	public String residentChangeAddress() {
		return "residentChangeAddress";
	}
	
	@RequestMapping("/apply/resident/change/apply")
	public String residentChangeAddressApply() {
		return "residentChangeAddressApply";
	}
	
	@RequestMapping("/apply/resident/change/upload")
	public String residentChangeAddressUpload() {
		return "residentChangeAddressUpload";
	}
	
	@RequestMapping("/apply/resident/change/result")
	public String residentChangeAddressResult() {
		return "residentChangeAddressResult";
	}
	
	
	@RequestMapping("/transfer/company")
	public String companyChangeAddress() {
		return "companyChangeAddress";
	}
	
	@RequestMapping("/apply/company/change/apply")
	public String companyChangeAddressApply() {
		return "companyChangeAddressApply";
	}
	
	@RequestMapping("/apply/company/change/upload")
	public String companyChangeAddressUpload() {
		return "companyChangeAddressUpload";
	}
	
	@RequestMapping("/apply/company/change/result")
	public String companyChangeAddressResult() {
		return "companyChangeAddressResult";
	}
	
	
	
	@RequestMapping("/watermeter/split")
	public String tablePack() {
		return "tablePack";
	}
	
	@RequestMapping("/apply/watermeter/split/apply")
	public String tablePackApply() {
		return "tablePackApply";
	}
	
	@RequestMapping("/apply/watermeter/split/upload")
	public String tablePackResult() {
		return "tablePackUpload";
	}
	
	@RequestMapping("/apply/watermeter/split/result")
	public String tablePackUpload() {
		return "tablePackResult";
	}	
}
