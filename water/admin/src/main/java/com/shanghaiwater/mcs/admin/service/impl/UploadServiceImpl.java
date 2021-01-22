package com.shanghaiwater.mcs.admin.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shanghaiwater.mcs.db.entity.McsItemUserImageRel;
import com.shanghaiwater.mcs.db.entity.McsUserImage;
import com.shanghaiwater.mcs.db.entity.McsUserImageDetail;
import com.shanghaiwater.mcs.db.mapper.McsItemUserImageRelMapper;
import com.shanghaiwater.mcs.db.mapper.McsUserImageDetailMapper;
import com.shanghaiwater.mcs.db.mapper.McsUserImageMapper;
import com.shanghaiwater.mcs.admin.service.UploadService;

@Service
public class UploadServiceImpl implements UploadService{
	
	@Value("${filePath}")
    private String filePath;
	
	@Autowired
	private McsItemUserImageRelMapper imageUserRelMapper;
	
	@Autowired
	private McsUserImageMapper imageMapper;
	
	@Autowired
	private McsUserImageDetailMapper imageDetailMapper;
	
    public  Map<String,Object> uploading(MultipartFile file,HttpServletRequest request,String certType) {

    	Map<String,Object> map = new HashMap<>();
        try {
//        	System.out.println("文件路径"+filePath+file.getOriginalFilename());
//        	String name=file.getOriginalFilename();
//        	String str = "D:\\image\\img/";
//        	System.out.println("===================="+System.getProperty("user.dir"));
//        	String path = request.getSession().getServletContext().getRealPath("/");
//        	System.out.println(path);
//        	System.out.println("===============");
        	
        	String fileName = file.getOriginalFilename();
        	String ext = fileName.substring(fileName.indexOf("."));
        	fileName = UUID.randomUUID().toString()+ext;
        	String url="/image/"+fileName;
        	uploadFile(file.getBytes(), filePath, fileName);
        	String applyNo = (String)request.getSession().getAttribute("applyNo");
        	String userName = (String)request.getSession().getAttribute("userName");
//        	String userName = "张三";
        	
        	McsUserImageDetail imageDetail = new McsUserImageDetail();
        	imageDetail.setUserImageDetaileId(UUID.randomUUID().toString());
        	imageDetail.setCertType(certType);
//        	imageDetail.setCertNumber(certNumber);
        	imageDetail.setImageUuid(UUID.randomUUID().toString());
        	imageDetail.setFilename(fileName);
        	imageDetail.setFileType(ext);
        	imageDetail.setLocalUri(filePath+fileName);
        	imageDetail.setWebUri(url);
        	imageDetail.setFtpUri("");
        	imageDetail.setApplyNo(applyNo);
        	imageDetail.setCdate(new Date());
			imageDetail.setUdate(new Date());
			imageDetail.setCreator(userName);
			imageDetail.setUpdator(userName);
			imageDetail.setRecordId(UUID.randomUUID().toString());
			imageDetail.setRecordVersion(UUID.randomUUID().toString());
			
			imageDetailMapper.insert(imageDetail);
        	map.put("code", 200);
        	map.put("src", url);
        	System.out.println("文件上传成功!");
        } catch (Exception e) {
        	map.put("code", 400);
            e.printStackTrace();
            System.out.println("文件上传失败!");
        }
        return map;
        
    }
	
    @Override
	public Map<String, Object> uploading(HttpServletRequest request,String itemCode,String itemName) {
    	String applyNo = (String)request.getSession().getAttribute("applyNo");
    	Map<String,Object> map = new HashMap<>();
    	try {
			QueryWrapper<McsUserImageDetail> queryWrapper = new QueryWrapper<McsUserImageDetail>();
			queryWrapper.eq("apply_no", applyNo);
			List<McsUserImageDetail> list = imageDetailMapper.selectList(queryWrapper);
			String userImageId = UUID.randomUUID().toString();
			for (int x = 0; x < list.size(); x++) {
				list.get(x).setSortNo(100 * (10 + x));
				list.get(x).setFileNo(x + 1 + "");
				list.get(x).setUserImageId(userImageId);
				list.get(x).setCertNumber("22");
//				imageDetailMapper.insert(list.get(x));
				imageDetailMapper.updateById(list.get(x));
			}
			McsUserImage image = new McsUserImage();
			image.setUserImageId(UUID.randomUUID().toString());
			//    	image.setCertType(certType);
			//    	image.setCertNumber(certNumber);
			//    	image.setBusinessCode(businessCode);
			image.setItemCode(itemCode);
			image.setItemName(itemName);
			//    	image.setOrgName(file.getOriginalFilename());
			image.setFileQuantity(list.size());
			image.setUsername(list.get(0).getCreator());
			image.setCdate(new Date());
			image.setUdate(new Date());
			image.setCreator(list.get(0).getCreator());
			image.setUpdator(list.get(0).getCreator());
			image.setRecordId(UUID.randomUUID().toString());
			image.setRecordVersion(UUID.randomUUID().toString());
			imageMapper.insert(image);
			McsItemUserImageRel userImageRel = new McsItemUserImageRel();
			userImageRel.setItemUserImageRelId(UUID.randomUUID().toString());
			//userImageRel.setItemId(itemId);
			userImageRel.setUserImageId(image.getUserImageId());
			userImageRel.setCdate(new Date());
			userImageRel.setUdate(new Date());
			userImageRel.setCreator(list.get(0).getCreator());
			userImageRel.setUpdator(list.get(0).getCreator());
			userImageRel.setRecordId(UUID.randomUUID().toString());
			userImageRel.setRecordVersion(UUID.randomUUID().toString());
			imageUserRelMapper.insert(userImageRel);
        	map.put("success", true);
        } catch (Exception e) {
        	map.put("success", false);
            e.printStackTrace();
		}
		return map;
	}
    
	private void  uploadFile(byte[] file, String filePath, String fileName) throws Exception { 
	        File targetFile = new File(filePath);  
	        if(!targetFile.exists()){    
	            targetFile.mkdirs();    
	        }       
	        FileOutputStream out = new FileOutputStream(filePath+fileName);
	        out.write(file);
	        out.flush();
	        out.close();
	    }



	


}
