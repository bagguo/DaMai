package com.example.damai.cache;
import com.jakewharton.disklrucache.DiskLruCache;

import com.example.damai.utils.FileUtils;
import com.example.damai.utils.MD5Utils;
import com.example.damai.utils.PackageUtils;

import java.io.File;
import java.io.IOException;

/**
 * Created by guodazhao on 2018/2/1 0001.
 * 磁盘缓存
 */

public class DiskCache {
    //diskcache实例
    private static DiskCache instance;
    //disklrucache
    private DiskLruCache diskLruCache;

    public static DiskCache getInstance() {
        if (instance != null)
            return instance;

        synchronized (DiskCache.class) {
            if (instance != null)
                return instance;

            instance = new DiskCache();

        }
        return instance;
    }

    //构造函数
    private DiskCache() {
        //文件工具类得到json缓存文件夹 作为缓存文件夹
        File cacheFolder = FileUtils.getJsonCacheFolder();
        int versionCode = PackageUtils.getVersionCode();
        //DiskLruCache  1 key   url
//                        v     json    0
        //                 v    timeout 1
        int maxSize = 30 * 1024 * 1024;

        try {
            this.diskLruCache = DiskLruCache.open(cacheFolder, versionCode, 2, maxSize);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 取缓存，得到json
     *
     * @param k
     * @return
     */
    public String get(String k) {

        try {
            DiskLruCache.Snapshot snapshot = diskLruCache.get(getCacheKey(k));
            if (snapshot == null)
                return "";
            String json = snapshot.getString(0);
            return json;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";

    }

    /**
     * 缓存有一定期限（timeOut），过期将不使用缓存内容，进行网络请求
     * @param k
     * @param timeOut 缓存时长
     * @return
     */
    public boolean isTimeOut(String k, long timeOut) {
        try {
            DiskLruCache.Snapshot snapshot = diskLruCache.get(getCacheKey(k));
            if (snapshot == null)
                return true;
            String time = snapshot.getString(1);
            long timeMills = Long.parseLong(time);//字符串解析为long
            return System.currentTimeMillis() - timeMills > timeOut;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 存储，将json进行缓存
     * 存储，记录缓存的时间
     */
    public void set(String k, String v) {
        try {
            DiskLruCache.Editor editor = diskLruCache.edit(getCacheKey(k));
            editor.set(0, v);
            editor.set(1, System.currentTimeMillis() + "");//记录时间
            editor.commit();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getCacheKey(String k) {
        return MD5Utils.hashKeyForDisk(k);
    }

}
