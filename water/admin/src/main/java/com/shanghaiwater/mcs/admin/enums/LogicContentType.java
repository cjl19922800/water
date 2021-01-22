package com.shanghaiwater.mcs.admin.enums;

public enum LogicContentType {

	RepairLeakWaterResident("McsItemType.RepairLeakWaterResident","RepairLeakWater","水管漏水"),
	RepairLeakWaterCompany("McsItemType.RepairLeakWaterCompany","RepairLeakWater","水管漏水"),
	
	RepairUseWaterResident("McsItemType.RepairUseWaterResident","RepairUseWaterProblem","用水困难"),
	RepairUseWaterCompany("McsItemType.RepairUseWaterCompany","RepairUseWaterProblem","用水困难"),
	
	RepairWaterQualityResident("McsItemType.RepairWaterQualityResident","RepairWaterQuality","水质问题"),
	RepairWaterQualityCompany("McsItemType.RepairWaterQualityCompany","RepairWaterQuality","水质问题"),
	
	RepairWaterMeterResident("McsItemType.RepairWaterMeterResident","RepairWaterMeter","表务问题"),
	RepairWaterMeterCompany("McsItemType.RepairWaterMeterCompany","RepairWaterMeter","表务问题"),
	
	RepairOtherResidentCompany("McsItemType.RepairOtherResidentCompany","RepairOtherWater","其它"),
	RepairOtherCompany("McsItemType.RepairOtherCompany","RepairOtherWater","其它")
	;

	private final String incidentType;
	private final String logicType;
	private final String logicName;
	
	
	LogicContentType(String incidentType, String logicType, String logicName) {
		this.incidentType = incidentType;
		this.logicType = logicType;
		this.logicName = logicName;
	}


	
	public static String getFylb(String incidentType) {
		for (LogicContentType logicContentEums : LogicContentType.values()) {
			if (logicContentEums.getIncidentType().equals(incidentType)) {
				return logicContentEums.getLogicName();
			}
		}
		return null;
	}
	
	
	
	public static String getLogicType(String incidentType) {
		for (LogicContentType logicContentEums : LogicContentType.values()) {
			if (logicContentEums.getIncidentType().equals(incidentType)) {
				return logicContentEums.getLogicType();
			}
		}
		return null;
	}
	
	
	
	public String getIncidentType() {
		return incidentType;
	}


	public String getLogicType() {
		return logicType;
	}


	public String getLogicName() {
		return logicName;
	}
	
}
