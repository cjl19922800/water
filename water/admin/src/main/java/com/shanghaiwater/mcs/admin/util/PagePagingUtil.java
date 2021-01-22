package com.shanghaiwater.mcs.admin.util;

import java.util.ArrayList;
import java.util.List;

public class PagePagingUtil {

	
	public static List<Object> pagePaging(List<Object> dataList, int pageSize,int currentPage) {
		List<Object> dataPagingList = new ArrayList<Object>();
		 if (dataList != null && dataList.size() > 0) {
	            int currIdx = (currentPage > 1 ? (currentPage - 1) * pageSize : 0);
	            for (int i = 0; i < pageSize && i < dataList.size() - currIdx; i++) {
	            	Object data = dataList.get(currIdx + i);
	                dataPagingList.add(data);
	            }
	        }
		return dataPagingList;
	}
 
	
	
	
	
	
}
