package com.shanghaiwater.mcs.db.vo;

import java.io.Serializable;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class RoleResourcesPowerVO implements Serializable{
	private static final long serialVersionUID = 4128952203285220428L;
	private String parnavName;
	private String url;
	private String navName;
	
}
