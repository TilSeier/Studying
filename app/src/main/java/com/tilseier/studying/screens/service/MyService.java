package com.tilseier.studying.screens.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.util.concurrent.TimeUnit;

import androidx.annotation.Nullable;

public class MyService extends Service {

    private static final String TAG = "ServiceTAG";

    protected int Num1 = 7;
    int Num2 = 8;
    public int Num3 = 9;
    private int Num4 = 10;

    final PrintDemo printDemo = new PrintDemo();

    @Override
    public void onCreate() {
        Log.d(TAG,"onCreate");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG,"onStartCommand");

        someTask();
        return super.onStartCommand(intent, flags, startId);
    }

    //    @androidx.annotation.Nullable
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG,"onBind");
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"onDestroy");
    }

    class PrintDemo {
        public synchronized void printCount() {
            try {
                for (int i = 10; i > 0; i--) {
                    Log.d(TAG,"Selected number is: "  + i );
                    try {
        //                wait(1000);
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } catch (Exception e) {
                System.out.println("Thread has been interrupted.");
            }
        }
    }

    void someTask(){

        synchronized (this) {

            new Thread(new Runnable() {
                @Override
                public void run() {
                    Log.d(TAG, "someTask");

                    printDemo.printCount();

//                    for (int i = 0; i < 10; i++) {
//
//                        Log.d(TAG, "i = " + i);
//                        try {
////                wait(1000);
//                            TimeUnit.SECONDS.sleep(1);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//
//                    }
                    stopSelf();
//                stopSelfResult(1);
                }
            }).start();

        }

    }

}
