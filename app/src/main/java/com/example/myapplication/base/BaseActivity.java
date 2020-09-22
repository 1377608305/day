package com.example.myapplication.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.interfaces.IPresenterImpl;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity<P extends IPresenterImpl>  extends AppCompatActivity implements BaseView{
    protected P basePresenter;
     Unbinder bind;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        bind = ButterKnife.bind(this);
        initView();
        basePresenter=initPresenter();
        if (basePresenter!=null){
            basePresenter.attachView(this);
            initData();
        }

    }
    protected abstract P initPresenter();
    protected abstract void initData();
    protected abstract void initView();
    protected abstract int getLayout();


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (bind!=null){
            bind.unbind();
        }
        if (basePresenter!=null){
            basePresenter.detachView();
        }
    }
    @Override
    public void showTips(String errorMsg) {

    }
}
