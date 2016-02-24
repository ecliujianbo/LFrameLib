package com.liu.lframelib.utils;

import android.content.Context;
import android.util.Log;

/**
 * 调试程序信息
 * 
 * @author liu 2013-6-5
 */
public class LogUtil {

	private static String tag = "otouzi";

	public static boolean DEBUG = true;

	public static void logD(String strLog) {
		if (DEBUG) {
			StackTraceElement invoker = getInvoker();
			Log.d(tag, "【" + invoker.getClassName() + ":" + invoker.getMethodName() + ":" + invoker.getLineNumber()
					+ "】" + strLog);
		}
	}

	public static void logI(String strLog) {
		if (DEBUG) {
			StackTraceElement invoker = getInvoker();
			Log.i(tag, "【" + invoker.getClassName() + ":" + invoker.getMethodName() + ":" + invoker.getLineNumber()
					+ "】" + strLog);
		}
	}

	public static void logE(Exception e) {
		if (DEBUG) {
			StackTraceElement invoker = getInvoker();
			Log.e(tag, "【" + invoker.getClassName() + ":" + invoker.getMethodName() + ":" + invoker.getLineNumber()
					+ "】" + e);
		}
	}

	public static void logE(String strLog) {
		if (DEBUG) {
			StackTraceElement invoker = getInvoker();
			Log.e(tag, "【" + invoker.getClassName() + ":" + invoker.getMethodName() + ":" + invoker.getLineNumber()
					+ "】" + strLog);
		}
	}

	private static StackTraceElement getInvoker() {
		return Thread.currentThread().getStackTrace()[4];
	}

}
