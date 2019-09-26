package com.tilseier.studying.screens.eventbus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.tilseier.studying.R;

import org.greenrobot.eventbus.EventBus;

public class PublisherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publisher);
    }

    public void onPostEventBusClick(View view){
        CustomMessageEvent customMessageEvent = new CustomMessageEvent();
        customMessageEvent.setCustomMessage("Event from PublisherActivity");
        EventBus.getDefault().post(customMessageEvent);
    }

}
