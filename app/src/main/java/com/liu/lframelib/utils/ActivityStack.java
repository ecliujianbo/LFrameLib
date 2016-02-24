package com.liu.lframelib.utils;

import java.util.ArrayList;
import java.util.List;


import android.app.Activity;

/**
 * 管理Activity
 * 
 * 2014-10-11
 */
public class ActivityStack {

	private List<Activity> mActivityList = new ArrayList<Activity>();;
	private static ActivityStack mInstance;

	private ActivityStack() {
	}

	public static synchronized ActivityStack getInstance() {
		if (mInstance == null) {
			mInstance = new ActivityStack();
		}
		return mInstance;
	}

	/**
	 * add the activity in to a list end
	 * 
	 * @param activity
	 */
	public void addActivity(Activity activity) {
		try {
			if (activity != null && mActivityList != null) {
				int size = mActivityList.size();
				if (checkActivityIsVasivle(activity)) {
					removeActivity(activity);
					mActivityList.add(mActivityList.size(), activity);
				} else {
					mActivityList.add(activity);
				}
				size = mActivityList.size();
				for (int i = 0; i < size; i++) {
					LogUtil.logD("addActivity ==[" + i + "]" + " " + mActivityList.get(i));
				}
			}
		} catch (Exception e) {
		}

	}

	/**
	 * finish all the activity in the list.
	 * 
	 * @param context
	 *            the activity calling this method hold the context
	 */
	public void finishAllActivity() {
		if (mActivityList != null) {
			int size = mActivityList.size();
			for (int i = size - 1; i >= 0; i--) {
				Activity activity = mActivityList.get(i);
				if (activity != null) {
					activity.finish();
				}
				LogUtil.logD("finishAllActivity ==[" + i + "]" + " " + activity);
				mActivityList.remove(activity);
				// activityList.clear();
			}
		}

	}

	/**
	 * remove the finished activity in the list.
	 * 
	 * @param activity
	 *            the activity is removed from activityList
	 */
	public void removeActivity(Activity activity) {
		try {
			if (mActivityList != null) {
				mActivityList.remove(activity);
				LogUtil.logD("removeActivity==" + " " + activity + "activityList.size===" + mActivityList.size());
			}
		} catch (Exception e) {
			LogUtil.logE("removeActivity" + e.getMessage());
		}
	}

	public boolean checkActivityIsVasivle(Activity activity) {
		LogUtil.logD(" " + mActivityList.contains(activity));
		return mActivityList.contains(activity);
	}

}
