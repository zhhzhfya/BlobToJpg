package com.appsoft.cache;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class ColCache {

	private static ColCache co = null;

	private static HashMap<String, LinkedHashMap<String, String>> MM = null;

	public static ColCache getInstance() {
		if (null == co) {
			co = new ColCache();
		}
		return co;
	}

	public static HashMap<String, LinkedHashMap<String, String>> getCacheMap() {
		if (null == MM) {
			MM = new HashMap<>();
		}
		return MM;
	}

	public Map<String, String> getCacheMap(String tableName) {
		return MM.get(tableName);
	}

	public void setCaCheMap(LinkedHashMap mm, String tableName) {
		MM.put(tableName, mm);
	}
}
