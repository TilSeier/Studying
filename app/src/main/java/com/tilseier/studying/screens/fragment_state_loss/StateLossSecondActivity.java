package com.tilseier.studying.screens.fragment_state_loss;

import android.os.Bundle;

import com.tilseier.studying.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class StateLossSecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_state_loss_second);
        Toolbar toolbar = (Toolbar) findViewById(R.id.tb_state_loss_second);
        setSupportActionBar(toolbar);
    }

}
