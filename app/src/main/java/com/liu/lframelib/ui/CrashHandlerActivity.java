package com.liu.lframelib.ui;

import com.liu.lframelib.BaseActivity;
import com.liu.lframelib.R;
import com.liu.lframelib.utils.CrashHandler;
import com.liu.lframelib.utils.LogUtil;
import com.liu.lframelib.widget.TitleBar;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
/**
 * 需要注册
 *
 */
public class CrashHandlerActivity extends BaseActivity {
	private Button mBtnRestartApp;
	private Button mBtnCheckError;
	private Button mBtnUploadInfo;
	private String mStackTraceString;
	/**
	 * 上传crash异常
	 * 
	 * @param info
	 */
	private void uploadCrashInfo(String info) {
//		if (TextUtils.isEmpty(info)) {
//			mBtnUploadInfo.setVisibility(View.GONE);
//			return;
//		}
//		if (!isNetEnable()) {
//			mBtnUploadInfo.setVisibility(View.VISIBLE);
//			return;
//		}
//		PostParams params = new PostParams();
//		params.with("crash", info);
//		RequestData data = new RequestData(API.CRASH_UPLOAD, Method.POST, params, this);
//		submit(data, new NetHandler(this) {
//			@Override
//			public void onServerBusy() {
//				mBtnUploadInfo.setVisibility(View.VISIBLE);
//			}
//
//			@Override
//			public void onResponse(JSONObject obj) {
//				LogUtil.logD("crash:   "+obj.toString());
//				mBtnUploadInfo.setVisibility(View.GONE);
//			}
//
//			@Override
//			public void onErrorResponse(VolleyError error) {
//				mBtnUploadInfo.setVisibility(View.VISIBLE);
//			}
//
//			@Override
//			public void getErrorCode(int code) {
//				mBtnUploadInfo.setVisibility(View.VISIBLE);
//			}
//		});
	}

	@Override
	protected void beforeContentView() {
		
	}

	@Override
	protected int getContentView() {
		return R.layout.activity_crash_handle;
	}

	@Override
	protected void obtainIntent(Intent intent) {
		
	}

	@Override
	protected void initView() {
		mBtnRestartApp = (Button) findViewById(R.id.btn_crash_handler_restart);
		mBtnCheckError = (Button) findViewById(R.id.btn_crash_handler_deatail_info);
		mBtnUploadInfo = (Button) findViewById(R.id.btn_crash_handler_upload_info);
		new TitleBar(this).setTitleName("程序异常").setLeftIconClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				CrashHandler.closeApplication(CrashHandlerActivity.this);
			}
		});
	}

	@Override
	protected void initListener() {
		mBtnUploadInfo.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (!isNetworkAvailable()) {
					showToast(R.string.net_work_error);
					return;
				}
				uploadCrashInfo(mStackTraceString);
			}
		});
		if (LogUtil.DEBUG) {
			mBtnCheckError.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
//					TitleTipsDialog dialog = new TitleTipsDialog(CrashHandlerActivity.this);
//					dialog.setTitle("异常信息");
//					dialog.setContent(mStackTraceString);
//					dialog.show();
				}
			});
		} else {
			mBtnCheckError.setVisibility(View.GONE);
		}
	}

	@Override
	protected void initData() {
		mStackTraceString = CrashHandler.getAllErrorDetailsFromIntent(this, getIntent());
		final Class<? extends Activity> restartActivityClass = (Class<? extends Activity>) getIntent()
				.getSerializableExtra(CrashHandler.EXTRA_RESTART_ACTIVITY_CLASS);
		
		
		uploadCrashInfo(mStackTraceString);
		
		
		if (restartActivityClass != null) {
			mBtnRestartApp.setText(R.string.crash_handler_restart_app);
		}

		// final String stackTraceString =
		// getIntent().getStringExtra(CrashHandler.EXTRA_STACK_TRACE);
		mBtnRestartApp.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (restartActivityClass != null) {
					Intent intent = new Intent(CrashHandlerActivity.this, restartActivityClass);
					CrashHandler.restartApplicationWithIntent(CrashHandlerActivity.this, intent);
				} else {
					CrashHandler.closeApplication(CrashHandlerActivity.this);
				}
			}
		});
	
	}
}
