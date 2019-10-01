package com.tilseier.studying.screens.retrofit2_rxjava.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tilseier.studying.R;
import com.tilseier.studying.screens.retrofit2_rxjava.models.Post;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostViewHolder> {

    Context context;
    List<Post> postList;

    public PostAdapter(Context context, List<Post> postList) {
        this.context = context;
        this.postList = postList;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_item_post_layout, viewGroup, false);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder postViewHolder, int i) {

        postViewHolder.tvTitle.setText(String.valueOf(postList.get(i).title));
        postViewHolder.tvContent.setText(new StringBuilder(postList.get(i).body.substring(0, 20))
            .append("..."));
        postViewHolder.tvAuthor.setText(String.valueOf(postList.get(i).userId));

    }

    @Override
    public int getItemCount() {
        return postList.size();
    }
}
