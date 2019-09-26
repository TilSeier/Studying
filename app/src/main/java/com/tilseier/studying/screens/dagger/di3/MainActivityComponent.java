package com.tilseier.studying.screens.dagger.di3;

import com.tilseier.studying.screens.dagger.DaggerActivity3;
import com.tilseier.studying.screens.dagger.di2.di.RandomUserComponent;

import dagger.Component;

@Component(modules = MainActivityModule.class, dependencies = RandomUserComponent.class)
@MainActivityScope
public interface MainActivityComponent {

    void inject(DaggerActivity3 daggerActivity3);

//    RandomUserAdapter getRandomUserAdapter();
//    RandomUsersApi getRandomUserService();
}
