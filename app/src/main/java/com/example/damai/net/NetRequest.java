package com.example.damai.net;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

import static com.bumptech.glide.gifdecoder.GifHeaderParser.TAG;

/**
 * Created by guodazhao on 2018/1/31 0031.
 * 拦截器
 * 客户端
 */

public class NetRequest {


    private static OkHttpClient client;


    public static OkHttpClient getHttpClient(){
        if (client!=null) {
            return client;
        }

        synchronized (NetRequest.class){
            if (client!=null) {
                return client;
            }

            HttpLoggingInterceptor interceptor=new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger(){
                @Override
                public void log(String message) {
                    Log.i(TAG, "log: =========="+message);
                }
            });
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            client = new OkHttpClient.Builder()
                    .connectTimeout(20, TimeUnit.SECONDS)
                    .writeTimeout(20, TimeUnit.SECONDS)
                    .readTimeout(20, TimeUnit.SECONDS)
                    .addInterceptor(interceptor)
                    .addInterceptor(new BasicParamsInterceptor())
                    .build();

        }

        return client;
    }
}
