package com.tilseier.studying.screens.fragment_state_loss;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.tilseier.studying.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

public class StateLossActivity extends AppCompatActivity {
    private boolean allowStateLoss;
    private boolean changeOnStop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_state_loss);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, new BlueFragment())
                    .commit();
        }

        Button disallowStateLossButton = (Button) findViewById(R.id.disallow_state_loss);
        Button allowStateLossButton = (Button) findViewById(R.id.allow_state_loss);
        Button noStateLossButton = (Button) findViewById(R.id.no_state_loss);

        disallowStateLossButton.setOnClickListener(view -> {
            allowStateLoss = false;
            changeOnStop = true;
            startActivity(new Intent(StateLossActivity.this, StateLossSecondActivity.class));
        });

        allowStateLossButton.setOnClickListener(view -> {
            allowStateLoss = true;
            changeOnStop = true;
            startActivity(new Intent(StateLossActivity.this, StateLossSecondActivity.class));
        });

        noStateLossButton.setOnClickListener(view -> {
            changeOnStop = false;

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, new RedFragment());

            if (allowStateLoss) {
                transaction.commitAllowingStateLoss();
            } else {
                transaction.commit();
            }

            startActivity(new Intent(StateLossActivity.this, StateLossSecondActivity.class));
        });
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (changeOnStop) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, new RedFragment());

            if (allowStateLoss) {
                transaction.commitAllowingStateLoss();
            } else {
                transaction.commit();
            }
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }
}
