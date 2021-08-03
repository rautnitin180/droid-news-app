package com.example.news;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.IdlingRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.news.view.MainActivity;
import com.example.news.utils.EspressoIdlingResource;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    @Rule
    public ActivityTestRule<MainActivity> mIntentsRule =
            new ActivityTestRule<>(MainActivity.class);

    public static RecyclerViewMatcher withRecyclerView(final int recyclerViewId) {
        return new RecyclerViewMatcher(recyclerViewId);
    }

    @Before
    public void registerIdlingResource() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getIdlingResource());
    }

    @Test
    public void useAppContext() {
        Context appContext = InstrumentationRegistry.getTargetContext();
        assertEquals("com.example.news", appContext.getPackageName());
    }

    @Test
    public void testRecyclerView() {
        onView(withId(R.id.articles_recycler)).check(matches(isDisplayed()));
    }

    @Test
    public void testRecyclerViewItem() {
        onView(withRecyclerView(R.id.articles_recycler).atPositionOnView(3, R.id.title)).check(matches(isDisplayed()));
        onView(withRecyclerView(R.id.articles_recycler).atPositionOnView(3, R.id.created_by)).check(matches(isDisplayed()));
        onView(withRecyclerView(R.id.articles_recycler).atPositionOnView(3, R.id.source)).check(matches(isDisplayed()));
        onView(withRecyclerView(R.id.articles_recycler).atPositionOnView(3, R.id.date)).check(matches(isDisplayed()));
    }

    @Test
    public void testClick() {
        onView(withRecyclerView(R.id.articles_recycler).atPosition(2)).perform(click());
    }

    @Test
    public void testDetailsFragment() {
        onView(withRecyclerView(R.id.articles_recycler).atPosition(2)).perform(click());
        onView(withId(R.id.title_des)).check(matches(isDisplayed()));
        onView(withId(R.id.description_des)).check(matches(isDisplayed()));
        onView(withId(R.id.created_by_des)).check(matches(isDisplayed()));
        onView(withId(R.id.created_by_title_des)).check(matches(isDisplayed()));
    }

}
