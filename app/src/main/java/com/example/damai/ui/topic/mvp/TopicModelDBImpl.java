package com.example.damai.ui.topic.mvp;

import com.alibaba.fastjson.JSONObject;
import com.example.damai.db.SQLManager;
import com.example.damai.net.NetRequest;
import com.example.damai.ui.topic.TopicBean;
import com.example.damai.ui.topic.TopicBeanDao;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by guodazhao on 2018/2/2 0002.
 */

public class TopicModelDBImpl implements ITopicModel {
    //网络获取
    @Override
    public Observable<List<TopicBean>> getDataFromNet(String path) {
        return Observable.just(path)
                .map(new TopicModelDBImpl.TopicFunctionWithDB())//插入数据库
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    //从数据库读取
    @Override
    public Observable<List<TopicBean>> getDataFromCache(String path) {
        return Observable.just(path)
                .map(new TopicDBFunction())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public boolean isTimeOut(String path, long timeOut) {
        TopicBeanDao topicBeanDao = SQLManager.getInstance().getTopicDao();
        List<TopicBean> list = topicBeanDao.queryBuilder()
                .where(TopicBeanDao.Properties.Key.eq(path))
                .limit(1)
                .list();
        if (list == null || list.size() == 0) {
            return true;//没有缓存数据
        }
        Long time = list.get(0).getTime();
        return System.currentTimeMillis() - time > timeOut;
    }

    //查询
    public static class TopicDBFunction implements Function<String, List<TopicBean>> {

        @Override
        public List<TopicBean> apply(@NonNull String s) throws Exception {
            TopicBeanDao topicBeanDao = SQLManager.getInstance().getTopicDao();
            List<TopicBean> list = topicBeanDao.queryBuilder()
                    .where(TopicBeanDao.Properties.Key.eq(s))
                    .list();
            return list;
        }
    }

    //更新数据
    public static class TopicFunctionWithDB implements Function<String, List<TopicBean>> {

        @Override
        public List<TopicBean> apply(@NonNull String s) throws Exception {

            //网络请求
            OkHttpClient client = NetRequest.getHttpClient();
            Request request = new Request.Builder()
                    .url(s)
                    .build();
            Response response = client.newCall(request).execute();
            String json = response.body().string();

            //JSON 解析
            JSONObject object = JSONObject.parseObject(json);
            String list = object.getString("l");
            List<TopicBean> topicList = JSONObject.parseArray(list, TopicBean.class);

            for (TopicBean topicBean : topicList) {
                topicBean.setKey(s);
                topicBean.setTime(System.currentTimeMillis());//

            }

            TopicBeanDao topicBeanDao = SQLManager.getInstance().getTopicDao();
            //查询
            List<TopicBean> old = topicBeanDao.queryBuilder()
                    .where(TopicBeanDao.Properties.Key.eq(s))
                    .list();
            //删除旧数据
            topicBeanDao.deleteInTx(old);
            topicBeanDao.insertOrReplaceInTx(topicList);//插入数据库

            return topicList;
        }
    }
}
