package com.example.myapplication.model.http;

import android.util.Log;

import com.example.myapplication.commit.Constants;
import com.example.myapplication.model.api.IHomeApi;
import com.example.myapplication.util.SystemUtils;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpMessage {
    private static HttpMessage httpMessage;


    public static HttpMessage getInstance(){
        if (httpMessage==null){
            synchronized (HttpMessage.class){
                if (httpMessage==null){
                    httpMessage=new HttpMessage();
                }
            }
        }
        return httpMessage;
    }
    private static Retrofit getRetrofit(String url){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(url)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(getokHttpClient())
                .build();
        return retrofit;

    }

    private static OkHttpClient getokHttpClient() {
        File file = new File(Constants.PATH_CACHE);
        Cache cache = new Cache(file, 1024 * 1024 * 100);
        OkHttpClient builder = new OkHttpClient.Builder()
                .cache(cache)
                .addInterceptor(new LoggingInterceptor())
                .addNetworkInterceptor(new NetInterceptor())
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(10,TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build();
        return builder;

    }


    /**
     * 日志的拦截器打印报文信息
     */
    static class LoggingInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            long t1 = System.nanoTime();
            Log.i("interceptor",String.format("Sending request %s on %s%n%s",request.url(),chain.connection(),request.headers()));

            Response response = chain.proceed(request);
            long t2 = System.nanoTime();
            Log.i("Received:",String.format("Received response for %s in %.1fms%n%s",response.request().url(),(t2-t1)/1e6d,response.headers()));
            if(response.header("session_id") != null){
                //Constant.session_id = response.header("session_id");
            }
            return response;
        }
    }

    static class NetInterceptor implements Interceptor{

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            if(!SystemUtils.checkNetWork()){
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build();
            }
            Response response = chain.proceed(request);
            //通过判断网络连接是否存在获取本地或者服务器的数据
            if(!SystemUtils.checkNetWork()){
                int maxAge = 0;
                return response.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control","public ,max-age="+maxAge).build();
            }else{
                int maxStale = 60*60*24*28; //设置缓存数据的保存时间
                return response.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control","public, onlyif-cached, max-stale="+maxStale).build();
            }
        }
    }

    IHomeApi iHomeApi;
    public IHomeApi getiHomeApi(){
        if (iHomeApi==null){
            iHomeApi=getRetrofit(Constants.Base_Url).create(IHomeApi.class);
        }
        return iHomeApi;
    }
}
