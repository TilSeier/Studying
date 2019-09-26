package com.tilseier.studying.screens.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.tilseier.studying.R;

public class FragmentB extends Fragment {

    public interface FragmentBListener {
        void onInputBSent(CharSequence input);
    }

    View view;
    Button btnSend;
    EditText etInput;

    private FragmentBListener fragmentBListener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_b, container, false);
        initFragment();
        return view;
    }

    private void initFragment() {

        btnSend = view.findViewById(R.id.btn_fragment_b);
        etInput = view.findViewById(R.id.et_fragment_b);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentBListener.onInputBSent(etInput.getText());
            }
        });

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof FragmentBListener){
            fragmentBListener = (FragmentBListener) context;
        } else {
            throw new RuntimeException(context.toString() +
                    " must implement FragmentBListener");
        }
    }

    public void updateEditText(CharSequence text){
        etInput.setText(text);
    }


    @Override
    public void onDetach() {
        super.onDetach();
        fragmentBListener = null;
    }
}
