package com.shanghaiwater.mcs.admin.service.impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.shanghaiwater.mcs.admin.common.AdminLoginInfo;
import com.shanghaiwater.mcs.admin.config.JtFtpConfig;
import com.shanghaiwater.mcs.admin.enums.BusinessTypeEnums;
import com.shanghaiwater.mcs.admin.model.BasicResponse;
import com.shanghaiwater.mcs.admin.model.request.IncidentDealRequest;
import com.shanghaiwater.mcs.admin.model.request.IncidentStatusRequest;
import com.shanghaiwater.mcs.admin.model.request.IncidentTransferRequest;
import com.shanghaiwater.mcs.admin.service.IncidentStatusService;
import com.shanghaiwater.mcs.admin.service.WebServiceIFService;
import com.shanghaiwater.mcs.admin.service.inf.YwtbClientService;
import com.shanghaiwater.mcs.admin.util.CommonUtil;
import com.shanghaiwater.mcs.common.constants.IncidentStatus;
import com.shanghaiwater.mcs.common.enums.CompanyEnum;
import com.shanghaiwater.mcs.common.enums.ItemCodeEnum;
import com.shanghaiwater.mcs.common.exception.MCSException;
import com.shanghaiwater.mcs.common.model.WSRepairResult;
import com.shanghaiwater.mcs.common.util.ExportExcelUtil;
import com.shanghaiwater.mcs.common.util.MCSFtpUtil;
import com.shanghaiwater.mcs.common.util.SecurityUtil;
import com.shanghaiwater.mcs.db.entity.McsIncident;
import com.shanghaiwater.mcs.db.entity.McsIncidentHis;
import com.shanghaiwater.mcs.db.entity.McsOplog;
import com.shanghaiwater.mcs.db.entity.McsUserImageDetail;
import com.shanghaiwater.mcs.db.entity.RprUsewaterRepair;
import com.shanghaiwater.mcs.db.entity.UswMulitiPopBen;
import com.shanghaiwater.mcs.db.entity.UswRealNameRegiste;
import com.shanghaiwater.mcs.db.entity.UswResidentApply;
import com.shanghaiwater.mcs.db.entity.UswTransfer;
import com.shanghaiwater.mcs.db.entity.UswWatermeterSplit;
import com.shanghaiwater.mcs.db.mapper.McsIncidentHisMapper;
import com.shanghaiwater.mcs.db.mapper.McsIncidentMapper;
import com.shanghaiwater.mcs.db.mapper.McsOplogMapper;
import com.shanghaiwater.mcs.db.mapper.McsUserImageDetailMapper;
import com.shanghaiwater.mcs.db.mapper.RprUsewaterRepairMapper;
import com.shanghaiwater.mcs.db.mapper.UswMulitiPopBenMapper;
import com.shanghaiwater.mcs.db.mapper.UswMulitiPopMapper;
import com.shanghaiwater.mcs.db.mapper.UswRealNameRegisteMapper;
import com.shanghaiwater.mcs.db.mapper.UswResidentApplyMapper;
import com.shanghaiwater.mcs.db.mapper.UswTransferMapper;
import com.shanghaiwater.mcs.db.mapper.UswWatermeterSplitMapper;
import com.shanghaiwater.mcs.db.vo.MultiPopMgVO;
import com.shanghaiwater.mcs.db.vo.RapirIncidentMgtVO;
import com.shanghaiwater.mcs.db.vo.RealNameIncidentMgtVO;
import com.shanghaiwater.mcs.db.vo.ResidentWaterIncidentMgtVO;
import com.shanghaiwater.mcs.db.vo.SplitIncidentMgtVO;
import com.shanghaiwater.mcs.db.vo.TransferIncidentMgtVO;
import com.shanghaiwater.mcs.mcsif.ywtb.models.YwtbProcessRequest;
import com.shanghaiwater.mcs.mcsif.ywtb.models.YwtbProcessResponse;

