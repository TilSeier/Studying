package com.tilseier.studying.screens.retrofit2_rxjava;

import android.os.Bundle;

import com.tilseier.studying.R;
import com.tilseier.studying.screens.retrofit2_rxjava.Retrofit.IMyAPI;
import com.tilseier.studying.screens.retrofit2_rxjava.Retrofit.RetrofitClient;
import com.tilseier.studying.screens.retrofit2_rxjava.adapters.PostAdapter;
import com.tilseier.studying.screens.retrofit2_rxjava.models.Post;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class Retrofit2RxJavaActivity extends AppCompatActivity {

    IMyAPI myAPI;
    RecyclerView rvPosts;
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit2_rx_java);

        //Init API
        Retrofit retrofit = RetrofitClient.getInstance();
        myAPI = retrofit.create(IMyAPI.class);

        //View
        rvPosts = findViewById(R.id.rv_posts);
        rvPosts.setHasFixedSize(true);
        rvPosts.setLayoutManager(new LinearLayoutManager(this));

        fetchData();

    }

    private void fetchData() {
        compositeDisposable.add(myAPI.getPosts()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Consumer<List<Post>>() {
            @Override
            public void accept(List<Post> posts) throws Exception {
                displayData(posts);
            }
        }));
    }

    private void displayData(List<Post> posts) {
        PostAdapter adapter = new PostAdapter(this, posts);
        rvPosts.setAdapter(adapter);
    }

    @Override
    protected void onStop() {
        compositeDisposable.clear();
        super.onStop();
    }
}
