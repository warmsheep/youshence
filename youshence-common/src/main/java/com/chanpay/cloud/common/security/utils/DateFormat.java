package com.chanpay.cloud.common.security.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormat {
	
	public String parse(String str, Date date)
	{
		if ((str == null) || (date == null)) return "";
		StringBuffer sb = new StringBuffer("");
		while (true) {
			int startIndex = str.indexOf("[DATE ");
			int endIndex = str.indexOf("]");
			if ((startIndex == -1) || (endIndex == -1)) {
				sb.append(str);
				break;
			}
			String format = str.substring(startIndex + 6, endIndex);
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			if (startIndex > 0) {
				sb.append(str.substring(0, startIndex));
			}
			sb.append(sdf.format(date));
			str = str.substring(endIndex + 1);
		}
		return sb.toString();
	}
}
