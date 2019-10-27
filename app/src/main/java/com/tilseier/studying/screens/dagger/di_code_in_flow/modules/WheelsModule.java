package com.tilseier.studying.screens.dagger.di_code_in_flow.modules;

import com.tilseier.studying.screens.dagger.di_code_in_flow.models.Rims;
import com.tilseier.studying.screens.dagger.di_code_in_flow.models.Tires;
import com.tilseier.studying.screens.dagger.di_code_in_flow.models.Wheels;

import dagger.Module;
import dagger.Provides;

@Module
public class WheelsModule {

    //we usually should use modules to provide third party components
    //also we should use modules to handle interface dependency injection

    @Provides
    static Rims provideRims(){
        return new Rims();
    }

    @Provides
    static Tires provideTires(){
        Tires tires = new Tires();
        tires.inflate();
        return tires;
    }

    @Provides
    static Wheels provideWheels(Rims rims, Tires tires) {
        return new Wheels(tires, rims);
    }

}
