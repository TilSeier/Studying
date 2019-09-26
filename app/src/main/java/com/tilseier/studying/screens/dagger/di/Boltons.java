package com.tilseier.studying.screens.dagger.di;

import javax.inject.Inject;

public class Boltons implements House {

    @Inject //Dagger 2
    Boltons(){
    }

    @Override
    public void prepareForWar() {
        // что-то происходит
        System.out.println(this.getClass().getSimpleName()+" prepared for war");
    }

    @Override
    public void reportForWar() {
        // что-то происходит
        System.out.println(this.getClass().getSimpleName()+" reporting..");
    }

}
