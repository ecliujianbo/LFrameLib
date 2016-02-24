package com.liu.lframelib;

import java.lang.reflect.ParameterizedType;

import com.liu.lframelib.interfaces.IUICache;
import com.liu.lframelib.utils.JsonTools;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseCacheFragment<T> extends BaseFragment implements IUICache<T> {
	private boolean mIsCache;
	private Class<T> mClazz;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(setLayoutRes(), null);
		initView(view);
		parseCache(savedInstanceState);
		initLogic();
		return view;
	}

	private void parseCache(Bundle arg0) {
		String cache = getCache();
		if (TextUtils.isEmpty(cache)) {
			mIsCache = false;
			onCreateNoCache(arg0);
			return;
		}
		ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
		mClazz = (Class<T>) genericSuperclass.getActualTypeArguments()[0];
		T t = JsonTools.jsonObj(cache, mClazz);
		if (t == null) {
			mIsCache = false;
			onCreateNoCache(arg0);
			return;
		}
		mIsCache = true;
		onCreateWithCache(arg0, t);
	}

	/**
	 * 获取是否有缓存
	 *
	 * @return
	 */
	public boolean isCache() {
		return mIsCache;
	}
}
