package com.tilseier.studying.screens.MVVM_Room_ViewModel_LiveData_RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tilseier.studying.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

//ListAdapter also uses DiffUtils
//so we shouldn't handle changes in the list with animation ourselves
public class NoteAdapter extends ListAdapter<Note, NoteAdapter.NoteHolder> {//RecyclerView.Adapter

    private OnItemClickListener listener;

    //since we use ListAdapter we don't need to save our list this way
//    private List<Note> notes = new ArrayList<>();

    public NoteAdapter() {
        super(DIFF_CALLBACK);
    }

    public static final DiffUtil.ItemCallback<Note> DIFF_CALLBACK = new DiffUtil.ItemCallback<Note>() {
        @Override
        public boolean areItemsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
            //we only return "true" if nothing within the item changed
//            return oldItem.equals(newItem);//important!!! we should to override "equals" in the object

            return oldItem.getTitle().equals(newItem.getTitle())
                    && oldItem.getDescription().equals(newItem.getDescription())
                    && oldItem.getPriority() == newItem.getPriority();
        }

    };

    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.note_item, parent, false);
        return new NoteHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteHolder holder, int position) {
//        Note currentNote = notes.get(position);
        Note currentNote = getItem(position);
        holder.tvTitle.setText(currentNote.getTitle());
        holder.tvDescription.setText(currentNote.getDescription());
        holder.tvPriority.setText(String.valueOf(currentNote.getPriority()));
    }

    public Note getNoteAt(int position) {
//        return notes.get(position);
        return getItem(position);
    }

//    public void setNotes(List<Note> notes) {
//        this.notes = notes;
//        //this method tells that whole list of data in adapter is invalid so we redraw everything
//        notifyDataSetChanged();//it is bad practice to use this method in the adapter// instead we can use notifyItemChanged and so on
//    }

    //ListAdapter will take care of this
//    @Override
//    public int getItemCount() {
//        return notes.size();
//    }

    class NoteHolder extends RecyclerView.ViewHolder {
        private TextView tvTitle;
        private TextView tvDescription;
        private TextView tvPriority;

        public NoteHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvDescription = itemView.findViewById(R.id.tv_description);
            tvPriority = itemView.findViewById(R.id.tv_priority);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
//                        listener.onItemClick(notes.get(position));
                        listener.onItemClick(getItem(position));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Note note);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

}
