package com.wontak.boilerplate;

import com.wontak.boilerplate.Timber.ReleaseTree;

import timber.log.Timber;

public class AndroidApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        // Crashlytics
        // Fabric.with(this, new Crashlytics());

        Timber.plant(new ReleaseTree());
    }
}
