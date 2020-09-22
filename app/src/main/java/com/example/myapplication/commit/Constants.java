package com.example.myapplication.commit;

import com.example.myapplication.appliaction.MyApp;

import java.io.File;

public class Constants {
    public static final String baseUrl="http://169.254.96.147:8085/";
    public static final String Base_Url = "http://cdwan.cn/api/";

    //缓存地址
    public static final String PATH_DATA = MyApp.app.getCacheDir().getAbsolutePath() + File.separator + "data";
    public static final String PATH_CACHE = PATH_DATA + "/app";
}
