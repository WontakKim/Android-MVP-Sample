package com.wontak.boilerplate.presentation.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.wontak.boilerplate.R;
import com.wontak.boilerplate.base.BaseFragment;
import com.wontak.boilerplate.di.components.UserComponent;
import com.wontak.boilerplate.domain.models.User;
import com.wontak.boilerplate.presentation.models.RepositoryItem;
import com.wontak.boilerplate.presentation.presenters.ResultPresenter;
import com.wontak.boilerplate.presentation.ui.activities.ResultActivity;
import com.wontak.boilerplate.presentation.ui.adapters.RepositoriesAdapter;
import com.wontak.boilerplate.presentation.ui.listeners.ResultView;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ResultFragment extends BaseFragment
    implements ResultView.View
{
    @Bind(R.id.rv_repositories)
    RecyclerView recyclerView;

    @Bind(R.id.progress)
    ProgressBar progressBar;

    @Inject
    ResultPresenter presenter;

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
        View fragmentView = inflater.inflate(R.layout.fragment_result, container, false);

        ButterKnife.bind(this, fragmentView);

        initializeRecyclerView();

        return fragmentView;
    }

    private void initializeRecyclerView()
    {
        adapter = new RepositoriesAdapter(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        ButterKnife.unbind(this);
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
        ((ResultActivity) getActivity()).launchResultActivity(repositoryItem.htmlUrl);
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
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading()
    {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showError(String message)
    {
        showToastMessage(message);
    }
}
