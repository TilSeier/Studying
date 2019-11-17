package com.tilseier.studying.screens.retrofit;

import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tilseier.studying.R;
import com.tilseier.studying.screens.retrofit.model.Comment;
import com.tilseier.studying.screens.retrofit.model.Post;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitActivity extends AppCompatActivity {

    private TextView tvResult;

    private JsonPlaceHolderApi jsonPlaceHolderApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);

        tvResult = findViewById(R.id.tv_results);

        Gson gson = new GsonBuilder().serializeNulls().create();//Gson will put NULL into a JSON

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.level(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @NotNull
                    @Override
                    public okhttp3.Response intercept(@NotNull Chain chain) throws IOException {
                        Request originalRequest = chain.request();

                        Request newRequest = originalRequest.newBuilder()
                                .header("Interceptor-Header", "in")
                                .build();

                        return chain.proceed(newRequest);
                    }
                })
                .addInterceptor(httpLoggingInterceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())//gson
                .client(okHttpClient)
                .build();

        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

//        getPosts();
//        getComments();
//        createPost();
        updatePost();
//        deletePost();
    }

    private void getPosts(){
        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put("userId", "2");
        parameters.put("_sort", "id");
        parameters.put("_order", "desc");

        Call<List<Post>> call = jsonPlaceHolderApi.getPosts4(parameters);
//        Call<List<Post>> call = jsonPlaceHolderApi.getPosts2(1, 2, "id", "desc");
//        Call<List<Post>> call = jsonPlaceHolderApi.getPosts3(new Integer[]{3,4,5}, "id", "desc");
//        Call<List<Post>> call = jsonPlaceHolderApi.getPosts2(1, 2, null, null);

        //Do on Background Thread | Instead of execute
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {

                if(!response.isSuccessful()){
                    tvResult.setText("Response Code: " + response.code());
                    return;
                }

                List<Post> posts = response.body();
                for (Post post: posts){
                    String content = "";
                    content += "ID: " + post.getId() + "\n";
                    content += "User ID: " + post.getUserId() + "\n";
                    content += "Title: " + post.getTitle() + "\n";
                    content += "Text: " + post.getText() + "\n";

                    tvResult.append(content);
                }

            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                tvResult.setText("On Failure: " + t.getLocalizedMessage());
            }
        });
    }

    private void getComments(){
//        Call<List<Comment>> call = jsonPlaceHolderApi.getComments(5);
//        Call<List<Comment>> call = jsonPlaceHolderApi.getComments2(5);
        Call<List<Comment>> call = jsonPlaceHolderApi.getComments3("posts/3/comments");

        //Do on Background Thread | Instead of execute
        call.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {

                if(!response.isSuccessful()){
                    tvResult.setText("Response Code: " + response.code());
                    return;
                }

                List<Comment> comments = response.body();
                for (Comment comment: comments){
                    String content = "";
                    content += "ID: " + comment.getId() + "\n";
                    content += "Post ID: " + comment.getPostId() + "\n";
                    content += "Name: " + comment.getName() + "\n";
                    content += "Email: " + comment.getEmail() + "\n";
                    content += "Text: " + comment.getText() + "\n";

                    tvResult.append(content);
                }

            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {
                tvResult.setText("On Failure: " + t.getLocalizedMessage());
            }
        });
    }

    private void createPost(){
//        Post post = new Post(24, "New Post", "New Test Post");
//        Call<Post> postCall = jsonPlaceHolderApi.createPost(post);

//        Call<Post> postCall = jsonPlaceHolderApi.createPost2(24, "New Post", "New Test Post");

        Map<String, String> fields = new HashMap<>();
        fields.put("userId", "24");
        fields.put("title", "New Post");
        fields.put("body", "New Test Post");

        Call<Post> postCall = jsonPlaceHolderApi.createPost3(fields);

        postCall.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {

                if(!response.isSuccessful()){
                    tvResult.setText("Response Code: " + response.code());
                    return;
                }

                Post post = response.body();
                String content = "";
                content += "Code: " + response.code() + "\n";
                content += "ID: " + post.getId() + "\n";
                content += "Post ID: " + post.getUserId() + "\n";
                content += "Email: " + post.getTitle() + "\n";
                content += "Text: " + post.getText() + "\n";

                tvResult.append(content);

            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                tvResult.setText("On Failure: " + t.getLocalizedMessage());
            }
        });

    }

    private void updatePost(){

        Post post = new Post(12, null, "New Text");

//        Call<Post> call = jsonPlaceHolderApi.putPost("5", post);

//        Call<Post> call = jsonPlaceHolderApi.patchPost("5", post);

//        Call<Post> call = jsonPlaceHolderApi.putPostWithHeaders("abc", "5", post);

        Map<String, String> headers = new HashMap<>();
        headers.put("Map-Header1", "123");
        headers.put("Map-Header2", "456");
        headers.put("Map-Header3", "789");

        Call<Post> call = jsonPlaceHolderApi.patchPostWithHeaders(headers,"5", post);

        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {

                if(!response.isSuccessful()){
                    tvResult.setText("Response Code: " + response.code());
                    return;
                }

                Post post = response.body();
                String content = "";
                content += "Code: " + response.code() + "\n";
                content += "ID: " + post.getId() + "\n";
                content += "Post ID: " + post.getUserId() + "\n";
                content += "Email: " + post.getTitle() + "\n";
                content += "Text: " + post.getText() + "\n";

                tvResult.append(content);

            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                tvResult.setText("On Failure: " + t.getLocalizedMessage());
            }
        });

    }

    private void deletePost(){

        Call<Void> call = jsonPlaceHolderApi.deletePost(5);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                tvResult.setText("Response Code: " + response.code());
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                tvResult.setText("On Failure: " + t.getLocalizedMessage());
            }
        });

    }

}
