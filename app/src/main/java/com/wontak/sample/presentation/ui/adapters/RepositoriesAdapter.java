package com.wontak.sample.presentation.ui.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wontak.sample.R;
import com.wontak.sample.presentation.models.RepositoryItem;
import com.wontak.sample.presentation.ui.listeners.ResultView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RepositoriesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements ResultView.RecyclerViewClickListener {

    private List<RepositoryItem> repositoryItems = new ArrayList();
    private ResultView.View view;

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.label_name)
        TextView nameLabel;

        private ResultView.RecyclerViewClickListener listener;

        public ViewHolder(View v, ResultView.RecyclerViewClickListener listener) {
            super(v);

            ButterKnife.bind(this, v);
            v.setOnClickListener(this);
            this.listener = listener;
        }

        public void setup(RepositoryItem item) {
            nameLabel.setText(item.name);
        }

        @Override
        public void onClick(View v) {
            listener.onViewClick(getAdapterPosition());
        }
    }

    public RepositoriesAdapter(ResultView.View view) {
        this.view = view;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.item_repository, parent, false);
        return new ViewHolder(view, this);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        RepositoryItem repositoryItem = repositoryItems.get(position);
        ((ViewHolder) holder).setup(repositoryItem);
    }

    @Override
    public int getItemCount() {
        return repositoryItems.size();
    }

    @Override
    public void onViewClick(int index) {
        view.onRepositoryClick(repositoryItems.get(index));
    }

    public void addNewRepositoryItems(@NonNull List<RepositoryItem> repositoryItems) {
        // clean up old data
        if (repositoryItems != null)
            this.repositoryItems.clear();

        this.repositoryItems = repositoryItems;

        notifyDataSetChanged();
    }
}
