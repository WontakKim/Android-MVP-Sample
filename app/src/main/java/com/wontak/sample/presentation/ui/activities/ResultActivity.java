package com.wontak.sample.presentation.ui.activities;

import android.content.Intent;
import android.os.Bundle;

import com.wontak.sample.R;
import com.wontak.sample.base.BaseActivity;
import com.wontak.sample.di.HasComponent;
import com.wontak.sample.di.components.DaggerUserComponent;
import com.wontak.sample.di.components.UserComponent;
import com.wontak.sample.di.modules.UserModule;
import com.wontak.sample.presentation.ui.fragments.ResultFragment;

import butterknife.ButterKnife;

public class ResultActivity extends BaseActivity implements HasComponent<UserComponent> {

    public static final String KEY_USERNAME = "username";

    private ResultFragment fragment;

    private UserComponent userComponent;

    @Override
    public UserComponent getComponent() {
        return userComponent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        ButterKnife.bind(this);

        initializeInjector();
        initializeFragment();
    }

    private void initializeInjector() {
        userComponent = DaggerUserComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .userModule(new UserModule())
                .build();
    }

    private void initializeFragment() {
        String username = getIntent().getExtras().getString(KEY_USERNAME);
        fragment = ResultFragment.newInstance(username);
        addFragment(R.id.content_main, fragment);
    }


    public void launchWebActivity(String url) {
        Intent intent = new Intent(this, WebActivity.class);
        intent.putExtra(WebActivity.KEY_URL, url);
        startActivity(intent);
    }
}