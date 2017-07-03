package com.wontak.boilerplate;

import com.wontak.boilerplate.Timber.DebugTree;

import timber.log.Timber;

public class AndroidApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        Timber.plant(new DebugTree());
    }
}
