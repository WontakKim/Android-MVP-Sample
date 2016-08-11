package com.wontak.boilerplate.presentation.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wontak.boilerplate.base.BaseFragment;
import com.wontak.boilerplate.databinding.MainFragmentBinding;
import com.wontak.boilerplate.presentation.ui.activities.MainActivity;

public class MainFragment extends BaseFragment
{
    private MainFragmentBinding binding;

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
        binding = MainFragmentBinding.inflate(inflater, container, false);
        binding.btnSearch.setOnClickListener(view -> onSearchClick());
        return binding.getRoot();
    }

    public void onSearchClick()
    {
        String username = binding.inputUsername.getText().toString();
        ((MainActivity) getActivity()).launchResultActivity(username);
    }
}
