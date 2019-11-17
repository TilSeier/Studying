package com.tilseier.studying.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.tilseier.studying.R;
import com.tilseier.studying.models.MainMenuItem;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MainMenuItemsAdapter extends RecyclerView.Adapter<MainMenuItemsAdapter.ItemViewHolder> {

    public interface MainMenuItemsListener{
        void onItemClick(String methodName, View view);
    }

    ArrayList<MainMenuItem> mainMenuItems;
    MainMenuItemsListener listener;

    public static class ItemViewHolder extends RecyclerView.ViewHolder {

        Button button;

        public ItemViewHolder(@NonNull View itemView, final ArrayList<MainMenuItem> mainMenuItems, final MainMenuItemsListener holderListener) {
            super(itemView);
            button = itemView.findViewById(R.id.btn_list_item);

//            button.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    if (holderListener != null) {
//                        int position = getAdapterPosition();
//                        MainMenuItem mainMenuItem = mainMenuItems.get(position);
//                        if(position != RecyclerView.NO_POSITION) {
//                            holderListener.onItemClick(mainMenuItem.getOnClickMethodName(), view);
//                        }
//                    }
//                }
//            });
        }

    }

    public MainMenuItemsAdapter(ArrayList<MainMenuItem> mainMenuItems, MainMenuItemsListener listener) {
        this.mainMenuItems = mainMenuItems;
        this.listener = listener;
    }

    public void setOnItemClickListener(MainMenuItemsListener listener){
        this.listener = listener;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_main_menu, parent,false);
        ItemViewHolder itemViewHolder = new ItemViewHolder(view, mainMenuItems, listener);

        return itemViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ItemViewHolder itemViewHolder, int i) {
        final MainMenuItem mainMenuItem = mainMenuItems.get(i);

        itemViewHolder.button.setText(mainMenuItem.getText());
        itemViewHolder.button.setOnClickListener(mainMenuItem.getOnClickMethod());
        itemViewHolder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onItemClick(mainMenuItem.getOnClickMethodName(), itemViewHolder.itemView);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mainMenuItems.size();
    }
}
