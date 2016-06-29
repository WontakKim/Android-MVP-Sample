package com.wontak.boilerplate.presentation.ui.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.wontak.boilerplate.R;
import com.wontak.boilerplate.base.BaseActivity;
import com.wontak.boilerplate.di.HasComponent;
import com.wontak.boilerplate.di.components.DaggerUserComponent;
import com.wontak.boilerplate.di.components.UserComponent;
import com.wontak.boilerplate.di.modules.UserModule;
import com.wontak.boilerplate.presentation.ui.fragments.MainFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity
        implements HasComponent<UserComponent>
{
    @Bind(R.id.toolbar)
    Toolbar toolbar;

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
        initializeToolbar();
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

    private void initializeToolbar()
    {
        toolbar.setTitleTextColor(Color.WHITE);

        setSupportActionBar(toolbar);
    }

    private void initializeFragment()
    {
        fragment = MainFragment.newInstance();
        addFragment(R.id.content_main, fragment);
    }
}
