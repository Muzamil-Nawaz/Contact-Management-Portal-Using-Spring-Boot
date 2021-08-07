package com.contact.util;

public class StringUtil {

	public static String toCommaSeparatedString(Object[] items) {
		StringBuilder sb = new StringBuilder();
		for (Object item : items) {
			sb.append(item).append(",");
			System.out.println(sb);
		}
		if (sb.length() > 0) {
			sb.deleteCharAt(sb.length() - 1);
		}
		return sb.toString();

	}
}
