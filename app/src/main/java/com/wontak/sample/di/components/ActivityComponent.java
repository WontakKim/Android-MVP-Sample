package com.wontak.sample.di.components;

import android.app.Activity;

import com.wontak.sample.di.PerActivity;
import com.wontak.sample.di.modules.ActivityModule;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    Activity activity();
}
