package com.wontak.boilerplate.presentation.ui.adapters;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wontak.boilerplate.databinding.RepositoryItemBinding;
import com.wontak.boilerplate.presentation.models.RepositoryItem;
import com.wontak.boilerplate.presentation.ui.listeners.ResultView;

import java.util.ArrayList;
import java.util.List;

public class RepositoriesAdapter extends RecyclerView.Adapter<RepositoriesAdapter.ViewHolder>
    implements ResultView.RecyclerViewClickListener
{
    private List<RepositoryItem> repositoryItems = new ArrayList();
    private ResultView.View view;

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        public RepositoryItemBinding binding;

        private ResultView.RecyclerViewClickListener listener;

        public ViewHolder(View v, ResultView.RecyclerViewClickListener listener)
        {
            super(v);

            binding = DataBindingUtil.getBinding(v);
            v.setOnClickListener(this);
            this.listener = listener;
        }

        @Override
        public void onClick(View v)
        {
            listener.onViewClick(getAdapterPosition());
        }
    }

    public RepositoriesAdapter(ResultView.View view)
    {
        this.view = view;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        RepositoryItemBinding binding = RepositoryItemBinding.inflate(inflater, parent, false);
        return new ViewHolder(binding.getRoot(), this);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        RepositoryItem repositoryItem = repositoryItems.get(position);
        holder.binding.setRepositoryItem(repositoryItem);
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount()
    {
        return repositoryItems.size();
    }

    @Override
    public void onViewClick(int index)
    {
        view.onRepositoryClick(repositoryItems.get(index));
    }

    public void addNewRepositoryItems(@NonNull List<RepositoryItem> repositoryItems)
    {
        // clean up old data
        if (repositoryItems != null)
            this.repositoryItems.clear();

        this.repositoryItems = repositoryItems;

        notifyDataSetChanged();
    }
}
