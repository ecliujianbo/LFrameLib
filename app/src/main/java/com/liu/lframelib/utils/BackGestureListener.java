package com.liu.lframelib.utils;


import com.liu.lframelib.BaseActivity;
import com.liu.lframelib.BaseFragment;

import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;

/**
 * 滑动退出activity,监听手势
 */
public class BackGestureListener implements OnGestureListener {
	BaseFragment mFragment;
	BaseActivity mActivity;

	public BackGestureListener(BaseActivity activity) {
		this.mActivity = activity;
	}

	public BackGestureListener(BaseFragment fragment) {
		this.mFragment = fragment;
	}

	@Override
	public boolean onDown(MotionEvent e) {
		return false;
	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
		return false;
	}

	@Override
	public void onLongPress(MotionEvent e) {

	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
		if ((e2.getX() - e1.getX()) > 150 && Math.abs(e1.getY() - e2.getY()) < 60) {
			if (mFragment != null) {
				mFragment.getActivity().onBackPressed();
			} else if (mActivity != null) {
				mActivity.finishAnim();
			}
			return true;
		}
		return false;
	}

	@Override
	public void onShowPress(MotionEvent e) {

	}

	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		return false;
	}

}
