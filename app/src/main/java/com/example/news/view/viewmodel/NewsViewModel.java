package com.example.news.view.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.example.news.data.entities.NewsEntity;
import com.example.news.domain.NewsUseCase;

import java.util.List;

public class NewsViewModel extends ViewModel {

    private NewsUseCase newsUseCase;


    NewsViewModel(NewsUseCase newsUseCase) {
        this.newsUseCase = newsUseCase;
    }

    public LiveData<List<NewsEntity>> loadArticles() {
        return newsUseCase.getArticles();
    }

}
