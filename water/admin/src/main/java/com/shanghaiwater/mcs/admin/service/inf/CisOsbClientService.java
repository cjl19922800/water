package com.shanghaiwater.mcs.admin.service.inf;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shanghaiwater.mcs.admin.enums.LogicContentType;
import com.shanghaiwater.mcs.admin.service.SysDictItemService;
import com.shanghaiwater.mcs.admin.util.CommonUtil;
import com.shanghaiwater.mcs.common.enums.ItemCodeEnum;
import com.shanghaiwater.mcs.common.exception.MCSException;
import com.shanghaiwater.mcs.common.model.WSAddressResult;
import com.shanghaiwater.mcs.common.model.WSRepairResult;
import com.shanghaiwater.mcs.common.model.incident.CisAttach;
import com.shanghaiwater.mcs.common.model.incident.RepairRequest;
import com.shanghaiwater.mcs.common.util.DateUtils;
import com.shanghaiwater.mcs.common.util.GsonUtil;
import com.shanghaiwater.mcs.db.entity.McsIncident;
import com.shanghaiwater.mcs.db.entity.McsUserImageDetail;
import com.shanghaiwater.mcs.db.entity.RprUsewaterRepair;
import com.shanghaiwater.mcs.db.mapper.McsUserImageDetailMapper;
import com.shanghaiwater.mcs.mcsif.wsc.CisOSBWSClient;
import com.shanghaiwater.mcs.mcsif.wsc.models.CisAccdInfo;
import com.shanghaiwater.mcs.mcsif.wsc.models.CisCustomerCert;
import com.shanghaiwater.mcs.mcsif.wsc.models.CisRepairResult;
import com.shanghaiwater.mcs.mcsif.wsc.models.CisWatermeterInfo;

@Service
public class CisOsbClientService {

	private static int loopTime = 3;
	
	@Autowired
	private SysDictItemService sysDictItemService;
	
	@Autowired
	private McsUserImageDetailMapper imageDetailMapper;

	@Value("${cis.endpointCisAddress}")
	private String endpointCisAddress;
	
	@Value("${cis.endpointCisBill}")
	private String endpointCisBill;
	
	private static String endpointCis24 = "http://10.3.1.32:8011/ouaf/XAIApp/xaiserver/R00601000024?wsdl";
	
	private static String endpointCis25 = "http://10.3.1.32:8011/ouaf/XAIApp/xaiserver/R00601000025?wsdl";
	
	@Value("${cis.endpointCisCertInfo}")
	private String endpointCisCertInfo;
	
	@Value("${cis.endpointCisRepair}")
	private String endpointCisRepair;
	
	
	/**
	 * 查询账单
	 * @param acctId
	 * @return
	 */
	public CisWatermeterInfo billInfo(String acctId) {
		CisWatermeterInfo info = new CisWatermeterInfo();
		CisOSBWSClient cisOSBWSClient = new CisOSBWSClient();
		
		for(int x=0;x<loopTime;x++) {
			try {
				System.out.println("调用接口地址："+endpointCis24);
				System.out.println("销根号："+acctId);
				info = cisOSBWSClient.getWatermeterInfoInfo(endpointCis24, acctId, "");
				if(null != info) {
					System.out.println(info.getAcctId());
					System.out.println(info.getLastBsegReading());
					System.out.println(info.getMrDate());
					return info;
				}
			} catch (MCSException e) {
				return null;
			}
		}
		return info;
	}
	
	
	
