package com.example.myapplication.util;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class UtilGlideImg {
    public static void setGlideImage(Context context, String url, ImageView img){
        if (!TextUtils.isEmpty(url)&&img!=null){
            Glide.with(context).load(url).into(img);
        }

    }
    public static void setImg(Context context, String url, ImageView img){
        if (!TextUtils.isEmpty(url)&&img!=null){
            RequestOptions requestOptions = RequestOptions.bitmapTransform(new CircleTransform(context));
            Glide.with(context).load(url).apply(requestOptions).into(img);
        }
    }
}
