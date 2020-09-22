package com.example.myapplication.interfaces;


import com.example.myapplication.base.BaseView;
import com.example.myapplication.bean.AddCartInfoBean;
import com.example.myapplication.bean.CartBean;
import com.example.myapplication.bean.DeleteCartBean;
import com.example.myapplication.bean.GoodDetailBean;

public interface ICart {
    interface IView extends BaseView {

        void getGoodDetailReturn(GoodDetailBean result);

        void addCartInfoReturn(AddCartInfoBean result);
    }

    interface IPersenter extends IPresenterImpl<IView>{
        void getGoodDetail(int id);
        void addCart(int goodsId,int number,int productId);
    }

    interface ICartView extends BaseView{
        void getCartListReturn(CartBean result);

        void deleteCartListReturn(DeleteCartBean result);
    }

    interface ICartPersenter extends IPresenterImpl<ICartView>{

        //获取购物车的数据
        void getCartList();

        //删除购物车数据
        void deleteCartList(String productIds);

    }
}
