package com.wontak.boilerplate.presentation.ui.activities;

import android.os.Bundle;
import android.webkit.WebView;

import com.wontak.boilerplate.R;
import com.wontak.boilerplate.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

public class WebActivity extends BaseActivity
{
    public static final String KEY_URL = "url";

    @Bind(R.id.webview)
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_activity);

        ButterKnife.bind(this);

        initializeWebView();
    }

    private void initializeWebView()
    {
        webView.loadUrl(getUrl());
    }

    private String getUrl()
    {
        return getIntent().getExtras().getString(KEY_URL);
    }
}
