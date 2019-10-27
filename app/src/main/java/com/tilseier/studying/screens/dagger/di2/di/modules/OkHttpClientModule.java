package com.tilseier.studying.screens.dagger.di2.di.modules;

import android.content.Context;

import com.tilseier.studying.screens.dagger.di2.di.ApplicationContext;
import com.tilseier.studying.screens.dagger.di2.di.RandomUserApplicationScope;

import java.io.File;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import timber.log.Timber;

@Module(includes = ContextModule.class)
public class OkHttpClientModule {

    @RandomUserApplicationScope
    @Provides
    public OkHttpClient okHttpClient(Cache cache, HttpLoggingInterceptor httpLoggingInterceptor){
        return new OkHttpClient()
                .newBuilder()
                .cache(cache)
                .addInterceptor(httpLoggingInterceptor)
                .build();
    }

    @RandomUserApplicationScope
    @Provides
    public Cache cache(File cacheFile){
        return new Cache(cacheFile, 10 * 1000 * 1000); //10 MB
    }

    @RandomUserApplicationScope
    @Provides
    public File file(@ApplicationContext Context context){//@Named("application_context")
        File file = new File(context.getCacheDir(), "HttpCache");
        file.mkdirs();
        return file;
    }

    @RandomUserApplicationScope
    @Provides
    public HttpLoggingInterceptor httpLoggingInterceptor(){
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Timber.d(message);
            }
        });
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return httpLoggingInterceptor;
    }

}
