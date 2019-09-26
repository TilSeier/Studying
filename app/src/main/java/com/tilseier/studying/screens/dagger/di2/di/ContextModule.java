package com.tilseier.studying.screens.dagger.di2.di;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

@Module
public class ContextModule {

    Context context;

    public ContextModule(Context context){
        this.context = context;
    }

//    @Named("application_context")
    @ApplicationContext
    @RandomUserApplicationScope
    @Provides
    public Context context(){ return context.getApplicationContext(); }

}
