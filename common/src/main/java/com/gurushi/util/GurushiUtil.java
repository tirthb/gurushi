package com.gurushi.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GurushiUtil {
	
	static Logger logger = LoggerFactory.getLogger(GurushiUtil.class);
 
	public static boolean isEmpty(String text) {
		return text == null || text.isEmpty();
	}

	public static int compareDates(Date date1, Date date2, String sortOrder) {
		return compareDates(date1, date2, sortOrder, false);
	}

	public static int compareDates(Date date1, Date date2, String sortOrder, boolean nonNullFirst) {
		if (sortOrder == null) return 0;
		if (date1 == null && date2 == null) return 0;

		if (date1 == null || date2 == null) {
			return compareNulls(date1, date2, sortOrder, nonNullFirst);
		}

		if (sortOrder.equalsIgnoreCase("ASC")) {
			return date1.compareTo(date2);
		}
		else {
			return date2.compareTo(date1);
		}
	}
	
	public static int compareLongValues(Long l1, Long l2, String sortOrder, boolean nonNullFirst) {
		if (sortOrder == null) return 0;
		if (l1 == null && l2 == null) return 0;
		
		if (l1 == null || l2 == null) {
			return compareNulls(l1, l2, sortOrder, nonNullFirst);
		}
		
		if (sortOrder.equalsIgnoreCase("ASC")) {
			return l1.compareTo(l2);
		}
		else {
			return l2.compareTo(l1);
		}
	}
	
	public static int compareNulls(Object o1, Object o2, String sortOrder, boolean nonNullFirst) {
		if (o1 == null || o2 == null) {
			if (sortOrder.equalsIgnoreCase("ASC")) {
				if (o1 == null) return nonNullFirst ? -1 : 1;
				return nonNullFirst ? 1 : -1;
			}
			else {
				if (o1 == null) return 1;
				return -1;
			}
		}
		else {
			throw new IllegalArgumentException("Both arguments are not null.");
		}
	}
	
	public static Integer getYear(Date date){
		Calendar calendar =  Calendar.getInstance();
		calendar.setTime(date) ;
		return calendar.get(Calendar.YEAR);
	}
	
	public static String dateToString(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
		return format.format(date);
	}
	
	 public static <C extends Object> List<List<C>> createBatches(
	            Collection<C> collection, int batchSize) {
	        if (batchSize < 1)
	            throw new IllegalArgumentException("Batch size cannot be zero or less");

	        List<C> objects = new ArrayList<C>(collection);
	        int noOfBatches = (int) Math.ceil((double) objects.size() / batchSize);
	        List<List<C>> batches = new ArrayList<List<C>>(noOfBatches);
	        int index = 0;
	        for (int i = 0; i < noOfBatches; i++) {
	            List<C> list = new ArrayList<C>(batchSize);
	            for (int j = 0; j < batchSize && index < objects.size(); j++, index++) {
	                list.add(objects.get(index));
	            }
	            batches.add(list);
	        }
	        return batches;
	    }
	 
	public static int compareBoolean(boolean b1, boolean b2, String sortOrder) {
		if (sortOrder.equalsIgnoreCase("NONE")) return 0;
		if (b1 == b2) return 0;
		
		if (sortOrder.equalsIgnoreCase("ASC")) {
			if (b1) return 1;
			return -1;
		}
		else {
			if (b1) return -1;
			return 1;
		}
	}
	
	public static boolean createDirectoryIfNotExists(String directoryName){
		File outFileDir = new File(directoryName);
		boolean flag = false;
		if(!outFileDir.exists()){
			flag = outFileDir.mkdirs();
		}
		return flag;
	}
	
	public static int compareStrings(String s1, String s2, String sortOrder) {
		return compareStrings(s1, s2, sortOrder, false);
	}
	
	public static int compareStrings(String s1, String s2, String sortOrder, boolean nonNullFirst) {
		if (sortOrder == null) return 0;
		if (s1 == null && s2 == null) return 0;
		
		if (s1 == null || s2 == null) {
			return compareNulls(s1, s2, sortOrder, nonNullFirst);
		}
		
		if (sortOrder.equalsIgnoreCase("ASC")) {
			return s1.compareToIgnoreCase(s2);
		}
		else {
			return s2.compareToIgnoreCase(s1);
		}
	}
	
	/**
	 * If argument is null return null
	 * else trim and return
	 * @param arg
	 * @return
	 */
	public static String trim(String arg){
		if(arg == null){
			return null;
		}
		
		return arg.trim();
	}
}
