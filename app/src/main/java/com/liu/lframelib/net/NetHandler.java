package com.liu.lframelib.net;

import org.json.JSONObject;

import com.android.volley.VolleyError;

import android.os.Handler;
import android.os.Message;

public abstract class NetHandler extends Handler {
	public static final int NET_OK = 200;
	public static final int NET_ERROR = -1;

	@Override
	public void handleMessage(Message msg) {
		super.handleMessage(msg);
		switch (msg.what) {
		case NET_OK:
			onResponse((JSONObject) msg.obj);
			break;
		case NET_ERROR:
			VolleyError error = (VolleyError) msg.obj;
			onErrorResponse(error);
			if (error != null)
				onErrorResponseDetail(new String(error.networkResponse.data));
			break;
		default:
			break;
		}
	}

	public abstract void onErrorResponse(VolleyError error);

	public abstract void onResponse(JSONObject obj);

	public void onErrorResponseDetail(String message) {
	}
}
