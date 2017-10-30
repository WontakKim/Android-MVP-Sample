package com.wontak.sample.presentation.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.wontak.sample.R;
import com.wontak.sample.base.BaseFragment;
import com.wontak.sample.di.components.UserComponent;
import com.wontak.sample.domain.models.User;
import com.wontak.sample.presentation.models.RepositoryItem;
import com.wontak.sample.presentation.presenters.ResultPresenter;
import com.wontak.sample.presentation.ui.activities.ResultActivity;
import com.wontak.sample.presentation.ui.adapters.RepositoriesAdapter;
import com.wontak.sample.presentation.ui.listeners.ResultView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ResultFragment extends BaseFragment implements ResultView.View {

    @BindView(R.id.rv_repositories)
    RecyclerView recyclerView;

    @BindView(R.id.progress)
    ProgressBar progressBar;

    @Inject
    ResultPresenter presenter;

    private RepositoriesAdapter adapter;

    private Unbinder unbinder;

    public static ResultFragment newInstance(String username) {
        ResultFragment fragment = new ResultFragment();

        Bundle args = new Bundle();
        args.putString(ResultActivity.KEY_USERNAME, username);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent(UserComponent.class).inject(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.destroy();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_result, container, false);
        unbinder = ButterKnife.bind(this, view);
        initializeRecyclerView();
        return view;
    }

    private void initializeRecyclerView() {
        adapter = new RepositoriesAdapter(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        showLoading();

        presenter.setView(this);
        presenter.getUser(getUsername());
    }

    private String getUsername() {
        return getArguments().getString(ResultActivity.KEY_USERNAME);
    }

    @Override
    public Context context() {
        return getActivity().getApplicationContext();
    }

    @Override
    public void onRepositoryClick(RepositoryItem repositoryItem) {
        ((ResultActivity) getActivity()).launchWebActivity(repositoryItem.htmlUrl);
    }

    @Override
    public void showUser(User user) {
        presenter.getUserRepositories(getUsername());
    }

    @Override
    public void showRepositories(List<RepositoryItem> repositoryItems) {
        adapter.addNewRepositoryItems(repositoryItems);
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showError(String message) {
        showToastMessage(message);
    }
}
