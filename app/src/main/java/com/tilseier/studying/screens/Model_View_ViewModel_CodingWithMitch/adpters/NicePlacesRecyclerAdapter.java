package com.tilseier.studying.screens.Model_View_ViewModel_CodingWithMitch.adpters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tilseier.studying.R;
import com.tilseier.studying.screens.Model_View_ViewModel_CodingWithMitch.models.NicePlace;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NicePlacesRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<NicePlace> mNicePlaces = new ArrayList<>();

    public NicePlacesRecyclerAdapter(List<NicePlace> mNicePlaces) {
        this.mNicePlaces = mNicePlaces;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_nice_place, parent, false);
        return new NicePlaceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        NicePlaceViewHolder viewHolder = (NicePlaceViewHolder) holder;
        viewHolder.textView.setText(mNicePlaces.get(position).getTitle());
        Picasso.with(viewHolder.textView.getContext())
                .load(mNicePlaces.get(position).getImageUrl())
                .into(viewHolder.imageView);
    }

    @Override
    public int getItemCount() {
        return this.mNicePlaces.size();
    }

    public class NicePlaceViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;

        NicePlaceViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv_nice_place);
            imageView = itemView.findViewById(R.id.iv_nice_place);
        }
    }

}
