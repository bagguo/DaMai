package com.example.damai.net;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by guodazhao on 2018/2/1 0001.
 * 参数拦截器：将要发出的请求进行拦截，然后改造再请求
 *
 *  原因：每次请求都会带有相同公共参数
 */

public class BasicParamsInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
            //请求
        Request request = chain.request();
        //拦截请求的url
        HttpUrl url=request.url();

        url = url.newBuilder()
                //插入键值对参数到url query中
                .addQueryParameter("source", NetConfig.source)
                .addQueryParameter("appType",NetConfig.appType)
                .addQueryParameter("osType",NetConfig.osType)
                .addQueryParameter("version",NetConfig.version)
                .addQueryParameter("channel_from",NetConfig.channel_from)
                .build();
        //1.构建请求体
        request = request.newBuilder()
                .url(url)
                .build();
        //2.开始，继续请求
        return chain.proceed(request);
    }
}
