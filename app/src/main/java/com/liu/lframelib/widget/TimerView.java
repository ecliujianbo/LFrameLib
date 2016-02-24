package com.liu.lframelib.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * setTime()
 */
public class TimerView extends TextView {
	long startTime;
	long total;

	public TimerView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public TimerView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public TimerView(Context context) {
		super(context);
	}

	public void setTime(long startTime, long total) {
		this.total = total;
		this.startTime = startTime;
		removeCallbacks(runnable);
		post(runnable);
	}

	@Override
	protected void onDetachedFromWindow() {
		removeCallbacks(runnable);
		super.onDetachedFromWindow();
	}

	private Runnable runnable = new Runnable() {

		@Override
		public void run() {
			long time = System.currentTimeMillis() - startTime;
			long t = total - time;
			if (t <= 0) {
				setText("活动结束");
			} else {
				setText("剩余 " + t / 1000 + "秒");
			}
			postDelayed(runnable, 500);
		}
	};
}
