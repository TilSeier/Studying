package com.tilseier.studying.screens.retrofit2_rxjava.adapters;

import android.view.View;
import android.widget.TextView;

import com.tilseier.studying.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PostViewHolder extends RecyclerView.ViewHolder {
    TextView tvTitle, tvContent, tvAuthor;
    public PostViewHolder(@NonNull View itemView) {
        super(itemView);

        tvTitle = itemView.findViewById(R.id.tv_title);
        tvContent = itemView.findViewById(R.id.tv_content);
        tvAuthor = itemView.findViewById(R.id.tv_author);

    }

}
