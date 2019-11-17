package com.tilseier.studying.screens.fragment_back_stack;

import android.os.Bundle;

import com.tilseier.studying.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class FragmentBackStackActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_back_stack);

        setTitle("dev2qa.com - Fragment Back Stack Example.");

        // Get FragmentManager and FragmentTransaction object.
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        // Create FragmentOne instance.
        FragmentOne fragmentOne = new FragmentOne();

        // Add fragment one with tag name.
        fragmentTransaction.add(R.id.fragment_back_stack_frame_layout, fragmentOne, "Fragment One");
        fragmentTransaction.commit();

        FragmentUtil.printActivityFragmentList(fragmentManager);

    }
}
