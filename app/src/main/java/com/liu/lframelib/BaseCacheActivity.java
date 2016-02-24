//package com.liu.lframelib;
//
//import java.lang.reflect.ParameterizedType;
//
//import com.liu.lframelib.interfaces.IUICache;
//import com.liu.lframelib.utils.JsonTools;
//
//import android.os.Bundle;
//import android.text.TextUtils;
//
///**
// * 1,先初始化控件。2，处理缓存。3，初始化逻辑
// *
// * @param <T>
// *            缓存的Class
// */
//public abstract class BaseCacheActivity<T> extends BaseActivity implements IUICache<T> {
//	private boolean mIsCache;
//	private Class<T> mClazz;
//
//	@Override
//	protected void onCreate(Bundle arg0) {
//		super.onCreate(arg0);
//		setContentView(setLayoutRes());
//		initView();
//		initParseCache(arg0);
//		initLogic();
//	}
//
//	private void initParseCache(Bundle arg0) {
//		String cache = getCache();
//		if (TextUtils.isEmpty(cache)) {
//			mIsCache = false;
//			onCreateNoCache(arg0);
//			return;
//		}
//		ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
//		mClazz = (Class<T>) genericSuperclass.getActualTypeArguments()[0];
//		T t = JsonTools.jsonObj(cache, mClazz);
//		if (t == null) {
//			mIsCache = false;
//			onCreateNoCache(arg0);
//			return;
//		}
//		mIsCache = true;
//		onCreateWithCache(arg0, t);
//	}
//
//	/**
//	 * 获取是否有缓存
//	 *
//	 * @return
//	 */
//	public boolean isCache() {
//		return mIsCache;
//	}
//
//}
