package com.example.news.view.master;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.news.R;
import com.example.news.data.entities.NewsEntity;
import com.example.news.interfaces.ItemClickListener;
import com.example.news.view.MainActivity;
import com.example.news.view.adapters.NewsAdapter;
import com.example.news.view.details.NewsDetailsFragment;
import com.example.news.view.viewmodel.NewsViewModel;
import com.example.news.view.viewmodel.ViewModelFactory;
import com.example.news.utils.Constants;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.AndroidSupportInjection;

public class NewsFragment extends Fragment implements ItemClickListener<NewsEntity> {
    private NewsViewModel mViewModel;
    @BindView(R.id.articles_recycler)
    public RecyclerView articlesRecyclerView;

    @Inject
    public NewsAdapter adapter;

    @Inject
    public ViewModelFactory viewModelFactory;

    @BindView(R.id.toolbar)
    public Toolbar toolbar;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidSupportInjection.inject(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.articles_fragment, container, false);
        ButterKnife.bind(this, view);

        initViews();
        setupToolbar();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(getActivity(), viewModelFactory).get(NewsViewModel.class);
    }

    private void initViews() {
        adapter.setItemClickListenr(this);
        articlesRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        articlesRecyclerView.setAdapter(adapter);
    }

    private void setupToolbar() {
        ((MainActivity) getActivity()).setSupportActionBar(toolbar);
    }

    @Override
    public void onResume() {
        super.onResume();
        loadData();
    }

    private void loadData() {
        mViewModel.loadArticles().observe(this, articleEntities -> adapter.setArticles(articleEntities));
    }

    @Override
    public void onItemClick(int position, NewsEntity model) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(Constants.ARTICLE, model);
        ((MainActivity) getActivity()).replaceCurrentFragment(bundle, new NewsDetailsFragment());
    }
}
