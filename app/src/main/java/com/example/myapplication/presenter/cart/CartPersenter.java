package com.example.myapplication.presenter.cart;



import com.example.myapplication.base.BasePresenter;
import com.example.myapplication.bean.AddCartInfoBean;
import com.example.myapplication.bean.GoodDetailBean;
import com.example.myapplication.commit.CommonSubscriber;
import com.example.myapplication.interfaces.ICart;
import com.example.myapplication.model.http.HttpMessage;
import com.example.myapplication.util.RxUtils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.functions.Function;

public class CartPersenter extends BasePresenter<ICart.IView> implements ICart.IPersenter {
    @Override
    public void getGoodDetail(int id) {
        addDisposable(HttpMessage.getInstance().getiHomeApi().getGoodDetail(id)
                .compose(RxUtils.<GoodDetailBean>rxScheduler())
                .subscribeWith(new CommonSubscriber<GoodDetailBean>(mView) {
                    @Override
                    public void onNext(GoodDetailBean result) {
                        mView.getGoodDetailReturn(result);
                    }
                }));
    }

    @Override
    public void addCart(int goodsId, int number, int productId) {
        addDisposable(HttpMessage.getInstance().getiHomeApi().addCart(goodsId,number,productId)
                .compose(RxUtils.<AddCartInfoBean>rxScheduler())
                .subscribeWith(new CommonSubscriber<AddCartInfoBean>(mView) {
                    @Override
                    public void onNext(AddCartInfoBean result) {
                        mView.addCartInfoReturn(result);
                    }
                }));
    }

}
