package com.example.damai.utils;

import android.os.Environment;

import com.example.damai.application.App;

import java.io.File;

/**
 * Created by guodazhao on 2018/2/1 0001.
 * 文件工具类
 * 得到缓存的根文件夹后在文件夹内新建json的缓存文件
 */

public class FileUtils {

    //得到缓存的根文件夹
    private static File getCacheRootFiloder(){
        if (Environment.isExternalStorageEmulated()) {
            return App.getContext().getExternalCacheDir();
        }else {
            return App.getContext().getCacheDir();
        }
    }

    //得到json缓存文件夹
    public static File getJsonCacheFolder(){
        File rootFiloder = getCacheRootFiloder();
        File cache = new File(rootFiloder, "json");
        if (!cache.exists()) {
            cache.mkdirs();
        }
        return cache;
    }
}
