package com.example.news.di;

import com.example.news.di.scopes.PerActivity;
import com.example.news.view.master.NewsFragment;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {
    @Provides
    @PerActivity
    public NewsFragment provideArticleFragment() {
        return new NewsFragment();
    }


}
