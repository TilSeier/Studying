package com.tilseier.studying.screens.dagger.di2.di;

import android.app.Activity;
import android.content.Context;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {

    private final Context context;

    ActivityModule(Activity context){
        this.context = context;
    }

    @Named("activity_context")
    @RandomUserApplicationScope
    @Provides
    public Context context(){ return context; }

}
