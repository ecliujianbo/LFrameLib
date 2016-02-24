package com.liu.lframelib.widget;


import com.liu.lframelib.R;
import com.liu.lframelib.ui.load.ILoadingView;
import com.liu.lframelib.utils.HttpUtils;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


/**
 * 预加载的view 使用setTargetView (targetView必须有父view,需要覆盖住的view)
 */
public class PreLoadingView extends RelativeLayout implements ILoadingView {
	private Context mContext;
	private View mProgressView;
	private LinearLayout mLinearLoading;
	private TextView mTextRetry;
	private ImageView mImageLogo;
	private OnRetryClickListener mOnRetryClickListener;

	public PreLoadingView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public PreLoadingView(Context context) {
		this(context, null);
	}

	public PreLoadingView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this.mContext = context;
		((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.loading_progress,
				this, true);
		this.initViews();
	}

	private void initViews() {
		ViewGroup.LayoutParams p = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.MATCH_PARENT);
		setLayoutParams(p);
		mImageLogo = (ImageView) findViewById(R.id.iv_preview_fail_icon);
		this.mProgressView = findViewById(R.id.pb_preview_loading);

		this.mLinearLoading = (LinearLayout) findViewById(R.id.ll_preview_loading);
		mTextRetry = (TextView) findViewById(R.id.tv_preview_retry);
		mLinearLoading.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (mOnRetryClickListener != null)
					mOnRetryClickListener.onRetryClickListener(v);
			}
		});
		this.showLoadingStatus();
	}
	@Override
	public void showLoadingStatus() {
		this.setVisibility(View.VISIBLE);
		mImageLogo.setVisibility(View.GONE);
		mTextRetry.setVisibility(View.GONE);
		mLinearLoading.setClickable(false);
		this.mProgressView.setVisibility(View.VISIBLE);
	}

	public void showLoadFailedStatus(String text) {
		mImageLogo.setVisibility(View.VISIBLE);
		this.setVisibility(View.VISIBLE);
		mLinearLoading.setClickable(true);
		mTextRetry.setVisibility(View.VISIBLE);
		this.mProgressView.setVisibility(View.GONE);

	}
	@Override
	public void hiddenLoadingView(){
		setVisibility(View.GONE);
	}
	@Override
	public void showLoadFailedStatus() {
		mImageLogo.setVisibility(View.VISIBLE);
		if (!HttpUtils.isNetworkAvailable(mContext)) {
			this.setVisibility(View.VISIBLE);
			mTextRetry.setVisibility(View.VISIBLE);
			mLinearLoading.setClickable(true);
			mTextRetry.setText("点击屏幕  重新加载");
			this.mProgressView.setVisibility(View.GONE);
		} else {
			showLoadFailedStatus("加载失败");
		}
	}

	public interface OnRetryClickListener {
		public void onRetryClickListener(View v);
	}
	@Override
	public void setOnRetryClickListener(PreLoadingView.OnRetryClickListener mOnRetryClickListener) {
		this.mOnRetryClickListener = mOnRetryClickListener;
	}

	/*
	 * Attach the LoadingPreView to the target view
	 * 
	 * @param target the view to attach the LoadingPreView
	 */
	@Override
	public void setTargetView(View target) {
		if (getParent() != null) {
			((ViewGroup) getParent()).removeView(this);
		}

		if (target == null) {
			return;
		}

		if (target.getParent() instanceof FrameLayout) {
			((FrameLayout) target.getParent()).addView(this);

		} else if (target.getParent() instanceof ViewGroup) {
			// use a new Framelayout container for adding badge
			ViewGroup parentContainer = (ViewGroup) target.getParent();
			int groupIndex = parentContainer.indexOfChild(target);
			parentContainer.removeView(target);

			FrameLayout badgeContainer = new FrameLayout(getContext());
			ViewGroup.LayoutParams parentLayoutParams = target.getLayoutParams();

			badgeContainer.setLayoutParams(parentLayoutParams);
			target.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
					ViewGroup.LayoutParams.MATCH_PARENT));

			badgeContainer.addView(target);

			badgeContainer.addView(this);
			parentContainer.addView(badgeContainer, groupIndex, parentLayoutParams);
		} else if (target.getParent() == null) {
			Log.e(getClass().getSimpleName(), "ParentView is needed");
		}

	}


}
