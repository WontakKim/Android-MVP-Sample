package com.wontak.boilerplate.di.components;

import com.wontak.boilerplate.base.BaseActivity;
import com.wontak.boilerplate.di.modules.ApplicationModule;
import com.wontak.boilerplate.domain.repositories.GithubRepository;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent
{
    void inject(BaseActivity activity);

    GithubRepository githubRepository();
}
