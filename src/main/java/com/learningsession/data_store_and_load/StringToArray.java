package com.learningsession.data_store_and_load;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class StringToArray {
	
	private static final String NEW_ITEM_SEPARATOR = "\n";
	private static final String MULTI_KEY_VALUE_SEPARATOR = ";";
	private static final String KEY_VALUE_SEPARATOR = "=";
	
	public StringToArray() throws Exception {
		throw new UnsupportedOperationException();
	}
	
	/**
	 * @param text String 
	 * @return arrayOfMap List<Map<String, String>>
	 * **/
	public static List<Map<String, String>> store(String in) {
		List<Map<String, String>> arr = new ArrayList<>();
		String[] items = in.split(NEW_ITEM_SEPARATOR);
		for(int a = 0; a < items.length; a++) {
			String[] lines = items[a].split(MULTI_KEY_VALUE_SEPARATOR);
			Map<String,String> map = new HashMap<>();
			for(int b = 0; b < lines.length; b++) {
				String[] kv = lines[b].split(KEY_VALUE_SEPARATOR);
				map.put(kv[0], kv[1]);
			}
			arr.add(map);
		}
		return arr;
	}
	
	/**
	 * @param arrayOfMap List<Map<String, String>>
	 * @return text String
	 * **/
	public static String load(List<Map<String, String>> arr) {
		StringBuilder builder = new StringBuilder();
		for (Map<String, String> map : arr) {
			int counter = 0;
			for (Map.Entry<String, String> entry : map.entrySet()) {
			    if (counter > 0) {
			    	builder.append(MULTI_KEY_VALUE_SEPARATOR);
			    }
				builder.append(entry.getKey()).append(KEY_VALUE_SEPARATOR).append(entry.getValue());
			    counter++;
			}
			builder.append(NEW_ITEM_SEPARATOR);
		}
		return builder.toString();
	}
	
	public static final String text = "key1=value1;key2=value2\nkeyA=valueA";
	public static List<Map<String, String>> test_store() {
		return StringToArray.store(text);
	}
	
	public static String test_load() {
		List<Map<String, String>> arr = new ArrayList<>();
		Map<String, String> map = new HashMap<>();
		map.put("key1", "value1");
		map.put("key2", "value2");
		arr.add(map);
		map = new HashMap<>();
		map.put("keyA", "valueA");
		arr.add(map);
		return StringToArray.load(arr);
	}
	
}
