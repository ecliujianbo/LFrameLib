package com.liu.lframelib.interfaces;

import android.os.Bundle;
import android.view.View;
/**
 * 需要缓存的UI界面
 * 
 * @param <T> 缓存的实体类aa
 */
public interface IUICache<T> {
	/**
	 * 初始化控件
	 */
	void initView();

	/**
	 * fragment
	 */
	void initView(View view);

	/**
	 * 初始化逻辑
	 */
	void initLogic();

	/**
	 * 获取缓存
	 * 
	 * @return jsonObject
	 */
	String getCache();

	/**
	 * 初始化有缓存情况
	 * 
	 * @param arg0
	 *            bundle
	 * @param t
	 *            缓存bean
	 */
	void onCreateWithCache(Bundle arg0, T t);

	/**
	 * 初始化 没有缓存的情况
	 * 
	 * @param arg0
	 *            bundle
	 */
	void onCreateNoCache(Bundle arg0);

	/**
	 * 设置layout 布局
	 * 
	 * @return
	 */
	int setLayoutRes();
}
