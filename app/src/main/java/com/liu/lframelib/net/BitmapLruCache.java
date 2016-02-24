package com.liu.lframelib.net;
//
//package com.otouzi.p2p.net;
//
//import android.graphics.Bitmap;
//import android.support.v4.util.LruCache;
//
//import com.android.volley.toolbox.ImageLoader;
//
///**
// * 缓存
// * 
// * 
// *         2014-9-2
// */
//public class BitmapLruCache extends LruCache<String, Bitmap> implements
//		ImageLoader.ImageCache {
//	public BitmapLruCache(int maxSize) {
//		super(maxSize);
//	}
//
//	@Override
//	protected int sizeOf(String key, Bitmap bitmap) {
//		return bitmap.getRowBytes() * bitmap.getHeight();
//	}
//
//	@Override
//	public Bitmap getBitmap(String url) {
//		return get(url);
//	}
//
//	@Override
//	public void putBitmap(String url, Bitmap bitmap) {
//		put(url, bitmap);
//	}
//}
