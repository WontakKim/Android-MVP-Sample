package com.wontak.boilerplate.di.components;

import com.wontak.boilerplate.di.PerActivity;
import com.wontak.boilerplate.di.modules.ActivityModule;
import com.wontak.boilerplate.di.modules.UserModule;
import com.wontak.boilerplate.presentation.ui.fragments.ResultFragment;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = { ActivityModule.class, UserModule.class })
public interface UserComponent
{
    void inject(ResultFragment fragment);
}
