package com.wontak.boilerplate.presentation.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wontak.boilerplate.R;
import com.wontak.boilerplate.base.BaseFragment;
import com.wontak.boilerplate.di.components.UserComponent;
import com.wontak.boilerplate.presentation.presenters.MainPresenter;
import com.wontak.boilerplate.presentation.ui.listeners.MainView;

import javax.inject.Inject;

public class MainFragment extends BaseFragment
    implements MainView.View
{
    @Inject MainPresenter presenter;

    public static MainFragment newInstance()
    {
        MainFragment fragment = new MainFragment();

        Bundle args = new Bundle();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        getComponent(UserComponent.class).inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View fragmentView = inflater.inflate(R.layout.fragment_main, container, false);
        return fragmentView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        presenter.setView(this);
    }
}
