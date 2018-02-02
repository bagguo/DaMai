package com.example.damai.utils;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.example.damai.application.App;

/**
 * Created by guodazhao on 2018/2/1 0001.
 * 得到app的包名、版本码
 */

public class PackageUtils {
    public static int getVersionCode(){
        PackageManager pm=App.getContext().getPackageManager();
        try {
            PackageInfo pi = pm.getPackageInfo(App.getContext().getPackageName(), 0);
            return pi.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 1;
    }
}
