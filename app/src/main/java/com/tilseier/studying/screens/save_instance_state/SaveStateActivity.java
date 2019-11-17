package com.tilseier.studying.screens.save_instance_state;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.tilseier.studying.R;
import com.tilseier.studying.models.UserModel;

import androidx.appcompat.app.AppCompatActivity;
import timber.log.Timber;

public class SaveStateActivity extends AppCompatActivity {

    private static final String TAG = "SaveStateActivity";

    UserModel user;
    String activity;

    TextView tvActivity;
    TextView tvName;
    TextView tvAge;
    TextView tvGender;

    Button btnCounter;
    TextView tvCounter;
    int counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_state);

        Timber.tag(TAG).e("onCreate");

        if (savedInstanceState != null) {
            String message = savedInstanceState.getString("message");
            Toast.makeText(this, message, Toast.LENGTH_LONG).show();

            counter = savedInstanceState.getInt("counter", 0);
        }

        user = getIntent().getParcelableExtra("user");
        activity = getIntent().getStringExtra("activity");

        tvActivity = findViewById(R.id.tv_activity);
        tvName = findViewById(R.id.tv_user_name);
        tvAge = findViewById(R.id.tv_user_age);
        tvGender = findViewById(R.id.tv_user_gender);

        btnCounter = findViewById(R.id.btn_counter);
        tvCounter = findViewById(R.id.tv_counter);

        btnCounter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter = Integer.valueOf(tvCounter.getText().toString()) + 1;
                tvCounter.setText(String.valueOf(counter));
            }
        });

        initApp();

    }

    private void initApp() {

        tvActivity.setText(activity);
        tvName.setText(user.getName());
        tvAge.setText(String.valueOf(user.getAge()));
        tvGender.setText(user.getGender());

        tvCounter.setText(String.valueOf(counter));

    }

    @Override
    protected void onStart() {
        super.onStart();
        Timber.tag(TAG).e("onStart");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) { //is rarely used, instead we can do it in the onCreate()
        super.onRestoreInstanceState(savedInstanceState);
        Timber.tag(TAG).e("onRestoreInstanceState");
//        counter = savedInstanceState.getInt("counter", 0);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Timber.tag(TAG).e("onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Timber.tag(TAG).e("onPause");
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Timber.tag(TAG).e("onSaveInstanceState");
        outState.putString("message", "This is a saved message");
        outState.putInt("counter", counter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Timber.tag(TAG).e("onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Timber.tag(TAG).e("onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Timber.tag(TAG).e("onRestart");
    }
}
