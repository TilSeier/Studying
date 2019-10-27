package com.tilseier.studying.screens.dagger.di_code_in_flow;

import com.tilseier.studying.screens.dagger.DaggerActivityCodeInFlow;
import com.tilseier.studying.screens.dagger.di_code_in_flow.modules.PetrolEngineModule;
import com.tilseier.studying.screens.dagger.di_code_in_flow.modules.WheelsModule;

import javax.inject.Named;

import dagger.BindsInstance;
import dagger.Component;

@Component (modules = {WheelsModule.class, PetrolEngineModule.class})//PetrolEngineModule.class
public interface CarComponent {

//    Car getCar();
    void inject(DaggerActivityCodeInFlow daggerActivityCodeInFlow);

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder provideHorsePower(@Named("horse power") int horsePower);

        @BindsInstance
        Builder provideEngineCapacity(@Named("engine capacity") int engineCapacity);

        CarComponent build();

    }

}
