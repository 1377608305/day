package com.example.myapplication.interfaces;

import com.example.myapplication.base.BaseView;

public interface IPresenterImpl <V extends BaseView>  {
    void attachView(V v);
    void detachView();
}
