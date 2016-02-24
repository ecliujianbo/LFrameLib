package com.liu.lframelib.widget.viewpager;

import java.lang.ref.WeakReference;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class AutoRecyclerViewPager extends ViewPager {
	private ScrollHandler mHandler;
	// 转动
	private static final int WHEEL = 100; 
	 // 默认轮播时间
	private static final long DELAY_TIME = 5000;
	private boolean mIsAutoScroll = true;
	private boolean mIsStopByTouch = true;
	//触摸停止动画
	private boolean mStopScrollWhenTouch = true;

	static class ScrollHandler extends Handler {
		private WeakReference<AutoRecyclerViewPager> autoViewPager;

		public ScrollHandler(AutoRecyclerViewPager autoViewPager) {
			this.autoViewPager = new WeakReference<AutoRecyclerViewPager>(autoViewPager);
		}

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			AutoRecyclerViewPager viewPager = autoViewPager.get();
			if (viewPager != null) {
				if (msg.what == WHEEL) {
					viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
					viewPager.sendScrollMessage(DELAY_TIME);
				}
			}
		}
	}

	public AutoRecyclerViewPager(Context context) {
		super(context);
		init(context);
	}

	public AutoRecyclerViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	private void init(Context context) {
		mHandler = new ScrollHandler(this);
	}

	private void sendScrollMessage(long delayTimeInMills) {
		/** remove messages before, keeps one message is running at most **/
		mHandler.removeMessages(WHEEL);
		mHandler.sendEmptyMessageDelayed(WHEEL, delayTimeInMills);
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		int action = MotionEventCompat.getActionMasked(ev);

		if (mStopScrollWhenTouch) {
			if ((action == MotionEvent.ACTION_DOWN) && mIsAutoScroll) {
				mIsStopByTouch = true;
				stopAutoScroll();
			} else if (ev.getAction() == MotionEvent.ACTION_UP && mIsStopByTouch) {
				startAutoScroll();
			}
		}
		return super.dispatchTouchEvent(ev);
	}

	/**
	 * whether stop auto scroll when touching, default is true
	 * 
	 * @return the stopScrollWhenTouch
	 */
	public boolean isStopScrollWhenTouch() {
		return mStopScrollWhenTouch;
	}

	/**
	 * set whether stop auto scroll when touching, default is true
	 * 
	 * @param stopScrollWhenTouch
	 */
	public void setStopScrollWhenTouch(boolean stopScrollWhenTouch) {
		this.mStopScrollWhenTouch = stopScrollWhenTouch;
	}

	public void stopAutoScroll() {
		mHandler.removeCallbacks(mTimeThread);

	}

	public void startAutoScroll() {
		mHandler.postDelayed(mTimeThread, DELAY_TIME);
	}

	private Runnable mTimeThread = new Runnable() {
		@Override
		public void run() {
			mHandler.sendEmptyMessage(WHEEL);
		}
	};

}
