package com.lys.utils;

import java.util.List;
import java.util.Map;

public class PageInnerMethod {
	/**
	 * 检查建材商城和电器商城的目录下是否有子目录
	 * @param categoryList
	 * @param map
	 * @return
	 */
	public static boolean IsExistChildItem(List<Map<String, Object>> categoryList,Map<String, Object> map){
		for(int s=0;s<categoryList.size();s++){
			if(categoryList.get(s).get("ab_st_parent").toString().equals(map.get("ab_st_id").toString())){
				return true;
			}
		}
		return false;
	}
}
