package com.liu.lframelib.widget.viewpager;

import java.lang.ref.WeakReference;
import java.util.List;

import com.liu.lframelib.R;
import com.liu.lframelib.utils.ImageViewFactory;
import com.liu.lframelib.utils.LogUtil;
import com.nostra13.universalimageloader.core.ImageLoader;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class ViewPagerAdapter extends PagerAdapter {

	private List<AdvInfo> mList;
	private Context mContext;

	public ViewPagerAdapter(List<AdvInfo> list, Context context) {
		this.mContext = new WeakReference<Context>(context).get();
		this.mList = list;
	}

	@Override
	public int getCount() {
		return Integer.MAX_VALUE;
	}

	/**
	 * 判断当前滑动view等不等进来的对象
	 * 
	 * true: 表示不去创建，使用缓存 false:去重新创建 view： 当前滑动的view
	 * object：将要进入的新创建的view，由instantiateItem方法创建
	 */
	@Override
	public boolean isViewFromObject(View view, Object object) {
		return view == object;
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		View view = View.inflate(mContext, R.layout.adapter_ad, null);
		ImageView imageView = (ImageView) view.findViewById(R.id.image);

		final AdvInfo ad = mList.get(position % mList.size());
		if (!TextUtils.isEmpty(ad.getImageUr())) {
			ImageViewFactory.loadImageView(ad.getImageUr(), imageView, R.drawable.ic_launcher);
		} else {
			imageView.setImageResource(ad.getIconResId());
		}
		imageView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (mListener != null) {
					mListener.onItemClick(ad);
				}
			}
		});
		// 一定不能少，将view加入到viewPager中
		container.addView(view);

		return view;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		// super.destroyItem(container, position, object);
		container.removeView((View) object);
	}

	@Override
	public int getItemPosition(Object object) {
		return POSITION_NONE;
	}

	private OnItemClickListener mListener;

	public void onItemCLickListener(OnItemClickListener listener) {
		this.mListener = listener;
	}

	public interface OnItemClickListener {
		void onItemClick(AdvInfo info);
	}

}
