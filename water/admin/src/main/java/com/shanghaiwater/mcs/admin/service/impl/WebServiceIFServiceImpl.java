package com.shanghaiwater.mcs.admin.service.impl;

import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.tempuri.ArrayOfCertList;
import org.tempuri.CertList;
import org.tempuri.TransferEntity;
import org.tempuri.XiangYingEntity;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shanghaiwater.mcs.admin.service.SysDictItemService;
import com.shanghaiwater.mcs.admin.service.WebServiceIFService;
import com.shanghaiwater.mcs.admin.service.inf.CisOsbClientService;
import com.shanghaiwater.mcs.admin.service.inf.ThreeFgsClientService;
import com.shanghaiwater.mcs.admin.service.inf.XinZhuangWSClientService;
import com.shanghaiwater.mcs.common.enums.CompanyEnum;
import com.shanghaiwater.mcs.common.enums.ItemCodeEnum;
import com.shanghaiwater.mcs.common.exception.MCSException;
import com.shanghaiwater.mcs.common.model.WSAddressResult;
import com.shanghaiwater.mcs.common.model.WSRepairResult;
import com.shanghaiwater.mcs.common.util.GsonUtil;
import com.shanghaiwater.mcs.db.entity.McsIncident;
import com.shanghaiwater.mcs.db.entity.McsUserImageDetail;
import com.shanghaiwater.mcs.db.entity.RprUsewaterRepair;
import com.shanghaiwater.mcs.db.entity.UswResidentApply;
import com.shanghaiwater.mcs.db.entity.UswTransfer;
import com.shanghaiwater.mcs.db.entity.UswWatermeterSplit;
import com.shanghaiwater.mcs.db.mapper.McsUserImageDetailMapper;
import com.shanghaiwater.mcs.mcsif.wsc.ThreeFgsWSClient;

@Service
public class WebServiceIFServiceImpl implements WebServiceIFService {

	@Value("${mcsws.endpointGSFGSRepair}")
	private String endpointGSFGSRepair;

	@Value("${mcsws.endpointPDWLYZLS}")
	private String endpointPDWLYZLS;

	@Value("${mcsws.endpointPDXQZLS}")
	private String endpointPDXQZLS;

	@Value("${mcsws.endpointSBBSZLS}")
	private String endpointSBBSZLS;

	@Value("${mcsws.endpointYWSLFGS}")
	private String endpointYWSLFGS;
	
	@Value("${cis.endpointCisAddress}")
	private String endpointCisAddress;
	
	@Value("${cis.endpointCisCertInfo}")
	private String endpointCisCertInfo;

	@Value("${cis.endpointCisRepair}")
	private String endpointCisRepair;
	
	@Value("${iniswitch.ywtFlag}")
	private boolean ywtFlag;

	@Value("${iniswitch.webFlag}")
	private boolean flag;
	
	@Value("${iniswitch.imageFlag}")
	private boolean imgFlag;
	
	@Autowired
	private ThreeFgsClientService threeFgsClientService;
	
	@Autowired
	private XinZhuangWSClientService xinZhuangWSClientService;

	@Autowired
	private McsUserImageDetailMapper imageDetailMapper;
	

	@Autowired
	private CisOsbClientService cisOsbClientService;

	@Autowired
	private SysDictItemService sysDictItemService;
	
	private static int loopTime = 3;
	
	/**
	 * 获取endpoint公用
	 * @param companyCode
	 * @return
	 */
	public String getEndPoint(String companyCode) {
		String endpoint = endpointGSFGSRepair;
		switch (companyCode) {
		case "PDWLYZLS":
			endpoint = endpointPDWLYZLS;
			break;
		case "PDXQZLS":
			endpoint = endpointPDXQZLS;
			break;
		case "SBBSZLS":
			endpoint = endpointSBBSZLS;
			break;
		case "YWSLFGS":
			endpoint = endpointYWSLFGS;
			break;
		}
		return endpoint;
	}
	
