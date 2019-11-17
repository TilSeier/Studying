package com.tilseier.studying.screens.job_schedulers.alarm_manager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import androidx.core.app.NotificationCompat;

public class AlertReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationHelper notificationHelper = new NotificationHelper(context);
        NotificationCompat.Builder nb = notificationHelper.getChannel1Notification("Title", "Message");
        notificationHelper.getManager().notify(1, nb.build());
    }
}
