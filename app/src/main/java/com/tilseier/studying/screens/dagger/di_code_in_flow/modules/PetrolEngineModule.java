package com.tilseier.studying.screens.dagger.di_code_in_flow.modules;

import com.tilseier.studying.screens.dagger.di_code_in_flow.models.Engine;
import com.tilseier.studying.screens.dagger.di_code_in_flow.models.PetrolEngine;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class PetrolEngineModule {

//    @Provides
//    Engine providesEngine(PetrolEngine engine) {
//        return engine;
//    }

    //instead of this we also can use next
    //we should use @Binds instead of Provides whenever we want to bind our implementation to an interface
    //Engine engine = new PetrolEngine()

    @Binds
    abstract Engine bindsEngine(PetrolEngine engine);

}
