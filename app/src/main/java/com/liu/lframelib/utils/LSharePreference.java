package com.liu.lframelib.utils;



import com.liu.lframelib.OApplication;

import android.content.Context;
import android.content.SharedPreferences;

public class LSharePreference {
	private static String sSharedPreName = OApplication.getInstance().getAppName();
	private SharedPreferences mSharePre;
	private SharedPreferences.Editor mEditor;

	private static LSharePreference sInstance;

	public static synchronized LSharePreference getInstance(Context context) {
		if (sInstance == null) {
			sInstance = new LSharePreference(context);
		}
		return sInstance;
	}

	/**
	 * 构造函数
	 * 
	 * @param context
	 */
	private LSharePreference(Context context) {
		init(context);
	}

	/**
	 * 初始化
	 */
	private void init(Context context) {
		if (context != null) {
			mSharePre = context.getApplicationContext().getSharedPreferences(sSharedPreName, Context.MODE_PRIVATE);
			mEditor = mSharePre.edit();
		}
	}

	/**
	 * 添加String
	 * 
	 * @param key
	 * @param value
	 */
	public void setString(String key, String value) {
		mEditor.putString(key, value);
		mEditor.commit();
	}

	/**
	 * 获取String
	 * 
	 * @param key
	 * @return
	 */
	public String getString(String key) {
		return getString(key, null);
	}

	/**
	 * 获取String
	 * 
	 * @param key
	 * @param defValue
	 * @return
	 */
	public String getString(String key, String defValue) {
		return mSharePre.getString(key, defValue);
	}

	/**
	 * 添加Int
	 * 
	 * @param key
	 * @param l
	 */
	public void setInt(String key, int l) {
		mEditor.putInt(key, l);
		mEditor.commit();
	}

	/**
	 * 获取Int
	 * 
	 * @param key
	 * @return
	 */
	public int getInt(String key) {
		return getInt(key, 0);
	}

	/**
	 * 获取Int
	 * 
	 * @param key
	 * @param defValue
	 * @return
	 */
	public int getInt(String key, int defValue) {
		return mSharePre.getInt(key, defValue);
	}

	/**
	 * 添加Long
	 * 
	 * @param key
	 * @param l
	 */
	public void setLong(String key, long l) {
		mEditor.putLong(key, l);
		mEditor.commit();
	}

	/**
	 * 获取Int
	 * 
	 * @param key
	 * @return
	 */
	public long getLong(String key) {
		return getLong(key, 0);
	}

	/**
	 * 获取Int
	 * 
	 * @param key
	 * @param defValue
	 * @return
	 */
	public long getLong(String key, long defValue) {
		return mSharePre.getLong(key, defValue);
	}

	/**
	 * 添加float
	 * 
	 * @param key
	 * @param value
	 */
	public void setFloat(String key, float value) {
		mEditor.putFloat(key, value);
		mEditor.commit();
	}

	/**
	 * 获取float
	 * 
	 * @param key
	 * @return
	 */
	public float getFloat(String key) {
		return getFloat(key, 0.0f);
	}

	/**
	 * 获取float
	 * 
	 * @param key
	 * @param defValue
	 * @return
	 */
	public float getFloat(String key, float defValue) {
		return mSharePre.getFloat(key, defValue);
	}

	/**
	 * 添加boolean
	 * 
	 * @param key
	 * @param value
	 */
	public void setBoolean(String key, boolean value) {
		mEditor.putBoolean(key, value);
		mEditor.commit();
	}

	/**
	 * 获取boolean
	 * 
	 * @param key
	 * @return
	 */
	public boolean getBoolean(String key) {
		return getBoolean(key, false);
	}

	/**
	 * 获取boolean
	 * 
	 * @param key
	 * @param defValue
	 * @return
	 */
	public boolean getBoolean(String key, boolean defValue) {
		return mSharePre.getBoolean(key, defValue);
	}

	/**
	 * 删除
	 * 
	 * @param key
	 */
	public void delContent(String key) {
		mEditor.remove(key);
		mEditor.commit();
	}

}
