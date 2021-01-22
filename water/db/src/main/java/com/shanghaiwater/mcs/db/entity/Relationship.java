package com.shanghaiwater.mcs.db.entity;

import java.io.Serializable;

import lombok.Data;

@Data
public class Relationship implements Serializable {

	private static final long serialVersionUID = 1L;

	private String branch_code;
	private String card_id;
	private String address;
	private String relationship;

}
