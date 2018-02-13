package com.udacity.gradle.builditbigger;


import android.content.Context;
import android.os.AsyncTask;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.ApplicationTestCase;
import android.test.suitebuilder.annotation.LargeTest;
import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.udacity.gradle.builtitbigger.paid.MainActivity;
import com.udacity.gradle.builtitbigger.paid.MainActivityFragment;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;
import static org.hamcrest.Matchers.allOf;
import static org.mockito.Mockito.mock;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class TestforNonString {

    final CountDownLatch finalCountDown = new CountDownLatch(1);


    @Test
    public void AsyncTaskTest() throws Throwable {
        final EndpointAsyncTask endpointAsyncTask = new EndpointAsyncTask() {

            @Override
            protected String doInBackground(Pair<Context, String>... params) {
                return super.doInBackground();
            }


            @Override
            protected void onPostExecute(String string){
                assertNotNull(string);
                assertFalse(string.equals(""));
                finalCountDown.countDown();

            }


        };

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                endpointAsyncTask.execute();
            }
        };

        runnable.run();

        //wait for 30 second for everything to finish
        finalCountDown.await(30, TimeUnit.SECONDS);

    }

}