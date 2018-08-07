package com.example.dsemigradsky.exercise_04;

public class MyCounterAsyncTaskImpl extends MySimpleAsyncTask<Integer> {

    private BaseAsyncWorkFragment.IAsyncWorkEvents asyncTaskEvents;

    public void refresh(BaseAsyncWorkFragment.IAsyncWorkEvents asyncTaskEvents) {
        this.asyncTaskEvents = asyncTaskEvents;
    }

    @Override
    protected Integer doInBackground() {
        for (int i = 1; i <= 10; i++) {
            if (isCancelled()) {
                return null;
            }

            try {
                Thread.sleep(500);

                publishProgress(i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        if (asyncTaskEvents != null) {
            asyncTaskEvents.onProgressUpdate(values[0]);
        }
    }

    @Override
    protected void onPostExecute() {
        if (asyncTaskEvents != null) {
            asyncTaskEvents.onPostExecute();
        }
    }
}
