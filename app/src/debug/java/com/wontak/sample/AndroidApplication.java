package com.wontak.sample;

import com.wontak.sample.Timber.DebugTree;

import butterknife.ButterKnife;
import timber.log.Timber;

public class AndroidApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        Timber.plant(new DebugTree());
        ButterKnife.setDebug(true);
    }
}
