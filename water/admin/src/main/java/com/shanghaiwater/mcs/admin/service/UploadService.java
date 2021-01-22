package com.shanghaiwater.mcs.admin.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

public interface UploadService {
	
    public  Map<String,Object> uploading(MultipartFile file,HttpServletRequest request,String name);
	
    public Map<String,Object> uploading(HttpServletRequest request,String itemCode,String itemName);
}
