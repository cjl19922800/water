package com.shanghaiwater.mcs.admin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.shanghaiwater.mcs.db.entity.WaterRepair;
import com.shanghaiwater.mcs.db.mapper.SysDictItemMapper;
import com.shanghaiwater.mcs.admin.model.BaseResponse;
import com.shanghaiwater.mcs.admin.model.BasicResponse;
import com.shanghaiwater.mcs.admin.model.WaterRepairRequest;
import com.shanghaiwater.mcs.admin.service.IMgtWaterCompanyService;
import com.shanghaiwater.mcs.admin.service.inf.CisCompanyCodeService;
import com.shanghaiwater.mcs.admin.service.inf.ReginCodeService;
import com.shanghaiwater.mcs.admin.service.inf.RelationShipService;
import com.shanghaiwater.mcs.common.vo.AreaTown;
import com.shanghaiwater.mcs.common.vo.DictItem;
import com.shanghaiwater.mcs.common.vo.DictType;
import com.shanghaiwater.mcs.common.vo.MultiCodeDetail;
import com.shanghaiwater.mcs.common.vo.WaterCompany;
import com.shanghaiwater.mcs.db.entity.McsUserRelationship;
import com.shanghaiwater.mcs.db.entity.MgtWaterCompany;

/**
 * 基础服务
 * 
 * @author Laipu
 *
 */
@RestController
@CrossOrigin
@RequestMapping("/water")
public class BaseController {

	@Autowired
	private ReginCodeService reginCodeService;

	@Autowired
	private RelationShipService relationShipService;

	@Autowired
	private CisCompanyCodeService cisCompanyCodeService;
	
	@Autowired
	private IMgtWaterCompanyService mgtWaterCompanyService;

	@Autowired
	private SysDictItemMapper sysDictItemMapper;

	/**
	 * 保存验证基本信息
	 * 
	 * @param waterRepairRequest
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/apply", method = RequestMethod.POST)
	public Map<String, String> personalApplyPage(@RequestBody WaterRepairRequest waterRepairRequest,
			HttpServletRequest request) {
		WaterRepair waterRepair = waterRepairRequest.getWaterRepair();
		HttpSession session = request.getSession();

		session.setAttribute("cisCompany", waterRepair.getCisCompany());
		session.setAttribute("cardId", waterRepair.getCardId());
		session.setAttribute("acctId", waterRepair.getAcctId());
		session.setAttribute("address", waterRepair.getAddress());
		session.setAttribute("xzq", waterRepair.getFyqm());
		session.setAttribute("town", waterRepairRequest.getTown());

		Map<String, String> map = new HashMap<>();
		map.put("success", "success");
		return map;
	}

	/**
	 * 获取证件类型
	 * 
	 * @param personalWaterRepairRequest
	 * @return
	 */
	@RequestMapping(value = "/getCode/query", method = RequestMethod.GET)
	public BaseResponse getDictCode(String DictCode, Integer maxInteger) {
		BaseResponse baseResponse = new BaseResponse();
		try {
			if (maxInteger == null) {
				maxInteger = 100;
			}
			List<DictItem> code = reginCodeService.getCode(DictCode);
			if (code != null && code.size() > 0) {
				baseResponse.setSuccess(true);
				baseResponse.setDictCode(code);
			} else {
				baseResponse.setSuccess(false);
			}
		} catch (Exception e) {
			baseResponse.setErrCode("errCode");
			baseResponse.setErrMessage(e.getMessage());
			baseResponse.setSuccess(false);
		}
		return baseResponse;
	}

	/**
	 * 获取字典名称
	 * 
	 * @param personalWaterRepairRequest
	 * @return
	 */
	@RequestMapping(value = "/selectDictByItemCode", method = RequestMethod.GET)
	public BaseResponse selectDictByItemCode(String itemCode) {
		BaseResponse baseResponse = new BaseResponse();
		try {
			List<DictItem> code = reginCodeService.selectDictByItemCode(itemCode);
			if (code != null && code.size() > 0) {
				baseResponse.setSuccess(true);
				baseResponse.setDictCode(code);
			} else {
				baseResponse.setSuccess(false);
			}
		} catch (Exception e) {
			baseResponse.setErrCode("errCode");
			baseResponse.setErrMessage(e.getMessage());
			baseResponse.setSuccess(false);
		}
		return baseResponse;
	}

	/**
	 * 获取地区
	 * 
	 * @param personalWaterRepairRequest
	 * @return
	 */
	@RequestMapping(value = "/selectByMultiCode", method = RequestMethod.GET)
	public BaseResponse selectByMultiCode(String parentItemCode) {
		BaseResponse baseResponse = new BaseResponse();
		try {
			List<MultiCodeDetail> code = reginCodeService.selectByMultiCode("", parentItemCode);// TODO
			if (code != null && code.size() > 0) {
				baseResponse.setSuccess(true);
				baseResponse.setMultiCodeDetail(code);
			} else {
				baseResponse.setSuccess(false);
			}
		} catch (Exception e) {
			baseResponse.setErrCode("errCode");
			baseResponse.setErrMessage(e.getMessage());
			baseResponse.setSuccess(false);
		}
		return baseResponse;
	}

	/**
	 * 获取单个地区
	 * 
	 * @param personalWaterRepairRequest
	 * @return
	 */
	@RequestMapping(value = "/selectByItemCode", method = RequestMethod.GET)
	public BaseResponse selectByItemCode(String itemCode) {
		BaseResponse baseResponse = new BaseResponse();
		try {
			List<MultiCodeDetail> code = null;// reginCodeService.selectByItemCode("", itemCode);// TODO
			if (code != null && code.size() > 0) {
				baseResponse.setSuccess(true);
				baseResponse.setMultiCodeDetail(code);
			} else {
				baseResponse.setSuccess(false);
			}
		} catch (Exception e) {
			baseResponse.setErrCode("errCode");
			baseResponse.setErrMessage(e.getMessage());
			baseResponse.setSuccess(false);
		}
		return baseResponse;
	}

