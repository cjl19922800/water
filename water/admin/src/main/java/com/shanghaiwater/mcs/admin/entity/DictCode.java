package com.shanghaiwater.mcs.admin.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@Data
@TableName("sys_dict_item")
public class DictCode {

	@TableId("dict_item_id")
	private String dictItemId;
	
	@TableField("dict_code")
	private String dictCode;
	
	@TableField("item_code")
	private String itemCode;
	
	@TableField("item_value")
	private String itemValue;
	
	@TableField("status")
	private String status;
	
	@TableField("sort_no")
	private Integer sortNo;
	
	@TableField("description")
	private String description;
	
	@TableField("record_id")
	private String record_id;

	@TableField("cdate")
	private Date cdate;

	@TableField("udate")
	private Date udate;
	
	@TableField("record_version")
	private String record_version;

	public String getDictItemId() {
		return dictItemId;
	}

	public void setDictItemId(String dictItemId) {
		this.dictItemId = dictItemId;
	}

	public String getDictCode() {
		return dictCode;
	}

	public void setDictCode(String dictCode) {
		this.dictCode = dictCode;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getItemValue() {
		return itemValue;
	}

	public void setItemValue(String itemValue) {
		this.itemValue = itemValue;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getSortNo() {
		return sortNo;
	}

	public void setSortNo(Integer sortNo) {
		this.sortNo = sortNo;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRecord_id() {
		return record_id;
	}

	public void setRecord_id(String record_id) {
		this.record_id = record_id;
	}

	public Date getCdate() {
		return cdate;
	}

	public void setCdate(Date cdate) {
		this.cdate = cdate;
	}

	public Date getUdate() {
		return udate;
	}

	public void setUdate(Date udate) {
		this.udate = udate;
	}

	public String getRecord_version() {
		return record_version;
	}

	public void setRecord_version(String record_version) {
		this.record_version = record_version;
	}
	
	
}
