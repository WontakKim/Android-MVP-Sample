package com.wontak.boilerplate;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.wontak.boilerplate.di.components.ApplicationComponent;
import com.wontak.boilerplate.di.components.DaggerApplicationComponent;
import com.wontak.boilerplate.di.modules.ApplicationModule;

public class BaseApplication extends Application
{
    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate()
    {
        super.onCreate();

        initializeInjector();
        initializeFresco();
    }

    private void initializeInjector()
    {
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    private void initializeFresco()
    {
        Fresco.initialize(this);
    }

    public ApplicationComponent getApplicationComponent()
    {
        return applicationComponent;
    }
}
