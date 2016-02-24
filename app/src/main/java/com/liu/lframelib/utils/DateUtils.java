package com.liu.lframelib.utils;

import android.annotation.SuppressLint;
import android.text.TextUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
	private static final String DATE_FORMAT = "yyyy/MM/dd";

	/**
	 * Simple way to parse String to date
	 */
	public static Date getDate(String date) throws ParseException {
		return getDateByFormat(date, DATE_FORMAT);
	}

	/**
	 * Simple way to parse String to date with format
	 */
	@SuppressLint("SimpleDateFormat")
	public static Date getDateByFormat(String date, String dateFormat) throws ParseException {
		DateFormat df = new SimpleDateFormat(dateFormat);
		return df.parse(date);
	}

	/**
	 * Java Method to calculate difference between two dates in Java * without
	 * using any third party library.
	 */
	public static long daysBetween(Date one, Date two) {
		long difference = (one.getTime() - two.getTime()) / 86400000;
		return Math.abs(difference);
	}
	// int Java 8
	// /*
	// * * Java Method to find number of days between two dates * in Java using
	// * JodaTime library. To find difference * we first need to convert
	// * java.util.Date to LocalDate * in JodaTime.
	// */
	// public static int daysBetweenUsingJoda(Date d1, Date d2) {
	// return daysBetween(new LocalDate(d1.getTime()), new
	// LocalDate(d2.getTime())).getDays();
	// }
	
	
	
	/**
	 * 计算时间差
	 * 
	 * @param format
	 *            时间格式
	 * @param oneTime
	 *            开始日期
	 * @param twoTime
	 *            结束日期（如果为Null，距离现在时间）
	 * @return -3为计算失败
	 */
	public static long timeBetween(String format, String oneTime, String twoTime) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String dateString = oneTime;
		long secondDate;
		try {
			if (TextUtils.isEmpty(twoTime)) {
				Calendar calendar = Calendar.getInstance();
				secondDate = calendar.getTime().getTime(); // Date.getTime()
															// 获得毫秒型日期
			} else {
				secondDate = sdf.parse(twoTime).getTime();
			}
			long specialDate = sdf.parse(dateString).getTime();
			long betweenDate = (specialDate - secondDate) / (1000 * 60 * 60 * 24); // 计算间隔多少天，则除以毫秒到天的转换公式
			return Math.abs(betweenDate);
		} catch (ParseException e) {
			// e.printStackTrace();
		}
		return -3;
	}

	/**
	 * 
	 * 计算日期大小
	 * 
	 * @param format
	 *            格式
	 * @param firstDate
	 *            第一个日期
	 * @param secondDate
	 *            第二个日期
	 * @return 后者大于前者为1，后者小于前者为-1，相等为0
	 */
	public static int timeCompare(String format, String firstDate, String secondDate) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String dateString_01 = firstDate;
		String dateString_02 = secondDate;
		try {
			Date date_01 = sdf.parse(dateString_01);
			Date date_02 = sdf.parse(dateString_02);
			// System.out.println(date_01.before(date_02)); //true，当 date_01 小于
			// date_02 时，为 true，否则为 false
			// System.out.println(date_02.after(date_01)); //true，当 date_02 大于
			// date_01 时，为 true，否则为 false
			// System.out.println(date_01.compareTo(date_02)); //-1，当 date_01 小于
			// date_02 时，为 -1
			// System.out.println(date_02.compareTo(date_01)); //1，当 date_02 大于
			// date_01 时，为 1
			// System.out.println(date_02.compareTo(date_02)); //0，当两个日期相等时，为 0
			return date_02.compareTo(date_01);
		} catch (ParseException e) {
			// e.printStackTrace();
		}
		return -3;
	}
}
