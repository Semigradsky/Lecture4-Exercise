package com.example.dsemigradsky.exercise_04;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;

public class CounterLoader extends AsyncTaskLoader<Object> {

    private BaseAsyncWorkFragment.IAsyncWorkEvents asyncTaskEvents;

    CounterLoader(@NonNull Context context) {
        super(context);
    }

    public void refresh(BaseAsyncWorkFragment.IAsyncWorkEvents asyncTaskEvents) {
        this.asyncTaskEvents = asyncTaskEvents;
    }

    @Nullable
    @Override
    public Object loadInBackground() {
        for (int i = 1; i <= 10; i++) {
            if (isLoadInBackgroundCanceled()) {
                return null;
            }

            try {
                Thread.sleep(500);

                if (asyncTaskEvents != null) {
                    asyncTaskEvents.onProgressUpdate(i);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (asyncTaskEvents != null) {
            asyncTaskEvents.onPostExecute();
        }

        return null;
    }

    @Override
    protected void onStartLoading() {
        if (takeContentChanged()) {
            forceLoad();
        }
    }

    @Override
    protected void onStopLoading() {
        cancelLoad();
    }
}
