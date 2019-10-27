package com.tilseier.studying.screens.dagger.di_code_in_flow.models;

public class Wheels {
    //we don't owe this class so we can't annotate it with @Inject

    private Tires tires;
    private Rims rims;
    public Wheels(Tires tires, Rims rims) {
        this.tires = tires;
        this.rims = rims;
    }

//    @Inject
//    public Wheels() {
//    }
}
