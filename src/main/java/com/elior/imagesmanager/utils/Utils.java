package com.elior.imagesmanager.utils;

public class Utils {
	
	public static String StringtoJsonString(String key, String value) {
		StringBuilder sb = new StringBuilder();
		sb.append("{\"")
		.append(key)
		.append("\": ")
		.append(value)
		.append("}");
		return sb.toString();
	}
}
