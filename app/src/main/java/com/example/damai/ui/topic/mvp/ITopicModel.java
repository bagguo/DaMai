package com.example.damai.ui.topic.mvp;

import com.example.damai.ui.topic.TopicBean;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by guodazhao on 2018/2/1 0001.
 * mvp:
 * model:加载数据
 */

public interface ITopicModel {

    Observable<List<TopicBean>> getDataFromNet(String path);


    Observable<List<TopicBean>> getDataFromCache(String path);


    boolean isTimeOut(String path, long timeOut);
}
