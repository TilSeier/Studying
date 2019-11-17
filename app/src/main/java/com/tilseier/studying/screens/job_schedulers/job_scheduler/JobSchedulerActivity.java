package com.tilseier.studying.screens.job_schedulers.job_scheduler;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.tilseier.studying.R;

import androidx.appcompat.app.AppCompatActivity;
import timber.log.Timber;

//FROM API 21
public class JobSchedulerActivity extends AppCompatActivity {

    private static final int JOB_ID = 123;
    private static final String TAG = "JobSchedulerActivity";
//    private JobScheduler jobScheduler;
//    private JobInfo jobInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_scheduler);
    }

    public void onScheduleJobClick(View view) {

        Toast.makeText(this, "Job Scheduled...", Toast.LENGTH_SHORT).show();

        ComponentName serviceName = new ComponentName(this, JobSchedulerService.class);
        JobInfo info = new JobInfo.Builder(JOB_ID, serviceName)
                //conditions for our job scheduler
                .setRequiresCharging(true)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)
                //this will keep our job alive even we reboot our device
                // needs permission RECEIVE_BOOT_COMPLETED
                .setPersisted(true)
                //period is not exact
                // means job will be executed in this period
                // but we don't know when exactly will it be executed
                //since Android Nougat can't be lower than 15 minutes (15 * 60 * 1000) milliseconds
                .setPeriodic(15 * 60 * 1000)
                .build();

        JobScheduler scheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        int resultCode = scheduler.schedule(info);
        if(resultCode == JobScheduler.RESULT_SUCCESS){
            Timber.tag(TAG).d("Job Scheduled");
        } else {
            Timber.tag(TAG).d("Job Scheduling Failed");
        }

    }

    public void onClearScheduleJobClick(View view) {

//        Toast.makeText(this, "Job Cancelled...", Toast.LENGTH_SHORT).show();

        JobScheduler scheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        scheduler.cancel(JOB_ID);
        Timber.tag(TAG).d("Job Cancelled");
    }
}
