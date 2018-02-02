package com.example.damai.db;

import com.example.damai.application.App;
import com.example.damai.ui.topic.DaoMaster;
import com.example.damai.ui.topic.DaoSession;
import com.example.damai.ui.topic.TopicBeanDao;

/**
 * Created by guodazhao on 2018/2/2 0002.
 */

public class SQLManager {
    private static SQLManager instance;
    private static DaoSession daoSession;

    public static SQLManager getInstance() {
        if (instance == null) {
            synchronized (SQLManager.class) {
                if (instance == null) {
                    instance = new SQLManager();
                }
            }
        }
        return instance;
    }

    public SQLManager() {
        DaoMaster.DevOpenHelper openHelper = new DaoMaster.DevOpenHelper(App.getContext(), "data.db");
        DaoMaster master = new DaoMaster(openHelper.getWritableDb());
        daoSession = master.newSession();
    }

    public TopicBeanDao getTopicDao(){
        return daoSession.getTopicBeanDao();
    }
}
