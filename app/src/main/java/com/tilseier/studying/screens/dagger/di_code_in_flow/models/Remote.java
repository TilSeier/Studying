package com.tilseier.studying.screens.dagger.di_code_in_flow.models;

import com.tilseier.studying.utils.Const;

import javax.inject.Inject;

import timber.log.Timber;

public class Remote {

    @Inject
    public Remote() {
    }

    public void setListener(Car car){
        Timber.tag(Const.TAG_DAGGER).e("setRemoteListener");
    }
}
