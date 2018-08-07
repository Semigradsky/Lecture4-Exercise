package com.example.dsemigradsky.exercise_04;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

public class LoaderAsyncWorkFragment extends BaseAsyncWorkFragment implements LoaderManager.LoaderCallbacks<Object> {

    @Override
    public void refresh() {
        LoaderManager loaderManager = getLoaderManager();
        CounterLoader loader = (CounterLoader) loaderManager.getLoader(0);

        if (loader != null) {
            loader.refresh(events);
        }
    }

    @Override
    public void init() {
        LoaderManager loaderManager = getLoaderManager();
        loaderManager.initLoader(0, null, this);
    }

    @Override
    public void start() {
        LoaderManager loaderManager = getLoaderManager();
        Loader loader = loaderManager.getLoader(0);

        if (loader != null) {
            loader.forceLoad();
        }
    }

    @Override
    public void cancel() {
        LoaderManager loaderManager = getLoaderManager();
        loaderManager.destroyLoader(0);
    }

    @NonNull
    @Override
    public Loader<Object> onCreateLoader(int i, @Nullable Bundle bundle) {
        return new CounterLoader(this.getContext());
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Object> loader, Object o) {
        cancel();
        events.onPostExecute();
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Object> loader) {
        cancel();
    }
}
