package com.tilseier.studying.screens.FragmentManager;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.tilseier.studying.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class FragmentManagerActivity extends AppCompatActivity implements FragmentManager.OnBackStackChangedListener {

    FragmentManager manager;
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_manager);
        manager = getSupportFragmentManager();
        text = findViewById(R.id.message);
        manager.addOnBackStackChangedListener(this);
    }

    public void replaceWithA(View v){
        FirstAFragment f1 = new FirstAFragment();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.group, f1, "A");
        transaction.addToBackStack("replaceWithA");
        transaction.commit();
    }

    public void replaceWithB(View v){
        SecondBFragment f2 = new SecondBFragment();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.group, f2, "B");
        transaction.addToBackStack("replaceWithB");
        transaction.commit();
    }

    public void addA(View v){
        FirstAFragment f1 = new FirstAFragment();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.group, f1, "A");
        transaction.addToBackStack("addA");
        transaction.commit();
    }

    public void addB(View v){
        SecondBFragment f2 = new SecondBFragment();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.group, f2, "B");
        transaction.addToBackStack("addB");
        transaction.commit();
    }

    public void removeA(View v){
        FirstAFragment f1 = (FirstAFragment) manager.findFragmentByTag("A");
        FragmentTransaction transaction = manager.beginTransaction();
        if (f1 != null) {
            transaction.remove(f1);
            transaction.addToBackStack("removeA");
            transaction.commit();
        } else {
            Toast.makeText(this, "The Fragment A was not added before", Toast.LENGTH_SHORT).show();
        }
    }

    public void removeB(View v){
        SecondBFragment f2 = (SecondBFragment) manager.findFragmentByTag("B");
        FragmentTransaction transaction = manager.beginTransaction();
        if (f2 != null) {
            transaction.remove(f2);
            transaction.addToBackStack("removeB");
            transaction.commit();
        } else {
            Toast.makeText(this, "The Fragment B was not added before", Toast.LENGTH_SHORT).show();
        }
    }

    public void attachA(View v){
        FirstAFragment f1 = (FirstAFragment) manager.findFragmentByTag("A");
        FragmentTransaction transaction = manager.beginTransaction();
        if (f1 != null) {
            transaction.attach(f1);
            transaction.addToBackStack("attachA");
            transaction.commit();
        } else {
            Toast.makeText(this, "The Fragment A was not added before", Toast.LENGTH_SHORT).show();
        }
    }

    public void attachB(View v){
        SecondBFragment f2 = (SecondBFragment) manager.findFragmentByTag("B");
        FragmentTransaction transaction = manager.beginTransaction();
        if (f2 != null) {
            transaction.remove(f2);
            transaction.addToBackStack("attachB");
            transaction.commit();
        } else {
            Toast.makeText(this, "The Fragment B was not added before", Toast.LENGTH_SHORT).show();
        }
    }

    public void detachA(View v){
        FirstAFragment f1 = (FirstAFragment) manager.findFragmentByTag("A");
        FragmentTransaction transaction = manager.beginTransaction();
        if (f1 != null) {
            transaction.detach(f1);
            transaction.addToBackStack("detachA");
            transaction.commit();
        } else {
            Toast.makeText(this, "The Fragment A was not added before", Toast.LENGTH_SHORT).show();
        }
    }

    public void detachB(View v){
        SecondBFragment f2 = (SecondBFragment) manager.findFragmentByTag("B");
        FragmentTransaction transaction = manager.beginTransaction();
        if (f2 != null) {
            transaction.detach(f2);
            transaction.addToBackStack("detachB");
            transaction.commit();
        } else {
            Toast.makeText(this, "The Fragment B was not added before", Toast.LENGTH_SHORT).show();
        }
    }

    public void back(View v){
        manager.popBackStack();
    }

    public void popAddB(View v){
        manager.popBackStack("addB", 0);

        //remove including addB
//        manager.popBackStack("addB", FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

    @Override
    public void onBackStackChanged() {
        StringBuilder resText = new StringBuilder("The current status of Back Stack\n");
        int count = manager.getBackStackEntryCount();
        for (int i = count - 1; i >= 0; i--) {
            FragmentManager.BackStackEntry entry = manager.getBackStackEntryAt(i);
            resText.append(" ").append(entry.getName()).append("\n");
        }
        resText.append(text.getText().toString()).append("\n\n");
        text.setText(resText);
    }
}