package com.shanghaiwater.mcs.admin.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.shanghaiwater.mcs.admin.annotation.PermissionIntercept;
import com.shanghaiwater.mcs.admin.common.AdminLoginInfo;
import com.shanghaiwater.mcs.admin.model.BasicResponse;
import com.shanghaiwater.mcs.admin.model.request.IncidentDealRequest;
import com.shanghaiwater.mcs.admin.model.request.IncidentStatusRequest;
import com.shanghaiwater.mcs.admin.model.request.IncidentTransferRequest;
import com.shanghaiwater.mcs.admin.service.IMgtWaterCompanyService;
import com.shanghaiwater.mcs.admin.service.IncidentStatusService;
import com.shanghaiwater.mcs.admin.util.CommonUtil;
import com.shanghaiwater.mcs.db.entity.McsIncident;
import com.shanghaiwater.mcs.db.entity.MgtWaterCompany;
import com.shanghaiwater.mcs.db.mapper.McsIncidentMapper;

@RestController
@RequestMapping("/incident/handle")
@PermissionIntercept
public class IncidentHandleController {

	@Autowired
	private IncidentStatusService incidentStatusService;
	
	@Autowired
	private IMgtWaterCompanyService mgtWaterCompanyService;
	
	@Autowired
	private McsIncidentMapper mcsIncidentMapper;
	
	@Value("${filePath}")
	private String filePath;
	
