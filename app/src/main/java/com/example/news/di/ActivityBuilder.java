package com.example.news.di;


import com.example.news.di.scopes.PerActivity;
import com.example.news.view.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public interface ActivityBuilder {


    @PerActivity
    @ContributesAndroidInjector(modules = {ApiModule.class, ActivityModule.class, FragmentBuilder.class})
    MainActivity getMainActivity();
}



