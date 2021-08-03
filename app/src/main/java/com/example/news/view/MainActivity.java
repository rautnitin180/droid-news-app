package com.example.news.view;

import android.os.Bundle;
import android.support.test.espresso.IdlingRegistry;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.example.news.R;
import com.example.news.view.master.NewsFragment;
import com.example.news.utils.EspressoIdlingResource;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;
import io.reactivex.disposables.CompositeDisposable;

public class MainActivity extends DaggerAppCompatActivity {

    private static final String CURRENT_FRAGMENT_TAG = "current_fragment";

    @Inject
    public NewsFragment newsFragment;

    @Inject
    public CompositeDisposable compositeDisposable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getIdlingResource());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            initView();
        } else {
            handleOnConfigurationChanged();
        }
    }

    private void initView() {
        replaceCurrentFragment(newsFragment, false);
    }

    private void handleOnConfigurationChanged() {
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(CURRENT_FRAGMENT_TAG);
        replaceCurrentFragment(fragment, false);
    }

    public void replaceCurrentFragment(Bundle data, Fragment fragment) {
        fragment.setArguments(data);
        replaceCurrentFragment(fragment, true);
    }

    private void replaceCurrentFragment(Fragment fragment, boolean addToBackStack) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment, CURRENT_FRAGMENT_TAG);
        if (addToBackStack)
            fragmentTransaction.addToBackStack(newsFragment.getClass().getName());
        fragmentTransaction.commit();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (!compositeDisposable.isDisposed())
            compositeDisposable.isDisposed();
    }
}
