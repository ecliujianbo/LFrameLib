package com.liu.lframelib;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.liu.lframelib.utils.CrashHandler;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import android.app.Application;
import android.content.Context;

public class OApplication extends Application {
	private RequestQueue mRequestQueue;
	/**
	 * 全局LApplication唯一实例
	 */
	private static OApplication sInstance;
	/**
	 * 全局APP标识名称
	 */
	private String mAppName;
	@Override
	public void onCreate() {
		super.onCreate();
//		RequestManager.init(this);
		CrashHandler.install(this);
		mRequestQueue = Volley.newRequestQueue(this);
		initImageLoader(getApplicationContext());
	}

	public RequestQueue getRequestQueue() {
		return mRequestQueue;
	}

	public static void initImageLoader(Context context) {
		// This configuration tuning is custom. You can tune every option, you
		// may tune some of them,
		// or you can create default configuration by
		// ImageLoaderConfiguration.createDefault(this);
		// method.
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
				.threadPriority(Thread.NORM_PRIORITY - 2).denyCacheImageMultipleSizesInMemory()
				.discCacheFileNameGenerator(new Md5FileNameGenerator()).tasksProcessingOrder(QueueProcessingType.LIFO)
				// .writeDebugLogs() // Remove for release app
				.build();
		// Initialize ImageLoader with configuration.
		ImageLoader.getInstance().init(config);
	}
	/**
	 * 获取一个LApplication的实例
	 * 
	 * @return
	 */
	public static synchronized OApplication getInstance() {
		if (sInstance == null) {
			sInstance = new OApplication();
		}
		return sInstance;
	}

	/**
	 * 设置一个LApplication的实例
	 * 
	 * @param app
	 */
	public static void setLApplication(OApplication app) {
		sInstance = app;
	}
	/**
	 * 获取应用标识名称
	 * 
	 * @return
	 */
	public String getAppName() {
		return mAppName;
	}

	/**
	 * 设置应用标识名称
	 * 
	 * @param appName
	 */
	public void setAppName(String appName) {
		this.mAppName = appName;
	}
}
