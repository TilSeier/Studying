package com.tilseier.studying.screens.dagger.di2.di.modules;

import android.content.Context;

import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;
import com.tilseier.studying.screens.dagger.di2.di.ApplicationContext;
import com.tilseier.studying.screens.dagger.di2.di.RandomUserApplicationScope;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

@Module(includes = OkHttpClientModule.class)
public class PicassoModule {

    @RandomUserApplicationScope
    @Provides
    public Picasso picasso(@ApplicationContext Context context, OkHttp3Downloader okHttp3Downloader){//@Named("application_context") => @ApplicationContext
        return new Picasso.Builder(context).
                downloader(okHttp3Downloader).
                build();
    }

    @RandomUserApplicationScope
    @Provides
    public OkHttp3Downloader okHttp3Downloader(OkHttpClient okHttpClient){
        return new OkHttp3Downloader(okHttpClient);
    }

}
