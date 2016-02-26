package com.liu.lframelib.ui.load;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.liu.lframelib.BaseActivity;
import com.liu.lframelib.R;
import com.liu.lframelib.utils.JsonTools;
import com.liu.lframelib.utils.ToastUtils;
import com.liu.lframelib.widget.PreLoadingView;

import java.lang.reflect.ParameterizedType;

/**
 * <p>
 * use this ni need use <b>R.id.loading_root_view</b> as rootView,preLoadingView need attach.
 * <b>R.id.loading_content_view</b> as the content view.
 * </p>
 * <p/>
 * <p/>
 * Created by liu on 2016-2-22.
 */
public abstract class BaseLoadingActivity<T> extends BaseActivity {
    private View mLoadingView;
    private View mContentView;
    private PreLoadingView mDefaultLoadingView;
    private View mRootView;
    private Class<T> mClazz;
    /**
     * 是否有缓存
     */
    private boolean mCacheAvailable = false;

    private int mShortAnimationDuration;

    @Override
    protected void initData() {
        initLoadingView();
        String cache = getCacheData();
        // no cache
        if (TextUtils.isEmpty(cache)) {
            mCacheAvailable = false;
            if (isNetworkAvailable()) {
                initNetData();
            } else {
                // show loading failure
                if (mLoadingView != null && mLoadingView instanceof ILoadingView) {
                    ((ILoadingView) mLoadingView).showLoadFailedStatus();
                }
            }
        } else {
            mCacheAvailable = true;
            // hidden loading,show cache
            if (mLoadingView != null && mLoadingView instanceof ILoadingView) {
                ((ILoadingView) mLoadingView).hiddenLoadingView();
                initCacheData(parseJson(cache));
            }
            if (isNetworkAvailable()) {
                initNetData();
            }
        }
    }

    public void setCacheAvailable(boolean available) {
        this.mCacheAvailable = available;
    }

    public boolean isCacheAvailable() {
        return mCacheAvailable;
    }

    protected T parseJson(String jsonStr) {
        if (TextUtils.isEmpty(jsonStr)) {
            return null;
        }
        ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
        mClazz = (Class<T>) genericSuperclass.getActualTypeArguments()[0];
        T t = JsonTools.jsonObj(jsonStr, mClazz);
        if (t == null) {
            return null;
        }
        return t;
    }

    private void initLoadingView() {
        mContentView = findViewById(R.id.loading_content_view);
        // Retrieve and cache the system's default "short" animation time.
        mShortAnimationDuration = getResources().getInteger(android.R.integer.config_shortAnimTime);
        if (getLoadingView() == null) {
            mDefaultLoadingView = new PreLoadingView(this);
            mLoadingView = mDefaultLoadingView;
        } else {
            mLoadingView = getLoadingView();
        }
        if (mLoadingView instanceof ILoadingView) {
            mRootView = findViewById(R.id.loading_root_view);
            ((ILoadingView) mLoadingView).setTargetView(mRootView);
            ((ILoadingView) mLoadingView).setOnRetryClickListener(new PreLoadingView.OnRetryClickListener() {
                @Override
                public void onRetryClickListener(View v) {
                    if (isNetworkAvailable()) {
                        initNetData();
                    } else {
                        ToastUtils.getInstance(getApplicationContext(), R.string.net_work_error, Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }

    protected boolean isNeedLoadingView() {
        return true;
    }

    protected void showLoadingStatus() {
        showContentOrLoadingIndicator(false);
        ((ILoadingView) mLoadingView).showLoadingStatus();
    }

    protected void showLoadingFailStatus() {
//		if (mLoadingView != null && mLoadingView instanceof ILoadingView) {
        showContentOrLoadingIndicator(false);
        ((ILoadingView) mLoadingView).showLoadFailedStatus();
//		}
    }

    protected void hiddenLoadingView() {
//		if (mLoadingView != null && mLoadingView instanceof ILoadingView) {
//			((ILoadingView) mLoadingView).hiddenLoadingView();
//		}
        showContentOrLoadingIndicator(true);
    }

    /**
     * Cross-fades between {@link #mContentView} and {@link #mLoadingView}.
     */
    private void showContentOrLoadingIndicator(boolean contentLoaded) {
        // Decide which view to hide and which to show.
        final View showView = contentLoaded ? mContentView : mLoadingView;
        final View hideView = contentLoaded ? mLoadingView : mContentView;

        // Set the "show" view to 0% opacity but visible, so that it is visible
        // (but fully transparent) during the animation.
        showView.setAlpha(0f);
        showView.setVisibility(View.VISIBLE);

        // Animate the "show" view to 100% opacity, and clear any animation listener set on
        // the view. Remember that listeners are not limited to the specific animation
        // describes in the chained method calls. Listeners are set on the
        // ViewPropertyAnimator object for the view, which persists across several
        // animations.
        showView.animate()
                .alpha(1f)
                .setDuration(mShortAnimationDuration)
                .setListener(null);

        // Animate the "hide" view to 0% opacity. After the animation ends, set its visibility
        // to GONE as an optimization step (it won't participate in layout passes, etc.)
        hideView.animate()
                .alpha(0f)
                .setDuration(mShortAnimationDuration)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        hideView.setVisibility(View.GONE);
                    }
                });
    }

    /**
     * loadingView,must impl ILoadingView
     *
     * @return
     */
    protected View getLoadingView() {
        return null;
    }


    /**
     * cache data
     *
     * @return
     */
    protected abstract String getCacheData();

    protected abstract void initCacheData(T t);

    protected abstract void initNetData();

    @Override
    protected void beforeContentView() {
    }

    @Override
    protected void obtainIntent(Intent intent) {
    }

    @Override
    protected void initListener() {

    }
}
