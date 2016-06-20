package com.wontak.boilerplate.presentation.presenters;

import com.wontak.boilerplate.di.PerActivity;
import com.wontak.boilerplate.presentation.ui.listeners.MainView;

import javax.inject.Inject;

@PerActivity
public class MainPresenter
{
    private MainView.View view;

    @Inject
    public MainPresenter()
    {

    }

    public void setView(MainView.View view)
    {
        this.view = view;
    }
}
