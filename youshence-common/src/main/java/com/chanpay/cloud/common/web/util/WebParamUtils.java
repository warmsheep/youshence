package com.chanpay.cloud.common.web.util;

import java.math.BigDecimal;

public class WebParamUtils {

	public static String getStringValue(Object obj){
		if(obj != null){
			if(obj.getClass() == String.class){
				return (String)obj;
			}
			throw new RuntimeException("Request param type error!");
		} else {
			return null;
		}
	}
	
	public static Integer getIntegerValue(Object obj){
		if(obj != null){
			if(obj.getClass() == Integer.class){
				return (Integer)obj;
			}
			if(obj.getClass() == Long.class){
				return ((Long)obj).intValue();
			}
			if(obj.getClass() == String.class){
				try{
					return Integer.valueOf((String)obj);
				} catch (Exception e) {
					return null;
				}
			}
			throw new RuntimeException("Request param type error!");
		} else {
			return null;
		}
	}
	
	public static Long getLongValue(Object obj){
		if(obj != null){
			if(obj.getClass() == Integer.class){
				return ((Integer)obj).longValue();
			}
			if(obj.getClass() == Long.class){
				return (Long)obj;
			}
			if(obj.getClass() == String.class){
				try{
					return Long.valueOf((String)obj);
				} catch (Exception e) {
					return null;
				}
			}
			throw new RuntimeException("Request param type error!");
		} else {
			return null;
		}
	}
	
	public static Double getDoubleValue(Object obj){
		if(obj != null){
			if(obj.getClass() == BigDecimal.class){
				return ((BigDecimal)obj).doubleValue();
			}
			if(obj.getClass() == Integer.class){
				return ((Integer)obj).doubleValue();
			}
			if(obj.getClass() == Double.class){
				return (Double)obj;
			}
			if(obj.getClass() == String.class){
				try{
					return Double.valueOf((String)obj);
				} catch (Exception e) {
					return null;
				}
			}
			throw new RuntimeException("Request param type error!");
		} else {
			return null;
		}
	}
	
	public static BigDecimal getBigDecimalValue(Object obj){
			
		if(obj != null){
			if(obj.getClass() == BigDecimal.class){
					return (BigDecimal)obj;
				}
				throw new RuntimeException("Request param type error!");
			} else {
				return null;
			}
	}
}
