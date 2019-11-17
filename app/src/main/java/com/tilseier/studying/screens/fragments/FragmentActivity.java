package com.tilseier.studying.screens.fragments;

import android.os.Bundle;

import com.tilseier.studying.R;

import androidx.appcompat.app.AppCompatActivity;

public class FragmentActivity extends AppCompatActivity implements FragmentA.FragmentAListener, FragmentB.FragmentBListener {

    FragmentA fragmentA;
    FragmentB fragmentB;

    public int value = 293;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        fragmentA = new FragmentA();
        fragmentB = new FragmentB();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_a, fragmentA)
                .replace(R.id.container_b, fragmentB)
                .commit();
    }

    @Override
    public void onInputASent(CharSequence input) {
        fragmentB.updateEditText(input);
    }

    @Override
    public void onInputBSent(CharSequence input) {
        fragmentA.updateEditText(input);
    }
}
