package com.tilseier.studying.screens.fragment_back_stack;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.tilseier.studying.R;

public class FragmentOne extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View retView = inflater.inflate(R.layout.fragment_back_stack_one, container, false);

        final FragmentManager fragmentManager = getFragmentManager();

//        fragmentManager.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
//            @Override
//            public void onBackStackChanged() {
//                Log.d(FragmentUtil.TAG_NAME_FRAGMENT, "ON_BACK");
//            }
//        });

        Button gotoFragmentTwoBtn = (Button)retView.findViewById(R.id.fragment_back_stack_one_button);
        gotoFragmentTwoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Fragment fragmentTwo = FragmentUtil.getFragmentByTagName(fragmentManager, "Fragment Two");

                // Because fragment two has been popup from the back stack, so it must be null.
                if(fragmentTwo==null)
                {
                    fragmentTwo = new FragmentTwo();
                }

                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                // Replace fragment one with fragment two, the second fragment tag name is "Fragment Two".
                // This action will remove Fragment one and add Fragment two.
                fragmentTransaction.replace(R.id.fragment_back_stack_frame_layout, fragmentTwo, "Fragment Two");

                // Add fragment one in back stack.So it will not be destroyed. Press back menu can pop it up from the stack.
                fragmentTransaction.addToBackStack(null);

                fragmentTransaction.commit();

                FragmentUtil.printActivityFragmentList(fragmentManager);
            }
        });

        return retView;
    }
}
