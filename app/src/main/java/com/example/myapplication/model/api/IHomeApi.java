package com.example.myapplication.model.api;

import com.example.myapplication.bean.AddCartInfoBean;
import com.example.myapplication.bean.CartBean;
import com.example.myapplication.bean.DeleteCartBean;
import com.example.myapplication.bean.GoodDetailBean;
import com.example.myapplication.bean.HomeBean;

import io.reactivex.Flowable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface IHomeApi {



    @GET("index")
    Flowable<HomeBean> getHomeContent();
    //商品购买页详情
    @GET("goods/detail")
    Flowable<GoodDetailBean> getGoodDetail(@Query("id") int id);

    //添加到购物车
    @POST("cart/add")
    @FormUrlEncoded
    Flowable<AddCartInfoBean> addCart(@Field("goodsId") int goodsId, @Field("number") int number, @Field("productId") int productId);

    @GET("cart/index")
    Flowable<CartBean> getCartList();

    //删除购物车
    @POST("cart/delete")
    @FormUrlEncoded
    Flowable<DeleteCartBean> cartDelete(@Field("productIds") String productIds);
}
