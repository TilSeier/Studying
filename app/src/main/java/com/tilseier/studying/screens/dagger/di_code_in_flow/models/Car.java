package com.tilseier.studying.screens.dagger.di_code_in_flow.models;

import com.tilseier.studying.utils.Const;

import javax.inject.Inject;

import timber.log.Timber;

public class Car {

    //The order Dagger work
    //1: call injected constructor
    //2: inject fields
    //3: call injected methods - rarely used - usually we don't use it in the Activities
    //only the case to do method injection if we need to pass instance of object (this) in that method

    //fields and methods injected are only executed if we also do constructor injection
    //so as we can't do constructor injection in the Activity
    //we should trigger our inject process manually by calling inject on the component and passing our Activity
    //CarComponent component = DaggerCarComponent.create();
    //component.inject(this);

    Engine engine;
    Wheels wheels;

    @Inject
    public Car(Engine engine, Wheels wheels) {
        this.engine = engine;
        this.wheels = wheels;
    }

    //so we don't need to call this method, because Dagger will call it
    @Inject //Dagger will automatically execute this method after constructor ready (finished)
    public void enableRemote(Remote remote){
        remote.setListener(this);
    }

    public void drive(){
        engine.start();
        Timber.tag(Const.TAG_DAGGER).e("Driving...");
    }

}
