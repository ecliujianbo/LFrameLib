package com.liu.lframelib;

import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.liu.lframelib.net.HttpConnectsProxy;
import com.liu.lframelib.net.NetHandler;
import com.liu.lframelib.net.RequestData;
import com.liu.lframelib.utils.HttpUtils;
import com.liu.lframelib.utils.LogUtil;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.Toast;

public class BaseFragment extends Fragment implements Response.Listener<String>, Response.ErrorListener {
	private HttpConnectsProxy mConnectProxy;
	// 0正确的
	private int mResultCode = -1;

	@Override
	public void onErrorResponse(VolleyError arg0) {
	}

	@Override
	public void onResponse(String arg0) {
		try {
			JSONObject obj = new JSONObject(arg0);
			mResultCode = obj.optInt("errNum", -1);
			LogUtil.logD("basefragment   code:  " + mResultCode);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public boolean isNetWorks() {
		return HttpUtils.isNetworkAvailable(getActivity());
	}

	public int getCode() {
		return mResultCode;
	}

	protected void submit(RequestData data, NetHandler handler) {
		mConnectProxy = new HttpConnectsProxy(data, handler, getActivity());
		mConnectProxy.submit();
	}

	protected void showToast(String message) {
		Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
	}

	protected void showToast(int resId) {
		Toast.makeText(getActivity(), getResources().getString(resId), Toast.LENGTH_SHORT).show();
	}

	protected void animStartActivity(Class<?> clazz) {
		animStartActivity(clazz, null);
	}

	protected void animStartActivity(Class<?> clazz, Bundle bundle) {
		Intent intent = new Intent(getActivity(), clazz);
		if (bundle != null) {
			intent.putExtras(bundle);
		}
		startActivity(intent);
//		getActivity().overridePendingTransition(R.anim.right_in, R.anim.left_out);
	}

	@Override
	public void onDestroyView() {
		super.onDestroy();
		try {
			mConnectProxy.cancelAll(getActivity());
		} catch (Exception e) {
		}
	}

}
