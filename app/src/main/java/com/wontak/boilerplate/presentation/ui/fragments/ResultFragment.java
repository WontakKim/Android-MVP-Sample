package com.wontak.boilerplate.presentation.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.wontak.boilerplate.R;
import com.wontak.boilerplate.base.BaseFragment;
import com.wontak.boilerplate.di.components.UserComponent;
import com.wontak.boilerplate.domain.models.User;
import com.wontak.boilerplate.presentation.presenters.ResultPresenter;
import com.wontak.boilerplate.presentation.ui.activities.ResultActivity;
import com.wontak.boilerplate.presentation.ui.listeners.ResultView;

import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ResultFragment extends BaseFragment
    implements ResultView.View
{
    @Bind(R.id.image_avatar)
    SimpleDraweeView avatarImage;

    @Bind(R.id.label_name)
    TextView nameLabel;

    @Bind(R.id.label_username)
    TextView usernameLabel;

    @Bind(R.id.label_created_at)
    TextView createAtLabel;

    @Bind(R.id.label_email)
    TextView emailLabel;

    @Inject
    ResultPresenter presenter;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View fragmentView = inflater.inflate(R.layout.fragment_result, container, false);

        ButterKnife.bind(this, fragmentView);

        return fragmentView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        presenter.setView(this);
        presenter.getUser(getUsername());
    }

    private String getUsername()
    {
        return getArguments().getString(ResultActivity.KEY_USERNAME);
    }

    @Override
    public void renderUser(User user)
    {
        avatarImage.setImageURI(user.avatarUrl);

        nameLabel.setText(user.name);
        usernameLabel.setText(user.username);
        emailLabel.setText(user.email);

        SimpleDateFormat dateFormat = new SimpleDateFormat("d MMM yyyy", Locale.US);
        String dateStr = dateFormat.format(user.createdAt);
        String createdAtStr = String.format("Joined on %s", dateStr);
        createAtLabel.setText(createdAtStr);
    }
}
