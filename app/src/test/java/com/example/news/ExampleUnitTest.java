package com.example.news;

import com.example.news.data.entities.NewsResponse;
import com.example.news.data.services.ApiService;
import com.google.gson.Gson;

import org.junit.Before;
import org.junit.Test;

import io.reactivex.functions.Predicate;
import io.reactivex.observers.TestObserver;
import retrofit2.Retrofit;

public class ExampleUnitTest {
    public ApiService apiService;
    public TestObserver testObserver = new TestObserver<NewsResponse>();
    public Retrofit retrofit;

    public TestUtils testUtils;

    @Before
    public void before() {
        testUtils = new TestUtils();
        retrofit = testUtils.buildRetrofit();
        apiService = retrofit.create(ApiService.class);
    }

    @Test
    public void testResponse() {
        apiService.getArticles(TestConstants.API_KEY).subscribe(testObserver);
        testObserver.assertValue(new Predicate() {
            @Override
            public boolean test(Object t) throws Exception {
                NewsResponse response = testUtils.getArticles();
                NewsResponse testedResponse = (NewsResponse) t;
                String json = new Gson().toJson(t);
                return testedResponse.getCopyright().equals(response.getCopyright());
            }
        });
    }
}