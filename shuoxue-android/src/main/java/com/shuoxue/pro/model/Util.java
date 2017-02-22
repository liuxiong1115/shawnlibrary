package com.shuoxue.pro.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * Util
 */
public class Util {
	/**
	 * ids Converter
	 */
	public static String idsSetToString(Set<String> ids) {
		StringBuffer sb = new StringBuffer();
		Iterator<String> iter = ids.iterator();
		while(iter.hasNext()) {
			if(sb.length() == 0)			
				sb.append("'" + iter.next() + "'");
			else
				sb.append(",'" + iter.next() + "'");
		}
		return sb.toString();
    }
	public static String idsListToString(List<String> ids) {
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i < ids.size(); i++) {
			if(sb.length() == 0)			
				sb.append("'" + ids.get(i) + "'");
			else
				sb.append(",'" + ids.get(i) + "'");
		}
		return sb.toString();
    }
	public static Set<String> idsStringToSet(String ids) {
		Set<String> aSet = new HashSet<String>();
		String[] array = ids.split(",");
		for(int i = 0; i < array.length; i++) {
			aSet.add(array[i]);
		}
		return aSet;
	}
	
	public static Map<String, String> kvsStringToMap(String mStr) {
		Map<String, String> aMap = new HashMap<String, String>();
		String[] array = mStr.split(";");
		for(int i = 0; i < array.length; i++) {
			String item = array[i];
			String[] kv = item.split("=");
			if(kv.length == 2) {
				aMap.put(kv[0], kv[1]);
			}
		}
		return aMap;
	}
	
	
	public static String mapToString(Map<String, String> aMap) {
		StringBuffer sb = new StringBuffer();
		Set<Entry<String, String>> aSet = aMap.entrySet();
		Iterator<Entry<String, String>> iter = aSet.iterator();
		while (iter.hasNext()) {
			Entry<String, String> entry = iter.next();
			if (sb.length() == 0)
				sb.append(entry.getKey() + "=" + entry.getValue());
			else
				sb.append(";" + entry.getKey() + "=" + entry.getValue());
		}
		return sb.toString();
	}

	public static String[] stringToArray(String mStr , String split){
		return mStr.split(split);
	}

	public static List<String>  stringToList(String mStr , String split){
		String[] array = stringToArray(mStr , split);
		List<String> list = new ArrayList<>();
		for (String str : array) {
			list.add(str);
		}
		return list;
	}

	
	public static boolean validateEmailAddr(String email) {
		return true;
	}
	
	static SimpleDateFormat DayFormat = new SimpleDateFormat("yyyy-MM-dd");

	static SimpleDateFormat DayFormatFor_ZH = new SimpleDateFormat("yyyy/MM/dd");

	static SimpleDateFormat DayFormatFor_Time = new SimpleDateFormat("HH:mm:ss");

	static SimpleDateFormat TimeFormat_ZH = new SimpleDateFormat("yyyy/MM/dd");
	static SimpleDateFormat TimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	static SimpleDateFormat TimeFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	public static Date stringToDate(String date) throws ParseException {
		return DayFormat.parse(date);
	}
	public static String dateToString(Date date) {
		return TimeFormat1.format(date);
	}
	public static String dateToString_zh(Date date) {
		return DayFormatFor_ZH.format(date);
	}
	public static String dateToString_Time(Date date) {
		return DayFormatFor_Time.format(date);
	}

	public static Date stringToTime(String date) throws ParseException {
		return TimeFormat.parse(date);
	}
	public static Date stringToTime_zh(String date) throws ParseException {
		return TimeFormat_ZH.parse(date);
	}

	public static String timeToString(Date date) {
		return TimeFormat.format(date);
	}}