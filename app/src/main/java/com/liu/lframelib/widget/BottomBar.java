package com.liu.lframelib.widget;


import com.liu.lframelib.R;
import com.liu.lframelib.utils.LogUtil;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * 底部切换tab
 */
public class BottomBar extends LinearLayout implements OnClickListener {
	private LinearLayout mRelativeMine, mRelativeHome, mRelativeProduct, mRelativeMore;
//	private TextView mTextHome, mTextProduct, mTextMine, mTextMore;
	private ImageView mImageHome, mImageProduct, mImageMine, mImageMore;
	public static final int PAGE_HOME = 0;
	public static final int PAGE_PRODUCT = 1;
	public static final int PAGE_MINE = 2;
	public static final int PAGE_MORE = 3;
	// private boolean mIsPageChange;
	private int mCurrentPage = -1;
	private int mPrePage = -1;

	public BottomBar(Context context) {
		this(context, null);
	}

	@SuppressLint("NewApi")
	public BottomBar(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init(context);
	}

	public BottomBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	private void init(Context context) {
		setOrientation(LinearLayout.HORIZONTAL);
		View view = LayoutInflater.from(context).inflate(R.layout.bottom_bar, null);
		// mIsPageChange = false;
		initItem(view);
		ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.MATCH_PARENT);
		addView(view, params);
		setBackgroundColor(getResources().getColor(R.color.common_titlebar_color));
	}

	public int getPrePage() {
		return mPrePage;
	}

	private void initItem(View view) {
		mRelativeHome = getView(view, R.id.rl_botto_bar_home);
		mRelativeProduct = getView(view, R.id.rl_bottom_bar_product);
		mRelativeMine = getView(view, R.id.rl_bottom_bar_mine);
		mRelativeMore = getView(view, R.id.rl_bottom_bar_more);
//		mTextHome = getView(view, R.id.tv_bottom_home);
//		mTextProduct = getView(view, R.id.tv_bottom_production);
//		mTextMine = getView(view, R.id.tv_bottom_mine);
//		mTextMore = getView(view, R.id.tv_bottom_more);
		mImageHome = getView(view, R.id.iv_bottom_home);
		mImageProduct = getView(view, R.id.iv_bottom_product);
		mImageMine = getView(view, R.id.iv_bottom_mine);
		mImageMore = getView(view, R.id.iv_bottom_more);

		mRelativeHome.setOnClickListener(this);
		mRelativeProduct.setOnClickListener(this);
		mRelativeMine.setOnClickListener(this);
		mRelativeMore.setOnClickListener(this);
	}

	// 重置bottombar
	private void resetBottomBar() {
//		mTextHome.setTextColor(getResources().getColor(R.color.bottom_bar_unchecked_color));
//		mTextProduct.setTextColor(getResources().getColor(R.color.bottom_bar_unchecked_color));
//		mTextMine.setTextColor(getResources().getColor(R.color.bottom_bar_unchecked_color));
//		mTextMore.setTextColor(getResources().getColor(R.color.bottom_bar_unchecked_color));
		mImageHome.setImageResource(R.drawable.main_tab_home_page_normal);
		mImageProduct.setImageResource(R.drawable.main_tab_product_normal);
		mImageMine.setImageResource(R.drawable.main_tab_mine_normal);
		mImageMore.setImageResource(R.drawable.main_tab_more_normal);
	}

	public void setCurrentItem(int index) {
		if (mCurrentPage == index) {
			return;
		}
		mPrePage = mCurrentPage;
		mCurrentPage = index;
		resetBottomBar();
		switch (index) {
		case PAGE_HOME:
			mImageHome.setImageResource(R.drawable.main_tab_home_page_checked);
//			mTextHome.setTextColor(getResources().getColor(R.color.bottom_bar_checked_color));
			break;
		case PAGE_PRODUCT:
			mImageProduct.setImageResource(R.drawable.main_tab_product_checked);
//			mTextProduct.setTextColor(getResources().getColor(R.color.bottom_bar_checked_color));
			break;
		case PAGE_MINE:
			mImageMine.setImageResource(R.drawable.main_tab_mine_checked);
//			mTextMine.setTextColor(getResources().getColor(R.color.bottom_bar_checked_color));
			break;
		case PAGE_MORE:
			mImageMore.setImageResource(R.drawable.main_tab_more_checked);
//			mTextMore.setTextColor(getResources().getColor(R.color.bottom_bar_checked_color));
			break;
		default:
			break;
		}
		if (mListener != null) {
			mListener.onPageChange(mCurrentPage);
		}
	}

	private final <E extends View> E getView(View view, int id) {
		try {
			return (E) view.findViewById(id);
		} catch (ClassCastException ex) {
			LogUtil.logE(ex);
			throw ex;
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
//		case R.id.rl_botto_bar_home:
//			setCurrentItem(PAGE_HOME);
//			break;
//		case R.id.rl_bottom_bar_product:
//			setCurrentItem(PAGE_PRODUCT);
//			break;
//		case R.id.rl_bottom_bar_mine:
//			setCurrentItem(PAGE_MINE);
//			break;
//		case R.id.rl_bottom_bar_more:
//			setCurrentItem(PAGE_MORE);
//			break;
		default:
			break;
		}
	}

	public View getTabIcon(int index) {
		View view = null;
		switch (index) {
		case 0:
			view = mImageHome;
			break;
		case 1:
			view = mImageProduct;
			break;
		case 2:
			view = mImageMine;
			break;
		case 3:
			view = mImageMore;
			break;
		default:
			view = mImageHome;
			break;
		}
		return view;
	}

	private OnPageChangeListener mListener;

	public void setOnPageChangeListener(OnPageChangeListener listener) {
		this.mListener = listener;
	}

	/**
	 * 页面切换回调
	 */
	public interface OnPageChangeListener {
		void onPageChange(int position);
	}
}
