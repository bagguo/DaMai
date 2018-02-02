package com.example.damai.ui.topic.mvp;

import android.util.Log;

import com.example.damai.cache.DiskObserver;
import com.example.damai.net.NetConfig;
import com.example.damai.net.NetObserver;
import com.example.damai.net.RequestParams;
import com.example.damai.ui.topic.TopicBean;

import java.util.List;

import static com.bumptech.glide.gifdecoder.GifHeaderParser.TAG;

/**
 * Created by guodazhao on 2018/2/1 0001.
 */

public class TopicPresenterDiskImpl implements ITopicPresenter {

    private ITopicModel model;
    private ITopicView view;
    int page;

    public TopicPresenterDiskImpl(ITopicView view) {
        this.view = view;
        model = new TopicModelDiskImpl();
    }

    @Override
    public void loadData() {
        page = 1;
        String url = new RequestParams.Builder()
                .baseUrl(NetConfig.BASE_URL)
                .path("ProjLst.aspx")
                .params("cc", "0")
                .params("ps", "20")
                .params("mc", "0")
                .params("ot", "0")
                .params("v", "0")
                .params("p", page + "")
                .params("cityId", "852")
                .build()
                .generateUrl();
        //1.从本地取数据
        model.getDataFromCache(url).subscribe(new DiskObserver<List<TopicBean>>() {
            @Override
            public void onNext(List<TopicBean> topicBeen) {
                view.fillData(topicBeen, true);
            }
        });

        //2.判断是否超时
        if (!model.isTimeOut(url, 60 * 1000)) {
            view.hideLoading();
            return;
        }
        //3.网络请求
        view.showLoading();
        Log.i(TAG, String.valueOf(Thread.currentThread()));
        model.getDataFromNet(url)
                .subscribe(new NetObserver(view.getContext()) {
                               @Override
                               public void onNext(Object o) {
                                   super.onNext(o);
                                   view.hideLoading();
                                   view.fillData((List<TopicBean>) o, true);
                               }

                               @Override
                               public void onError(Throwable e) {
                                   super.onError(e);
                                   view.hideLoading();
                                   view.showError(e.getMessage());
                               }
                           }
                );

    }

    @Override
    public void addData() {
        page++;
//        view.showLoading();
        Log.i("tag", String.valueOf(Thread.currentThread()));
        String url = new RequestParams.Builder()
                .baseUrl(NetConfig.BASE_URL)
                .path("ProjLst.aspx")
                .params("cc", "0")
                .params("ps", "20")
                .params("mc", "0")
                .params("ot", "0")
                .params("v", "0")
                .params("p", page + "")
                .params("cityId", "852")
                .build()
                .generateUrl();
//1.从本地取数据
        model.getDataFromCache(url).subscribe(new DiskObserver<List<TopicBean>>() {
            @Override
            public void onNext(List<TopicBean> topicBeen) {
                view.fillData(topicBeen, false);
            }
        });
        //2.判断是否超时
        if (!model.isTimeOut(url, 60 * 1000)) {
            view.hideLoading();
            return;
        }
        //3.网络请求
        view.showLoading();
        model.getDataFromNet(url)
                .subscribe(new NetObserver(view.getContext()) {
                    @Override
                    public void onNext(Object o) {
                        super.onNext(o);
                        view.hideLoading();//隐藏加载
                        view.fillData((List<TopicBean>) o, false);
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        view.hideLoading();
                        view.showError(e.getMessage());
                    }
                });
    }
}
