package com.chanpay.cloud.common.security.utils;

import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StrUtil {
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");

	private static String hexString = "0123456789ABCDEF";

	private static String SPACE = "                                                                                                    ";

	private static String ZERO = "0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";

	private static String MAC = "CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC";

	public static String getDateRand(Date date) {
		String str = sdf.format(date);
		double d = Math.random();
		int t = 1000 + (int) (d * 9000.0D);
		return str + t;
	}

	public static String encode(String str) {
		byte[] bytes = str.getBytes();
		StringBuilder sb = new StringBuilder(bytes.length * 2);

		for (int i = 0; i < bytes.length; i++) {
			sb.append(hexString.charAt((bytes[i] & 0xF0) >> 4));
			sb.append(hexString.charAt((bytes[i] & 0xF) >> 0));
			sb.append(" ");
			if ((i + 1) % 20 == 0) {
				sb.append("\n");
			}
		}
		return sb.toString();
	}

	public static String decode(String bytes) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream(bytes.length() / 2);

		for (int i = 0; i < bytes.length(); i += 2)
			baos.write(hexString.indexOf(bytes.charAt(i)) << 4 | hexString.indexOf(bytes.charAt(i + 1)));
		return new String(baos.toByteArray());
	}

	public static String getMidStr(String str, int startIndex, int len) {
		byte[] b = str.getBytes();
		byte[] value = new byte[len];
		System.arraycopy(b, startIndex, value, 0, len);
		String returnValue = new String(value);
		b = (byte[]) null;
		value = (byte[]) null;
		return returnValue;
	}

	public static String toFull(String input) {
		char[] c = input.toCharArray();
		for (int i = 0; i < c.length; i++) {
			if (c[i] == ' ') {
				c[i] = '　';
			} else if (c[i] < '') {
				c[i] = ((char) (c[i] + 65248));
			}
		}

		return new String(c);
	}

	public static String toHalf(String input) {
		char[] c = input.toCharArray();
		for (int i = 0; i < c.length; i++) {
			if (c[i] == '　')
				c[i] = ' ';
			else if ((c[i] > 65280) && (c[i] < 65375)) {
				c[i] = ((char) (c[i] - 65248));
			}
		}

		String returnString = new String(c);

		return returnString;
	}

	public static String add(String a, String b) {
		BigDecimal bd = new BigDecimal(a);
		bd = bd.add(new BigDecimal(b));
		return bd.toString();
	}

	public static String get(Object obj) {
		if (obj == null)
			return "";
		if (obj.toString().trim().equals("null"))
			return "";
		String returnValue = obj.toString().trim();
		return returnValue;
	}

	public static String money2str(String money) {
		try {
			return (Double.parseDouble(money) * 100.0D) + "";
		} catch (Exception e) {
			System.err.println(e);
		}
		return money;
	}

	public static String str2money(String str) {
		try {
			double amt = Long.parseLong(str) / 100.0D;
			return formatMoney(amt);
		} catch (Exception e) {
			System.err.println(e);
		}
		return str;
	}

	private static String moneyChange(String money) {
		int index = money.indexOf(".");
		String head = money.substring(0, index);
		String end = money.substring(index + 1);
		if (head.equals("0")) {
			if (end.startsWith("0")) {
				return Integer.parseInt(end) + "";
			}
			return end;
		}

		return head + end;
	}

	public static String mon2money(String str) {
		return moneyChange(formatMoney(str));
	}

	public static String mon2money(double value) {
		return moneyChange(formatMoney(value));
	}

	public static String formatMoney(double d) {
		DecimalFormat format = (DecimalFormat) NumberFormat.getPercentInstance();
		format.applyPattern("###########0.00");
		return format.format(d);
	}

	public static String formatMoney(String money) {
		if (money == null)
			return "";
		try {
			money = money.trim();
			double d = Double.parseDouble(money);
			return formatMoney(d);
		} catch (Exception e) {
			System.err.println(e);
		}
		return money;
	}

	private static String getSpace(int len) {
		int t = len / 100 + 1;
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < t; i++) {
			sb.append(SPACE);
		}
		return sb.toString();
	}

	private static String getZero(int len) {
		int t = len / 100 + 1;
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < t; i++) {
			sb.append(ZERO);
		}
		return sb.toString();
	}

	public static String getA(String str, int len) {
		String line = str + getSpace(len);
		return toStr(line, len, 0);
	}

	public static String getAn(String str, int len) {
		return getA(str, len);
	}

	public static String getAns(String str, int len) {
		return getA(str, len);
	}

	public static String getN(String str, int len) {
		if ((str == null) || (str.trim().equals("")))
			str = "0";
		String line = getZero(len) + str;
		return toStr(line, len, 1);
	}

	public static String getG(String str, int len) {
		return getA(toFull(str), len);
	}

	private static String toStr(String str, int len, int type) {
		byte[] b = str.getBytes();
		byte[] value = new byte[len];
		if (type == 0)
			System.arraycopy(b, 0, value, 0, len);
		else {
			System.arraycopy(b, b.length - len, value, 0, len);
		}
		return new String(value);
	}

	public static String getHex(byte b) {
		String value = Integer.toHexString(b);
		if (value.startsWith("ffffff"))
			value = value.substring(value.length() - 2);
		if (value.length() == 1) {
			return "0" + value;
		}
		return value;
	}

	public static String getHex(byte[] b) {
		int ROW_DISPACE_COLUMN = 20;
		if ((b == null) || (b.length == 0))
			return "";
		StringBuffer sb = new StringBuffer();
		sb.append("Displacement -1--2--3--4--5--6--7--8--9-10-11-12-13-14-15-16-17-18-19-20  --- ASCII Value ---\r\n");
		int rowIndex = 0;
		int columnIndex = 0;
		byte[] row = new byte[20];
		int maxRowIndex = b.length / 20;
		for (int i = 0; i < b.length; i++) {
			if ((b[i] == 10) || (b[i] == 13))
				row[columnIndex] = 0;
			else {
				row[columnIndex] = b[i];
			}
			if (columnIndex == 0) {
				if (rowIndex == maxRowIndex)
					sb.append(getN(new StringBuilder(String.valueOf(rowIndex * 20 + 1)).toString(), 5) + "(" + getN(new StringBuilder(String.valueOf(b.length)).toString(), 5)
							+ ") ");
				else {
					sb.append(getN(new StringBuilder(String.valueOf(rowIndex * 20 + 1)).toString(), 5) + "("
							+ getN(new StringBuilder(String.valueOf((rowIndex + 1) * 20)).toString(), 5) + ") ");
				}
			}
			columnIndex++;
			if ((columnIndex == 20) || (i == b.length - 1)) {
				for (int j = 0; j < 20; j++) {
					int index = rowIndex * 20 + j;
					if (index < b.length)
						sb.append(getHex(b[index]) + " ");
					else {
						sb.append("   ");
					}
				}
				if (i < b.length - 1) {
					sb.append(" " + new String(row));
				} else {
					byte[] bb = new byte[columnIndex];
					System.arraycopy(row, 0, bb, 0, bb.length);
					sb.append(" " + new String(bb));
					bb = (byte[]) null;
				}
				if (i < b.length - 1) {
					sb.append("\r\n");
				}
				columnIndex = 0;
				rowIndex++;
				row = new byte[20];
			}
		}
		row = (byte[]) null;
		return sb.toString();
	}
}
