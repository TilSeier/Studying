package com.tilseier.studying.screens.service;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

public class MyIntentService extends IntentService {

    private static final String TAG = "IntentServiceTAG";

    public MyIntentService() {
        super("Intent_Service");
        Log.d(TAG,"MyIntentService");
    }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        Log.d(TAG,"onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        Log.d(TAG,"onHandleIntent");

        synchronized (this){

            int count = 0;

            while (count < 10){

                Log.d(TAG,"count = "+count);
                try {
                    wait(1000);
                    count++;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"onDestroy");
    }

}
