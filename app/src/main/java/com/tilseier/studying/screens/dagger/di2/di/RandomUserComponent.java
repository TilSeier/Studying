package com.tilseier.studying.screens.dagger.di2.di;

import com.squareup.picasso.Picasso;
import com.tilseier.studying.screens.dagger.di2.interfaces.RandomUsersApi;

import dagger.Component;

@RandomUserApplicationScope
@Component(modules = {RandomUsersModule.class, PicassoModule.class})
public interface RandomUserComponent {
    RandomUsersApi getRandomUserService();
    Picasso getPicasso();
}
