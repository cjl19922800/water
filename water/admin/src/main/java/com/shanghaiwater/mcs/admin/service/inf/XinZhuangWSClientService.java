package com.shanghaiwater.mcs.admin.service.inf;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.tempuri.xinzhuang.ProjectInfo_Single;
import org.tempuri.xinzhuang.ReturnMsg;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shanghaiwater.mcs.admin.service.SysDictItemService;
import com.shanghaiwater.mcs.admin.service.SysMultiCodeDetailService;
import com.shanghaiwater.mcs.common.enums.ItemCodeEnum;
import com.shanghaiwater.mcs.common.model.WSRepairResult;
import com.shanghaiwater.mcs.common.util.GsonUtil;
import com.shanghaiwater.mcs.db.entity.McsIncident;
import com.shanghaiwater.mcs.db.entity.McsUserImageDetail;
import com.shanghaiwater.mcs.db.entity.SysMultiCodeDetail;
import com.shanghaiwater.mcs.db.entity.UswResidentApply;
import com.shanghaiwater.mcs.db.entity.UswWatermeterSplit;
import com.shanghaiwater.mcs.db.mapper.McsUserImageDetailMapper;
import com.shanghaiwater.mcs.mcsif.wsc.XinZhuangWSClient;
import org.tempuri.xinzhuang.ArrayOfCertList;
import org.tempuri.xinzhuang.CertList;
import org.tempuri.xinzhuang.ProjectInfo;

@Service
public class XinZhuangWSClientService {
	
	private static int loopTime = 3;
	
	@Value("${iniswitch.imageFlag}")
	private boolean imgFlag;
	
	@Autowired
	private McsUserImageDetailMapper imageDetailMapper;
	
	@Autowired
	private SysDictItemService sysDictItemService;
	
	@Autowired
	private SysMultiCodeDetailService sysMultiCodeDetailService;
	