import freemarker.template.utility.StringUtil;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class IncidentStatusServiceImpl implements IncidentStatusService{
	
	@Autowired
	private McsIncidentMapper mcsIncidentMapper;
	
	@Autowired
	private McsIncidentHisMapper mcsIncidentHisMapper;
	
	@Autowired
	private YwtbClientService ywtbClientService;
	
	@Autowired
	private McsUserImageDetailMapper imageDetailMapper;
	
	@Autowired
	private McsOplogMapper mcsOplogMapper;
	
	@Value("${filePath}")
	private String filePath;
	
	@Autowired
	private JtFtpConfig config;
	
	@Autowired
	private UswTransferMapper transferMapper;
	
	@Autowired
	private UswResidentApplyMapper residentApplyWaterMapper;
	
	@Autowired
	private UswWatermeterSplitMapper uswWatermeterSplitMapper;
	
	@Autowired
	private RprUsewaterRepairMapper mcsRepairMapper;
	
	@Autowired
	private WebServiceIFService webServiceIFService;
	
	@Autowired
	private UswMulitiPopMapper uswMulitiPopMapper;
	
	@Autowired
	private UswMulitiPopBenMapper uswMulitiPopBenMapper;
	
	@Autowired
	private UswRealNameRegisteMapper uswRealNameRegisteMapper;
	

	@SuppressWarnings("resource")
	@Override
	@Transactional
	public HSSFWorkbook export(Map<String,Object> map,String bussinessType,AdminLoginInfo adminLoginInfo) throws EncryptedDocumentException, FileNotFoundException, InvalidFormatException, IOException {
		HSSFWorkbook sheets = new HSSFWorkbook();
		List<String> companyCodes = adminLoginInfo.getCompanyAuthorities();
		if(companyCodes.size() == 0 || null == companyCodes) {
			return null;
		}
		List<String> headers = new ArrayList<String>();
		headers.add("水司");
		headers.add("申请人");
		headers.add("申请时间");
		headers.add("状态");
		headers.add("用水地址");
		headers.add("申请人证件类型");
		headers.add("申请人证件号码");
		headers.add("统一审批编码");
		headers.add("是否处理");
		headers.add("来源");
		if(bussinessType.equals(BusinessTypeEnums.REPAIR_LEAK_WATER_COMPANY.getBusinessType()) || 
			bussinessType.equals(BusinessTypeEnums.REPAIR_USE_WATER_COMPANY.getBusinessType()) || 
			bussinessType.equals(BusinessTypeEnums.REPAIR_WATER_QUALITY_COMPANY.getBusinessType()) ||
			bussinessType.equals(BusinessTypeEnums.REPAIR_WATER_METER_COMPANY.getBusinessType()) ||
			bussinessType.equals(BusinessTypeEnums.REPAIR_OTHER_COMPANY.getBusinessType())) {
			List<String> incidentTypes = BusinessTypeEnums.getIncidentTypes(bussinessType);
			List<List<String>> callList = new ArrayList<List<String>>();
			if(incidentTypes.size()==0 || null == incidentTypes) {
				return null;
			}
			String[] types = incidentTypes.toArray(new String[incidentTypes.size()]);
			map.put("types", types);
			headers.add("申请单类型");
			headers.add("户号");
			headers.add("联系电话");
			headers.add("反应区名");
			headers.add("反应内容");
			headers.add("报修位置");
			headers.add("备注");
			callList.add(headers);
			List<RapirIncidentMgtVO> list = mcsRepairMapper.queryAllRepairList(map);
			if(list.size()>0) {
				for(RapirIncidentMgtVO vo:list) {
					List<String> dataList = new ArrayList<String>();
					dataList.add(vo.getCompanyName());
					dataList.add(vo.getApplicant());
					dataList.add(CommonUtil.dateFormatString("yyyy-MM-dd HH:mm:ss", vo.getAppdate()));
					dataList.add(vo.getStatusDict());
					dataList.add(vo.getShwAddress());
					dataList.add(vo.getCertTypeDict());
					dataList.add(vo.getCertNumber());
					dataList.add(vo.getApplyNo());
					if(vo.getDeal()) {
						dataList.add("已处理");
					}else {
						dataList.add("未处理");
					}
					dataList.add(vo.getSource());
					dataList.add(ItemCodeEnum.getItemNameByIncidentType(vo.getIncidentType()));
					dataList.add(vo.getUserNo());
					dataList.add(vo.getContactValue());
					dataList.add(vo.getXzqName());
					dataList.add(vo.getFynrDict());
					dataList.add(vo.getBxwzDict());
					dataList.add(vo.getRepairComment());
					callList.add(dataList);
				}
				sheets = ExportExcelUtil.Excels(BusinessTypeEnums.getBusinessName(bussinessType), callList);
			}
		}else if(bussinessType.equals(BusinessTypeEnums.REPAIR_RESIDENT_TRANSFER.getBusinessType())) {
			map.put("incidentType", BusinessTypeEnums.REPAIR_RESIDENT_TRANSFER.getIncidentType());
			List<TransferIncidentMgtVO> list = transferMapper.queryAllTransferList(map);
			List<List<String>> callList = new ArrayList<List<String>>();
			headers.add("户号");
			headers.add("联系电话");
			headers.add("房产证件类型");
			headers.add("房产证件号码");
			headers.add("备注");
			callList.add(headers);
			if(list.size()>0) {
				for(TransferIncidentMgtVO vo:list) {
					List<String> dataList = new ArrayList<String>();
					dataList.add(vo.getCompanyName());
					dataList.add(vo.getApplicant());
					dataList.add(CommonUtil.dateFormatString("yyyy-MM-dd HH:mm:ss", vo.getAppdate()));
					dataList.add(vo.getStatusDict());
					dataList.add(vo.getShwAddress());
					dataList.add(vo.getCertTypeDict());
					dataList.add(vo.getCertNumber());
					dataList.add(vo.getApplyNo());
					if(vo.getDeal()) {
						dataList.add("已处理");
					}else {
						dataList.add("未处理");
					}
					dataList.add(vo.getSource());
					dataList.add(vo.getUserNo());
					dataList.add(vo.getContactValue());
					dataList.add(vo.getHourseCertDict());
					dataList.add(vo.getFczjhm());
					dataList.add(vo.getTransferComment());
					callList.add(dataList);
				}
				sheets = ExportExcelUtil.Excels(BusinessTypeEnums.getBusinessName(bussinessType), callList);
			}
		}else if(bussinessType.equals(BusinessTypeEnums.RESIDENT_APPLY.getBusinessType())) {
			List<ResidentWaterIncidentMgtVO> list = residentApplyWaterMapper.queryAllResidentList(map);
			List<List<String>> callList = new ArrayList<List<String>>();
			headers.add("房产证件类型");
			headers.add("房产证件号");
			headers.add("接水类别");
			headers.add("联系人");
			headers.add("联系人手机");
			headers.add("联系地址");
			callList.add(headers);
			if(list.size()>0) {
				for(ResidentWaterIncidentMgtVO vo:list) {
					List<String> dataList = new ArrayList<String>();
					dataList.add(vo.getCompanyName());
					dataList.add(vo.getApplicant());
					dataList.add(CommonUtil.dateFormatString("yyyy-MM-dd HH:mm:ss", vo.getAppdate()));
					dataList.add(vo.getStatusDict());
					dataList.add(vo.getShwAddress());
					dataList.add(vo.getCertTypeDict());
					dataList.add(vo.getCertNumber());
					dataList.add(vo.getApplyNo());
					if(vo.getDeal()) {
						dataList.add("已处理");
					}else {
						dataList.add("未处理");
					}
					dataList.add(vo.getSource());
					dataList.add(vo.getHoueseCertDict());
					dataList.add(vo.getHoueseCertNo());
					dataList.add(vo.getApplyContentDict());
					dataList.add(vo.getConName());
					dataList.add(vo.getPhone());
					dataList.add(vo.getContactAddress());
					callList.add(dataList);
				}
			}
			sheets = ExportExcelUtil.Excels(BusinessTypeEnums.getBusinessName(bussinessType), callList);
		}else if(bussinessType.equals(BusinessTypeEnums.REPAIR_COMPANY_TRANSFER.getBusinessType())) {
			map.put("incidentType", BusinessTypeEnums.REPAIR_COMPANY_TRANSFER.getIncidentType());
			List<TransferIncidentMgtVO> list = transferMapper.queryAllTransferList(map);
			List<List<String>> callList = new ArrayList<List<String>>();
			headers.add("户号");
			headers.add("公司名称");
			headers.add("联系电话");
			headers.add("房产证件类型");
			headers.add("房产证件号码");
			headers.add("备注");
			callList.add(headers);
			if(list.size()>0) {
				for(TransferIncidentMgtVO vo:list) {
					List<String> dataList = new ArrayList<String>();
					dataList.add(vo.getCompanyName());
					dataList.add(vo.getApplicant());
					dataList.add(CommonUtil.dateFormatString("yyyy-MM-dd HH:mm:ss", vo.getAppdate()));
					dataList.add(vo.getStatusDict());
					dataList.add(vo.getShwAddress());
					dataList.add(vo.getCertTypeDict());
					dataList.add(vo.getCertNumber());
					dataList.add(vo.getApplyNo());
					if(vo.getDeal()) {
						dataList.add("已处理");
					}else {
						dataList.add("未处理");
					}
					dataList.add(vo.getSource());
					dataList.add(vo.getUserNo());
					dataList.add(vo.getApplyCompanyName());
					dataList.add(vo.getContactValue());
					dataList.add(vo.getHourseCertDict());
					dataList.add(vo.getFczjhm());
					dataList.add(vo.getTransferComment());
					callList.add(dataList);
				}
			}
			sheets = ExportExcelUtil.Excels(BusinessTypeEnums.getBusinessName(bussinessType), callList);
		}else if(bussinessType.equals(BusinessTypeEnums.REPAIR_WATER_METER_SPLIT.getBusinessType())) {
			List<SplitIncidentMgtVO> list = uswWatermeterSplitMapper.queryAllSplitList(map);
			List<List<String>> callList = new ArrayList<List<String>>();
			headers.add("户号");
			headers.add("联系人");
			headers.add("联系人固定电话");
			headers.add("联系人手机");
			headers.add("联系邮箱");
			headers.add("邮编");
			headers.add("备注");
			callList.add(headers);
			if(list.size()>0) {
				for(SplitIncidentMgtVO vo:list) {
					List<String> dataList = new ArrayList<String>();
					dataList.add(vo.getCompanyName());
					dataList.add(vo.getApplicant());
					dataList.add(CommonUtil.dateFormatString("yyyy-MM-dd HH:mm:ss", vo.getAppdate()));
					dataList.add(vo.getStatusDict());
					dataList.add(vo.getShwAddress());
					dataList.add(vo.getCertTypeDict());
					dataList.add(vo.getCertNumber());
					dataList.add(vo.getApplyNo());
					if(vo.getDeal()) {
						dataList.add("已处理");
					}else {
						dataList.add("未处理");
					}
					dataList.add(vo.getSource());
					dataList.add(vo.getUserNo());
					dataList.add(vo.getConName());
					dataList.add(vo.getContactValue());
					dataList.add(vo.getPhone());
					dataList.add(vo.getMail());
					dataList.add(vo.getYb());
					dataList.add(vo.getSplitComment());
					callList.add(dataList);
				}
			}
			sheets = ExportExcelUtil.Excels(BusinessTypeEnums.getBusinessName(bussinessType), callList);
		}else if(bussinessType.equals(BusinessTypeEnums.MULTI_HOUSE_SIMPLE_HOLD.getBusinessType())) {
			List<MultiPopMgVO> list = uswMulitiPopMapper.queryPopAllList(map);
			List<List<String>> callList = new ArrayList<List<String>>();
			headers.add("户号");
			headers.add("联系电话");
			headers.add("办理类型");
			headers.add("收益人数");
			headers.add("优惠方式");
			headers.add("办件类型");
			headers.add("一网通办是否收件");
			headers.add("业务系统发送状态");
			headers.add("审核时间");
			headers.add("业务审核状态");
			callList.add(headers);
			if(list.size()>0) {
				for(MultiPopMgVO vo:list) {
					List<String> dataList = new ArrayList<String>();
					dataList.add(vo.getCompanyName());
					dataList.add(vo.getApplicant());
					dataList.add(CommonUtil.dateFormatString("yyyy-MM-dd HH:mm:ss", vo.getAppdate()));
					dataList.add(vo.getStatusDict());
					dataList.add(vo.getShwAddress());
					dataList.add(vo.getCertTypeDict());
					dataList.add(vo.getCertNumber());
					dataList.add(vo.getApplyNo());
					if(vo.getDeal()) {
						dataList.add("已处理");
					}else {
						dataList.add("未处理");
					}
					dataList.add(vo.getSource());
					dataList.add(vo.getUserNo());
					dataList.add(vo.getPhone());
					if(vo.getReqType().equals("01")) {
						dataList.add("新办");
					}else if(vo.getReqType().equals("02")){
						dataList.add("续办");
					}else {
						dataList.add("终止");
					}
					dataList.add(vo.getPeopleNum());
					if(vo.getSettleMethod().equals("01")) {
						dataList.add("每户年度增加100立方米水量");
					}else {
						dataList.add("3.65元/立方米综合水价");
					}
					if(vo.getOperType().equals("01")) {
						dataList.add("即办件");
					}else {
						dataList.add("流转件");
					}
					
					if(vo.getYwtbSuccess()) {
						dataList.add("已收件");
					}else {
						dataList.add("未收件");
					}
					if(vo.getYwxtSuccess()) {
						dataList.add("发送成功");
					}else {
						dataList.add("发送失败");
					}
					
					if(StringUtils.isEmpty(vo.getIfResult())) {
						dataList.add("未审核");
						dataList.add("未审核");
					}else {
						if(vo.getIfResult().equals("01")) {
							dataList.add(vo.getUpdateTime());
							dataList.add("审核通过");
						}else{
							dataList.add(vo.getUpdateTime());
							dataList.add("审核不通过");
						}
					}
					callList.add(dataList);
				}
				sheets = ExportExcelUtil.Excels(BusinessTypeEnums.getBusinessName(bussinessType), callList);
			}
			
		}else if(bussinessType.equals(BusinessTypeEnums.MULTI_HOUSE_HOLD.getBusinessType())){
			List<MultiPopMgVO> list = uswMulitiPopMapper.queryPopAllList(map);
			List<List<String>> callList = new ArrayList<List<String>>();
			headers.add("户号");
			headers.add("联系电话");
			headers.add("办理类型");
			headers.add("收益人数");
			headers.add("优惠方式");
			headers.add("办件类型");
			headers.add("一网通办是否收件");
			headers.add("业务系统发送状态");
			headers.add("审核时间");
			headers.add("业务审核状态");
			headers.add("受益人1");
			headers.add("受益人2");
			headers.add("受益人3");
			headers.add("受益人4");
			headers.add("受益人5");
			headers.add("受益人6");
			headers.add("受益人7");
			callList.add(headers);
			if(list.size()>0) {
				for(MultiPopMgVO vo:list) {
					List<String> dataList = new ArrayList<String>();
					dataList.add(vo.getCompanyName());
					dataList.add(vo.getApplicant());
					dataList.add(CommonUtil.dateFormatString("yyyy-MM-dd HH:mm:ss", vo.getAppdate()));
					dataList.add(vo.getStatusDict());
					dataList.add(vo.getShwAddress());
					dataList.add(vo.getCertTypeDict());
					dataList.add(vo.getCertNumber());
					dataList.add(vo.getApplyNo());
					if(vo.getDeal()) {
						dataList.add("已处理");
					}else {
						dataList.add("未处理");
					}
					dataList.add(vo.getSource());
					dataList.add(vo.getUserNo());
					dataList.add(vo.getPhone());
					if(vo.getReqType().equals("01")) {
						dataList.add("新办");
					}else if(vo.getReqType().equals("02")){
						dataList.add("续办");
					}else {
						dataList.add("终止");
					}
					dataList.add(vo.getPeopleNum());
					if(vo.getSettleMethod().equals("01")) {
						dataList.add("每户年度增加100立方米水量");
					}else {
						dataList.add("3.65元/立方米综合水价");
					}
					if(vo.getOperType().equals("01")) {
						dataList.add("即办件");
					}else {
						dataList.add("流转件");
					}
					
					if(vo.getYwtbSuccess()) {
						dataList.add("已收件");
					}else {
						dataList.add("未收件");
					}
					if(vo.getYwxtSuccess()) {
						dataList.add("发送成功");
					}else {
						dataList.add("发送失败");
					}
					
					if(StringUtils.isEmpty(vo.getIfResult())) {
						dataList.add("未审核");
						dataList.add("未审核");
					}else {
						if(vo.getIfResult().equals("01")) {
							dataList.add(vo.getUpdateTime());
							dataList.add("审核通过");
						}else{
							dataList.add(vo.getUpdateTime());
							dataList.add("审核不通过");
						}
					}
					
					QueryWrapper<UswMulitiPopBen> qwBen = new QueryWrapper<UswMulitiPopBen>();
					qwBen.eq("INCIDENT_ID", vo.getIncidentId());
					List<UswMulitiPopBen> benList = uswMulitiPopBenMapper.selectList(qwBen);
					if(benList.size()>0) {
						for(UswMulitiPopBen ben:benList) {
							StringBuilder sb = new StringBuilder();
							sb.append(ben.getName());
							sb.append("(");
							sb.append(ben.getCertNum());
							sb.append(")");
							sb.append("【");
							sb.append(ben.getAddress());
							sb.append("】");
							dataList.add(sb.toString());
						}
						
					}
					callList.add(dataList);
				}
				sheets = ExportExcelUtil.Excels(BusinessTypeEnums.getBusinessName(bussinessType), callList);
			}
		}else if(bussinessType.equals(BusinessTypeEnums.UNI_TRANSFER.getBusinessType())){
			map.put("incidentType", BusinessTypeEnums.UNI_TRANSFER.getIncidentType());
			List<TransferIncidentMgtVO> list = transferMapper.queryAllTransferList(map);
			List<List<String>> callList = new ArrayList<List<String>>();
			headers.add("户号");
			headers.add("联系电话");
			headers.add("房产证件类型");
			headers.add("房产证件号码");
			headers.add("不动产坐落");
			headers.add("不动产登记日期");
			headers.add("一网通办是否收件");
			headers.add("业务系统发送状态");
			headers.add("审核时间");
			headers.add("业务审核状态");
			headers.add("备注");
			callList.add(headers);
			if(list.size()>0) {
				for(TransferIncidentMgtVO vo:list) {
					List<String> dataList = new ArrayList<String>();
					dataList.add(vo.getCompanyName());
					dataList.add(vo.getApplicant());
					dataList.add(CommonUtil.dateFormatString("yyyy-MM-dd HH:mm:ss", vo.getAppdate()));
					dataList.add(vo.getStatusDict());
					dataList.add(vo.getShwAddress());
					dataList.add(vo.getCertTypeDict());
					dataList.add(vo.getCertNumber());
					dataList.add(vo.getApplyNo());
					if(vo.getDeal()) {
						dataList.add("已处理");
					}else {
						dataList.add("未处理");
					}
					dataList.add(vo.getSource());
					dataList.add(vo.getUserNo());
					dataList.add(vo.getContactValue());
					dataList.add(vo.getHourseCertDict());
					dataList.add(vo.getFczjhm());
					dataList.add(vo.getBdczl());
					dataList.add(vo.getBdcdbrq());
					
					if(vo.getYwtbSuccess()) {
						dataList.add("已收件");
					}else {
						dataList.add("未收件");
					}
					if(vo.getYwxtSuccess()) {
						dataList.add("发送成功");
					}else {
						dataList.add("发送失败");
					}
					
					if(StringUtils.isEmpty(vo.getIfResult())) {
						dataList.add("未审核");
						dataList.add("未审核");
					}else {
						if(vo.getIfResult().equals("01")) {
							dataList.add(vo.getUpdateTime());
							dataList.add("审核通过");
						}else{
							dataList.add(vo.getUpdateTime());
							dataList.add("审核不通过");
						}
					}
					
					dataList.add(vo.getTransferComment());
					callList.add(dataList);
				}
				sheets = ExportExcelUtil.Excels(BusinessTypeEnums.getBusinessName(bussinessType), callList);
			}
			
			
			
			
		}else if(bussinessType.equals(BusinessTypeEnums.REAL_NAME_REGISTE.getBusinessType())) {
			map.put("incidentType", BusinessTypeEnums.REAL_NAME_REGISTE.getIncidentType());
			List<RealNameIncidentMgtVO> list = uswRealNameRegisteMapper.queryAllRealNameList(map);
			List<List<String>> callList = new ArrayList<List<String>>();
			headers.add("户号");
			headers.add("联系电话");
			
		} else {
			return null;
		}
		
		return sheets;
	}
	
	@Override
	@Transactional
	public BasicResponse deal(IncidentDealRequest request,AdminLoginInfo adminLoginInfo){
		BasicResponse response = new BasicResponse();
		McsIncident incident = mcsIncidentMapper.selectById(request.getIncidentId());
		if(null == incident) {
			return response.error("INCIDENT.NOT.EXITS","申请单不存在");
		}
		incident.setDeal(request.isDealStatus());
		mcsIncidentMapper.updateById(incident);
		return response.success();
	}
	
	
	@Override
	@Transactional
	public BasicResponse transfer(IncidentTransferRequest request,AdminLoginInfo adminLoginInfo) {
		BasicResponse response = new BasicResponse();
		McsIncident incident = mcsIncidentMapper.selectById(request.getIncidentId());
		if(null == incident) {
			return response.error("INCIDENT.NOT.EXITS","申请单不存在");
		}
		
		incident.setCertNumber(SecurityUtil.decryptAES(incident.getCertNumber()));
		incident.setShwAddress(SecurityUtil.decryptAES(incident.getShwAddress()));
		
		String oldCompany = incident.getShwCompany();
		//判断申请单是否已经结束
		if(incident.getStatus().equals(IncidentStatus.FINISH)||incident.getStatus().equals(IncidentStatus.RETURN)) {
			return response.error("INCIDENT.IS.OVER","申请单已结束");
		}
		incident.setTransfer(true);//转单
		incident.setTransferCompany(incident.getShwCompany());//原公司
		incident.setShwCompany(request.getCompanyCode());//转单后公司
		incident.setUpdator(adminLoginInfo.getUserId());
		incident.setUdate(new Date());
		McsIncidentHis mcsIncidentHis = new McsIncidentHis();
		BeanUtils.copyProperties(incident, mcsIncidentHis);
		mcsIncidentHis.setCdate(new Date());
		mcsIncidentHis.setCreator(adminLoginInfo.getUserId());
		mcsIncidentHis.setIncidentId(incident.getIncidentId());
		mcsIncidentHis.setIncidentHisId(UUID.randomUUID().toString());
		QueryWrapper<McsUserImageDetail> queryWrapper = new QueryWrapper<McsUserImageDetail>();
		queryWrapper.eq("apply_no", incident.getApplyNo());
		List<McsUserImageDetail> list = imageDetailMapper.selectList(queryWrapper);
		if(list.size()>0) {
			try {
				String newPath = "/"+incident.getShwCompany()+"/"+incident.getIncidentId()+"/";
				String oldPath = "/"+oldCompany+"/"+incident.getIncidentId()+"/";
				if(!MCSFtpUtil.mvFile(config.getHostname(),Integer.valueOf(config.getPort()),config.getUsername(),config.getPassword(), oldPath, newPath)) {
					return response.error("FTP.FIlE.ERROR", "文件上传失败");
				}
			} catch (MCSException e) {
				return response.error("FTP.FIlE.ERROR", "文件上传失败");
			}
		}
		try {
			mcsIncidentHisMapper.insert(mcsIncidentHis);
			mcsIncidentMapper.updateById(incident);
			WSRepairResult result = callEnter(incident);
			if(!result.getIsSuccess()) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return response.error(result.getErrCode(), result.getMessage());
			}
			saveLog("MCS.Business.TransferIncident", incident, adminLoginInfo);
		} catch (MCSException e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return response.error("SEVER.ERROR", e.getMessage());
		}
		
		return response.success();
	}
	
	public WSRepairResult getError(String errorCode,String msg) {
		WSRepairResult result = new WSRepairResult();
		result.setCode(500);
		result.setErrCode(errorCode);
		result.setIsSuccess(false);
		result.setMessage(msg);
		return result;
	}
	
	//转单主方法
	public WSRepairResult callEnter(McsIncident incident) {
		WSRepairResult result = new WSRepairResult();
		String requestType = incident.getIncidentType();//根据不同业务,业务上传不同
		ItemCodeEnum item = ItemCodeEnum.getInstanceByIncidentType(requestType);
		switch (item) {
		case RESIDENT_APPLY://居民接水
			QueryWrapper<UswResidentApply> queryWrapper2 = new QueryWrapper<UswResidentApply>();
			queryWrapper2.eq("incident_id", incident.getIncidentId());
			UswResidentApply apply = residentApplyWaterMapper.selectOne(queryWrapper2);
			
			if(null == apply) {
				return getError("INCIDENT.NOT.EXITS", "申请单不存在");
			}
			
			apply.setAddress(SecurityUtil.decryptAES(apply.getAddress()));
			apply.setPhone(SecurityUtil.decryptAES(apply.getPhone()));
			apply.setHourseCertNo(SecurityUtil.decryptAES(apply.getHourseCertNo()));
			apply.setContactAddress(SecurityUtil.decryptAES(apply.getContactAddress()));
			
			result = webServiceIFService.waterApply(incident, apply);
			break;
		case REPAIR_RESIDENT_TRANSFER://过户
		case REPAIR_COMPANY_TRANSFER:
			QueryWrapper<UswTransfer> queryWrapper3 = new QueryWrapper<UswTransfer>();
			queryWrapper3.eq("incident_id", incident.getIncidentId());
			UswTransfer transfer = transferMapper.selectOne(queryWrapper3);
			if(null == transfer) {
				return getError("INCIDENT.NOT.EXITS", "申请单不存在");
			}
			if(incident.getShwCompany().equals(CompanyEnum.GSFGS.getCompanyCode())) {
				return getError("INCIDENT.NOT.EXITS", "申请单不存在");
			}
			result = webServiceIFService.waterTransferApply(incident, transfer);
			break;
		case REPAIR_WATER_METER_SPLIT://总表分装
			QueryWrapper<UswWatermeterSplit> queryWrapper4 = new QueryWrapper<>();
			queryWrapper4.eq("incident_id", incident.getIncidentId());
			UswWatermeterSplit split = uswWatermeterSplitMapper.selectOne(queryWrapper4);
			if(null == split) {
				return getError("INCIDENT.NOT.EXITS", "申请单不存在");
			}
			result = webServiceIFService.waterSplitApply(incident, split);
			break;
		case REPAIR_LEAK_WATER_RESIDENT://报修类
		case REPAIR_LEAK_WATER_COMPANY:
		case REPAIR_USE_WATER_RESIDENT:
		case REPAIR_USE_WATER_COMPANY:
		case REPAIR_WATER_QUALITY_RESIDENT:
		case REPAIR_WATER_QUALITY_COMPANY:
		case REPAIR_WATER_METER_RESIDENT:
		case REPAIR_WATER_METER_COMPANY:
		case REPAIR_OTHER_RESIDENT:
		case REPAIR_OTHER_COMPANY:
			QueryWrapper<RprUsewaterRepair> queryWrapper5 = new QueryWrapper<RprUsewaterRepair>();
			queryWrapper5.eq("incident_id", incident.getIncidentId());
			RprUsewaterRepair repair = mcsRepairMapper.selectOne(queryWrapper5);
			if(null == repair) {
				return getError("INCIDENT.NOT.EXITS", "申请单不存在");
			}
			result = webServiceIFService.repair(incident, repair);
			break;
		default:
			return getError("INCIDENT.NOT.EXITS", "申请单不存在");
		}
		return result;
	}
	
	
	
	
	@Override
	@Transactional
	public BasicResponse updateStatus(IncidentStatusRequest request,AdminLoginInfo adminLoginInfo) {
		BasicResponse response = new BasicResponse();
		McsIncident incident = mcsIncidentMapper.selectById(request.getIncidentId());
		if(null == incident) {
			return response.error("INCIDENT.NOT.EXITS","申请单不存在");
		}
		//判断申请单是否已经结束
		if(incident.getStatus().equals(IncidentStatus.FINISH)||incident.getStatus().equals(IncidentStatus.RETURN)) {
			return response.error("INCIDENT.IS.OVER","申请单已结束");
		}
		incident.setStatus(request.getStatus());
		incident.setDescription(request.getDescription());//返回的备注信息
		incident.setUpdator(adminLoginInfo.getUserId());//更改人
		incident.setUdate(new Date());//修改时间
		String evId = "";
		boolean ywtbFlag = false;
		switch (request.getStatus()) {
		case IncidentStatus.FINISH://办结
			incident.setIfResult("01");
			evId = "MCS.Business.UpdateIncidentStatus";
			ywtbFlag = true;
			break;
		case IncidentStatus.DEFER://延期
			evId = "MCS.Business.UpdateIncidentDefer";
			ywtbFlag = true;
			break;
		case IncidentStatus.RETURN://退单
			evId = "MCS.Business.UpdateIncidentStatus";
			incident.setIfResult("02");
			ywtbFlag = true;
			break;
		default:
			break;
		}
		McsIncidentHis mcsIncidentHis = new McsIncidentHis();
		BeanUtils.copyProperties(incident, mcsIncidentHis);
		mcsIncidentHis.setCdate(new Date());
		mcsIncidentHis.setCreator(adminLoginInfo.getUserId());
		mcsIncidentHis.setIncidentId(incident.getIncidentId());
		mcsIncidentHis.setIncidentHisId(UUID.randomUUID().toString());
		try {
			mcsIncidentHisMapper.insert(mcsIncidentHis);
			mcsIncidentMapper.updateById(incident);
			if(incident.getSource().equals("一网通办")) {//由一网通办入口进入的申请单
				if(ywtbFlag) {
					YwtbProcessRequest ywtbRequest = this.getYwtbProcessRequest(incident, request, adminLoginInfo);
					YwtbProcessResponse reponse = ywtbClientService.process(ywtbRequest);
					if(!reponse.getCode().equals("200")) {
						return response.error("YWTB.ERROR", reponse.getMsg());
					}
				}
			}
			saveLog(evId, incident, adminLoginInfo);
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return response.error("SERVER.ERROR", "系统错误");
		}
		return response.success();
	}
	
	public void saveLog(String evId,McsIncident incident,AdminLoginInfo adminLoginInfo) {
		McsOplog mcsOplog = new McsOplog();
		mcsOplog.setId(UUID.randomUUID().toString());
		mcsOplog.setEventid("MCS.Business.UpdateIncidentStatus");
		mcsOplog.setOperator(adminLoginInfo.getUserName());
		mcsOplog.setOptarget(incident.getIncidentId());
		mcsOplog.setOperation("update");
		mcsOplog.setStartTime(new Date());
		mcsOplog.setEndTime(new Date());
		mcsOplog.setStatus("200");
		mcsOplog.setResult("0");
		mcsOplog.setField1(CompanyEnum.getCompanyNo(incident.getShwCompany()));
		mcsOplog.setField2(incident.getUserNo());
		mcsOplog.setField6(incident.getSource());
		mcsOplogMapper.insert(mcsOplog);
	}
	
	
	
	public YwtbProcessRequest getYwtbProcessRequest(McsIncident incident,IncidentStatusRequest form,AdminLoginInfo adminLoginInfo) {
		YwtbProcessRequest request = new YwtbProcessRequest();
		request.setApplyNo(incident.getApplyNo());
		switch (form.getStatus()) {
		case IncidentStatus.FINISH://办结
			request.setOp("审查与决定");
			request.setResult("通过");
			request.setSpecialType("");// 特别程序种类
			request.setSpecialName("");// 特别程序种类名称
			request.setSpecialReason("");// 特别程序启动理由或依据
			break;
		case IncidentStatus.DEFER://延期
			request.setOp("特别程序");
			request.setResult("通过");
			request.setSpecialType("INCIDENT.DEFER");// 特别程序种类
			request.setSpecialName("延期");// 特别程序种类名称
			request.setSpecialReason(incident.getDescription());// 特别程序启动理由或依据
			break;
		case IncidentStatus.RETURN://退单
			request.setOp("审查与决定");
			request.setResult("不通过");
			request.setSpecialType("");// 特别程序种类
			request.setSpecialName("");// 特别程序种类名称
			request.setSpecialReason("");// 特别程序启动理由或依据
			break;
		default:
			break;
		}
		request.setSuggestion(incident.getDescription());
		request.setOpDepartCode("SHCTSH");
		request.setOpDepartName("上海城投水务(集团)有限公司");
		request.setOpUserId(adminLoginInfo.getUserName());
		request.setOpUsername(CompanyEnum.getCompanyNameByCode(incident.getShwCompany()));
		request.setOpTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		request.setFinishtime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		request.setIsDeliveryResults("2");
		request.setResultCetrType("3");
		return request;
	}
	
	@Override
	public boolean downSaleFile(String incidentId) {
		log.info("========客户下载合同begin");
		McsIncident incident = mcsIncidentMapper.selectById(incidentId);
		if(null != incident) {
			String ftpPath = "/"+incident.getShwCompany()+"HT"+"/"+incident.getIncidentId()+"/";
			String localSalePath = filePath +incident.getIncidentId()+"/";
//			System.out.println(config.getHostname());
//			System.out.println(config.getPort());
//			System.out.println(config.getUsername());
//			System.out.println(config.getPassword());
			if(!MCSFtpUtil.downFile("10.9.6.30","21","ruantongwkfzz","rt@wkf666vs", ftpPath, localSalePath)) {
				log.error("合同下载本地失败");
			}
		}else {
			log.error("incidentId:"+incidentId+"不存在");
			return false;
		}
		log.info("========客户下载合同end");
		return true;
	}
	
}