	@Override
	public WSAddressResult getAddress(String companyCode, String cardId) {
		String endpoint = "";
		switch (companyCode) {
		case "PDWLYZLS":
			endpoint = endpointPDWLYZLS;
			break;
		case "PDXQZLS":
			endpoint = endpointPDXQZLS;
			break;
		case "SBBSZLS":
			endpoint = endpointSBBSZLS;
			break;
		case "GSFGS":
			endpoint = endpointCisAddress;
			break;
		}
		WSAddressResult wsResult = new WSAddressResult();
		if(flag) {
			switch (companyCode) {
			case "PDWLYZLS":
			case "PDXQZLS":
			case "SBBSZLS":
				wsResult = threeFgsClientService.queryAddressResult(endpoint, cardId);
				break;
			case "GSFGS":
				wsResult = cisOsbClientService.queryAddressResult(endpoint, cardId);
				break;
			}
		}else {
			wsResult.setCode(200);
			wsResult.setIsSuccess(true);
			wsResult.setShw_address("上南路4265弄164号102室");
		}
		return wsResult;
	}

	
	public ArrayOfCertList getArrayOfCertList(McsIncident incident){
		ArrayOfCertList arrayOfCertList = new ArrayOfCertList();
		if(imgFlag) {
			QueryWrapper<McsUserImageDetail> queWrapper = new QueryWrapper<McsUserImageDetail>();
			queWrapper.eq("apply_no", incident.getApplyNo());
			List<McsUserImageDetail> selectList = imageDetailMapper.selectList(queWrapper);
			for(McsUserImageDetail image : selectList) {
				CertList cert = new CertList();
				cert.setCert_source("02");//固定为"用户上传"
				cert.setCert_type(image.getCertType());
				cert.setCert_number(incident.getCertNumber());
				cert.setFile_uuid(image.getImageUuid());
				cert.setFilename(image.getFilename());
				cert.setWeb_uri(image.getFilename());
				arrayOfCertList.addCertList(cert);
			}
		}
		return arrayOfCertList;
	}
	
	
	
	@Override
	public WSRepairResult waterSplitApply(McsIncident incident,UswWatermeterSplit split) {
		WSRepairResult wsResult = new WSRepairResult();
		if(flag) {
			String endpoint = getEndPoint(incident.getShwCompany());
			switch (incident.getShwCompany()) {
			case "PDWLYZLS":
			case "SBBSZLS":
			case "PDXQZLS":
				wsResult = threeFgsClientService.waterSplitApply(incident, split, endpoint);
				break;
			case "GSFGS":
				wsResult = xinZhuangWSClientService.waterSplitApply(incident, split, endpointYWSLFGS);
				break;
			default:
				break;
			}
		}
			
		return wsResult;
	}
	
