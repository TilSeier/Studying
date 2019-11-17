package com.tilseier.studying.screens.MVVM_Room_ViewModel_LiveData_RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.tilseier.studying.R;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import timber.log.Timber;

public class MVVMArchitectureActivity extends AppCompatActivity {

    private static final String TAG = "MVVMArchitecture";
    private NoteViewModel noteViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvvmarchitecture);

        final NoteAdapter adapter = new NoteAdapter();

        RecyclerView recyclerView = findViewById(R.id.rv_notes_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);
        noteViewModel.getAllNotes().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                //update RecyclerView
                Toast.makeText(MVVMArchitectureActivity.this, "onChanged", Toast.LENGTH_SHORT).show();
                for (Note note: notes){
                    Timber.tag(TAG).e(note.getTitle());
                }
                adapter.setNotes(notes);
            }
        });
    }

}
