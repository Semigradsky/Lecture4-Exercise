package com.example.dsemigradsky.exercise_04;

import android.os.AsyncTask;

public class AsyncTaskAsyncWorkFragment extends BaseAsyncWorkFragment {
    private CounterAsyncTask asyncTask;

    @Override
    public void init() {
        asyncTask = new CounterAsyncTask();
    }

    @Override
    public void refresh() {
        if (asyncTask != null) {
            asyncTask.refresh(events);
        }
    }

    @Override
    public void start() {
        if (asyncTask == null) {
            return;
        }

        AsyncTask.Status status = asyncTask.getStatus();
        if (status == AsyncTask.Status.PENDING) {
            asyncTask.execute();
        }
    }

    @Override
    public void cancel() {
        if (asyncTask != null) {
            asyncTask.cancel(true);
            asyncTask = null;
        }
    }
}
