package com.example.news.di.component;

import android.app.Application;

import com.example.news.MyApplication;
import com.example.news.di.ActivityBuilder;
import com.example.news.di.modules.ContextModule;
import com.example.news.di.modules.NetworkModule;
import com.example.news.di.modules.PicassoModule;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {AndroidSupportInjectionModule.class, NetworkModule.class, ActivityBuilder.class, PicassoModule.class, ContextModule.class})
public interface AppComponent {
    void inject(MyApplication app);


    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        @BindsInstance
        Builder url(@Named("url") String url);

        AppComponent build();
    }

}
