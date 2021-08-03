package com.example.news.view.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.example.news.domain.NewsUseCase;

import javax.inject.Inject;

public class ViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private NewsUseCase newsUseCase;

    @Inject
    public ViewModelFactory(NewsUseCase newsUseCase) {
        this.newsUseCase = newsUseCase;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new NewsViewModel(newsUseCase);
    }
}
