package com.wontak.boilerplate.presentation.ui.activities;

import android.os.Bundle;

import com.wontak.boilerplate.R;
import com.wontak.boilerplate.base.BaseActivity;
import com.wontak.boilerplate.di.HasComponent;
import com.wontak.boilerplate.di.components.DaggerUserComponent;
import com.wontak.boilerplate.di.components.UserComponent;
import com.wontak.boilerplate.di.modules.UserModule;
import com.wontak.boilerplate.presentation.ui.fragments.MainFragment;

import butterknife.ButterKnife;

public class MainActivity extends BaseActivity
        implements HasComponent<UserComponent>
{
    private MainFragment fragment;

    private UserComponent userComponent;

    @Override
    public UserComponent getComponent()
    {
        return userComponent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        initializeInjector();
        initializeFragment();
    }

    private void initializeInjector()
    {
        userComponent = DaggerUserComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .userModule(new UserModule())
                .build();
    }

    private void initializeFragment()
    {
        fragment = MainFragment.newInstance();
        addFragment(R.id.content_main, fragment);
    }
}
