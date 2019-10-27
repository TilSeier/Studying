package com.tilseier.studying.screens.dagger.di_code_in_flow.models;

import com.tilseier.studying.utils.Const;

import javax.inject.Inject;

import timber.log.Timber;

public class DieselEngine implements Engine {

    private int horsePower;

    @Inject
    public DieselEngine(int horsePower) {
        this.horsePower = horsePower;
}

//    @Inject
//    public DieselEngine() {
//    }

    @Override
    public void start() {
        Timber.tag(Const.TAG_DAGGER).e("start DieselEngine. Horse Power: %s", horsePower);
    }
}
