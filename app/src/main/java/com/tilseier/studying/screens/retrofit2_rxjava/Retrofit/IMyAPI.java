package com.tilseier.studying.screens.retrofit2_rxjava.Retrofit;

import com.tilseier.studying.screens.retrofit2_rxjava.models.Post;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface IMyAPI {

    @GET("posts")
    Observable<List<Post>> getPosts();

}
