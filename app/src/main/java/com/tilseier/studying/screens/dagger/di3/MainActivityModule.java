package com.tilseier.studying.screens.dagger.di3;

import com.squareup.picasso.Picasso;
import com.tilseier.studying.screens.dagger.DaggerActivity3;
import com.tilseier.studying.screens.dagger.di2.adapter.RandomUserAdapter;

import dagger.Module;
import dagger.Provides;

@Module
public class MainActivityModule {

    private final DaggerActivity3 mainActivity;

    public MainActivityModule(DaggerActivity3 mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Provides
    @MainActivityScope
    public RandomUserAdapter randomUserAdapter(Picasso picasso){
        return new RandomUserAdapter(mainActivity, picasso);
    }
}
