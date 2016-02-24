package com.liu.lframelib.ui.load;

import com.liu.lframelib.widget.PreLoadingView;

import android.view.View;

/**
 * Created by Administrator on 2016-2-18.
 */
public interface ILoadingView {
    void setTargetView(View targetView);

    void showLoadFailedStatus();

    void showLoadingStatus();

    void hiddenLoadingView();

    void setOnRetryClickListener(PreLoadingView.OnRetryClickListener mOnRetryClickListener);
}
