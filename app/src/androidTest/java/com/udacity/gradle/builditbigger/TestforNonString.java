package com.udacity.gradle.builditbigger;


import android.app.Activity;
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


import com.android.dx.command.Main;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;
import com.udacity.gradle.builtitbigger.free.MainActivity;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
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
    Context context;
    private static MyApi myApi = null;

    @Test
    public void AsyncTaskTest() throws Throwable {

        final EndpointAsyncTask endpointAsyncTask = new EndpointAsyncTask(context) {

            @Override
            protected String doInBackground(Pair<Context, String>... params) {
                return super.doInBackground();

            }


            @Override
            protected void onPostExecute(String string){
                finalCountDown.countDown();
                //make sure it is string
                assertNotNull(string);
                //make sure it is NOT empty
                assertFalse(string.isEmpty());


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
        finalCountDown.await(10, TimeUnit.SECONDS);

    }

}