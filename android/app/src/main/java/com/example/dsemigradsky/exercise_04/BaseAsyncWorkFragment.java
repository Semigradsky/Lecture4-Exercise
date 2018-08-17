package com.example.dsemigradsky.exercise_04;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

public abstract class BaseAsyncWorkFragment extends Fragment {
    interface IAsyncWorkEvents {
        void onPostExecute();
        void onProgressUpdate(Integer integer);
    }

    IAsyncWorkEvents events;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        events = (IAsyncWorkEvents) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.refresh();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        events = null;
        this.refresh();
    }

    public void create() {
        this.cancel();
        this.init();
        this.refresh();
    }

    abstract protected void refresh();

    abstract public void init();

    abstract public void start();

    abstract public void cancel();
}
