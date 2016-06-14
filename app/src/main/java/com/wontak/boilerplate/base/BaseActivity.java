package com.wontak.boilerplate.base;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import com.wontak.boilerplate.BaseApplication;
import com.wontak.boilerplate.di.components.ApplicationComponent;
import com.wontak.boilerplate.di.modules.ActivityModule;

import javax.inject.Inject;

import rx.subscriptions.CompositeSubscription;

public abstract class BaseActivity extends AppCompatActivity
{
    @Inject RxBus bus;

    private CompositeSubscription subscription;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        getApplicationComponent().inject(this);
    }

    @Override
    public void onStart()
    {
        super.onStart();

        subscription = new CompositeSubscription();
        subscription.add(bus.toObservable().subscribe(event -> onEvent(event)));
    }

    @Override
    public void onStop()
    {
        super.onStop();

        subscription.clear();
    }

    protected void addFragment(int containerViewId, Fragment fragment)
    {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(containerViewId, fragment);
        fragmentTransaction.commit();
    }

    protected ApplicationComponent getApplicationComponent()
    {
        return ((BaseApplication)getApplication()).getApplicationComponent();
    }

    protected ActivityModule getActivityModule()
    {
        return new ActivityModule(this);
    }

    public void post(Object event)
    {
        if (bus.hasObservers() == true)
            bus.post(event);
    }

    public void onEvent(Object event)
    {

    }
}
