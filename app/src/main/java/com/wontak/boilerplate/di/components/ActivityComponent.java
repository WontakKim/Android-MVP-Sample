package com.wontak.boilerplate.di.components;

import android.app.Activity;

import com.wontak.boilerplate.di.PerActivity;
import com.wontak.boilerplate.di.modules.ActivityModule;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    Activity activity();
}
