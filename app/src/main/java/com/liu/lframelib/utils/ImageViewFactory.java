package com.liu.lframelib.utils;

import com.liu.lframelib.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;

public class ImageViewFactory {

	public static ImageView getImageView(Context context, String url, DisplayImageOptions options) {
		ImageView imageView = (ImageView) LayoutInflater.from(context).inflate(R.layout.adapter_ad, null);
		ImageLoader.getInstance().displayImage(url, imageView, options);
		return imageView;
	}

	/**
	 * 加载网络图片
	 * 
	 * @param url
	 *            网络图片地址
	 *            
	 * @param targetView
	 *            目标的imageview
	 *            
	 * @param defaultImageResId
	 *            默认的图片资源Id
	 */
	public static void loadImageView(String url, ImageView targetView, int defaultImageResId) {
		if (defaultImageResId == -1) {
			ImageLoader.getInstance().displayImage(url, targetView);
		} else {
			DisplayImageOptions options = new DisplayImageOptions.Builder().showImageForEmptyUri(defaultImageResId)
					.showImageOnFail(defaultImageResId).showImageOnLoading(defaultImageResId).cacheInMemory(true)
					.cacheOnDisc(true).build();
			ImageLoader.getInstance().displayImage(url, targetView, options);
		}
	}

}