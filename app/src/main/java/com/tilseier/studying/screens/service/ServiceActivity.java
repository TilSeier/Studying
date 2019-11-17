package com.tilseier.studying.screens.service;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.tilseier.studying.R;

import java.util.Random;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

public class ServiceActivity extends AppCompatActivity {

    ViewDataBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_service);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_service);

    }

    public void onStartServiceClick(View view){
        startService(new Intent(this, MyService.class));
    }

    public void onStopServiceClick(View view){
        stopService(new Intent(this, MyService.class));
    }

    public void onStartIntentServiceClick(View view){
        startService(new Intent(this, MyIntentService.class));
    }

    public void onStopIntentServiceClick(View view){
        stopService(new Intent(this, MyIntentService.class));
    }

    public void onStartJobIntentServiceClick(View view){
        String input = "Some Input For Service: " + new Random().nextInt(10);

        Intent serviceIntent = new Intent(this, MyJobIntentService.class);
        serviceIntent.putExtra("inputExtra", input);

        MyJobIntentService.enqueueWork(this, serviceIntent);
    }

}
