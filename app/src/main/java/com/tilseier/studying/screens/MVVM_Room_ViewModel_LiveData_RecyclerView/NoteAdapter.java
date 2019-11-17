package com.tilseier.studying.screens.MVVM_Room_ViewModel_LiveData_RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tilseier.studying.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteHolder> {
    private List<Note> notes = new ArrayList<>();

    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.note_item, parent, false);
        return new NoteHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteHolder holder, int position) {
        Note currentNote = notes.get(position);
        holder.tvTitle.setText(currentNote.getTitle());
        holder.tvDescription.setText(currentNote.getDescription());
        holder.tvPriority.setText(String.valueOf(currentNote.getPriority()));
    }

    public void setNotes(List<Note> notes){
        this.notes = notes;
        notifyDataSetChanged();//it is bad practice to use this method in the adapter
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    class NoteHolder extends RecyclerView.ViewHolder {
        private TextView tvTitle;
        private TextView tvDescription;
        private TextView tvPriority;

        public NoteHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvDescription = itemView.findViewById(R.id.tv_description);
            tvPriority = itemView.findViewById(R.id.tv_priority);
        }
    }
}
