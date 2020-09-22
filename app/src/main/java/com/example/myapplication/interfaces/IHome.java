package com.example.myapplication.interfaces;

import com.example.myapplication.base.BaseView;
import com.example.myapplication.bean.HomeBean;

import java.util.List;

public interface IHome {


    interface View extends BaseView{
        void getData(List<HomeBean.HomeListBean> homeBean);
    }

    interface IPresenter extends IPresenterImpl<View>{
        void getData();
    }
}
