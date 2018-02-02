package com.example.damai.application;

import android.app.Application;
import android.content.Context;

/**
 * Created by guodazhao on 2018/2/1 0001.
 * app类提供得到上下文方法
 */

public class App extends Application {

    private static Context context;

    public static Context getContext() {
        return context;
    }
//调用oncreat方法时，将当前类对像赋值给上下文，即上下文为当前类对象

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }
}