	@RequestMapping("/downSale")
	@PermissionIntercept
	public BasicResponse down(
			HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value = "incident_id", required = false) String incidentId) throws Exception {
		BasicResponse result = new BasicResponse();
		boolean flag = incidentStatusService.downSaleFile(incidentId);
		if(!flag) {
			return result.error("NOT.FILE.EXITS", "无合同文件");
		}
		List<String> fileNameList = CommonUtil.findFileList(filePath+incidentId);
		if(fileNameList.size() >0) {
			if(fileNameList.size() == 1) {
				downOne(request, response, fileNameList.get(0));
			}else {
				String[] arr = fileNameList.toArray(new String[fileNameList.size()]);
				downMore(filePath, arr, request, response);
			}
		}
		return result.success();
	}
	
	@RequestMapping("/status")
	@PermissionIntercept
	public BasicResponse updateStatus(@RequestBody IncidentStatusRequest form,HttpServletRequest request) {
		AdminLoginInfo adminLoginInfo = (AdminLoginInfo) request.getSession().getAttribute("adminLoginInfo");
		return incidentStatusService.updateStatus(form, adminLoginInfo);
	}
	
	@RequestMapping("/transfer/index")
	@PermissionIntercept
	public ModelAndView transferIndex(Model model,@RequestParam("incidentId") String incidentId) {
		List<MgtWaterCompany> companys = mgtWaterCompanyService.findAllCompany();
		McsIncident incident = mcsIncidentMapper.selectById(incidentId);
		if(companys.size()>0) {
			for(int i=0;i<companys.size();i++) {
				if(companys.get(i).getCompanyCode().equals(incident.getShwCompany())) {
					companys.remove(i);
					break;
				}
			}
		}
		model.addAttribute("companys", companys);
		model.addAttribute("incident", incident);
		return new ModelAndView("views/home/incident/transferForm", "model", model);
	}
	
	@RequestMapping("/transfer")
	@PermissionIntercept
	public BasicResponse tranfer(@RequestBody IncidentTransferRequest form,HttpServletRequest request) {
		AdminLoginInfo adminLoginInfo = (AdminLoginInfo) request.getSession().getAttribute("adminLoginInfo");
		return incidentStatusService.transfer(form, adminLoginInfo);
	}
	
	
	@RequestMapping("/deal")
	@PermissionIntercept
	public BasicResponse deal(@RequestBody IncidentDealRequest form,HttpServletRequest request) {
		AdminLoginInfo adminLoginInfo = (AdminLoginInfo) request.getSession().getAttribute("adminLoginInfo");
		return incidentStatusService.deal(form, adminLoginInfo);
	}
	
	@RequestMapping("/export")
	@PermissionIntercept
	public String export(HttpServletRequest reqeust, HttpServletResponse response,
			@RequestParam("incidentType") String incidentType,
			@RequestParam(value = "status", required = false) String status,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "phone", required = false) String phone,
			@RequestParam(value = "companyCode", required = false) String companyCode,
			@RequestParam(value = "userNo", required = false) String userNo,
			@RequestParam(value = "deal", required = false) Integer deal,
			@RequestParam(value = "startdate", required = false) String startdate,
			@RequestParam(value = "enddate", required = false) String enddate) throws EncryptedDocumentException, FileNotFoundException, InvalidFormatException, IOException {
		AdminLoginInfo adminLoginInfo = (AdminLoginInfo) reqeust.getSession().getAttribute("adminLoginInfo");
		List<String> companyCodes = adminLoginInfo.getCompanyAuthorities();
		if(companyCodes.size() == 0 || null == companyCodes) {
			return null;
		}
		
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("status",status);
		if(StringUtils.isEmpty(phone)) {
			map.put("phone", phone);
		}else {
			map.put("phone", "%"+phone+"%");
		}
		if(StringUtils.isEmpty(name)) {
			map.put("name", name);
		}else {
			map.put("name", "%"+name+"%");
		}
		map.put("companyCode", companyCode);
		map.put("userNo", userNo);
		map.put("deal", deal);
//		map.put("startdate", startdate);
//		map.put("enddate", enddate);
		if(StringUtils.isNotEmpty(startdate)) {
			map.put("startdate", startdate+" 00:00:00");
		}
		if(StringUtils.isNotEmpty(enddate)) {
			map.put("enddate", enddate+" 23:59:59");
		}
		map.put("companys", companyCodes.toArray(new String[companyCodes.size()]));
		HSSFWorkbook hssfWorkbook = incidentStatusService.export(map, incidentType, adminLoginInfo);
		if(hssfWorkbook == null) {
			return null;
		}
		String dateString = CommonUtil.dateFormatString("yyyyMMdd", new Date());
		try {
			response.setHeader("Content-Disposition","attachment;filename="+dateString+".xls");
			response.setContentType("application/vnd.ms-excel;utf-8");
			hssfWorkbook.write(response.getOutputStream());
		} catch (IOException  e) {
			e.printStackTrace();
		}finally {
			hssfWorkbook.close();
		}
		return null;
	}
	
	
	
	
	/**
	 * 单文件下载
	 * @param request
	 * @param response
	 * @param filePath
	 * @throws IOException
	 */
	public void downOne(HttpServletRequest request, HttpServletResponse response,String filePath) throws IOException {
		String fileNamePath = filePath;
		String filename = "合同.pdf";
		File file = new File(fileNamePath);
		if (file.exists()) {
            OutputStream out = null;
            FileInputStream in = null;
            try {
                // 1.读取要下载的内容
                in = new FileInputStream(file);
                // 2. 告诉浏览器下载的方式以及一些设置
                // 解决文件名乱码问题，获取浏览器类型，转换对应文件名编码格式，IE要求文件名必须是utf-8, firefo要求是iso-8859-1编码
                String agent = request.getHeader("user-agent");
                if (agent.contains("FireFox")) {
                    filename = new String(filename.getBytes("UTF-8"), "iso-8859-1");
                } else {
                    filename = URLEncoder.encode(filename, "UTF-8");
                }
                // 设置下载文件的mineType，告诉浏览器下载文件类型
                String mineType = request.getServletContext().getMimeType(filename);
                response.setContentType(mineType);
                // 设置一个响应头，无论是否被浏览器解析，都下载
                response.setHeader("Content-disposition", "attachment; filename=" + filename);
                // 将要下载的文件内容通过输出流写到浏览器
                out = response.getOutputStream();
                int len = 0;
                byte[] buffer = new byte[1024];
                while ((len = in.read(buffer)) > 0) {
                    out.write(buffer, 0, len);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            }
		}
	}
	
	/**
	 * 多文件压缩包下载
	 * @param path
	 * @param files
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void downMore(String path, String[] files, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// path 压缩文件初始设置 
		String base_name = "合同";
		String fileZip = base_name + ".zip"; // 拼接zip文件,之后下载下来的压缩文件的名字
		String filePath = path + fileZip;// 之后用来生成zip文件
 
		// 创建临时压缩文件
		try {
			BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath));
			ZipOutputStream zos = new ZipOutputStream(bos);
			ZipEntry ze = null;
			for (int i = 0; i < files.length; i++) {// 将所有需要下载的文件都写入临时zip文件
				BufferedInputStream bis = new BufferedInputStream(
						new FileInputStream(files[i]));
				ze = new ZipEntry(
						files[i].substring(files[i].lastIndexOf("\\")));
				zos.putNextEntry(ze);
				int s = -1;
				while ((s = bis.read()) != -1) {
					zos.write(s);
				}
				bis.close();
			}
			zos.flush();
			zos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 以上，临时压缩文件创建完成
 
		// 进行浏览器下载
		// 获得浏览器代理信息
		String agent = request.getHeader("User-Agent").toUpperCase();
		// 判断浏览器代理并分别设置响应给浏览器的编码格式
		String finalFileName = null;
		if ((agent.indexOf("MSIE") > 0)
				|| ((agent.indexOf("RV") != -1) && (agent.indexOf("FIREFOX") == -1)))
			finalFileName = URLEncoder.encode(fileZip, "UTF-8");
		else {
			finalFileName = new String(fileZip.getBytes("UTF-8"), "ISO8859-1");
		}
		response.setContentType("application/x-download");// 告知浏览器下载文件，而不是直接打开，浏览器默认为打开
		response.setHeader("Content-Disposition", "attachment;filename=\""
				+ finalFileName + "\"");// 下载文件的名称
		//输出到本地
		ServletOutputStream servletOutputStream = response.getOutputStream();
		DataOutputStream temps = new DataOutputStream(servletOutputStream);
 
		DataInputStream in = new DataInputStream(new FileInputStream(filePath));// 浏览器下载临时文件的路径
		byte[] b = new byte[2048];
		File reportZip = new File(filePath);// 之后用来删除临时压缩文件
		try {
			while ((in.read(b)) != -1) {
				temps.write(b);
			}
			temps.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (temps != null)
				temps.close();
			if (in != null)
				in.close();
			if (reportZip != null)
				reportZip.delete();// 删除服务器本地产生的临时压缩文件
			servletOutputStream.close();
		}
	}
	
	
}
