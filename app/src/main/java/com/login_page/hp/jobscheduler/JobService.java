package com.login_page.hp.jobscheduler;

import android.app.job.JobParameters;
import android.util.Log;

public class JobService extends android.app.job.JobService {

    public static final String TAG = "Job scheduler";
    boolean jobCancelled = false;
    
    @Override
    public boolean onStartJob(JobParameters params) {
        Log.d(TAG,"Job started");
        startjob(params);
        return true;
    }

    private void startjob(final JobParameters params) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=0;i<10;i++){
                    Log.d(TAG,"run:"+i);
                    if (jobCancelled ){
                        return;
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Log.d(TAG,"Job finished");
                jobFinished(params,false);
            }
        }).start();
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        Log.d(TAG,"job cancelled before completion");
        jobCancelled = true;
        return true;
    }
}
