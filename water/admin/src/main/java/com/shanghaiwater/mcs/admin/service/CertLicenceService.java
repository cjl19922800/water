package com.shanghaiwater.mcs.admin.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface CertLicenceService {
	
    public  Map<String,Object> updateCert(HttpServletRequest request,String cardName,String itemCode,String itemName);
	
}
