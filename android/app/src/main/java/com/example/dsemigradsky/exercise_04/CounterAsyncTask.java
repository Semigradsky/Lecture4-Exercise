package com.example.dsemigradsky.exercise_04;

import android.os.AsyncTask;

public class CounterAsyncTask extends AsyncTask {

    private BaseAsyncWorkFragment.IAsyncWorkEvents asyncTaskEvents;

    public void refresh(BaseAsyncWorkFragment.IAsyncWorkEvents asyncTaskEvents) {
        this.asyncTaskEvents = asyncTaskEvents;
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        for (int i = 1; i <= 10; i++) {
            if (isCancelled()) {
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
}
