package com.wontak.sample.di.components;

import com.wontak.sample.di.PerActivity;
import com.wontak.sample.di.modules.ActivityModule;
import com.wontak.sample.di.modules.UserModule;
import com.wontak.sample.presentation.ui.fragments.ResultFragment;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, UserModule.class})
public interface UserComponent {

    void inject(ResultFragment fragment);
}
