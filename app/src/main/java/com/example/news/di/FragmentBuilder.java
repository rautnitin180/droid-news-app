package com.example.news.di;

import com.example.news.view.master.NewsFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public interface FragmentBuilder {

    @ContributesAndroidInjector
    NewsFragment getArticlesFragment();
}
