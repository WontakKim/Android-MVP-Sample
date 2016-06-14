package com.wontak.boilerplate;

import android.app.Application;

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
    }

    private void initializeInjector()
    {
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent()
    {
        return applicationComponent;
    }
}
