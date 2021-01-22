package com.shanghaiwater.mcs.admin.service.inf;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shanghaiwater.mcs.admin.feign.CodeService;
import com.shanghaiwater.mcs.common.model.masterdata.AreaTownResponse;
import com.shanghaiwater.mcs.common.model.masterdata.DictCodeResponse;
import com.shanghaiwater.mcs.common.model.masterdata.DictTypeResponse;
import com.shanghaiwater.mcs.common.model.masterdata.MultiCodeDetailResponse;
import com.shanghaiwater.mcs.common.vo.AreaTown;
import com.shanghaiwater.mcs.common.vo.DictItem;
import com.shanghaiwater.mcs.common.vo.DictType;
import com.shanghaiwater.mcs.common.vo.MultiCodeDetail;

@Service
public class ReginCodeService {

	@Autowired
	private CodeService codeService;

	// 暂时不用
	public List<DictItem> getCode(String dictCode) {
		DictCodeResponse response = codeService.selectAllCode(dictCode);
		List<DictItem> data = response.getData();
		return data;
	}

	public List<DictItem> selectDictByItemCode(String dictCode, String itemCode) {
		DictCodeResponse response = codeService.selectDictByItemCode(dictCode, itemCode);
		List<DictItem> data = response.getData();
		return data;
	}

	public List<DictItem> selectDictByItemCode(String itemCode) { // TODO

		return null;
	}

	// 暂时不用
	public List<DictType> getCertificateType() {
		DictTypeResponse response = codeService.selectAllType();
		List<DictType> data = response.getData();
		return data;
	}

	public List<MultiCodeDetail> selectByMultiCode(String codeType, String parentItemCode) {
		MultiCodeDetailResponse response = codeService.selectByMultiCode(codeType, parentItemCode);
		List<MultiCodeDetail> data = response.getData();
		return data;
	}

	public List<MultiCodeDetail> selectByItemCode(String codeType, String parentItemCode, String itemCode) {
		MultiCodeDetailResponse response = codeService.selectByItemCode(codeType, parentItemCode, itemCode);
		List<MultiCodeDetail> data = response.getData();
		return data;
	}

	public Map<String, Map<String, List<MultiCodeDetail>>> selectMultiCodeDetailByCodeType(String codeType) {
		MultiCodeDetailResponse response = codeService.selectMultiCodeDetailByCodeType(codeType);
		Map<String, Map<String, List<MultiCodeDetail>>> dataTree = response.getDataTree();
		return dataTree;
	}

	public List<AreaTown> selectAllArea() {
		AreaTownResponse response = codeService.selectAllArea();
		List<AreaTown> data = response.getData();
		return data;
	}

	public List<AreaTown> selectTownByArea(String area) {
		AreaTownResponse response = codeService.selectTownByArea(area);
		List<AreaTown> data = response.getData();
		return data;
	}

	public List<AreaTown> selectOneTown(String jdzdmApply) {
		AreaTownResponse response = codeService.selectOneTown(jdzdmApply);
		List<AreaTown> data = response.getData();
		return data;
	}
}
