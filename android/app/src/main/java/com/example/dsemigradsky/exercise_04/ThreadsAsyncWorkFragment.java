package com.example.dsemigradsky.exercise_04;

public class ThreadsAsyncWorkFragment extends BaseAsyncWorkFragment {
    private MyCounterAsyncTaskImpl myAsyncTask;

    @Override
    public void refresh() {
        if (myAsyncTask != null) {
            myAsyncTask.refresh(events);
        }
    }

    @Override
    public void init() {
        myAsyncTask = new MyCounterAsyncTaskImpl();
    }

    @Override
    public void start() {
        if (myAsyncTask == null) {
            return;
        }

        if (!myAsyncTask.isCancelled()) {
            myAsyncTask.execute();
        }
    }

    @Override
    public void cancel() {
        if (myAsyncTask != null) {
            myAsyncTask.cancel();
            myAsyncTask = null;
        }
    }
}
