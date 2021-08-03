package com.example.news.view.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.news.R;
import com.example.news.data.entities.NewsEntity;
import com.example.news.interfaces.ItemClickListener;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ArticlesViewHolder> {
    private List<NewsEntity> articleEntities = new ArrayList<>();
    private ItemClickListener<NewsEntity> itemClickListener;

    @Inject
    public NewsAdapter() {

    }

    @NonNull
    @Override
    public ArticlesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ArticlesViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.article_recycler_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ArticlesViewHolder holder, int position) {
        NewsEntity newsEntity = articleEntities.get(position);
        holder.tvTitle.setText(newsEntity.getTitle());
        holder.tvCreatedBy.setText(newsEntity.getByline());
        holder.tvSource.setText(newsEntity.getSource());
        holder.tvDate.setText(newsEntity.getPublishedDate());
        holder.image.setImageURI(newsEntity.getMedia().get(0).getMediaMetadata().get(2).getUrl());
    }

    public void setArticles(List<NewsEntity> articleEntities) {
        this.articleEntities = articleEntities;
        notifyDataSetChanged();
    }

    public void setItemClickListenr(ItemClickListener<NewsEntity> itemClickListenr) {
        this.itemClickListener = itemClickListenr;
    }

    @Override
    public int getItemCount() {
        return articleEntities.size();
    }

    class ArticlesViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.title)
        public TextView tvTitle;
        @BindView(R.id.image)
        public SimpleDraweeView image;

        @BindView(R.id.created_by)
        public TextView tvCreatedBy;
        @BindView(R.id.source)
        public TextView tvSource;

        @BindView(R.id.date)
        public TextView tvDate;

        public ArticlesViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(view -> {
                if (itemClickListener != null) {
                    itemClickListener.onItemClick(getAdapterPosition(), articleEntities.get(getAdapterPosition()));
                }
            });

        }
    }

}



