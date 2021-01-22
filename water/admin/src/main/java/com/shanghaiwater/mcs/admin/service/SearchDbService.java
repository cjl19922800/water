package com.shanghaiwater.mcs.admin.service;
import java.sql.ResultSet;
import java.util.Map;

import com.shanghaiwater.mcs.admin.model.SearchDbResponse;

public interface SearchDbService {
	
	public SearchDbResponse search(Map<String,Object> map);

}
