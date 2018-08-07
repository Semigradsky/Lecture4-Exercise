package com.example.dsemigradsky.exercise_04;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class AsyncWorkActivity extends AppCompatActivity implements BaseAsyncWorkFragment.IAsyncWorkEvents {

    static String ASYNC_WORK_FRAGMENT_TAG = "asyncWorkFragmentTag";

    BaseAsyncWorkFragment asyncWorkFragment;
    TextView textView;

    protected BaseAsyncWorkFragment getAsyncWorkFragment(AsyncWorkFragments fragmentTag) {
        switch (fragmentTag) {
            case ASYNC_TASK:
                return new AsyncTaskAsyncWorkFragment();
            case LOADER:
                return new LoaderAsyncWorkFragment();
            case THREADS:
                return new ThreadsAsyncWorkFragment();
            default:
                return null;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asynctask);

        this.textView = findViewById(R.id.textView);

        String fragmentTag = getIntent().getStringExtra(ASYNC_WORK_FRAGMENT_TAG);

        FragmentManager fm = getSupportFragmentManager();
        asyncWorkFragment = (BaseAsyncWorkFragment) fm.findFragmentByTag(fragmentTag);

        if (asyncWorkFragment == null) {
            asyncWorkFragment = getAsyncWorkFragment(AsyncWorkFragments.valueOf(fragmentTag));
            fm.beginTransaction().add(asyncWorkFragment, fragmentTag).commit();
        }

        findViewById(R.id.btnCreateAsyncWork).setOnClickListener(view -> asyncWorkFragment.create());

        findViewById(R.id.btnStartAsyncWork).setOnClickListener(view -> {
            textView.setText("");
            asyncWorkFragment.start();
        });

        findViewById(R.id.btnCancelAsyncWork).setOnClickListener(view -> asyncWorkFragment.cancel());
    }

    @Override
    public void onPostExecute() {
        runOnUiThread(() -> textView.setText(R.string.done));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onProgressUpdate(Integer integer) {
        runOnUiThread(() -> textView.setText(integer.toString()));
    }
}
