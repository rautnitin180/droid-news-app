package com.example.news.data.services;

import com.example.news.data.entities.NewsResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("v2/mostviewed/all-sections/7.json")
    Observable<NewsResponse> getArticles(@Query("api-key") String apiKey);

}
