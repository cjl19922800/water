package com.shanghaiwater.mcs.admin.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.stereotype.Service;

import com.shanghaiwater.mcs.admin.common.AdminLoginInfo;
import com.shanghaiwater.mcs.admin.model.BasicResponse;
import com.shanghaiwater.mcs.admin.model.request.IncidentDealRequest;
import com.shanghaiwater.mcs.admin.model.request.IncidentStatusRequest;
import com.shanghaiwater.mcs.admin.model.request.IncidentTransferRequest;

@Service
public interface IncidentStatusService {

	/**
	 * 修改状态
	 * @param request
	 * @return
	 */
	public BasicResponse updateStatus(IncidentStatusRequest request, AdminLoginInfo adminLoginInfo);

	/**
	 * 转单
	 * @param request
	 * @param adminLoginInfo
	 * @return
	 */
	public BasicResponse transfer(IncidentTransferRequest request, AdminLoginInfo adminLoginInfo);

	/**
	 * 处理标识
	 * @param incidentId
	 * @param adminLoginInfo
	 * @return
	 */
	public BasicResponse deal(IncidentDealRequest request, AdminLoginInfo adminLoginInfo);

	/**
	 * 导出主方法
	 * @param map
	 * @param bussinessType
	 * @param adminLoginInfo
	 * @return 
	 * @throws IOException 
	 * @throws InvalidFormatException 
	 * @throws FileNotFoundException 
	 * @throws EncryptedDocumentException 
	 */
	public HSSFWorkbook export(Map<String, Object> map, String bussinessType, AdminLoginInfo adminLoginInfo) throws EncryptedDocumentException, FileNotFoundException, InvalidFormatException, IOException;

	/**
	 * 合同下载
	 * @param incidentId
	 * @return
	 */
	public boolean downSaleFile(String incidentId);
	
}
