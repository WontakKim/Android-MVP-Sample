package com.wontak.boilerplate.presentation.ui.activities;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

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
        setContentView(R.layout.activity_web);

        ButterKnife.bind(this);

        initializeWebView();
    }

    private void initializeWebView()
    {
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(getUrl());
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        webView.removeAllViews();
        webView.destroy();
    }

    @Override
    public void onBackPressed()
    {
        if (webView.canGoBack())
            webView.goBack();
        else
            finish();
    }

    private String getUrl()
    {
        return getIntent().getExtras().getString(KEY_URL);
    }
}
