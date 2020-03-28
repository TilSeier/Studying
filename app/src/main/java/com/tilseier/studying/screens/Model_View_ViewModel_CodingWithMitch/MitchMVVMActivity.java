package com.tilseier.studying.screens.Model_View_ViewModel_CodingWithMitch;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.tilseier.studying.R;
import com.tilseier.studying.screens.Model_View_ViewModel_CodingWithMitch.adpters.NicePlacesRecyclerAdapter;
import com.tilseier.studying.screens.Model_View_ViewModel_CodingWithMitch.models.NicePlace;
import com.tilseier.studying.screens.Model_View_ViewModel_CodingWithMitch.viewmodels.NicePlacesViewModel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MitchMVVMActivity extends AppCompatActivity {

    private FloatingActionButton mFab;
    private RecyclerView mRecycleView;
    private NicePlacesRecyclerAdapter mNicePlacesAdapter;
    private ProgressBar mProgressBar;
    private NicePlacesViewModel mNicePlacesViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mitch_mvvm);
        mFab = findViewById(R.id.fab);
        mRecycleView = findViewById(R.id.rv_nice_places);
        mProgressBar = findViewById(R.id.progress_bar);

        mNicePlacesViewModel = new ViewModelProvider(this).get(NicePlacesViewModel.class);
        mNicePlacesViewModel.init();
        mNicePlacesViewModel.getNicePlaces().observe(this, nicePlaces -> {
            Log.e("TAG", (nicePlaces != null ? ""+nicePlaces.size() : "NULL"));
            mNicePlacesAdapter.notifyDataSetChanged();
        });
        mNicePlacesViewModel.getIsUpdating().observe(this, aBoolean -> {
            if (aBoolean) {
                showProgressBar();
            } else {
                hideProgressBar();
                mRecycleView.smoothScrollToPosition(mNicePlacesViewModel.getNicePlaces().getValue().size()-1);
            }
        });
        mFab.setOnClickListener(view -> {
            mNicePlacesViewModel.addNewValue(
                    new NicePlace("Just Created Title",
                            "https://i.pinimg.com/originals/af/18/54/af1854b640dc6e65046e6663f0ac51a0.jpg")
            );
        });

        initRecyclerView();
    }

    private void initRecyclerView() {
        mNicePlacesAdapter = new NicePlacesRecyclerAdapter(mNicePlacesViewModel.getNicePlaces().getValue());
        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecycleView.setLayoutManager(linearLayoutManager);
        mRecycleView.setAdapter(mNicePlacesAdapter);
    }

    private void showProgressBar(){
        mProgressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar(){
        mProgressBar.setVisibility(View.GONE);
    }

}
