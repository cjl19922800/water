package com.shanghaiwater.mcs.admin.enums;

import java.util.ArrayList;
import java.util.List;

import com.shanghaiwater.mcs.common.enums.ItemCodeEnum;

public enum BusinessTypeEnums {

	
	RESIDENT_APPLY("WaterApply", "P", "McsItemType.ResidentApply", "居民住宅单独接水"),
	COMPANY_APPLY("WaterApplyCompany", "C", "McsItemType.CompanyApply", "单位接水"),
	REPAIR_LEAK_WATER_RESIDENT("RepairLeakWater", "P", "McsItemType.RepairLeakWaterResident", "居民用水报修（水管漏水）"),
	REPAIR_LEAK_WATER_COMPANY("RepairLeakWater", "C", "McsItemType.RepairLeakWaterCompany", "单位用水报修（水管漏水）"),
	REPAIR_USE_WATER_RESIDENT("RepairUseWater", "P", "McsItemType.RepairUseWaterResident", "居民用水报修（用水问题）"),
	REPAIR_USE_WATER_COMPANY("RepairUseWater", "C", "McsItemType.RepairUseWaterCompany", "单位用水报修（用水问题）"),
	REPAIR_WATER_QUALITY_RESIDENT("RepairWaterQuality", "P", "McsItemType.RepairWaterQualityResident", "居民用水报修（水质问题）"),
	REPAIR_WATER_QUALITY_COMPANY("RepairWaterQuality", "C", "McsItemType.RepairWaterQualityCompany", "单位用水报修（水质问题）"),
	REPAIR_WATER_METER_RESIDENT("RepairWaterMeter", "P", "McsItemType.RepairWaterMeterResident", "居民用水报修（表务问题）"),
	REPAIR_WATER_METER_COMPANY("RepairWaterMeter", "C", "McsItemType.RepairWaterMeterCompany", "单位用水报修（表务问题）"),
	REPAIR_OTHER_RESIDENT("RepairOther", "P", "McsItemType.RepairOtherResidentCompany", "居民用水报修（其它）"),
	REPAIR_OTHER_COMPANY("RepairOther", "C", "McsItemType.RepairOtherCompany", "单位用水报修（其它）"),
	REPAIR_RESIDENT_TRANSFER("TransferResident", "P", "McsItemType.RepairResidentTransfer", "居民过户"),
	REPAIR_COMPANY_TRANSFER("TransferCompany", "C", "McsItemType.RepairCompanyTransfer", "单位过户"),
	REPAIR_WATER_METER_SPLIT("Split", "P", "McsItemType.RepairWatermeterSplit", "居民住宅总表分装"),
	MULTI_HOUSE_HOLD("MultiHousehold", "P", "McsItemType.MultiHousehold", "一户多人口"),
	MULTI_HOUSE_SIMPLE_HOLD("MultiHouseholdSimple", "P", "McsItemType.MultiHousehold", "一户多人口"),
	UNI_TRANSFER("UniTransfer", "P", "McsItemType.UniTransfer", "联合过户"),
	REAL_NAME_REGISTE("RealNameRegiste", "P", "McsItemType.RealNameRegiste", "实名制登记"),
	REAL_NAME_REGISTE_COMPANY("RealNameCompanyRegiste", "C", "McsItemType.RealNameCompanyRegiste", "实名制(单位)登记"),
	CUSTOMER_INFO_CHANGE("CustomerInfoChange", "P", "McsItemType.CustomerInfoChange", "客户信息变更(居民)"),
	CUSTOMER_INFO_COMPANY_CHANGE("CustomerCompanyInfoChange", "C", "McsItemType.CustomerCompanyInfoChange", "客户信息变更(单位)"),
	PAY_NOTICE_REMAIL("PayNoticeRemail", "P", "McsItemType.PayNoticeRemail", "补送缴费通知单（居民）"),
	PAY_NOTICE_COMPANY_REMAIL("PayNoticeCompanyRemail", "C", "McsItemType.PayNoticeCompanyRemail", "补送缴费通知单（单位）"),
	ELEC_INVOICE_AUTO_PUSH("ElecInvoiceAutoPush", "P", "McsItemType.ElecInvoiceAutoPush", "电子发票自动推送"),
	ELEC_INVOICE_COMPANY_AUTO_PUSH("ElecInvoiceCompanyAutoPush", "C", "McsItemType.ElecInvoiceCompanyAutoPush", "电子发票自动推送"),
	ELEC_INVOICE_OBTAIN("ElecInvoiceObtain", "P", "McsItemType.ElecInvoiceObtain", "电子发票获取（单次）"),
	ELEC_INVOICE_COMPANY_OBTAIN("ElecInvoiceCompanyObtain", "C", "McsItemType.ElecInvoiceCompanyObtain", "电子发票获取（单次）"),
	REPAIR_METEROUTWARD("RepairMeterOutward", "P", "McsItemType.RepairMeterOutward", "水表外移"),
	REPAIR_UNSEAL("RepairUnseal", "P", "McsItemType.RepairUnseal", "报修（新表启封）"),
	WRITE_OFF_ACCOUNT("WriteOffAccount", "C", "McsItemType.WriteOffAccount", "水表拆表销户"),
	WATER_RATE_COLLECTION("WaterRateCollection", "C", "McsItemType.WaterRateCollection", "水费托收"),
	SHARING_WATER_METER("SharingWaterMeter", "P", "McsItemType.SharingWaterMeter", "居民合用表"),
	USE_WATER_NATURE_CHANGE("UseWaterNatureChange", "C", "McsItemType.UseWaterNatureChange", "用水性质变更"),
	ADVICE_COMPLAINT("AdviceComplaint", "P", "McsItemType.AdviceComplaint", "业务咨询、表扬、投诉、建议"),
	ADVICE_COMPLAINT_COMPANY("AdviceComplaintCompany", "C", "McsItemType.AdviceComplaintCompany", "业务咨询、表扬、投诉、建议"),
	;
	
	private String businessType;
	private String type;
	private String incidentType;
	private String itemName;
	
	private BusinessTypeEnums(String businessType,String type,String incidentType,String itemName) {
		this.businessType = businessType;
		this.type = type;
		this.incidentType = incidentType;
		this.itemName = itemName;
	}

	public static String getBusinessName(String businessType) {
		for (BusinessTypeEnums typeEnum : BusinessTypeEnums.values()) {
			if(typeEnum.getBusinessType().equals(businessType)) {
				return typeEnum.getItemName();
			}
		}
		return null;
	} 
	
	public static List<String> getIncidentTypes(String businessType){
		List<String> list = new ArrayList<String>();
		for (BusinessTypeEnums typeEnum : BusinessTypeEnums.values()) {
			if(typeEnum.getBusinessType().equals(businessType)) {
				list.add(typeEnum.getIncidentType());
			}
		}
		return list;
	}
	
	public String getBusinessType() {
		return businessType;
	}

	public String getType() {
		return type;
	}

	public String getIncidentType() {
		return incidentType;
	}

	public String getItemName() {
		return itemName;
	} 
	
}
