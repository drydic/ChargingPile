package com.tiangong.chargingPile.application;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

/**
 * Created by Administrator on 2017/11/20.
 */

public class MultiDexApplication extends Application {
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
