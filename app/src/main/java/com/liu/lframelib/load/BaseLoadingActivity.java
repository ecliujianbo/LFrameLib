//package com.liu.lframelib.load;
//
//import android.content.Intent;
//import android.text.TextUtils;
//import android.view.View;
//import android.widget.Toast;
//
//
//import java.lang.reflect.ParameterizedType;
//
//import com.liu.lframelib.BaseActivity;
//import com.liu.lframelib.R;
//import com.liu.lframelib.utils.JsonTools;
//import com.liu.lframelib.widget.PreLoadingView;
//
///**
// * Created by liu on 2016-2-22.
// */
//public abstract class BaseLoadingActivity<T> extends BaseActivity {
//    private View mLoadingView;
//    private PreLoadingView mDefaultLoadingView;
//    private View mRootLoadingView;
//    private Class<T> mClazz;
//    /**
//     * 是否有缓存
//     */
//    private boolean mCacheAvailable = false;
//
//    @Override
//    protected void initData() {
//        initLoadingView();
//        String cache = getCacheData();
//        //no cache
//        if (TextUtils.isEmpty(cache)) {
//            mCacheAvailable = false;
//            if (isNetworkAvailable()) {
//                initNetData();
//            } else {
//                //show loading failure
//                if (mLoadingView != null && mLoadingView instanceof ILoadingView) {
//                    ((ILoadingView) mLoadingView).showLoadFailedStatus();
//                }
//            }
//        } else {
//            mCacheAvailable = true;
//            //hidden loading,show cache
//            if (mLoadingView != null && mLoadingView instanceof ILoadingView) {
//                ((ILoadingView) mLoadingView).hiddenLoadingView();
//                initCacheData(parseJson(cache));
//            }
//            if (isNetworkAvailable()) {
//                initNetData();
//            }
//        }
//    }
//
//    public void setCacheAvailable(boolean available) {
//        this.mCacheAvailable = available;
//    }
//
//    public boolean isCacheAvailable() {
//        return mCacheAvailable;
//    }
//
//    protected T parseJson(String jsonStr) {
//        if (TextUtils.isEmpty(jsonStr)) {
//            return null;
//        }
//        ParameterizedType genericSuperclass = (ParameterizedType) getClass()
//                .getGenericSuperclass();
//        mClazz = (Class<T>) genericSuperclass.getActualTypeArguments()[0];
//        T t = JsonTools.jsonObj(jsonStr, mClazz);
//        if (t == null) {
//            return null;
//        }
//        return t;
//    }
//
//
//    private void initLoadingView() {
////        if(getLoadingView() == null){
//        mDefaultLoadingView = new PreLoadingView(this);
//        mLoadingView = mDefaultLoadingView;
////        }else{
////            mLoadingView = getLoadingView();
////        }
//        if (mLoadingView instanceof ILoadingView) {
//            mRootLoadingView = findViewById(R.id.loading_root_view); 
//            ((ILoadingView) mLoadingView).setTargetView(mRootLoadingView);
//            ((ILoadingView) mLoadingView).setOnRetryClickListener(new PreLoadingView.OnRetryClickListener() {
//                @Override
//                public void onRetryClickListener(View v) {
//                    if(isNetworkAvailable()){
//                        initNetData();
//                    }else{
//                        Toast.makeText(getApplicationContext(),"网络不给力",Toast.LENGTH_SHORT).show();
//                    }
//                }
//            });
//        }
//
//    }
//
//    protected boolean isNeedLoadingView(){
//        return true;
//    }
//
//    protected void showLoadingStatus() {
//        if (mLoadingView != null && mLoadingView instanceof ILoadingView) {
//            ((ILoadingView) mLoadingView).showLoadingStatus();
//        }
//    }
//
//    protected void showLoadingFailStatus() {
//        if (mLoadingView != null && mLoadingView instanceof ILoadingView) {
//            ((ILoadingView) mLoadingView).showLoadFailedStatus();
//        }
//    }
//
//    protected void hiddenLoadingView() {
//        if (mLoadingView != null && mLoadingView instanceof ILoadingView) {
//            ((ILoadingView) mLoadingView).hiddenLoadingView();
//        }
//    }
//
//
//    /**
//     * loadingView,must impl ILoadingView
//     *
//     * @return
//     */
//    protected View getLoadingView() {
//        return null;
//    }
//
//    /**
//     * cache data
//     *
//     * @return
//     */
//    protected abstract String getCacheData();
//
//    protected abstract void initCacheData(T t);
//
//    protected abstract void initNetData();
//
//    @Override
//    protected void beforeContentView() {
//    }
//
//    @Override
//    protected void obtainIntent(Intent intent) {
//    }
//
//    @Override
//    protected void initListener() {
//
//    }
//}
