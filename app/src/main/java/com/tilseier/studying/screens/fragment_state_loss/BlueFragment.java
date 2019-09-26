package com.tilseier.studying.screens.fragment_state_loss;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tilseier.studying.R;

/**
 * A Fragment that is blue.
 *
 * @author bherbst
 */
public class BlueFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_blue, container, false);
    }
}
