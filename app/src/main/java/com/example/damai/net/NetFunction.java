package com.example.damai.net;

import com.example.damai.cache.DiskCache;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by guodazhao on 2018/1/31 0031.
 * 网络请求
 */

public class NetFunction implements Function<String, String> {
    private boolean diskCacheable;

    public NetFunction(boolean diskCacheable) {
        this.diskCacheable = diskCacheable;
    }

    @Override
    public String apply(@NonNull String s) throws Exception {


        OkHttpClient client = NetRequest.getHttpClient();
        Request request = new Request.Builder()
                .url(s)
                .build();
        Response response = client.newCall(request).execute();
        String json=response.body().string();

        //写入本地缓存
        DiskCache.getInstance().set(s, json);
        return json;


    }
}
