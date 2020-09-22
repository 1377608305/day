package com.example.myapplication.presenter.cart;


import com.example.myapplication.base.BasePresenter;
import com.example.myapplication.bean.CartBean;
import com.example.myapplication.bean.DeleteCartBean;
import com.example.myapplication.commit.CommonSubscriber;
import com.example.myapplication.interfaces.ICart;
import com.example.myapplication.model.http.HttpMessage;
import com.example.myapplication.util.RxUtils;

public class CartListPersenter extends BasePresenter<ICart.ICartView> implements ICart.ICartPersenter {
    @Override
    public void getCartList() {
        addDisposable(HttpMessage.getInstance().getiHomeApi().getCartList()
                .compose(RxUtils.<CartBean>rxScheduler())
                .subscribeWith(new CommonSubscriber<CartBean>(mView) {
                    @Override
                    public void onNext(CartBean result) {
                        mView.getCartListReturn(result);
                    }
                }));
    }

    @Override
    public void deleteCartList(String productIds) {
        addDisposable(HttpMessage.getInstance().getiHomeApi().cartDelete(productIds)
                .compose(RxUtils.<DeleteCartBean>rxScheduler())
                .subscribeWith(new CommonSubscriber<DeleteCartBean>(mView) {
                    @Override
                    public void onNext(DeleteCartBean result) {
                        mView.deleteCartListReturn(result);
                    }
                }));
    }
}
