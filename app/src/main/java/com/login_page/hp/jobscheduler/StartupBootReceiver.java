package com.login_page.hp.jobscheduler;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class StartupBootReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

            Log.d("Job scheduler", "StartUpBootReceiver BOOT_COMPLETED");
    }
}
