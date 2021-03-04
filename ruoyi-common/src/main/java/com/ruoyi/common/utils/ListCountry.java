package com.ruoyi.common.utils;
 
import org.apache.commons.collections4.CollectionUtils;

import java.util.*;

public class ListCountry {

	/**
	 * 获取国家
	 */

	public static List<Map<String,Object>> getCountry() {
		String[] countries = Locale.getISOCountries();
		Map<String,Object> countryMap= new HashMap<>();
		List<Map<String,Object>> maps = new ArrayList<>();
		for (String countryCode : countries) {
			Locale obj = new Locale("", countryCode);
			String upEname = StringUtils.getUpEname(obj.getDisplayCountry());
			countryMap.put("code",upEname);
			countryMap.put("name",obj.getDisplayCountry());
			maps.add(countryMap);
			countryMap = new HashMap<>();

		}
		List<Map<String, Object>> mapList = removeRepeatMapByKey(maps, "code");

		return mapList;
	}

	public static List<Map<String, Object>> removeRepeatMapByKey(List<Map<String, Object>> list, String mapKey) {


		//把list中的数据转换成msp,去掉同一id值多余数据，保留查找到第一个id值对应的数据
		List<Map<String, Object>> listMap = new ArrayList<>();
		Map<String, Map> msp = new HashMap<>();
		for (int i = list.size() - 1; i >= 0; i--) {
			Map map = list.get(i);
			String id = (String) map.get(mapKey);
			map.remove(mapKey);
			msp.put(id, map);
		}
		//把msp再转换成list,就会得到根据某一字段去掉重复的数据的List<Map>
		Set<String> mspKey = msp.keySet();
		for (String key : mspKey) {
			Map newMap = msp.get(key);
			newMap.put(mapKey, key);
			listMap.add(newMap);
		}
		return listMap;
	}


}