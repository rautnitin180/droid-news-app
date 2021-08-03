package com.example.news.domain;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.example.news.data.entities.NewsEntity;
import com.example.news.data.entities.NewsResponse;
import com.example.news.data.services.ApiService;
import com.example.news.utils.Constants;
import com.example.news.utils.EspressoIdlingResource;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class NewsUseCase {

    private ApiService apiService;
    private CompositeDisposable compositeDisposable;

    @Inject
    public NewsUseCase(ApiService apiService, CompositeDisposable compositeDisposable) {
        this.apiService = apiService;
        this.compositeDisposable = compositeDisposable;
    }

    public LiveData<List<NewsEntity>> getArticles() {
        final MutableLiveData<List<NewsEntity>> data = new MutableLiveData<>();

        EspressoIdlingResource.increment();

        Observable<NewsResponse> articlesResponseObservable = apiService.getArticles(Constants.API_KEY);


        articlesResponseObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<NewsResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(NewsResponse newsResponse) {
                        data.postValue(newsResponse.getArticles());
                    }

                    @Override
                    public void onError(Throwable e) {
                        EspressoIdlingResource.decrement();
                    }

                    @Override
                    public void onComplete() {
                        EspressoIdlingResource.decrement();
                    }
                });

        return data;
    }
}