	/**
	 * 报修接口
	 * @param i
	 * @param a
	 * @param endpoint
	 * @return
	 */
	public WSRepairResult repair(McsIncident i,RprUsewaterRepair a,String endpoint) {
		WSRepairResult result = new WSRepairResult();
		CisOSBWSClient cisOSBWSClient = new CisOSBWSClient();
		for(int x=0;x<loopTime;x++) {
			try {
				RepairRequest repairRequest = new RepairRequest();
				CisAccdInfo info = getCisAccdInfo(endpoint, i.getUserNo());
				if(null == info) {
					result.setCode(500);
					result.setErrCode("CIS.Err.GetAccIdInfo");
					result.setIsSuccess(false);
					result.setMessage("调用CIS接口获取户号信息失败");
					result.setIncident_id(i.getIncidentId());
					continue;
				}
				repairRequest.setAttachs(getAttachList(i, info.getSpId()));
				repairRequest.setSpId(info.getSpId());
				repairRequest.setIncident_id(i.getIncidentId());
				repairRequest.setApply_no(i.getApplyNo());
				repairRequest.setCustom_type("01");
				repairRequest.setShw_address(i.getShwAddress());
				repairRequest.setApplicant(i.getApplicant());
				repairRequest.setAppdate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(i.getAppdate()));
				repairRequest.setIncident_type(i.getIncidentType());
				if(i.isTransfer()) {
					repairRequest.setIs_transfer_order("1");
					repairRequest.setShw_company_source(i.getTransferCompany());
				}else {
					repairRequest.setIs_transfer_order("0");
					repairRequest.setShw_company_source("");
				}
				repairRequest.setCert_type(i.getCertType());
				repairRequest.setCert_number(i.getCertNumber());
				repairRequest.setEmail(i.getEmail());
				repairRequest.setCompany_name(i.getCompanyName());
				repairRequest.setCompany_type(i.getCompanyType());
				repairRequest.setSocial_credit_code(i.getSocialCreditCode());
				repairRequest.setCase_type_cd("报修");
				repairRequest.setCard_id(i.getUserNo());
				repairRequest.setAddress(i.getShwAddress());
				repairRequest.setContact_value(a.getContactValue());
				repairRequest.setFyly(i.getSource());
				repairRequest.setFyxs("网络");
				repairRequest.setFyqm(a.getFyqm());
				repairRequest.setFylb(LogicContentType.getLogicType(i.getIncidentType()));
				String fynr = "";
				if(i.getIncidentType().equals(ItemCodeEnum.REPAIR_OTHER_RESIDENT.getIncidentType())|| 
						i.getIncidentType().equals(ItemCodeEnum.REPAIR_OTHER_COMPANY.getIncidentType())) {
					fynr = a.getFynr();
				}else {
					fynr = sysDictItemService.findItemValue("RepairContent", a.getFynr());
				}
				repairRequest.setFynr(fynr);
				repairRequest.setBx_dttm(DateUtils.currentDateTime());
				int cljb = ItemCodeEnum.getCljb(i.getIncidentType());
				String cljbString = "";
				switch (cljb) {
				case 1:
					cljbString = "24";
					break;
				case 3:
					cljbString = "72";
					break;
				case 7:
					cljbString = "168";
					break;
				default:
					cljbString = "24";
					break;
				}
				repairRequest.setCljb(cljbString);
				Date date = CommonUtil.dateAddDay(new Date(), cljb);
				String cn_dttm = CommonUtil.dateFormatString("yyyy-MM-dd HH:mm:ss", date);
				repairRequest.setCn_dttm(cn_dttm);
				String bx_loc_cd = sysDictItemService.findItemValue("RepairPosition", a.getBxLocCd()); // 报修内容
				repairRequest.setBx_loc_cd(bx_loc_cd);
				repairRequest.setRepair_comment(a.getRepairComment());
				System.out.println("\n\n\n开始调用-报修工单受理接口-OSB接口：");
				System.out.println("endpoint：" + endpoint);
				System.out.println("");
				System.out.println("请求内容：");
				System.out.println(GsonUtil.getInstance().toJson(repairRequest));
				CisRepairResult cisRepairResult = cisOSBWSClient.repair(endpoint, repairRequest);
				System.out.println("执行结果：");
				System.out.println(GsonUtil.getInstance().toJson(cisRepairResult));
				System.out.println("IsSuccess=" + cisRepairResult.getIsSuccess());
				System.out.println("Code=" + cisRepairResult.getCode());
				System.out.println("ErrCode=" + cisRepairResult.getErrCode());
				System.out.println("Message=" + cisRepairResult.getMessage());
				System.out.println("Incident id=" + cisRepairResult.getIncident_id());
				result.setIsSuccess(Boolean.valueOf(cisRepairResult.getIsSuccess()));
				result.setErrCode(cisRepairResult.getErrCode());
				result.setCode(Integer.valueOf(cisRepairResult.getCode()));
				result.setMessage(cisRepairResult.getMessage());
				result.setIncident_id(cisRepairResult.getIncident_id());
				System.out.println("\n调用完成-报修工单受理接口-OSB接口。\n\n");
				if(Boolean.valueOf(cisRepairResult.getIsSuccess()) || cisRepairResult.getErrCode().equals("00")) {
					result.setIsSuccess(true);
					break;
				}
			} catch (MCSException e) {
				result.setCode(500);
				result.setErrCode("CIS.Err.SubmitRepair");
				result.setIsSuccess(false);
				result.setMessage("调用CIS接口提交报修申请单失败");
				result.setIncident_id(i.getIncidentId());
			}
		}
		return result;
	}
	
	
	/**
	 * 查询地址
	 * @param endpoint
	 * @param cardId
	 * @return
	 */
	public WSAddressResult queryAddressResult(String endpoint,String cardId) {
		WSAddressResult result = new WSAddressResult();
		boolean success = false;
		for(int i=0;i<loopTime;i++) {
			CisAccdInfo info = getCisAccdInfo(endpoint, cardId);
			if(info != null) {
				success = true;
				result.setShw_address(info.getAddresss1());
				break;
			}
		}
		if(!success) {
			result.setCode(500);
			result.setErrCode("cis.error");
			result.setIsSuccess(false);
			result.setMessage("Cis查询户号信息调用失败");
		}else {
			result.setCode(200);
			result.setIsSuccess(true);
		}
		return result;
	}
	
	
	/**
	 * 查询户号相关
	 * @param endpoint
	 * @param cardId
	 * @return
	 */
	public CisAccdInfo getCisAccdInfo(String endpoint,String cardId) {
		CisAccdInfo info = new CisAccdInfo();
		try {
			CisOSBWSClient cisOSBWSClient = new CisOSBWSClient();
			System.out.println("\n\n\n开始调用-客户证件号码查询接口-OSB接口：");
			System.out.println("endpoint：" + endpointCisAddress);
			System.out.println("");
			System.out.println("请求内容：");
			System.out.println("acctId=" + cardId);
			info = cisOSBWSClient.getAcctInfo(endpointCisAddress, cardId);
			System.out.println("执行结果：");
			System.out.println(GsonUtil.getInstance().toJson(info));
			System.out.println("\n调用完成-客户证件号码查询接口-OSB接口。\n\n");
		} catch (MCSException e) {
			e.printStackTrace();
			return null;
		}
		return info;
	}
	
	
	
	
	
	/**
	 * 查询证件信息
	 * @param userNo
	 * @param endpoint
	 * @return
	 */
	public CisCustomerCert getCustomerCert(String userNo,String endpoint) {
		CisCustomerCert certInfo = new CisCustomerCert();
		for(int i=0;i<loopTime;i++) {
			try {
				CisOSBWSClient cisOSBWSClient = new CisOSBWSClient();
				System.out.println("\n\n\n开始调用-客户证件号码查询接口-OSB接口：");
				System.out.println("endpoint：" + endpoint);
				System.out.println("");
				System.out.println("请求内容：");
				System.out.println("acctId=" + userNo);
				System.out.println("idTypeCd= null");
				certInfo = cisOSBWSClient.getCustomerCertInfo(endpoint, userNo, "");
				System.out.println("执行结果：");
				System.out.println(GsonUtil.getInstance().toJson(certInfo));
				System.out.println("\n调用完成-客户证件号码查询接口-OSB接口。\n\n");
			} catch (Exception e) {
				return new CisCustomerCert();
			}
		}
		return certInfo;
	}
	public List<CisAttach> getAttachList(McsIncident incident,String spId){
		List<CisAttach> list = new ArrayList<CisAttach>();
		QueryWrapper<McsUserImageDetail> queWrapper = new QueryWrapper<McsUserImageDetail>();
		queWrapper.eq("apply_no", incident.getApplyNo());
		List<McsUserImageDetail> selectList = imageDetailMapper.selectList(queWrapper);
		for(McsUserImageDetail image : selectList) {
			CisAttach attach = new CisAttach();
			attach.setSpId(spId);
			attach.setFileName(image.getFilename());
			attach.setFileType(image.getFileType());
			attach.setFileUrl(image.getFilename());
			list.add(attach);
		}
		return list;
	}
	
	
	
	
	
	
}
