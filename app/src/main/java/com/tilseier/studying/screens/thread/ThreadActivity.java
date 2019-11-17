package com.tilseier.studying.screens.thread;

import android.os.Bundle;

import com.tilseier.studying.R;

import androidx.appcompat.app.AppCompatActivity;

public class ThreadActivity extends AppCompatActivity {
    Thread t = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_thread);

        t = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    t.sleep(5000);
                    runOnUiThread(new Runnable() {
                        public void run() {
                            setContentView(R.layout.activity_thread);
                        }
                    });
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }
        });
        t.start();

    }
}
