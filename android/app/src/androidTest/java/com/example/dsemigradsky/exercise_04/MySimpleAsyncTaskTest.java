package com.example.dsemigradsky.exercise_04;

import android.os.Looper;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MySimpleAsyncTaskTest {

    int counter = 0;

    @Test
    public void execute() throws Exception {
        new MySimpleAsyncTask() {
            @Override
            protected void onPreExecute() {
                assertTrue(isOnUiThread());
                assertEquals(counter++, 0);
            }

            @Override
            protected Object doInBackground() {
                assertFalse(isOnUiThread());
                assertEquals(counter++, 1);
                return new Object();
            }

            @Override
            protected void onPostExecute() {
                assertTrue(isOnUiThread());
                assertEquals(counter++, 2);
            }
        }.execute();
    }

    public boolean isOnUiThread() {
        return Looper.myLooper() == Looper.getMainLooper();
    }
}
