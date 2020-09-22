package com.example.myapplication.commit;

import android.text.TextUtils;
import android.util.Log;


import com.example.myapplication.base.BaseView;

import io.reactivex.subscribers.ResourceSubscriber;

public abstract class CommonSubscriber<T> extends ResourceSubscriber<T> {

    private BaseView mView;
    private String errorMsg;
    private boolean isShowErrorState = false;

    protected CommonSubscriber(BaseView view){
        mView = view;
    }

    protected CommonSubscriber(BaseView view, String emsg){
        mView = view;
        errorMsg = emsg;
    }

    protected CommonSubscriber(BaseView view, boolean isError){
        mView = view;
        isShowErrorState = isError;
    }

    protected CommonSubscriber(BaseView view, String emsg, boolean isError){
        mView = view;
        errorMsg = emsg;
        isShowErrorState = isError;
    }

    @Override
    public void onError(Throwable t) {
        Log.e("TAG", "onError: "+t.getMessage() );
        if(mView == null) return;
        if(errorMsg != null && TextUtils.isEmpty(errorMsg)){
            mView.showTips(errorMsg);
        }
    }

    @Override
    public void onComplete() {

    }
}
