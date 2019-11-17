package com.tilseier.studying.screens.service;

import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;

import androidx.annotation.NonNull;
import androidx.core.app.JobIntentService;
import timber.log.Timber;

public class MyJobIntentService extends JobIntentService {

    private static final String TAG = "MyJobIntentService";

    static void enqueueWork(Context context, Intent work){
        enqueueWork(context, MyJobIntentService.class, 123, work);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Timber.tag(TAG).e("onCreate");
    }

    @Override
    protected void onHandleWork(@NonNull Intent intent) {

        Timber.tag(TAG).e("onHandleWork");

        String input = intent.getStringExtra("inputExtra");

        for (int i = 0; i < 5; i++) {
            Timber.tag(TAG).e(input + " - " + i);

            if (isStopped()) return;

            SystemClock.sleep(1000);
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Timber.tag(TAG).e("onDestroy");
    }

    @Override
    public boolean onStopCurrentWork() {
        Timber.tag(TAG).e("onStopCurrentWork");
        return super.onStopCurrentWork();
    }
}
