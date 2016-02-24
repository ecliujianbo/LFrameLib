package com.liu.lframelib.widget;


import com.liu.lframelib.R;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * tiltebar
 *
 */
public class TitleBar {
	private View mRootView;
	private ImageView mImageLeftIcon, mImageRightIcon;
	private TextView mTextName;
	private TextView mTextRightText;

	private ImageView mImageLogo;

	/**
	 * activity attach
	 * 
	 * @param context
	 *            activity
	 */
	public TitleBar(Activity context) {
		mRootView = context.findViewById(R.id.ll_rootview);
		initView(mRootView);
	}

	/**
	 * fragment attach
	 * 
	 * @param context
	 *            fragment
	 */
	public TitleBar(View context) {
		mRootView = context.findViewById(R.id.ll_rootview);
		mRootView.setBackgroundColor(context.getResources().getColor(R.color.common_titlebar_color));
		initView(mRootView);
	}

	private void initView(View rootView) {
		mImageLeftIcon = (ImageView) rootView.findViewById(R.id.iv_titlebar_lefticon);
		mImageRightIcon = (ImageView) rootView.findViewById(R.id.iv_tiltebar_righticon);
		mTextName = (TextView) rootView.findViewById(R.id.tv_titlebar_name);
		mTextRightText = (TextView) rootView.findViewById(R.id.tv_titlebar_right_text);
		mImageLogo = (ImageView) rootView.findViewById(R.id.iv_title_logo);
	}

	public TitleBar setTitleLogo(int resId) {
		mImageLogo.setImageResource(resId);
		if (mImageLogo.getVisibility() != View.VISIBLE) {
			mImageLogo.setVisibility(View.VISIBLE);
		}
		if (mTextName.getVisibility() != View.GONE) {
			mTextName.setVisibility(View.GONE);
		}

		return this;
	}

	/**
	 * set titlebar background
	 * 
	 * @param color
	 *            resource id
	 * @return
	 */
	public TitleBar setBackgroundColor(int color) {
		mRootView.setBackgroundColor(color);
		return this;
	}

	/**
	 * set titlebar background
	 * 
	 * @param background
	 *            resource id
	 * @return
	 */
	public TitleBar setBackground(Drawable background) {
		mRootView.setBackgroundDrawable(background);
		return this;
	}

	/**
	 * set left image src
	 * 
	 * @param resId
	 *            image resource id
	 * @return
	 */
	public TitleBar setLeftIcon(int resId) {
		if (mImageLeftIcon.getVisibility() != View.VISIBLE) {
			mImageLeftIcon.setVisibility(View.VISIBLE);
		}
		mImageLeftIcon.setImageResource(resId);
		return this;
	}

	// left image visible
	public TitleBar setLeftIconVisibility(int visibility) {
		mImageLeftIcon.setVisibility(visibility);
		return this;
	}

	// set right icon resource id
	public TitleBar setRightIcon(int resId) {
		if (mImageRightIcon.getVisibility() != View.VISIBLE) {
			mImageRightIcon.setVisibility(View.VISIBLE);
		}
		if (mTextRightText.getVisibility() == View.VISIBLE) {
			mTextRightText.setVisibility(View.GONE);
		}
		mImageRightIcon.setImageResource(resId);
		return this;
	}

	// right image visible
	public TitleBar setRightIconVIsibility(int visibility) {
		mImageRightIcon.setVisibility(visibility);
		return this;
	}

	// set title name
	public TitleBar setTitleName(int resId) {
		mTextName.setText(resId);
		checkMiddleTitle();
		return this;
	}

	// 隐藏logo
	private void checkMiddleTitle() {
		if (mTextName.getVisibility() != View.VISIBLE) {
			mTextName.setVisibility(View.VISIBLE);
		}
		if (mImageLogo.getVisibility() == View.VISIBLE) {
			mImageLogo.setVisibility(View.GONE);
		}
	}

	public TitleBar setTitleName(String name) {
		mTextName.setText(name);
		checkMiddleTitle();
		return this;
	}

	public TitleBar setRightText(int resId) {
		if (mImageRightIcon.getVisibility() == View.VISIBLE) {
			mImageRightIcon.setVisibility(View.INVISIBLE);
		}
		if (mTextRightText.getVisibility() != View.VISIBLE) {
			mTextRightText.setVisibility(View.VISIBLE);
		}
		mTextRightText.setText(resId);
		return this;
	}

	public TitleBar setRightText(String text) {
		if (mImageRightIcon.getVisibility() == View.VISIBLE) {
			mImageRightIcon.setVisibility(View.INVISIBLE);
		}
		if (mTextRightText.getVisibility() != View.VISIBLE) {
			mTextRightText.setVisibility(View.VISIBLE);
		}
		mTextRightText.setText(text);
		return this;
	}

	public TitleBar setRightHidden() {
		if (mImageRightIcon.getVisibility() == View.VISIBLE) {
			mImageRightIcon.setVisibility(View.INVISIBLE);
		}

		if (mTextRightText.getVisibility() == View.VISIBLE) {
			mTextRightText.setVisibility(View.GONE);
		}
		return this;
	}

	public TitleBar setRightTextClickListener(View.OnClickListener listener) {
		if (mTextRightText.getVisibility() == View.VISIBLE) {
			mTextRightText.setOnClickListener(listener);
		}
		return this;
	}

	public TitleBar setLeftIconClickListener(View.OnClickListener listener) {
		if (mImageLeftIcon.getVisibility() == View.VISIBLE) {
			mImageLeftIcon.setOnClickListener(listener);
		}
		return this;
	}

	public View getRootView() {
		return mRootView;
	}

	public TitleBar setRightIconClickListener(View.OnClickListener listener) {
		if (mImageRightIcon.getVisibility() == View.VISIBLE) {
			mImageRightIcon.setOnClickListener(listener);
		}
		return this;
	}

}
