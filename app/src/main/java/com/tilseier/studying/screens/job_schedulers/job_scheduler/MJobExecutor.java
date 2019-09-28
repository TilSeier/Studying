package com.tilseier.studying.screens.job_schedulers.job_scheduler;

import android.os.AsyncTask;

public class MJobExecutor extends AsyncTask<Void, Void, String> {

    @Override
    protected String doInBackground(Void... voids) {
        return "Background Long Running Task Finishes...";
    }

}
