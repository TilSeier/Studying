package com.tilseier.studying.screens.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.tilseier.studying.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentA extends Fragment {

    public interface FragmentAListener {
        void onInputASent(CharSequence input);
    }

    View view;
    Button btnSend;
    EditText etInput;

    private FragmentAListener fragmentAListener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_a, container, false);
        initFragment();
        return view;
    }

    private void initFragment() {

        btnSend = view.findViewById(R.id.btn_fragment_a);
        etInput = view.findViewById(R.id.et_fragment_a);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentAListener.onInputASent(etInput.getText());
            }
        });

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof FragmentAListener){
            fragmentAListener = (FragmentAListener) context;
        } else {
            throw new RuntimeException(context.toString() +
                    " must implement FragmentAListener");
        }
    }

    public void updateEditText(CharSequence text){
        etInput.setText(text);
    }


    @Override
    public void onDetach() {
        super.onDetach();
        fragmentAListener = null;
    }
}
