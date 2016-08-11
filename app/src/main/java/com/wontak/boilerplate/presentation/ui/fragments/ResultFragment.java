package com.wontak.boilerplate.presentation.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wontak.boilerplate.base.BaseFragment;
import com.wontak.boilerplate.databinding.ResultFragmentBinding;
import com.wontak.boilerplate.di.components.UserComponent;
import com.wontak.boilerplate.domain.models.User;
import com.wontak.boilerplate.presentation.models.RepositoryItem;
import com.wontak.boilerplate.presentation.presenters.ResultPresenter;
import com.wontak.boilerplate.presentation.ui.activities.ResultActivity;
import com.wontak.boilerplate.presentation.ui.adapters.RepositoriesAdapter;
import com.wontak.boilerplate.presentation.ui.listeners.ResultView;

import java.util.List;

import javax.inject.Inject;

public class ResultFragment extends BaseFragment
    implements ResultView.View
{
    @Inject
    ResultPresenter presenter;

    private ResultFragmentBinding binding;
    private RepositoriesAdapter adapter;

    public static ResultFragment newInstance(String username)
    {
        ResultFragment fragment = new ResultFragment();

        Bundle args = new Bundle();
        args.putString(ResultActivity.KEY_USERNAME, username);
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
    public void onDestroy()
    {
        super.onDestroy();
        presenter.destroy();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        binding = ResultFragmentBinding.inflate(inflater, container, false);

        initializeRecyclerView();

        return binding.getRoot();
    }

    private void initializeRecyclerView()
    {
        adapter = new RepositoriesAdapter(this);

        binding.rvRepositories.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.rvRepositories.setAdapter(adapter);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        showLoading();

        presenter.setView(this);
        presenter.getUser(getUsername());
    }

    private String getUsername()
    {
        return getArguments().getString(ResultActivity.KEY_USERNAME);
    }

    @Override
    public Context context()
    {
        return getActivity().getApplicationContext();
    }

    @Override
    public void onRepositoryClick(RepositoryItem repositoryItem)
    {
        ((ResultActivity) getActivity()).launchWebActivity(repositoryItem.htmlUrl);
    }

    @Override
    public void showUser(User user)
    {
        presenter.getUserRepositories(getUsername());
    }

    @Override
    public void showRepositories(List<RepositoryItem> repositoryItems)
    {
        adapter.addNewRepositoryItems(repositoryItems);
    }

    @Override
    public void showLoading()
    {
        binding.progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading()
    {
        binding.progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showError(String message)
    {
        showToastMessage(message);
    }
}
