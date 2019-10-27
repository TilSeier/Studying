package com.tilseier.studying.screens.dagger.di3;

import android.app.Activity;
import android.app.Application;

import com.tilseier.studying.screens.dagger.di2.di.modules.ContextModule;
import com.tilseier.studying.screens.dagger.di2.di.DaggerRandomUserComponent;
import com.tilseier.studying.screens.dagger.di2.di.RandomUserComponent;

import timber.log.Timber;

public class RandomUserApplication  extends Application {

    // добавьте имя этого класса в манифест
    private RandomUserComponent randomUserApplicationComponent;

    public static RandomUserApplication get(Activity activity){
        return (RandomUserApplication) activity.getApplication();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(new Timber.DebugTree());

        randomUserApplicationComponent = DaggerRandomUserComponent.builder()
                .contextModule(new ContextModule(this))
                .build();
    }

    public RandomUserComponent getRandomUserApplicationComponent(){
        return randomUserApplicationComponent;
    }

}
