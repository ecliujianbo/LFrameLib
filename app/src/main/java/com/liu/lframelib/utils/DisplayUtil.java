package com.liu.lframelib.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.text.ClipboardManager;
import android.util.DisplayMetrics;

public class DisplayUtil {

	/** 屏幕宽度 */
	private static int mDisplayWidthPixels = 0;
	/** 屏幕高度 */
	private static int mDisplayheightPixels = 0;

	/**
	 * 获取屏幕参数
	 * 
	 * @param context
	 */
	private static void getDisplayMetrics(Context context) {
		DisplayMetrics dm = new DisplayMetrics();
		((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(dm);
		mDisplayWidthPixels = dm.widthPixels;// 宽度
		mDisplayheightPixels = dm.heightPixels;// 高度
	}

	/**
	 * 获取屏幕宽度
	 * 
	 * @param context
	 * @return
	 */
	public static int getDisplayWidthPixels(Context context) {
		if (context == null) {
			return -1;
		}
		if (mDisplayWidthPixels == 0) {
			getDisplayMetrics(context);
		}
		return mDisplayWidthPixels;
	}

	/**
	 * 获取屏幕高度
	 * 
	 * @param context
	 * @return
	 */
	public static int getDisplayheightPixels(Context context) {
		if (context == null) {
			return -1;
		}
		if (mDisplayheightPixels == 0) {
			getDisplayMetrics(context);
		}
		return mDisplayheightPixels;
	}

	/**
	 * 将px值转换为dip或dp值
	 * 
	 * @param pxValue
	 * @return
	 */
	public static int px2dip(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}

	/**
	 * 将dip或dp值转换为px值
	 * 
	 * @param dipValue
	 * @return
	 */
	public static int dip2px(Context context, float dipValue) {
		if (context == null) {
			return 0;
		}
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dipValue * scale + 0.5f);
	}

	/**
	 * 将px值转换为sp值
	 * 
	 * @param pxValue
	 * @return
	 */
	public static int px2sp(Context context, float pxValue) {
		final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
		return (int) (pxValue / fontScale + 0.5f);
	}

	/**
	 * 将sp值转换为px值
	 * 
	 * @param spValue
	 * @param spValue
	 *            （DisplayMetrics类中属性scaledDensity）
	 * @return
	 */
	public static int sp2px(Context context, float spValue) {
		final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
		return (int) (spValue * fontScale + 0.5f);
	}

	/**
	 * 是否有导航键
	 * 
	 * @param resources
	 * @return
	 */
	public static boolean hasNavBar(Resources resources) {
		int id = resources.getIdentifier("config_showNavigationBar", "bool", "android");
		if (id > 0)
			return resources.getBoolean(id);
		else
			return false;
	}

	/**
	 * 文本复制功能
	 * 
	 * @param content
	 */
	public static void copy(String content, Context context) {
		// 得到剪贴板管理器
		ClipboardManager cmb = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
		cmb.setText(content.trim());
	}

	public static boolean checkClipborad(Context context) {
		ClipboardManager cmb = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
		return cmb.hasText();
	}

	/**
	 * 实现粘贴功能
	 * 
	 * @param context
	 * @return
	 */
	public static String paste(Context context) {
		// 得到剪贴板管理器
		ClipboardManager cmb = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
		return cmb.getText().toString().trim();
	}
}
