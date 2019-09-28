package com.tilseier.studying.screens.job_schedulers.job_scheduler;

import android.app.job.JobParameters;
import android.app.job.JobService;

import timber.log.Timber;

//executes in the main thread, that's why we need to create new Thread
public class JobSchedulerService extends JobService {

    private static final String TAG = "JobSchedulerService";
    private boolean jobCancelled = false;

//    private MJobExecutor mJobExecutor;

//    @SuppressLint("StaticFieldLeak")
    @Override
    public boolean onStartJob(JobParameters jobParameters) {

        Timber.tag(TAG).e("onStartJob");

        doBackgroundWork(jobParameters);

//        mJobExecutor = new MJobExecutor(){
//            @Override
//            protected void onPostExecute(String s) {
//                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
//                jobFinished(jobParameters, false);
//            }
//        };
//
//        mJobExecutor.execute();

        //if task is short and can be executed in this scope
        return false;

        //for long term operations
        // and we are responsible for when our system finish job
//        return true;
    }

    private void doBackgroundWork(JobParameters jobParameters) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++){
                    Timber.tag(TAG).d("run: " + i);

                    if (jobCancelled){
                        return;
                    }

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                Timber.tag(TAG).d("Job Finished");
                jobFinished(jobParameters, false);//We can set true if something failed
            }
        }).start();

    }

    //will be called be the system when our job is cancelled
    //for example user stops a job, or internet disappeared, or something else
    @Override
    public boolean onStopJob(JobParameters jobParameters) {

        Timber.tag(TAG).d("Job Cancelled Before Completion");

        jobCancelled = true;

//        mJobExecutor.cancel(true);
        return false;//if we need retry our job latter we have to return true
        //we can set true if we would like to reschedule our job
    }

}
