package com.tilseier.studying.screens.dagger.di;

import dagger.Module;
import dagger.Provides;

@Module
public class BraavosModule {
    Cash cash;
    Soldiers soldiers;

    public BraavosModule(Cash cash, Soldiers soldiers){
        this.cash=cash;
        this.soldiers=soldiers;
    }

    @Provides // Предоставляет зависимость Cash
    Cash provideCash(){
        return cash;
    }

    @Provides // Предоставляет зависимость Soldiers
    Soldiers provideSoldiers(){
        return soldiers;
    }
}
