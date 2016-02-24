package com.liu.lframelib;

import com.liu.lframelib.net.HttpConnectsProxy;
import com.liu.lframelib.net.NetHandler;
import com.liu.lframelib.net.RequestData;
import com.liu.lframelib.utils.ActivityStack;
import com.liu.lframelib.utils.BackGestureListener;
import com.liu.lframelib.utils.HttpUtils;
import com.liu.lframelib.utils.LogUtil;
import com.liu.lframelib.utils.SystemBarTintManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.provider.SyncStateContract.Constants;
import android.support.v4.app.FragmentActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public abstract class BaseActivity extends FragmentActivity {
	/** 联网类 */
	private HttpConnectsProxy mConnectProxy;
	/** 手势监听 */
	GestureDetector mGestureDetector;
	/** 是否需要监听手势关闭功能 */
	private boolean mNeedBackGesture = false;
	/** 4.4以上,状态栏颜色 */
	private SystemBarTintManager mSystemBarTintManager;
	/** 状态栏是否改变颜色 */
	protected boolean mShowStatusBarColor = true;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		ActivityStack.getInstance().addActivity(this);
		initGestureDetector();
		int mainColor = getResources().getColor(R.color.common_titlebar_color);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && mShowStatusBarColor) {
			initSystemBar(mainColor);
		}
		beforeContentView();
		setContentView(getContentView());
		obtainIntent(getIntent());
		initView();
		initListener();
		initData();
	}

	/**
	 * 4.4以上,状态栏跟titleBar统一
	 */
	private void initSystemBar(int mainColor) {
		mSystemBarTintManager = new SystemBarTintManager(this);
		mSystemBarTintManager.setStatusBarTintEnabled(true);
		//true mx4pro底部栏有问题
		mSystemBarTintManager.setNavigationBarTintEnabled(false);

		applyColor(153, mainColor);
	}

	/**
	 * 重新设置状态栏颜色
	 * 
	 * @param mainColor
	 * 
	 * @param alpha
	 *            透明度
	 */
	protected void applyColor(int alpha, int mainColor) {
		// 0--255
		int color = Color.argb(255, Color.red(mainColor), Color.green(mainColor), Color.blue(mainColor));
		// 给系统栏设置颜色
		mSystemBarTintManager.setTintColor(color);
		// mSystemBarTintManager.setTintDrawable(getResources().getDrawable(R.drawable.ic_launcher));
	}

	/*
	 * 返回
	 */
	public void doBack(View view) {
		onBackPressed();
	}

	private void initGestureDetector() {
		if (mGestureDetector == null) {
			mGestureDetector = new GestureDetector(getApplicationContext(), new BackGestureListener(this));
		}
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		if (mNeedBackGesture) {
			return mGestureDetector.onTouchEvent(ev) || super.dispatchTouchEvent(ev);
		}
		return super.dispatchTouchEvent(ev);
	}

	/*
	 * 设置是否进行手势监听
	 */
	public void setBackGestureEnable(boolean backGestureEnable) {
		this.mNeedBackGesture = backGestureEnable;
	}

	/**
	 * findviewbyid
	 * 
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public final <E extends View> E getView(int id) {
		try {
			return (E) findViewById(id);
		} catch (ClassCastException ex) {
			LogUtil.logE(ex);
			throw ex;
		}
	}

	/**
	 * 联网获取数据
	 * 
	 * @param data
	 * @param handler
	 */
	protected void submit(RequestData data, NetHandler handler) {
		LogUtil.logD("base submit");
		mConnectProxy = new HttpConnectsProxy(data, handler, this);
		mConnectProxy.submit();
	}

	/**
	 * 显示toast
	 * 
	 * @param message
	 */
	protected void showToast(String message) {
		Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
	}
	protected void showToast(int message) {
		Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
	}

	/**
	 * 界面，右进左出 动画
	 * 
	 * @param clazz
	 */
	public void startAnimActivity(Class<?> clazz) {
		startAnimActivity(clazz, null);
	}

	public void startAnimActivity(Class<?> clazz, Bundle bundle) {
		Intent intent = new Intent(this, clazz);
		if (bundle != null) {
			intent.putExtras(bundle);
		}
		startActivity(intent);
		overridePendingTransition(R.anim.right_in, R.anim.left_out);
	}

	public void finishAnim() {
		finish();
		overridePendingTransition(R.anim.left_in, R.anim.right_out);
	}

	/**
	 * check network
	 * 
	 * @return
	 */
	protected boolean isNetworkAvailable() {
		return HttpUtils.isNetworkAvailable(this);
	}

	protected abstract void beforeContentView();

	protected abstract int getContentView();

	protected abstract void obtainIntent(Intent intent);

	protected abstract void initView();

	protected abstract void initListener();

	protected abstract void initData();

	@Override
	protected void onDestroy() {
		super.onDestroy();
		ActivityStack.getInstance().removeActivity(this);
		try {
			mConnectProxy.cancelAll(this);
		} catch (Exception e) {
		}
	}

}
