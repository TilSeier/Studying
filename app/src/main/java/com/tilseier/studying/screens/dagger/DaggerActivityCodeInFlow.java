package com.tilseier.studying.screens.dagger;

import android.os.Bundle;

import com.tilseier.studying.R;
import com.tilseier.studying.screens.dagger.di_code_in_flow.CarComponent;
import com.tilseier.studying.screens.dagger.di_code_in_flow.DaggerCarComponent;
import com.tilseier.studying.screens.dagger.di_code_in_flow.models.Car;

import javax.inject.Inject;

import androidx.appcompat.app.AppCompatActivity;

public class DaggerActivityCodeInFlow extends AppCompatActivity {

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


    @Inject
    Car car;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dagger_code_in_flow);

        //BEFORE DAGGER
//        Block block = new Block();
//        Cylinders cylinders = new Cylinders();
//        SparkPlugs sparkPlugs = new SparkPlugs();
//        Tires tires = new Tires();
//        Rims rims = new Rims();
//
//        Engine engine = new Engine(block, cylinders, sparkPlugs);
//        Wheels wheels = new Wheels(tires, rims);
//
//        Car car = new Car(engine, wheels);
//        car.drive();

        //AFTER DAGGER

        CarComponent component = DaggerCarComponent.builder()
                .provideHorsePower(100)
                .provideEngineCapacity(1400)
                .build();
//                .dieselEngineModule(new DieselEngineModule(100))
//                .build();
        component.inject(this);

//        CarComponent component = DaggerCarComponent.create();
//        component.inject(this);

//        car = component.getCar();
        car.drive();

    }
}
