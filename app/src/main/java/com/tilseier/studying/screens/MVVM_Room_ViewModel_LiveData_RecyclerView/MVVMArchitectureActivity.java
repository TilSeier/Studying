package com.tilseier.studying.screens.MVVM_Room_ViewModel_LiveData_RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.tilseier.studying.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MVVMArchitectureActivity extends AppCompatActivity {

    public static final int ADD_NOTE_REQUEST = 1;
    public static final int EDIT_NOTE_REQUEST = 2;

    private static final String TAG = "MVVMArchitecture";
    private NoteViewModel noteViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvvmarchitecture);

        FloatingActionButton btnAddNote = findViewById(R.id.btn_add_note);
        btnAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MVVMArchitectureActivity.this, MVVMAddEditNoteActivity.class);
                startActivityForResult(intent, ADD_NOTE_REQUEST);
            }
        });

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
//                adapter.setNotes(notes);
                adapter.submitList(notes);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                noteViewModel.delete(adapter.getNoteAt(viewHolder.getAdapterPosition()));
                Toast.makeText(MVVMArchitectureActivity.this, "Note deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);

        adapter.setOnItemClickListener(new NoteAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Note note) {
                Intent intent = new Intent(MVVMArchitectureActivity.this, MVVMAddEditNoteActivity.class);
                intent.putExtra(MVVMAddEditNoteActivity.EXTRA_ID, note.getId());
                intent.putExtra(MVVMAddEditNoteActivity.EXTRA_TITLE, note.getTitle());
                intent.putExtra(MVVMAddEditNoteActivity.EXTRA_DESCRIPTION, note.getDescription());
                intent.putExtra(MVVMAddEditNoteActivity.EXTRA_PRIORITY, note.getPriority());
                startActivityForResult(intent, EDIT_NOTE_REQUEST);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_NOTE_REQUEST && resultCode == RESULT_OK) {
            String title = data.getStringExtra(MVVMAddEditNoteActivity.EXTRA_TITLE);
            String description = data.getStringExtra(MVVMAddEditNoteActivity.EXTRA_DESCRIPTION);
            int priority = data.getIntExtra(MVVMAddEditNoteActivity.EXTRA_PRIORITY, 1);

            Note note = new Note(title, description, priority);
            noteViewModel.insert(note);

            Toast.makeText(this, "Note saved", Toast.LENGTH_SHORT).show();
        } else if (requestCode == EDIT_NOTE_REQUEST && resultCode == RESULT_OK) {
            if (data != null) {
                int id = data.getIntExtra(MVVMAddEditNoteActivity.EXTRA_ID, -1);
                if (id == -1){
                    Toast.makeText(this, "Note can't be updated", Toast.LENGTH_SHORT).show();
                    return;
                }

                String title = data.getStringExtra(MVVMAddEditNoteActivity.EXTRA_TITLE);
                String description = data.getStringExtra(MVVMAddEditNoteActivity.EXTRA_DESCRIPTION);
                int priority = data.getIntExtra(MVVMAddEditNoteActivity.EXTRA_PRIORITY, 1);

                Note note = new Note(title, description, priority);
                note.setId(id);
                noteViewModel.update(note);

                Toast.makeText(this, "Note updated", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this, "Note can't be updated", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Note not saved", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.mvvm_main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.delete_all_notes:
                noteViewModel.deleteAllNotes();
                Toast.makeText(this, "All notes deleted", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}