	@Override
	public WSRepairResult waterTransferApply(McsIncident incident,UswTransfer transfer) {
		WSRepairResult wsResult = new WSRepairResult();
		if(flag) {
			String endpoint = getEndPoint(incident.getShwCompany());
			boolean isSuccess = false;
			MCSException mcsex = new MCSException("ERR.WebService", "ERR.WebService");
			for (int loop = 0; loop < loopTime; loop++) {
				try {
					ThreeFgsWSClient threeFgsWSClient = new ThreeFgsWSClient(endpoint);
					ArrayOfCertList arrayOfCertList = getArrayOfCertList(incident);
					TransferEntity transferEntity = new TransferEntity();
					transferEntity.setCertList(arrayOfCertList);
					transferEntity.setIncident_id(incident.getIncidentId());
					transferEntity.setApply_no(incident.getApplyNo());
					ItemCodeEnum item = ItemCodeEnum.getInstanceByIncidentType(incident.getIncidentType());
					switch (item) {
					case REPAIR_RESIDENT_TRANSFER:
						transferEntity.setCustom_type("01");
						break;
					case REPAIR_COMPANY_TRANSFER:
						transferEntity.setCustom_type("02");
						break;
					default:
						break;
					}
					transferEntity.setId_type(incident.getCertType());
					transferEntity.setId_nbr(incident.getCertNumber());
					transferEntity.setCert_type(transfer.getIdType());
					transferEntity.setCert_number(transfer.getIdNbr());
					transferEntity.setApplicant(incident.getApplicant());
					transferEntity.setAppdate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(incident.getAppdate()));
					
					if(incident.isTransfer()) {
						transferEntity.setIs_transfer_order("1");
						transferEntity.setShw_company_source(incident.getTransferCompany());
					}else {
						transferEntity.setIs_transfer_order("0");
						transferEntity.setShw_company_source("");
					}
					
					transferEntity.setEmail(incident.getEmail());
					transferEntity.setCompany_name(transfer.getNewEntityName());
					
					if(StringUtils.isEmpty(transfer.getQylx())) {
						transferEntity.setCompany_type("");
					}else {
						String companyTypeLabel = sysDictItemService.findItemValue("CompanyType", transfer.getQylx());
						transferEntity.setCompany_type(companyTypeLabel);
					}
					
					transferEntity.setBusiness_type(transfer.getBusinessType());
					transferEntity.setCard_id(incident.getUserNo());
					transferEntity.setNew_entity_name(transfer.getNewEntityName());
					transferEntity.setFczjlx(transfer.getFczjlx());
					transferEntity.setFczjhm(transfer.getFczjhm());
					transferEntity.setAddress(transfer.getAddress());
					transferEntity.setComm_rte_type_cd("手机");
					transferEntity.setContact_value(transfer.getContactValue());
					//合同10001
					transferEntity.setGyshtbh(transfer.getGyshtbh());
					transferEntity.setFplx(transfer.getFplx());
					transferEntity.setFptt(transfer.getFptt());
					transferEntity.setNsrsbh(transfer.getNsrsbh());
					transferEntity.setYsrks(transfer.getYsrks());
					transferEntity.setTransfer_comment(transfer.getTransferComment());
					String typc = "PC001"; // 同一批次
					transferEntity.setTypc(typc);
					System.out.println("\n\n\n开始调用-居民过户、单位过户-WebService接口：");
					System.out.println("endpoint：" + endpoint);
					System.out.println("");

					System.out.println("请求内容：");
					System.out.println(GsonUtil.getInstance().toJson(transferEntity));

					XiangYingEntity xiangYingEntity = threeFgsWSClient.transfer(transferEntity);

					System.out.println("执行结果：");
					System.out.println(GsonUtil.getInstance().toJson(xiangYingEntity));

					System.out.println("");
					System.out.println("IsSuccess=" + xiangYingEntity.getIsSuccess());
					System.out.println("Code=" + xiangYingEntity.getCode());
 					System.out.println("ErrCode=" + xiangYingEntity.getErrCode());
					System.out.println("Message=" + xiangYingEntity.getMessage());
					System.out.println("Incident id=" + xiangYingEntity.getIncident_id());
					System.out.println("\n调用完成-居民过户、单位过户-WebService接口。\n\n");
					isSuccess = xiangYingEntity.getIsSuccess();
					BeanUtils.copyProperties(xiangYingEntity, wsResult);
					if(isSuccess) {
						break;
					}
				} catch (Exception ex) {
					mcsex = new MCSException("ERR.WebService", ex);
				}
			}
		}
		return wsResult;
	}
	
	
	
	
	
	@Override
	public WSRepairResult waterApply(McsIncident incident,UswResidentApply apply) {
		WSRepairResult wsResult = new WSRepairResult();
		String endpoint = getEndPoint(incident.getShwCompany());
		CompanyEnum companyEnum = CompanyEnum.getCompanyEnumByCompanyCode(incident.getShwCompany());
		if(flag) {
			switch (companyEnum) {
			case GSFGS:
				wsResult = xinZhuangWSClientService.residenceApply(incident, apply, endpointYWSLFGS);
				break;
			case PDWLYZLS:
			case PDXQZLS:
			case SBBSZLS:
				wsResult = threeFgsClientService.waterApply(incident, apply, endpoint);
				break;
			case YWSLFGS:
				wsResult = xinZhuangWSClientService.waterApply(incident, apply, endpoint);
				break;
			default:
				break;
			}
		}
		if(!wsResult.getIsSuccess()) {
			throw new MCSException(wsResult.getErrCode(),wsResult.getMessage());
		}
		return wsResult;
	}
	
	
	
	@Override
	public WSRepairResult repair(McsIncident i, RprUsewaterRepair a) {
		String companyCode = i.getShwCompany();
		WSRepairResult wsResult = new WSRepairResult();
		switch (companyCode) {
		case "PDWLYZLS":
			wsResult = threeFgsClientService.repair(i, a, endpointPDWLYZLS);
			break;
		case "PDXQZLS":
			wsResult = threeFgsClientService.repair(i, a, endpointPDXQZLS);
			break;
		case "SBBSZLS":
			wsResult = threeFgsClientService.repair(i, a, endpointSBBSZLS);
			break;
		case "GSFGS":
			wsResult = cisOsbClientService.repair(i, a, endpointCisRepair);
			break;
		}
		return wsResult;
	}


}
