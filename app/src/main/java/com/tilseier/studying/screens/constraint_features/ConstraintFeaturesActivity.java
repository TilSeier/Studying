package com.tilseier.studying.screens.constraint_features;

import android.os.Bundle;
import android.transition.TransitionManager;

import com.tilseier.studying.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class ConstraintFeaturesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_constraint_features);

        ConstraintLayout clFeatures = findViewById(R.id.cl_features);

        TransitionManager.beginDelayedTransition(clFeatures);
    }

}
