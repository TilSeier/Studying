package com.tilseier.studying.screens.dagger.di_code_in_flow.modules;

import com.tilseier.studying.screens.dagger.di_code_in_flow.models.DieselEngine;
import com.tilseier.studying.screens.dagger.di_code_in_flow.models.Engine;

import dagger.Module;
import dagger.Provides;

@Module
public class DieselEngineModule {

    private int horsePower;

    public DieselEngineModule(int horsePower){
        this.horsePower = horsePower;
    }

    @Provides
    int provideHorsePower(){
        return horsePower;
    }

    @Provides
    Engine providesEngine(DieselEngine engine) {
        return engine;
    }


//    @Provides
//    Engine providesEngine() {
//        return new DieselEngine(horsePower);
//    }


//    @Provides
//    Engine providesEngine(DieselEngine engine) {
//        return engine;
//    }

    //instead of this we also can use next
    //we should use @Binds instead of Provides whenever we want to bind our implementation to an interface
    //Engine engine = new DieselEngine()
    //because it is more concise and more officiant

//    @Binds
//    abstract Engine bindsEngine(DieselEngine engine);

}
