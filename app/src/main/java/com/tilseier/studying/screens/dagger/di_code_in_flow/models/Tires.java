package com.tilseier.studying.screens.dagger.di_code_in_flow.models;

import com.tilseier.studying.utils.Const;

import timber.log.Timber;

public class Tires {
    //we don't owe this class so we can't annotate it with @Inject

    public void inflate(){
        Timber.tag(Const.TAG_DAGGER).e("Tires inflated");
    }

}
