package com.tilseier.studying.screens.retrofit;

import com.tilseier.studying.screens.retrofit.model.Comment;
import com.tilseier.studying.screens.retrofit.model.Post;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface JsonPlaceHolderApi {

    @GET("posts")
    Call<List<Post>> getPosts();

    @GET("posts")//posts?userId=2&_sort=id&_order=desc
    Call<List<Post>> getPosts2(
            @Query("userId") Integer userId,//instead of int; can be null
            @Query("userId") Integer userId2,//instead of int; can be null
            @Query("_sort") String sort,
            @Query("_order") String order
    );

    @GET("posts")//posts?userId=2&_sort=id&_order=desc
    Call<List<Post>> getPosts3(
            @Query("userId") Integer[] userIds,//instead of int; can be null
            @Query("_sort") String sort,
            @Query("_order") String order
    );

    @GET("posts")
    Call<List<Post>> getPosts4(@QueryMap Map<String, String> parameters);

    @GET("posts/{id}/comments")
    Call<List<Comment>> getComments(@Path("id") int postId);

    @GET("comments")//comments?postId=1
    Call<List<Comment>> getComments2(@Query("postId") int postId);

    @GET
    Call<List<Comment>> getComments3(@Url String url);

    @POST("posts")
    Call<Post> createPost(@Body Post post);

    @FormUrlEncoded
    @POST("posts")
    Call<Post> createPost2(
            @Field("userId") int userId,
            @Field("title") String title,
            @Field("body") String text
    );

    @FormUrlEncoded
    @POST("posts")
    Call<Post> createPost3(@FieldMap Map<String, String> fields);

    //Change Whole Object
    @PUT("posts/{id}")
    Call<Post> putPost(@Path("id") String id, @Body Post post);

    @Headers({"Static-Header1: 123", "Static-Header2: 456"})
    @PUT("posts/{id}")
    Call<Post> putPostWithHeaders(@Header ("Dynamic-Header") String header,
                                  @Path("id") String id,
                                  @Body Post post);

    //Change Only Exist Object's Fields
    @PATCH("posts/{id}")
    Call<Post> patchPost(@Path("id") String id, @Body Post post);

    @PATCH("posts/{id}")
    Call<Post> patchPostWithHeaders(@HeaderMap Map<String, String> headers,
                                   @Path("id") String id,
                                   @Body Post post);

    @DELETE("posts/{id}")
    Call<Void> deletePost(@Path("id") int id);

}
