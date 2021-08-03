package com.example.news.di;


import com.example.news.di.scopes.PerActivity;
import com.example.news.data.services.ApiService;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class ApiModule {

    @Provides
    @PerActivity
    public ApiService getService(Retrofit retrofit) {
        return retrofit.create(ApiService.class);
    }


}
