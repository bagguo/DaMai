package com.example.damai.cache;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * Created by guodazhao on 2018/2/2 0002.
 *
 */

public class DiskFunction implements Function<String,String>{
    @Override
    public String apply(@NonNull String s) throws Exception {
        return DiskCache.getInstance().get(s);
    }
}
