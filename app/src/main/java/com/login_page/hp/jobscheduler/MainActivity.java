package com.login_page.hp.jobscheduler;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG = "Job scheduler";
    Button b1,b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b1 = findViewById(R.id.button1);
        b2 = findViewById(R.id.button2);
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if (b1 == v){
            ComponentName componentName = new ComponentName(this,JobService.class);
            JobInfo jobInfo = new JobInfo.Builder(123,componentName)
                    .setRequiresCharging(true)
                    .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)
                    .setPersisted(true)
                    .setPeriodic(15*60*1000)
                    .build();

            JobScheduler jobScheduler = (JobScheduler)getSystemService(JOB_SCHEDULER_SERVICE);
            int resultcode = jobScheduler.schedule(jobInfo);

            if (resultcode == JobScheduler.RESULT_SUCCESS){
                Log.d(TAG,"Job scheduling successfull");
            }
            else{
                Log.d(TAG,"Job scheduling unsuccessfull");
            }
        }
        else if (b2 == v){
            JobScheduler jobScheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
            jobScheduler.cancel(123);
            Log.d(TAG,"Job scheduling cancelled");
        }
    }
}
