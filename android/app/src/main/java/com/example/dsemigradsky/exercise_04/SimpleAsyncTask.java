package com.example.dsemigradsky.exercise_04;

public abstract class SimpleAsyncTask<T> {
    protected boolean cancelled = false;

    abstract protected void onPreExecute();

    abstract protected T doInBackground();

    abstract protected void onPostExecute();

    abstract protected void onProgressUpdate(T... values);

    abstract protected void publishProgress(final T... values);

    abstract public void execute();

    abstract public void cancel();

    public boolean isCancelled() {
        return this.cancelled;
    }
}
