package com.jhart.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatter {
	
	public static final String STANDARD_PATTERN = "yyyy-MM-dd";
	public static final String DETAILED_PATTERN = "yyyy-MM-dd-hh:mm:ss";
	public static final String DMY_PATTERN = "dd-MM-yyyy";
	
	public static String getStandardDate(Date d) {
		return getStandardDate(d, DateFormatter.STANDARD_PATTERN);
	}
	
	public static String getStandardDate(Date d, String format) {
		
		if (null == format) {
			format = DateFormatter.STANDARD_PATTERN;
		}
		
		if (null != d) {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
			return simpleDateFormat.format(d);
		}
		else {
			return " ";
		}
	}
	
//	public static Date formatDate(Date date, String formatType) {
//		return new SimpleDateFormat(formatType).parse(date);
//		
//	}
}
