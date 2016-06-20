package com.wontak.boilerplate.di.modules;

import android.app.Application;
import android.content.Context;

import com.wontak.boilerplate.presentation.ui.listeners.RxBus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule
{
    private final Application application;

    public ApplicationModule(Application application)
    {
        this.application = application;
    }

    @Provides
    @Singleton
    public Application provideApplication()
    {
        return application;
    }

    @Provides
    @Singleton
    public Context provideContext()
    {
        return application.getApplicationContext();
    }

    @Provides
    @Singleton
    public RxBus provideRxBus()
    {
        return new RxBus();
    }
}
