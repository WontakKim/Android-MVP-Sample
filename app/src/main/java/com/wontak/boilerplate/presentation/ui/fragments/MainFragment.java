package com.wontak.boilerplate.presentation.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.wontak.boilerplate.R;
import com.wontak.boilerplate.base.BaseFragment;
import com.wontak.boilerplate.presentation.ui.activities.MainActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainFragment extends BaseFragment
{
    @Bind(R.id.input_username)
    EditText usernameInput;

    public static MainFragment newInstance()
    {
        MainFragment fragment = new MainFragment();

        Bundle args = new Bundle();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View fragmentView = inflater.inflate(R.layout.fragment_main, container, false);

        ButterKnife.bind(this, fragmentView);

        return fragmentView;
    }

    @OnClick(R.id.btn_search)
    public void onSearchClick()
    {
        String username = usernameInput.getText().toString();
        ((MainActivity) getActivity()).launchResultActivity(username);
    }
}
