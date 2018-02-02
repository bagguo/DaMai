package com.example.damai.ui.topic.mvp;

import com.alibaba.fastjson.JSONObject;
import com.example.damai.cache.DiskCache;
import com.example.damai.cache.DiskFunction;
import com.example.damai.net.NetFunction;
import com.example.damai.ui.topic.TopicBean;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by guodazhao on 2018/2/2 0002.
 */

public class TopicModelDiskImpl implements ITopicModel {
    @Override
    public Observable<List<TopicBean>> getDataFromNet(String path) {
        return Observable.just(path)
                .map(new NetFunction(true))
                .map(new TopicModelDiskImpl.TopicFunction())//磁盘缓存
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<List<TopicBean>> getDataFromCache(String path) {
        return Observable.just(path)
                .map(new DiskFunction())
                .map(new TopicModelDiskImpl.TopicFunction())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public boolean isTimeOut(String path, long timeOut) {
        return DiskCache.getInstance().isTimeOut(path,timeOut);
    }

    public static class TopicFunction implements Function<String,List<TopicBean>>{

        @Override
        public List<TopicBean> apply(@NonNull String s) throws Exception {
            //JSON 解析
            JSONObject object = JSONObject.parseObject(s);
            String list = object.getString("l");
            return JSONObject.parseArray(list, TopicBean.class);
        }
    }
}
