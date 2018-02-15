package com.udacity.gradle.builditbigger.androidTest;

import android.app.Application;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.test.ApplicationTestCase;
import android.text.TextUtils;

import com.udacity.gradle.builditbigger.EndpointsAsyncTask;

import java.util.concurrent.CountDownLatch;
public class RetrevingJokesTest extends ApplicationTestCase<Application> {

    String mJsonString = null;
    CountDownLatch signal = null;

    public RetrevingJokesTest() {
        super(Application.class);
    }

    @Override
    protected void setUp() throws Exception {
        signal = new CountDownLatch(1);
    }

    @Override
    protected void tearDown() throws Exception {
        signal.countDown();
    }

    public void testEndpointsAsyncTask() throws InterruptedException {
        Context appContext = InstrumentationRegistry.getTargetContext();
        EndpointsAsyncTask task = new EndpointsAsyncTask(appContext);
        task.setListener(new EndpointsAsyncTask.TaskListener() {
            @Override
            public void onComplete(String jsonString) {
                mJsonString = jsonString;
                signal.countDown();
            }
        }).execute();
        signal.await();

        assertFalse(TextUtils.isEmpty(mJsonString));
        assertTrue(mJsonString.length()>0);

    }
}