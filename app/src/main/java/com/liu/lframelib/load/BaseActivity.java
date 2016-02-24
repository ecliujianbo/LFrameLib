//package com.liu.lframelib.load;
//
//import com.liu.lframelib.utils.HttpUtils;
//
//import android.app.Activity;
//import android.content.Intent;
//import android.os.Bundle;
//
//
///**
// * Created by Administrator on 2016-2-22.
// */
//public abstract class BaseActivity extends Activity {
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        beforeContentView();
//        setContentView(getContentView());
//        obtainIntent(getIntent());
//        initView();
//        initListener();
//        initData();
//
//    }
//
//    /**
//     * check network
//     * @return
//     */
//    protected boolean isNetworkAvailable(){
//        return HttpUtils.isNetworkAvailable(this);
//    }
//
//
//    protected abstract void beforeContentView();
//
//    protected abstract int getContentView();
//
//    protected abstract void obtainIntent(Intent intent);
//
//    protected abstract void initView();
//
//    protected abstract void initListener();
//
//    protected abstract void initData();
//
//}
