package com.liu.lframelib.utils;

import java.util.Locale;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;

/**
 * 通用工具类
 * 
 * @author liu
 * 
 *         2014-2-2
 */
public class PhoneUtil {
	/**
	 * 获取应用版本号
	 * 
	 * @return 当前应用的版本号
	 */
	public static String getVersion(Context context) {
		try {
			PackageManager manager = context.getPackageManager();
			PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
			String version = info.versionName;
			return version;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * 获取手机版本号
	 * 
	 * @return
	 */
	public static int getPhoneVersion() {
		return android.os.Build.VERSION.SDK_INT;
	}

	/**
	 * 获取手机型号
	 * 
	 * @return
	 */
	public static String getPhoneInfo() {
		String handSetInfo = android.os.Build.MODEL;
		return handSetInfo;

	}

	/**
	 * 手机型号
	 * 
	 * @return
	 */
	public static String getPhoneSystemVersion() {
		return android.os.Build.VERSION.RELEASE;
	}

	/**
	 * 获取手机品牌
	 * 
	 * @return
	 */
	public static String getPhoneBrand() {
		String brand = android.os.Build.BRAND;
		return TextUtils.isEmpty(brand) ? "未知" : brand;
	}

	/**
	 * 手机使用语言
	 * 
	 * @param context
	 * @return
	 */
	public static String getPhoneLanguage(Context context) {
		Locale locale = context.getResources().getConfiguration().locale;
		String language = locale.getLanguage();
		if (TextUtils.isEmpty(language)) {
			return "未知";
		}
		return language;
	}

}
