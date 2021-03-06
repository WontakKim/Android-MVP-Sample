package com.wontak.sample.presentation.ui.activities;

import android.content.Intent;
import android.os.Bundle;

import com.wontak.sample.R;
import com.wontak.sample.base.BaseActivity;
import com.wontak.sample.presentation.ui.fragments.MainFragment;

public class MainActivity extends BaseActivity {

    private MainFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeFragment();
    }

    private void initializeFragment() {
        fragment = MainFragment.newInstance();
        addFragment(R.id.content_main, fragment);
    }

    public void launchResultActivity(String username) {
        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra(ResultActivity.KEY_USERNAME, username);
        startActivity(intent);
    }
}
