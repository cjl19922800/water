package com.shanghaiwater.mcs.admin.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shanghaiwater.mcs.common.model.masterdata.AreaTownResponse;
import com.shanghaiwater.mcs.common.model.masterdata.DictCodeResponse;
import com.shanghaiwater.mcs.common.model.masterdata.DictTypeResponse;
import com.shanghaiwater.mcs.common.model.masterdata.MultiCodeDetailResponse;
import com.shanghaiwater.mcs.common.model.masterdata.WaterCompanyResponse;

@FeignClient(name = "code", url = "http://localhost:8020")
//@FeignClient(name = "code", url = "http://192.168.6.71:8020")
public interface CodeService {

	@RequestMapping(value = "/getCode/dictType")
	public DictCodeResponse selectAllCode(@RequestParam("dictCode") String dictCode);

	@RequestMapping(value = "/selectDictByItemCode")
	public DictCodeResponse selectDictByItemCode(@RequestParam("dictCode") String dictCode,
			@RequestParam("itemCode") String itemCode);

	@RequestMapping(value = "/getType/dictType")
	public DictTypeResponse selectAllType();

	@RequestMapping(value = "/selectAllCisCompany")
	public WaterCompanyResponse selectAllCisCompany();

	@RequestMapping(value = "/selectByMultiCode")
	public MultiCodeDetailResponse selectByMultiCode(@RequestParam("codeType") String codeType,
			@RequestParam("parentItemCode") String parentItemCode);

	@RequestMapping(value = "/selectMultiCodeDetailByCodeType")
	public MultiCodeDetailResponse selectMultiCodeDetailByCodeType(@RequestParam("codeType") String codeType);

	@RequestMapping(value = "/selectByItemCode")
	public MultiCodeDetailResponse selectByItemCode(@RequestParam("codeType") String codeType,
			@RequestParam("parentItemCode") String parentItemCode, @RequestParam("itemCode") String itemCode);

	@RequestMapping(value = "/selectAllArea")
	public AreaTownResponse selectAllArea();

	@RequestMapping(value = "/selectTownByArea")
	public AreaTownResponse selectTownByArea(@RequestParam("qxdmApply") String qxdmApply);

	@RequestMapping(value = "/selectOneTown")
	public AreaTownResponse selectOneTown(@RequestParam("jdzdmApply") String jdzdmApply);
}
