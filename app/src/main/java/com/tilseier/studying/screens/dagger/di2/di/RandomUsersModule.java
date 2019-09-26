package com.tilseier.studying.screens.dagger.di2.di;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tilseier.studying.screens.dagger.di2.interfaces.RandomUsersApi;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = OkHttpClientModule.class)
public class RandomUsersModule {

    @RandomUserApplicationScope
    @Provides
    public RandomUsersApi randomUsersApi(Retrofit retrofit){
        return retrofit.create(RandomUsersApi.class);
    }

    @RandomUserApplicationScope
    @Provides
    public Retrofit retrofit(OkHttpClient okHttpClient,
                             GsonConverterFactory gsonConverterFactory, Gson gson){
        return new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://randomuser.me/")
                .addConverterFactory(gsonConverterFactory)
                .build();
    }

    @RandomUserApplicationScope
    @Provides
    public Gson gson(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        return gsonBuilder.create();
    }

    @RandomUserApplicationScope
    @Provides
    public GsonConverterFactory gsonConverterFactory(Gson gson){
        return GsonConverterFactory.create(gson);
    }

}
