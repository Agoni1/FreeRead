package com.agoni.freeread;

import android.app.Application;

import com.yolanda.nohttp.NoHttp;

/**
 * Created by Agoni on 2016/12/22.
 */

public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        NoHttp.initialize(this);
    }
}
