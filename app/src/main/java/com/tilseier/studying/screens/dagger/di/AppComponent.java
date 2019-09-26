package com.tilseier.studying.screens.dagger.di;

import dagger.Component;

@Component(modules = BraavosModule.class)
public interface AppComponent {

    War getWar();

    Boltons getBoltons();
    Starks getStarks();

    Cash getCash();
    Soldiers getSoldiers();

}
