package com.shanghaiwater.mcs.admin.service.inf;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.tempuri.AddressResultEntity;
import org.tempuri.ArrayOfBillings;
import org.tempuri.ArrayOfCertList;
import org.tempuri.ArrearsBillResultEntity;
import org.tempuri.Billings;
import org.tempuri.CertList;
import org.tempuri.QueryArrearsBill;
import org.tempuri.QueryArrearsBillEntity;
import org.tempuri.RepairEntity;
import org.tempuri.ResidentApplyEntity;
import org.tempuri.ResultEntity;
import org.tempuri.WatermeterSplitEntity;
import org.tempuri.XiangYingEntity;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shanghaiwater.mcs.admin.enums.LogicContentType;
import com.shanghaiwater.mcs.admin.service.SysDictItemService;
import com.shanghaiwater.mcs.admin.util.CommonUtil;
import com.shanghaiwater.mcs.common.enums.ItemCodeEnum;
import com.shanghaiwater.mcs.common.exception.MCSException;
import com.shanghaiwater.mcs.common.model.WSAddressResult;
import com.shanghaiwater.mcs.common.model.WSRepairResult;
import com.shanghaiwater.mcs.common.util.DateUtils;
import com.shanghaiwater.mcs.common.util.GsonUtil;
import com.shanghaiwater.mcs.db.entity.McsIncident;
import com.shanghaiwater.mcs.db.entity.McsUserImageDetail;
import com.shanghaiwater.mcs.db.entity.RprUsewaterRepair;
import com.shanghaiwater.mcs.db.entity.UswResidentApply;
import com.shanghaiwater.mcs.db.entity.UswWatermeterSplit;
import com.shanghaiwater.mcs.db.mapper.McsUserImageDetailMapper;
import com.shanghaiwater.mcs.mcsif.wsc.ThreeFgsWSClient;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ThreeFgsClientService {

	private static int loopTime = 3;
	
	@Value("${iniswitch.imageFlag}")
	private boolean imgFlag;
	
	@Autowired
	private McsUserImageDetailMapper imageDetailMapper;
	
	@Autowired
	private SysDictItemService sysDictItemService;
	
	
	
	public WSAddressResult queryAddressResult(String endpoint,String cardId) {
		WSAddressResult wsResult = new WSAddressResult();
		boolean isSuccess = true;
		for (int loop = 0; loop < 3; loop++) {
			try {
				ThreeFgsWSClient threeFgsWSClient = new ThreeFgsWSClient(endpoint);
				System.out.println("\n\n\n开始调用-地址查询-WebService接口：");
				System.out.println("endpoint：" + endpoint);
				System.out.println("");
				System.out.println("请求内容：");
				System.out.println("card_id=" + cardId);
				AddressResultEntity addressResultEntity = new AddressResultEntity();
				addressResultEntity = threeFgsWSClient.getAddress(cardId);
				System.out.println("执行结果：");
				System.out.println(GsonUtil.getInstance().toJson(addressResultEntity));
				System.out.println("");
				System.out.println("IsSuccess=" + addressResultEntity.getIsSuccess());
				System.out.println("Code=" + addressResultEntity.getCode());
				System.out.println("ErrCode=" + addressResultEntity.getErrCode());
				System.out.println("Message=" + addressResultEntity.getMessage());
				System.out.println("Shw_address=" + addressResultEntity.getShw_address());

				System.out.println("\n调用完成-地址查询-WebService接口。\n\n");
				BeanUtils.copyProperties(addressResultEntity, wsResult);
				isSuccess = addressResultEntity.getIsSuccess();
				if(isSuccess) {
					break;
				}
			} catch (Exception ex) {
				wsResult.setIsSuccess(false);
				wsResult.setErrCode("web.err");
				wsResult.setMessage("地址接口调用失败");
				wsResult.setCode(500);
			}
		}
		return wsResult;
	}
	
	public WSRepairResult waterApply(McsIncident incident,UswResidentApply apply,String endpoint){
		WSRepairResult result = new WSRepairResult();
		boolean isSuccess = false;
		ResidentApplyEntity residentApplyEntity = new ResidentApplyEntity();
		for (int loop = 0; loop < loopTime; loop++) {
			try {
				ThreeFgsWSClient threeFgsWSClient = new ThreeFgsWSClient(endpoint);
				ArrayOfCertList arrayOfCertList = getArrayOfCertList(incident);
				residentApplyEntity.setCertList(arrayOfCertList);
				residentApplyEntity.setIncident_id(incident.getIncidentId());
				residentApplyEntity.setApply_no(incident.getApplyNo());
				residentApplyEntity.setShw_address(incident.getShwAddress());
				residentApplyEntity.setApplicant(incident.getApplicant());
				residentApplyEntity.setAppdate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(incident.getAppdate()));
				residentApplyEntity.setIncident_type(incident.getIncidentType());
				residentApplyEntity.setCert_type(incident.getCertType());
				residentApplyEntity.setCert_number(incident.getCertNumber());
				residentApplyEntity.setBusiness_type(apply.getBusinessType());
				if(incident.isTransfer()) {
					residentApplyEntity.setIs_transfer_order("1");
					residentApplyEntity.setShw_company_source(incident.getTransferCompany());
				}else {
					residentApplyEntity.setIs_transfer_order("0");
					residentApplyEntity.setShw_company_source("");
				}
				
				residentApplyEntity.setXzq(incident.getXzq());
				residentApplyEntity.setJdz(incident.getTown());
				residentApplyEntity.setFczhm(apply.getHourseCertNo());
				residentApplyEntity.setAddress(apply.getAddress());
				residentApplyEntity.setFensuo(getFensuoByCompanyCode(incident.getShwCompany()));
				String applyContent = sysDictItemService.findItemValue("UswTypeResident", apply.getApplyContent());
				residentApplyEntity.setApply_content(applyContent);
				residentApplyEntity.setApply_memo("个人居住");
				residentApplyEntity.setCon_name(apply.getConName());
				residentApplyEntity.setPhone(apply.getPhone());
				residentApplyEntity.setMail(apply.getMail());
				residentApplyEntity.setContact_address(apply.getContactAddress());
				System.out.println("\n\n\n开始调用-居民住宅单独接水-WebService接口：");
				System.out.println("endpoint：" + endpoint);
				System.out.println("");
				System.out.println("请求内容：");
				System.out.println(GsonUtil.getInstance().toJson(residentApplyEntity));
				XiangYingEntity xiangYingEntity = threeFgsWSClient.residentApply(residentApplyEntity);
				System.out.println("执行结果：");
				System.out.println(GsonUtil.getInstance().toJson(xiangYingEntity));
				System.out.println("");
				System.out.println("IsSuccess=" + xiangYingEntity.getIsSuccess());
				System.out.println("Code=" + xiangYingEntity.getCode());
				System.out.println("ErrCode=" + xiangYingEntity.getErrCode());
				System.out.println("Message=" + xiangYingEntity.getMessage());
				System.out.println("Incident id=" + xiangYingEntity.getIncident_id());
				System.out.println("\n调用完成-居民住宅单独接水-WebService接口。\n\n");
				isSuccess = xiangYingEntity.getIsSuccess();
				result.setIsSuccess(isSuccess);
				result.setCode(xiangYingEntity.getCode());
				result.setIncident_id(xiangYingEntity.getIncident_id());
				result.setErrCode(xiangYingEntity.getErrCode());
				result.setMessage(xiangYingEntity.getMessage());
				if(isSuccess) {
					break;
				}
			} catch (Exception ex) {
				result.setIsSuccess(false);
				result.setCode(500);
				result.setIncident_id(incident.getIncidentId());
				result.setErrCode("Web.Err");
				result.setMessage("调用失败");
			}
		}
		return result;
	}
	
	public WSRepairResult waterSplitApply(McsIncident incident,UswWatermeterSplit split,String endpoint) {
		WSRepairResult wResult = new WSRepairResult();
		boolean isSuccess= true;
		for (int loop = 0; loop < loopTime; loop++) {
			try {
				ThreeFgsWSClient threeFgsWSClient = new ThreeFgsWSClient(endpoint);
				//上传的图片
				ArrayOfCertList arrayOfCertList = getArrayOfCertList(incident);
				//申请单信息
				WatermeterSplitEntity watermeterSplitEntity = new WatermeterSplitEntity();
				watermeterSplitEntity.setCertList(arrayOfCertList);
				watermeterSplitEntity.setIncident_id(incident.getIncidentId());
				watermeterSplitEntity.setApply_no(incident.getApplyNo());
				watermeterSplitEntity.setShw_address(incident.getShwAddress());
				watermeterSplitEntity.setApplicant(incident.getApplicant());
				watermeterSplitEntity.setAppdate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(incident.getAppdate()));
				watermeterSplitEntity.setIncident_type(incident.getIncidentType());
				watermeterSplitEntity.setCert_type(incident.getCertType());
				watermeterSplitEntity.setCert_number(incident.getCertNumber());
				watermeterSplitEntity.setEmail(incident.getEmail());
				watermeterSplitEntity.setBusiness_type(split.getBusinessType());
				if(incident.isTransfer()) {
					watermeterSplitEntity.setIs_transfer_order("1");
					watermeterSplitEntity.setShw_company_source(incident.getTransferCompany());
				}else {
					watermeterSplitEntity.setIs_transfer_order("0");
					watermeterSplitEntity.setShw_company_source("");
				}
				
				
				watermeterSplitEntity.setXzq(incident.getXzq());
				watermeterSplitEntity.setJdz(incident.getTown());
				watermeterSplitEntity.setAddress(split.getAddress());
				watermeterSplitEntity.setFensuo(getFensuoByCompanyCode(incident.getShwCompany()));
				watermeterSplitEntity.setCon_name(split.getConName());
				watermeterSplitEntity.setPhone(split.getPhone());
				watermeterSplitEntity.setContact_value(split.getContactValue());
				watermeterSplitEntity.setMail(split.getMail());
				watermeterSplitEntity.setYb(split.getYb());
				watermeterSplitEntity.setSplit_comment(split.getSplitComment());
				watermeterSplitEntity.setCard_id(incident.getUserNo());
				
				System.out.println("\n\n\n开始调用-居民住宅总表分装-WebService接口：");
				System.out.println("endpoint：" + endpoint);
				System.out.println("");
				System.out.println("请求内容：");
				System.out.println(GsonUtil.getInstance().toJson(watermeterSplitEntity));
				XiangYingEntity xiangYingEntity = threeFgsWSClient.watermeterSplit(watermeterSplitEntity);
				System.out.println("执行结果：");
				System.out.println(GsonUtil.getInstance().toJson(xiangYingEntity));
				System.out.println("");
				System.out.println("IsSuccess=" + xiangYingEntity.getIsSuccess());
				System.out.println("Code=" + xiangYingEntity.getCode());
				System.out.println("ErrCode=" + xiangYingEntity.getErrCode());
				System.out.println("Message=" + xiangYingEntity.getMessage());
				System.out.println("Incident id=" + xiangYingEntity.getIncident_id());
				System.out.println("\n调用完成-居民住宅总表分装-WebService接口。\n\n");
				isSuccess = xiangYingEntity.getIsSuccess();
				BeanUtils.copyProperties(xiangYingEntity, wResult);
				if(isSuccess) {
					break;
				}
			} catch (Exception ex) {
				wResult.setCode(500);
				wResult.setErrCode("web.err");
				wResult.setIncident_id(incident.getIncidentId());
				wResult.setIsSuccess(false);
				wResult.setMessage("三高接口调用失败");
			}
		}
		return wResult;
	}
	
	
	
	
	public WSRepairResult repair(McsIncident i,RprUsewaterRepair a,String endpoint) {
		WSRepairResult result = new WSRepairResult();
		for (int loop = 0; loop < 3; loop++) {
			try {
				ThreeFgsWSClient threeFgsWSClient = new ThreeFgsWSClient(endpoint);
				String incident_id = i.getIncidentId();
				String apply_no = i.getApplyNo();
				String custom_type = "01";
				String shw_address = a.getAddress();
				String applicant = i.getApplicant();
				String appdate = DateUtils.currentDateTime();
				String incident_type = i.getIncidentType(); 
				String is_transfer_order = "0";
				String shw_company_source = "";
				if(i.isTransfer()) {
					is_transfer_order = "1";
					shw_company_source = i.getTransferCompany();
				}
//				String cert_type = sysDictItemService.findItemValue("CertificateType", i.getCertType());
				String cert_number = i.getCertNumber();
				String email = i.getEmail();
				String company_name = i.getCompanyName();
				String company_type = i.getCompanyType();
				if (StringUtils.isNotEmpty(i.getCompanyType())) {
					company_type = sysDictItemService.findItemValue("CompanyType", i.getCompanyType());
				}
				String social_credit_code = i.getSocialCreditCode();
				String case_type_cd = "报修";
				String card_id = i.getUserNo();
				String address = a.getAddress();
				String contact_value = a.getContactValue();
				String fyxs = "网络";
//				String fyqm = commonService.getMultiCodeDetailName(a.getFyqm(), "SHWXZQH");
				String fylb = LogicContentType.getFylb(incident_type);
				
				String fynr = "";
				if(i.getIncidentType().equals(ItemCodeEnum.REPAIR_OTHER_RESIDENT.getIncidentType())|| 
						i.getIncidentType().equals(ItemCodeEnum.REPAIR_OTHER_COMPANY.getIncidentType())) {
					fynr = a.getFynr();
				}else {
					fynr = sysDictItemService.findItemValue("RepairContent", a.getFynr());
				}
				String bx_loc_cd = sysDictItemService.findItemValue("RepairPosition", a.getBxLocCd()); // 报修内容
				String bx_dttm = DateUtils.currentDateTime();
				int clTime = ItemCodeEnum.getCljbTime(incident_type);
				int cljb = ItemCodeEnum.getCljb(incident_type);
//				Date date = CommonUtil.dateAddDay(new Date(), cljb);
				Date date = CommonUtil.dateAdd(new Date(), clTime,Calendar.HOUR);
				String cn_dttm = CommonUtil.dateFormatString("yyyy-MM-dd HH:mm:ss", date);
				RepairEntity repairEntity = new RepairEntity();
				repairEntity.setIncident_id(incident_id);
				repairEntity.setApply_no(apply_no);
				repairEntity.setCustom_type(custom_type);
				repairEntity.setShw_address(shw_address);
				repairEntity.setApplicant(applicant);
				repairEntity.setAppdate(appdate);
				repairEntity.setIncident_type(incident_type);
				repairEntity.setIs_transfer_order(is_transfer_order);
				repairEntity.setShw_company_source(shw_company_source);
				repairEntity.setCert_type(i.getCertType());
				repairEntity.setCert_number(cert_number);
				repairEntity.setEmail(email);
				repairEntity.setCompany_name(company_name);
				repairEntity.setCompany_type(company_type);
				repairEntity.setSocial_credit_code(social_credit_code);
				repairEntity.setCase_type_cd(case_type_cd);
				repairEntity.setCard_id(card_id);
				repairEntity.setAddress(address);
				repairEntity.setContact_value(contact_value);
				repairEntity.setFyly(a.getFyly());
				repairEntity.setFyxs(fyxs);
				repairEntity.setFyqm(a.getFyqm());
				repairEntity.setFylb(fylb);
				repairEntity.setFynr(fynr);
				repairEntity.setBx_dttm(bx_dttm);
				repairEntity.setCljb(String.valueOf(cljb));
				repairEntity.setCn_dttm(cn_dttm);
				repairEntity.setBx_loc_cd(bx_loc_cd);
				repairEntity.setRepair_comment(a.getRepairComment());
				repairEntity.setCertList(getArrayOfCertList(i));
				log.info("\n\n\n开始调用-报修-WebService接口：");
				log.info("endpoint：" + endpoint);
				log.info("");
				log.info("请求内容：");
				log.info(GsonUtil.getInstance().toJson(repairEntity));
				XiangYingEntity xiangYingEntity = threeFgsWSClient.repair(repairEntity);
				if (xiangYingEntity == null) {
					new MCSException("ERR.WebService.ReturnNULL", "Web Service return null.");
				}
				log.info("执行结果：");
				log.info(GsonUtil.getInstance().toJson(xiangYingEntity));
				log.info("IsSuccess=" + xiangYingEntity.getIsSuccess());
				log.info("Code=" + xiangYingEntity.getCode());
				log.info("ErrCode=" + xiangYingEntity.getErrCode());
				log.info("Message=" + xiangYingEntity.getMessage());
				log.info("Incident id=" + xiangYingEntity.getIncident_id());
				log.info("\n调用完成-报修-WebService接口。\n\n");
				BeanUtils.copyProperties(xiangYingEntity, result);
				if(xiangYingEntity.getIsSuccess()) {
					break;
				}
			} catch (Exception ex) {
				result.setCode(500);
				result.setErrCode("web.err");
				result.setIncident_id(i.getIncidentId());
				result.setIsSuccess(false);
				result.setMessage("三高接口调用失败");
			}
		}
		return result;
	}
	
	
	
	
	
	
	/**
	 * 文件信息公用
	 * @param incident
	 * @return
	 */
	public ArrayOfCertList getArrayOfCertList(McsIncident incident){
		ArrayOfCertList arrayOfCertList = new ArrayOfCertList();
		if(imgFlag) {
			QueryWrapper<McsUserImageDetail> queWrapper = new QueryWrapper<McsUserImageDetail>();
			queWrapper.eq("apply_no", incident.getApplyNo());
			List<McsUserImageDetail> selectList = imageDetailMapper.selectList(queWrapper);
			for(McsUserImageDetail image : selectList) {
				CertList cert = new CertList();
				cert.setCert_source(image.getUploadSource());//固定为"用户上传"
				cert.setCert_type(image.getCertType());
				cert.setCert_number(image.getCertNumber());
				cert.setFile_uuid(image.getImageUuid());
				cert.setFilename(image.getFilename());
				cert.setWeb_uri(image.getFilename());
				arrayOfCertList.addCertList(cert);
			}
		}
		return arrayOfCertList;
	}
	
	
	/**
	 * 查询分所
	 * @param companyCode
	 * @return
	 */
	public String getFensuoByCompanyCode(String companyCode) {
		String fensuo = "";
		switch (companyCode) {
		case "GSFGS":
		case "YWSLFGS":
			fensuo = "供水管理所";
			break;
		case "PDWLYZLS":
			fensuo = "业务受理中心";
			break;
		case "PDXQZLS":
			fensuo = "营业所";
			break;
		case "SBBSZLS":
			fensuo = "业务管理科";
			break;
		default:
			break;
		}
		return fensuo;
	}
	
	
	
	
	
}
