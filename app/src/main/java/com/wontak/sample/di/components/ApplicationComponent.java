package com.wontak.sample.di.components;

import com.wontak.sample.base.BaseActivity;
import com.wontak.sample.di.modules.ApplicationModule;
import com.wontak.sample.domain.repositories.GithubRepository;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(BaseActivity activity);

    GithubRepository githubRepository();
}
