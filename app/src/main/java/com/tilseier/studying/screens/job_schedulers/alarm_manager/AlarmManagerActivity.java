package com.tilseier.studying.screens.job_schedulers.alarm_manager;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;

import com.tilseier.studying.R;

import java.text.DateFormat;
import java.util.Calendar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

public class AlarmManagerActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {

    TextView tvAlarmManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_manager);

        tvAlarmManager = findViewById(R.id.tv_alarm_manager);
    }

    public void onPickTimeClick(View view) {
        DialogFragment timePicker = new TimePickerFragment();
        timePicker.show(getSupportFragmentManager(), "Time Picker");
    }

    public void onStopAlarmManagerClick(View view) {
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        Intent intent = new Intent(this, AlertReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, 0);

        alarmManager.cancel(pendingIntent);
        tvAlarmManager.setText("Alarm canceled");
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int i, int i1) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, i);
        c.set(Calendar.MINUTE, i1);
        c.set(Calendar.SECOND, 0);

        updateTimeText(c);
        startAlarm(c);
    }

    private void startAlarm(Calendar c) {
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        Intent intent = new Intent(this, AlertReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, 0);

        alarmManager.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);
    }

    private void updateTimeText(Calendar c) {
        String timeText = "Alarm set for: ";
        timeText += DateFormat.getTimeInstance(DateFormat.SHORT).format(c.getTime());

        tvAlarmManager.setText(timeText);
    }
}
