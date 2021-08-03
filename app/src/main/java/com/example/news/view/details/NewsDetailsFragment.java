package com.example.news.view.details;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.news.R;
import com.example.news.data.entities.NewsEntity;
import com.example.news.view.MainActivity;
import com.example.news.utils.Constants;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;


public class NewsDetailsFragment extends Fragment {


    @BindView(R.id.image)
    public SimpleDraweeView image;
    @BindView(R.id.title_des)
    public TextView title;
    @BindView(R.id.description_des)
    public TextView description;
    @BindView(R.id.created_by_des)
    public TextView createdBy;
    @BindView(R.id.source_des)
    public TextView source;
    @BindView(R.id.toolbar)
    public Toolbar toolbar;
    private NewsEntity newsEntity;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        newsEntity = getArguments().getParcelable(Constants.ARTICLE);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_articles_details, container, false);
        ButterKnife.bind(this, view);
        setupToolbar();
        setViewsData();
        return view;
    }

    private void setupToolbar() {
        ((MainActivity) getActivity()).setSupportActionBar(toolbar);
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
    }

    private void setViewsData() {
        image.setImageURI(newsEntity.getMedia().get(0).getMediaMetadata().get(2).getUrl());
        title.setText(newsEntity.getTitle());
        description.setText(newsEntity.getAbstract());
        createdBy.setText(newsEntity.getByline());
        source.setText(newsEntity.getSource());
    }


}
