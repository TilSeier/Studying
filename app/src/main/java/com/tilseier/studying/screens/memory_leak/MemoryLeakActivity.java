package com.tilseier.studying.screens.memory_leak;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import com.tilseier.studying.R;

import java.lang.ref.WeakReference;

import androidx.appcompat.app.AppCompatActivity;
import timber.log.Timber;

public class MemoryLeakActivity extends AppCompatActivity {

    //Memory Leaks occur when the garbage collector is unable to clean up a resource that uses block of memory
    //Memory Leak - error that causes an application to keep a reference to an object that is no longer needed.
    //As a result, the memory allocated for that object cannot be reclaimed, eventually leading to an OutOfMemoryError

    //What causes memory leaks: View, Context, Activity Reference saved on a background thread
    //To avoid Memory Leak: Don't save View, Context, Activity on a background thread

    //To detect Memory Leak: Library LeakCanary


    private static final String TAG = "MemoryLeakActivity";//logt
    private MyAsyncTaskWithMemoryLeak myAsyncTaskWithMemoryLeak;
    private MyAsyncTaskWithNoMemoryLeak myAsyncTaskWithNoMemoryLeak;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory_leak);
    }

    public void onBtnCauseMemoryLeak(View view) {

        if (myAsyncTaskWithMemoryLeak != null){
            finish();
        }

        myAsyncTaskWithMemoryLeak = new MyAsyncTaskWithMemoryLeak(this);
        myAsyncTaskWithMemoryLeak.execute();


    }

    public void onBtnNoMemoryLeak(View view) {

        if (myAsyncTaskWithNoMemoryLeak != null){
            finish();
        }

        myAsyncTaskWithNoMemoryLeak = new MyAsyncTaskWithNoMemoryLeak(this);
        myAsyncTaskWithNoMemoryLeak.execute();
    }

    @Override
    protected void onDestroy() {
        //WE SHOULD CANCEL OUR BACKGROUND JOB WITH CONTEXT BEFORE WE DESTROY ACTIVITY TO AVOID MEMORY LEAK
//        myAsyncTaskWithMemoryLeak.cancel(true);

//        myAsyncTaskWithNoMemoryLeak.cancel(true);
        super.onDestroy();
        Timber.tag(TAG).d("Activity destroyed");
    }

    private class MyAsyncTaskWithMemoryLeak extends AsyncTask<Void, Void, Void>{

        private Context mContext;

        public MyAsyncTaskWithMemoryLeak(Context context) {
            this.mContext = context;
        }

        @Override
        protected Void doInBackground(Void... voids) {

            Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.ic_launcher_background);

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return null;
        }
    }


    //We do AsyncTask class "static" to avoid implicit dependency with superclass, eventually leading to memory leaks
    private static class MyAsyncTaskWithNoMemoryLeak extends AsyncTask<Void, Void, Void>{

        private WeakReference<Context> mContext;

        public MyAsyncTaskWithNoMemoryLeak(Context context) {
            this.mContext = new WeakReference<>(context);
        }

        @Override
        protected Void doInBackground(Void... voids) {

            if (mContext.get() != null) {
                Bitmap bitmap = BitmapFactory.decodeResource(mContext.get().getResources(), R.drawable.ic_launcher_background);
            }

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return null;
        }
    }

}
