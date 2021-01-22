package com.shanghaiwater.mcs.admin.model;

import java.util.List;

import com.shanghaiwater.mcs.admin.common.ResponseModel;
import com.shanghaiwater.mcs.admin.common.UserInfo;
import com.shanghaiwater.mcs.common.vo.AreaTown;
import com.shanghaiwater.mcs.common.vo.DictItem;
import com.shanghaiwater.mcs.common.vo.DictType;
import com.shanghaiwater.mcs.common.vo.MultiCodeDetail;
import com.shanghaiwater.mcs.common.vo.WaterCompany;

public class BaseResponse extends ResponseModel {

	private List<DictItem> dictCode;

	private List<DictType> type;

	private List<WaterCompany> cisCompany;

	private List<AreaTown> area;

	private List<MultiCodeDetail> multiCodeDetail;

	private List<UserInfo> userInfo;

	private Integer total;

	private boolean success;

	public List<DictItem> getDictCode() {
		return dictCode;
	}

	public void setDictCode(List<DictItem> dictCode) {
		this.dictCode = dictCode;
	}

	public List<DictType> getType() {
		return type;
	}

	public void setType(List<DictType> type) {
		this.type = type;
	}

	public List<WaterCompany> getCisCompany() {
		return cisCompany;
	}

	public void setCisCompany(List<WaterCompany> cisCompany) {
		this.cisCompany = cisCompany;
	}

	public List<AreaTown> getArea() {
		return area;
	}

	public void setArea(List<AreaTown> area) {
		this.area = area;
	}

	public List<MultiCodeDetail> getMultiCodeDetail() {
		return multiCodeDetail;
	}

	public void setMultiCodeDetail(List<MultiCodeDetail> multiCodeDetail) {
		this.multiCodeDetail = multiCodeDetail;
	}

	public List<UserInfo> getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(List<UserInfo> userInfo) {
		this.userInfo = userInfo;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

}
