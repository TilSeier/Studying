package com.tilseier.studying.screens.dagger.di_code_in_flow.models;

import com.tilseier.studying.utils.Const;

import javax.inject.Inject;
import javax.inject.Named;

import timber.log.Timber;

public class PetrolEngine implements Engine {

    private int horsePower;
    private int engineCapacity;

    @Inject
    public PetrolEngine(@Named("horse power") int horsePower,
                        @Named("engine capacity")int engineCapacity) {
        this.horsePower = horsePower;
        this.engineCapacity = engineCapacity;
    }

//    @Inject
//    public PetrolEngine() {
//    }

    @Override
    public void start() {
        Timber.tag(Const.TAG_DAGGER)
                .e("start PetrolEngine. " +
                        "\nHorsepower: " + horsePower +
                        "\nEngine Capacity: " + engineCapacity);
    }
}
