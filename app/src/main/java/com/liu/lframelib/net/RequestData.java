package com.liu.lframelib.net;


import android.content.Context;

public class RequestData {
	private String mUrl;
	private int mMethod;
	private PostParams mPostParams;
	private Context mTag;
	private String mToken;
	private Object mGetBean;

	public RequestData(String url, int method, PostParams params, Context tag) {
		this.mUrl = url;
		this.mMethod = method;
		this.mPostParams = params;
		this.mTag = tag;
	}

	// public RequestData(String mUrl, int mMethod, PostParams mPostParams,
	// Context mTag, String mToken) {
	// super();
	// this.mUrl = mUrl;
	// this.mMethod = mMethod;
	// this.mPostParams = mPostParams;
	// this.mTag = mTag;
	// this.mToken = mToken;
	// }

	public Object getmGetBean() {
		return mGetBean;
	}

	public void setmGetBean(Object mGetBean) {
		this.mGetBean = mGetBean;
	}

	public PostParams getmPostParams() {
		return mPostParams;
	}

	public void setmPostParams(PostParams mPostParams) {
		this.mPostParams = mPostParams;
	}

	public Context getmTag() {
		return mTag;
	}

	public void setmTag(Context mTag) {
		this.mTag = mTag;
	}

	public String getmToken() {
		return mToken;
	}

	public void setmToken(String mToken) {
		this.mToken = mToken;
	}

	public void setmUrl(String mUrl) {
		this.mUrl = mUrl;
	}

	public void setmMethod(int mMethod) {
		this.mMethod = mMethod;
	}

	public Object getTag() {
		return mTag;
	}

	public String getmUrl() {
		return mUrl;
	}

	public PostParams getPostParams() {
		return mPostParams;
	}

	public int getmMethod() {
		return mMethod;
	}

	@Override
	public String toString() {
		return "RequestData [mUrl=" + mUrl + ", mMethod=" + mMethod + ", mPostParams=" + mPostParams + ", mTag=" + mTag
				+ ", mToken=" + mToken + ", mGetBean=" + mGetBean + "]";
	}

}
