package com.chanpay.cloud.common.web.util;

/**
 * 比较工具
 * @author Warmsheep
 *
 */
public class CompareUtils {

	/**
	 * 对象比较相等
	 * @param a
	 * @param b
	 * @return
	 */
	public static boolean objectEquals(Object a,Object b){
		if(a == null && b == null){
			return true;
		}
		if((a == null && b != null) || (a != null && b == null)){
			return false;
		}
		if(a.equals(b)){
			return true;
		} else {
			return false;
		}
	}
	/**
	 * 对象比较不相等
	 * @param a
	 * @param b
	 * @return
	 */
	public static boolean objectNotEquals(Object a,Object b){
		return !objectEquals(a, b);
	}
	/**
	 * Long比较相等
	 * @param a
	 * @param b
	 * @return
	 */
	public static boolean longEquals(Long a,Long b){
		if(a == null && b == null){
			return true;
		}
		if((a == null && b != null) || (a != null && b == null)){
			return false;
		}
		if(a.longValue() == b.longValue()){
			return true;
		} else {
			return false;
		}
	}
	/**
	 * Long比较不相等
	 * @param a
	 * @param b
	 * @return
	 */
	public static boolean longNotEquals(Long a,Long b){
		return !longEquals(a, b);
	}
	/**
	 * Integer比较相等
	 * @param a
	 * @param b
	 * @return
	 */
	public static boolean integerEquals(Integer a,Integer b){
		if(a == null && b == null){
			return true;
		}
		if((a == null && b != null) || (a != null && b == null)){
			return false;
		}
		if(a.intValue() == b.intValue()){
			return true;
		} else {
			return false;
		}
	}
	/**
	 * Integer比较不相等
	 * @param a
	 * @param b
	 * @return
	 */
	public static boolean integerNotEquals(Integer a,Integer b){
		return !integerEquals(a, b);
	}
	
	
	/**
	 * 判断值是否存在列表中
	 * @param a
	 * @param compareValues
	 * @return
	 */
	public static boolean integerExists(Integer a,Integer... compareValues){
		if(a == null || compareValues == null){
			return false;
		}
		for(Integer compareValue : compareValues){
			if(compareValue != null && a.intValue() == compareValue.intValue()){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 判断值是否存在列表中
	 * @param a
	 * @param compareValues
	 * @return
	 */
	public static boolean stringExists(String a,String... compareValues){
		if(a == null || compareValues == null){
			return false;
		}
		for(String compareValue : compareValues){
			if(compareValue != null && a.equals(compareValue)){
				return true;
			}
		}
		return false;
	}
	
}
