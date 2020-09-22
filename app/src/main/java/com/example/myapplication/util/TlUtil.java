package com.example.myapplication.util;

import android.text.TextUtils;
import android.widget.TextView;

import org.w3c.dom.Text;

public class TlUtil {
    public static void setText(TextView tl,String url){
        if (!TextUtils.isEmpty(url)){
            tl.setText(url);
        }


    }
    public static void setText(TextView tl, int url){
        tl.setText(String.valueOf(url));
    }
    public static void setText(TextView tl,float url){
        tl.setText(String.valueOf(url));
    }

}