	public WSRepairResult waterSplitApply(McsIncident incident,UswWatermeterSplit split,String endpoint) {
		WSRepairResult result = new WSRepairResult();
		boolean isSuccess = false;
		try {
			for(int loop = 0; loop < loopTime; loop++) {
				XinZhuangWSClient xinZhuangWSClient = new XinZhuangWSClient(endpoint);
				ProjectInfo projectInfo = new ProjectInfo();
				projectInfo.setIncident_id(incident.getIncidentId());
				projectInfo.setApply_no(incident.getApplyNo());
				projectInfo.setShw_address(incident.getShwAddress());
				projectInfo.setApplicant(incident.getApplicant());
				projectInfo.setAppdate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(incident.getAppdate()));
				projectInfo.setCert_type(incident.getCertType());
				projectInfo.setCert_number(incident.getCertNumber());
				projectInfo.setEmail(incident.getEmail());
				projectInfo.setBusiness_type(ItemCodeEnum.REPAIR_WATER_METER_SPLIT.getBusinessType());
				if(incident.isTransfer()) {
					projectInfo.setIs_transfer_order("1");
					projectInfo.setShw_company_source(incident.getTransferCompany());
				}else {
					projectInfo.setIs_transfer_order("0");
					projectInfo.setShw_company_source("");
				}
				projectInfo.setXzq(incident.getXzq());
				projectInfo.setJdz(incident.getTown());
				projectInfo.setAddress(incident.getShwAddress());
				SysMultiCodeDetail detail = sysMultiCodeDetailService.getDetailByItemCode(incident.getTown());
				projectInfo.setFensuo(detail.getFenshuo());
				projectInfo.setCon_name(split.getConName());
				projectInfo.setPhone(split.getPhone());
				projectInfo.setMail(incident.getEmail());
				projectInfo.setYb(split.getYb());
				projectInfo.setSplit_comment(split.getSplitComment());
				projectInfo.setCertList(getArrayOfCertList(incident));
				System.out.println("\n\n\n开始调用-总表拆分-WebService接口：");
				System.out.println("endpoint：" + endpoint);
				System.out.println(GsonUtil.getInstance().toJson(projectInfo));
				System.out.println("");
				ReturnMsg returnMsg = xinZhuangWSClient.watermeterSplit(projectInfo);
				System.out.println("执行结果：");
				System.out.println("IsSuccess=" + returnMsg.getIsSuccess());
				System.out.println("Code=" + returnMsg.getCode());
				System.out.println("ErrCode=" + returnMsg.getErrCode());
				System.out.println("Message=" + returnMsg.getMessage());
				System.out.println("Incident id=" + returnMsg.getIncident_id());
				System.out.println("\n调用完成-总表拆分-WebService接口。\\n\\n");
				isSuccess = returnMsg.getIsSuccess();
				BeanUtils.copyProperties(returnMsg, result);
				if(isSuccess) {
					break;
				}
			}
		} catch (Exception e) {
			result.setIsSuccess(false);
			result.setCode(500);
			result.setIncident_id(incident.getIncidentId());
			result.setErrCode("Web.Err");
			result.setMessage("新装分公司总表分装调用失败");
		}
		return result;
	}
	
	
	public WSRepairResult residenceApply(McsIncident incident,UswResidentApply apply,String endpoint) {
		WSRepairResult result = new WSRepairResult();
		boolean isSuccess = false;
		try {
			for(int loop = 0; loop < loopTime; loop++) {
				XinZhuangWSClient xinZhuangWSClient = new XinZhuangWSClient(endpoint);
				ProjectInfo_Single projectInfo_Single = new ProjectInfo_Single();
				projectInfo_Single.setIncident_id(incident.getIncidentId());
				projectInfo_Single.setApply_no(incident.getApplyNo());
				projectInfo_Single.setShw_address(incident.getShwAddress());
				projectInfo_Single.setApplicant(incident.getApplicant());
				projectInfo_Single.setAppdate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(incident.getAppdate()));
				projectInfo_Single.setIncident_type(incident.getIncidentType());
				projectInfo_Single.setCert_type(incident.getCertType());
				projectInfo_Single.setCert_number(incident.getCertNumber());
				projectInfo_Single.setBusiness_type(ItemCodeEnum.RESIDENT_APPLY.getBusinessType());
				if(incident.isTransfer()) {
					projectInfo_Single.setIs_transfer_order("1");
					projectInfo_Single.setShw_company_source(incident.getTransferCompany());
				}else {
					projectInfo_Single.setIs_transfer_order("0");
					projectInfo_Single.setShw_company_source("");
				}
				projectInfo_Single.setFczhm(apply.getHourseCertNo());
				projectInfo_Single.setXzq(incident.getXzq());
				projectInfo_Single.setJdz(incident.getTown());
				projectInfo_Single.setAddress(incident.getShwAddress());
				SysMultiCodeDetail detail = sysMultiCodeDetailService.getDetailByItemCode(incident.getTown());
				projectInfo_Single.setFensuo(detail.getFenshuo());
				projectInfo_Single.setApply_content("居民住宅");
				projectInfo_Single.setApply_memo("个人居住");
				projectInfo_Single.setCon_name(apply.getConName());
				projectInfo_Single.setPhone(apply.getPhone());
				projectInfo_Single.setMail(apply.getMail());
				projectInfo_Single.setContact_address(apply.getContactAddress());
				projectInfo_Single.setCertList(getArrayOfCertList(incident));

				System.out.println("\n\n\n开始调用-居民住宅单独接水——住宅-WebService接口：");
				System.out.println("endpoint：" + endpoint);
				System.out.println(GsonUtil.getInstance().toJson(projectInfo_Single));
				System.out.println("");
				ReturnMsg returnMsg = xinZhuangWSClient.residentApply(projectInfo_Single);
				System.out.println("执行结果：");
				System.out.println("IsSuccess=" + returnMsg.getIsSuccess());
				System.out.println("Code=" + returnMsg.getCode());
				System.out.println("ErrCode=" + returnMsg.getErrCode());
				System.out.println("Message=" + returnMsg.getMessage());
				System.out.println("Incident id=" + returnMsg.getIncident_id());
				System.out.println("\n调用完成-居民住宅单独接水——住宅-WebService接口。\\n\\n");
				isSuccess = returnMsg.getIsSuccess();
				BeanUtils.copyProperties(returnMsg, result);
				if(isSuccess) {
					break;
				}
				
			}
		} catch (Exception e) {
			result.setIsSuccess(false);
			result.setCode(500);
			result.setIncident_id(incident.getIncidentId());
			result.setErrCode("Web.Err");
			result.setMessage("新装分公司居民接水调用失败");
		}
		return result;
	}
	
	
	
	
	public WSRepairResult waterApply(McsIncident incident,UswResidentApply apply,String endpoint){
		WSRepairResult result = new WSRepairResult();
		boolean isSuccess = false;
			for (int loop = 0; loop < loopTime; loop++) {
				try {
					ProjectInfo_Single projectInfo_Single = new ProjectInfo_Single();
					XinZhuangWSClient xinZhuangWSClient = new XinZhuangWSClient(endpoint);
					projectInfo_Single.setIncident_id(incident.getIncidentId());
					projectInfo_Single.setApply_no(incident.getApplyNo());
					projectInfo_Single.setShw_address(incident.getShwCompany());
					projectInfo_Single.setApplicant(incident.getApplicant());
					projectInfo_Single.setAppdate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(incident.getAppdate()));
					projectInfo_Single.setIncident_type(incident.getIncidentType());
					projectInfo_Single.setCert_type(incident.getCertType());
					projectInfo_Single.setCert_number(incident.getCertNumber());
					projectInfo_Single.setBusiness_type(ItemCodeEnum.RESIDENT_APPLY.getBusinessType());
					if(incident.isTransfer()) {
						projectInfo_Single.setIs_transfer_order("1");
						projectInfo_Single.setShw_company_source(incident.getTransferCompany());
					}else {
						projectInfo_Single.setIs_transfer_order("0");
						projectInfo_Single.setShw_company_source("");
					}
					projectInfo_Single.setFczhm(apply.getHourseCertNo());
					projectInfo_Single.setXzq(incident.getXzq());
					projectInfo_Single.setJdz(incident.getTown());
					projectInfo_Single.setAddress(apply.getAddress());
					SysMultiCodeDetail detail = sysMultiCodeDetailService.getDetailByItemCode(incident.getTown());
					projectInfo_Single.setFensuo(detail.getFenshuo());
					projectInfo_Single.setApply_content("商铺");
					projectInfo_Single.setApply_memo("商铺用水");
					projectInfo_Single.setCon_name(apply.getConName());
					projectInfo_Single.setPhone(apply.getPhone());
					projectInfo_Single.setMail(apply.getMail());
					projectInfo_Single.setContact_address(apply.getContactAddress());
					projectInfo_Single.setCertList(getArrayOfCertList(incident));
					System.out.println("\n\n\n开始调用-居民住宅单独接水——商铺-WebService接口：");
					System.out.println("endpoint：" + endpoint);
					System.out.println("");
					System.out.println(GsonUtil.getInstance().toJson(projectInfo_Single));
					ReturnMsg returnMsg = xinZhuangWSClient.residentApply(projectInfo_Single);
					System.out.println("执行结果：");
					System.out.println("IsSuccess=" + returnMsg.getIsSuccess());
					System.out.println("Code=" + returnMsg.getCode());
					System.out.println("ErrCode=" + returnMsg.getErrCode());
					System.out.println("Message=" + returnMsg.getMessage());
					System.out.println("Incident id=" + returnMsg.getIncident_id());
					System.out.println("\n调用完成-居民住宅单独接水——商铺-WebService接口。\\n\\n");
					isSuccess = returnMsg.getIsSuccess();
					result.setIsSuccess(isSuccess);
					result.setCode(returnMsg.getCode());
					result.setIncident_id(returnMsg.getIncident_id());
					result.setErrCode(returnMsg.getErrCode());
					result.setMessage(returnMsg.getMessage());
					if(isSuccess) {
						break;
					}
				} catch (Exception e) {
					result.setIsSuccess(false);
					result.setCode(500);
					result.setIncident_id(incident.getIncidentId());
					result.setErrCode("Web.Err");
					result.setMessage("新装分公司商铺节水调用失败");
				}
			}
		
		return result;
	}
	
	
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
	

}