	/**
	 * 获取编码类型
	 * 
	 * @param personalWaterRepairRequest
	 * @return
	 */
	@RequestMapping(value = "/getType/query", method = RequestMethod.GET)
	public BaseResponse getDictType() {
		BaseResponse baseResponse = new BaseResponse();
		try {
			List<DictType> code = reginCodeService.getCertificateType();
			if (code != null && code.size() > 0) {
				baseResponse.setSuccess(true);
				baseResponse.setType(code);
			} else {
				baseResponse.setSuccess(false);
			}
		} catch (Exception e) {
			baseResponse.setErrCode("errCode");
			baseResponse.setErrMessage(e.getMessage());
			baseResponse.setSuccess(false);
		}
		return baseResponse;
	}

	/**
	 * 获取供水公司
	 * 
	 * @param personalWaterRepairRequest
	 * @return
	 */
	@RequestMapping(value = "/selectAllCisCompany", method = RequestMethod.GET)
	public BaseResponse selectAllCisCompany() {
		BaseResponse baseResponse = new BaseResponse();
		try {
			List<WaterCompany> code = cisCompanyCodeService.selectAllCisCompany();
			if (code != null && code.size() > 0) {
				baseResponse.setSuccess(true);
				baseResponse.setCisCompany(code);
			} else {
				baseResponse.setSuccess(false);
			}
		} catch (Exception e) {
			baseResponse.setErrCode("errCode");
			baseResponse.setErrMessage(e.getMessage());
			baseResponse.setSuccess(false);
		}
		return baseResponse;
	}

	/**
	 * 获取所有区名
	 * 
	 * @param personalWaterRepairRequest
	 * @return
	 */
	@RequestMapping(value = "/selectAllArea", method = RequestMethod.GET)
	public BaseResponse selectAllArea() {
		BaseResponse baseResponse = new BaseResponse();
		try {
			List<AreaTown> code = reginCodeService.selectAllArea();
			if (code != null && code.size() > 0) {
				baseResponse.setSuccess(true);
				baseResponse.setArea(code);
			} else {
				baseResponse.setSuccess(false);
			}
		} catch (Exception e) {
			baseResponse.setErrCode("errCode");
			baseResponse.setErrMessage(e.getMessage());
			baseResponse.setSuccess(false);
		}
		return baseResponse;
	}

	/**
	 * 根据区名获取街道
	 * 
	 * @param personalWaterRepairRequest
	 * @return
	 */
	@RequestMapping(value = "/selectTownByArea", method = RequestMethod.GET)
	public BaseResponse selectTownByArea(String area) {
		BaseResponse baseResponse = new BaseResponse();
		try {
			List<AreaTown> code = reginCodeService.selectTownByArea(area);
			if (code != null && code.size() > 0) {
				baseResponse.setSuccess(true);
				baseResponse.setArea(code);
			} else {
				baseResponse.setSuccess(false);
			}
		} catch (Exception e) {
			baseResponse.setErrCode("errCode");
			baseResponse.setErrMessage(e.getMessage());
			baseResponse.setSuccess(false);
		}
		return baseResponse;
	}

	/**
	 * 根据单个街道
	 * 
	 * @param personalWaterRepairRequest
	 * @return
	 */
	@RequestMapping(value = "/selectOneTown", method = RequestMethod.GET)
	public BaseResponse selectOneTown(String jdzdmApply) {
		BaseResponse baseResponse = new BaseResponse();
		try {
			List<AreaTown> code = reginCodeService.selectOneTown(jdzdmApply);
			if (code != null && code.size() > 0) {
				baseResponse.setSuccess(true);
				baseResponse.setArea(code);
			} else {
				baseResponse.setSuccess(false);
			}
		} catch (Exception e) {
			baseResponse.setErrCode("errCode");
			baseResponse.setErrMessage(e.getMessage());
			baseResponse.setSuccess(false);
		}
		return baseResponse;
	}

	@RequestMapping("/queryDictName")
	public BasicResponse getDictNameByItem(String dictType,String dictItem) {
		BasicResponse response = new BasicResponse();
    	Map<String,Object> paraMap = new HashMap<String,Object>();
        paraMap.put("dictCode", dictType);
        paraMap.put("itemCode", dictItem);
		List<DictItem> dictList = sysDictItemMapper.queryAllItemByCode(paraMap);
        String dictItemName = "";
		if(dictList.size()>0) {
			dictItemName = dictList.get(0).getItemValue();
		}
		response.setCode(200);
		response.setDataString(dictItemName);
		response.setSuccess(true);
		return response;
	}
	
	@RequestMapping("/queryCompanyName")
	public BasicResponse getCompanyNameByItem(String dictItem) {
		String companyName = "";
		BasicResponse response = new BasicResponse();
    	List<MgtWaterCompany> companys = mgtWaterCompanyService.findByCompanyCode(dictItem);
    	if(companys.size()>0) {
    		companyName = companys.get(0).getName();
		}
    	response.setCode(200);
		response.setDataString(companyName);
		response.setSuccess(true);
		return response;
	}
	
	
	
	
	/**
	 * 根据用户id查询其关联关系
	 */
	@RequestMapping(value = "/selectByUserId", method = RequestMethod.GET)
	public List<McsUserRelationship> selectByUserId() {
		// 获取当前登录用户id
		String userId = "f68f9e47-01ca-49a9-93e7-b9ca50a0ab6c";
		return null;// relationShipService.selectByUserId(userId);
	}
}
