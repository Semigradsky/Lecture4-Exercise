package com.example.dsemigradsky.exercise_04;

public abstract class MySimpleAsyncTask<T> extends SimpleAsyncTask<T> {
    @Override
    protected void onPreExecute() {
        // do nothing
    }

    @Override
    protected void onPostExecute() {
        // do nothing
    }

    @Override
    protected void onProgressUpdate(T... values) {
        // do nothing
    }

    @Override
    protected void publishProgress(T... values) {
        // TODO: Not completed!
        onProgressUpdate(values);
    }

    @Override
    public void execute() {
        // TODO: Not completed!
        onPreExecute();
        doInBackground();
        onPostExecute();
    }

    @Override
    public void cancel() {
        // TODO: Not completed!
        // cancel
        this.cancelled = true;
    }
}
