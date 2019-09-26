package com.tilseier.studying.screens.constraint_features;

import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.ChangeBounds;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.view.View;
import android.view.animation.OvershootInterpolator;

import com.tilseier.studying.R;

public class ConstraintSetsActivity extends AppCompatActivity {

    private ConstraintLayout clSets;
    private ConstraintSet constraintSetOld = new ConstraintSet();
    private ConstraintSet constraintSetNew = new ConstraintSet();
    private boolean altLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_constraint_sets);

        clSets = findViewById(R.id.cl_sets);

        constraintSetOld.clone(clSets);
        constraintSetNew.clone(this, R.layout.activity_constraint_sets_alt);

    }

    public void onSwapViewClick(View view){
        Transition changeBounds = new ChangeBounds();
        changeBounds.setInterpolator(new OvershootInterpolator());

        TransitionManager.beginDelayedTransition(clSets, changeBounds);

        if (!altLayout){
            constraintSetNew.applyTo(clSets);
            altLayout = true;
        } else {
            constraintSetOld.applyTo(clSets);
            altLayout = false;
        }

    }

}
