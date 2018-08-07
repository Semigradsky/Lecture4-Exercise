package com.example.dsemigradsky.exercise_04;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    View.OnClickListener getOpenAsyncTaskActivityClickListener(final AsyncWorkFragments tag) {
        return view -> {
            Intent intent = new Intent(MainActivity.this, AsyncWorkActivity.class);

            intent.putExtra(AsyncWorkActivity.ASYNC_WORK_FRAGMENT_TAG,  tag.name());

            startActivity(intent);
        };
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnAsyncTaskActivity)
                .setOnClickListener(getOpenAsyncTaskActivityClickListener(AsyncWorkFragments.ASYNC_TASK));

        findViewById(R.id.btnLoaderActivity)
                .setOnClickListener(getOpenAsyncTaskActivityClickListener(AsyncWorkFragments.LOADER));

        findViewById(R.id.btnThreadsActivity)
                .setOnClickListener(getOpenAsyncTaskActivityClickListener(AsyncWorkFragments.THREADS));
    }
}
