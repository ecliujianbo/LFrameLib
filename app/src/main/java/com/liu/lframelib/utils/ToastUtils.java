package com.liu.lframelib.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.Toast;

public class ToastUtils {
	public static Toast mToast;

	private ToastUtils() {
	}

	@SuppressLint("ShowToast")
	public synchronized static Toast getInstance(Context context, String text, int duration) {
		if (null == mToast) {
			mToast = Toast.makeText(context.getApplicationContext(), text, duration);
		} else {
			mToast.setText(text);
			mToast.setDuration(duration);
		}
		return mToast;
	}
	@SuppressLint("ShowToast")
	public synchronized static Toast getInstance(Context context, int textResId, int duration) {
		if (null == mToast) {
			mToast = Toast.makeText(context.getApplicationContext(), textResId, duration);
		} else {
			mToast.setText(textResId);
			mToast.setDuration(duration);
		}
		return mToast;
	}
	
	
	public static void show() {
		mToast.show();
	}
}
