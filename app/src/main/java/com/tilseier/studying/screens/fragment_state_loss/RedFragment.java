package com.tilseier.studying.screens.fragment_state_loss;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tilseier.studying.R;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * A Fragment that is red.
 *
 * @author bherbst
 */
public class RedFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_red, container, false);
    }
}
