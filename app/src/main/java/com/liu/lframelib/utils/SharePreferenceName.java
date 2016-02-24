package com.liu.lframelib.utils;

import android.content.Context;

public class SharePreferenceName {
	/**
	 * 存储设备唯一标识码
	 */
	public static final String UDID = "UDID";
	/**
	 * token
	 */
	public static final String ACCESS_TOKEN = "ACCESS_TOKEN";
	/**
	 * 设置session
	 */
	public static final String APP_SESSION = "APP_SESSION";
	/**
	 * 设置token的有效期
	 */
	public static final String TOKENEXPIRESIN = "TOKENEXPIRESIN";
	/**
	 * 保存用户名
	 */
	public static final String USERNAME = "USERNAME";
	/**
	 * 保存密码
	 */
	public static final String PASSWORD = "PASSWORD";
	/**
	 * 保存用户信息
	 */
	public static final String USERINFO = "USERINFO";
	/**
	 * 设置引导页是否显示过 (version-0,未显示过。version-1,显示过)
	 */
	public static final String INTRODUCTIONINFO = "INTRODUCTIONINFO";
	/**
	 * 设置手势密码
	 */
	public static final String GESTUREPASSWORD = "GESTUREPASSWORD";

	/**
	 * 设置退出按钮
	 */
	public static final String UNLOG = "UNLOG";
	/**
	 * 产品列表
	 */
	public static final String PRODUCT_LIST = "PRODUCTLIST";

	/**
	 * 退出的方法出的方法
	 */
	public static void unLog(Context context) {
		LSharePreference.getInstance(context).delContent(USERINFO);
		LSharePreference.getInstance(context).delContent(GESTUREPASSWORD);
		LSharePreference.getInstance(context).delContent(APP_SESSION);
		LSharePreference.getInstance(context).delContent(PASSWORD);
		LSharePreference.getInstance(context).delContent(TOKENEXPIRESIN);
		LSharePreference.getInstance(context).delContent(ACCESS_TOKEN);
		LSharePreference.getInstance(context).delContent(UNLOG);
	}
}
