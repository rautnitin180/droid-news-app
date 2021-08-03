package com.example.news.utils;

import android.support.test.espresso.IdlingResource;

import java.util.concurrent.atomic.AtomicInteger;

import kotlin.jvm.Volatile;

public class SimpleCountingIdlingResource implements IdlingResource {


    private AtomicInteger counter = new AtomicInteger(0);

    @Volatile
    private IdlingResource.ResourceCallback resourceCallback = null;

    @Override
    public String getName() {
        return "";
    }

    @Override
    public boolean isIdleNow() {
        return counter.get() == 0;
    }


    @Override
    public void registerIdleTransitionCallback(ResourceCallback callback) {
        this.resourceCallback = callback;
    }

    public void increment() {
        counter.getAndIncrement();
    }

    public void decrement() {
        int counterVal = counter.decrementAndGet();
        if (counterVal == 0) {
            if (resourceCallback != null)
                resourceCallback.onTransitionToIdle();
        } else if (counterVal < 0)
            throw new IllegalArgumentException("Counter has been corrupted!");
    }
}
