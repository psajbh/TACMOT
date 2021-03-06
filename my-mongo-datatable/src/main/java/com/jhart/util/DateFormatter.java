package com.jhart.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatter {
	
	private static final String STANDARD_PATTERN = "yyyy-MM-dd-hh:mm:ss";
	
	public static String getStandardDate(Date d) {
		if (null != d) {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(STANDARD_PATTERN);
			return simpleDateFormat.format(d);
		}
		else {
			return " ";
		}
	}
}
