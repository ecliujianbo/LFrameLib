package com.liu.lframelib.net;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.liu.lframelib.utils.LogUtil;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

public class HttpConnectsProxy {
	//装载数据
	private RequestData mRequestData;
	//返回回调
	private NetHandler mHandler;
	private Context mContext;
	private RequestQueue mQueue;

	public HttpConnectsProxy(RequestData requestData, NetHandler handler, Context context) {
		this.mRequestData = requestData;
		this.mHandler = handler;
		this.mContext = context;
		if (mQueue == null) {  
			mQueue = Volley.newRequestQueue(mContext.getApplicationContext());
		}
	}

	private void addRequest(Request<?> request, Object tag) {
		if (tag != null) {
			request.setTag(tag);
		}
		mQueue.add(request);
		LogUtil.logD("HttpConnectsProxy addRequest");
	}

	public void cancelAll(Object tag) {
		mQueue.cancelAll(tag);
	}

	private boolean mGetToken = false;

	public void setGetToekn(boolean getToken) {
		this.mGetToken = getToken;
	}
	/**
	 * 提交，获取数据
	 */
	public void submit() {
		JSONObject jObject = null;
		String url = null;
		if (mRequestData.getPostParams() != null) {
			jObject = new JSONObject(mRequestData.getPostParams());
			LogUtil.logD("-Proxy--jObject:  " + jObject.toString());
		}
		url = mRequestData.getmUrl();
		addRequest(new JsonObjectRequest(mRequestData.getmMethod(), url, jObject, new Listener<JSONObject>() {
			@Override
			public void onResponse(JSONObject arg0) {
				LogUtil.logD("HttpConnectsProxy onResponse");
				mHandler.obtainMessage(NetHandler.NET_OK, arg0).sendToTarget();
			}
		}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {

				mHandler.obtainMessage(NetHandler.NET_ERROR, error).sendToTarget();
			}
		}) {
			@Override
			public Map<String, String> getHeaders() throws AuthFailureError {
					return super.getHeaders();
			}
		}, mRequestData.getTag());
	}

}
