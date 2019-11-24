package com.tilseier.studying.screens.MVVM_Room_ViewModel_LiveData_RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.tilseier.studying.R;

import androidx.appcompat.app.AppCompatActivity;

public class MVVMAddEditNoteActivity extends AppCompatActivity {

    public static final String EXTRA_ID =
            "com.tilseier.studying.screens.MVVM_Room_ViewModel_LiveData_RecyclerView.EXTRA_ID";
    public static final String EXTRA_TITLE =
            "com.tilseier.studying.screens.MVVM_Room_ViewModel_LiveData_RecyclerView.EXTRA_TITLE";
    public static final String EXTRA_DESCRIPTION =
            "com.tilseier.studying.screens.MVVM_Room_ViewModel_LiveData_RecyclerView.EXTRA_DESCRIPTION";
    public static final String EXTRA_PRIORITY =
            "com.tilseier.studying.screens.MVVM_Room_ViewModel_LiveData_RecyclerView.EXTRA_PRIORITY";

    private EditText etTitle;
    private EditText etDescription;
    private NumberPicker npPriority;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvvmadd_note);

        if (getSupportActionBar()!=null) {
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        }

        etTitle = findViewById(R.id.et_title);
        etDescription = findViewById(R.id.et_description);
        npPriority = findViewById(R.id.np_priority);
        npPriority.setMinValue(1);
        npPriority.setMaxValue(10);

        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_ID)){
            setTitle("Edit Note");
            etTitle.setText(intent.getStringExtra(EXTRA_TITLE));
            etDescription.setText(intent.getStringExtra(EXTRA_DESCRIPTION));
            npPriority.setValue(intent.getIntExtra(EXTRA_PRIORITY, 1));
        } else {
            setTitle("Add Note");
        }

    }

    private void saveNote() {
        String title = etTitle.getText().toString();
        String description = etDescription.getText().toString();
        int priority = npPriority.getValue();

        if (title.trim().isEmpty() || description.trim().isEmpty()){
            Toast.makeText(this, "Fill in the fields", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_TITLE, title);
        data.putExtra(EXTRA_DESCRIPTION, description);
        data.putExtra(EXTRA_PRIORITY, priority);

        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        if (id != -1){
            data.putExtra(EXTRA_ID, id);
        }

        setResult(RESULT_OK, data);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_note_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.save_note:
                saveNote();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
