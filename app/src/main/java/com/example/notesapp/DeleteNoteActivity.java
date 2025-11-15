package com.example.notesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Map;

public class DeleteNoteActivity extends AppCompatActivity {

    private Spinner spinnerNotes;
    private Button btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_note);

        spinnerNotes = findViewById(R.id.spinnerNotes);
        btnDelete = findViewById(R.id.btnDelete);

        loadNotesIntoSpinner();

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteSelectedNote();
            }
        });
    }

    private void loadNotesIntoSpinner() {
        SharedPreferences prefs = getSharedPreferences("notes", MODE_PRIVATE);
        Map<String, ?> allNotes = prefs.getAll();

        ArrayList<String> noteNames = new ArrayList<>();

        for (Map.Entry<String, ?> entry : allNotes.entrySet()) {
            noteNames.add(entry.getKey());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                noteNames
        );

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerNotes.setAdapter(adapter);
    }

    private void deleteSelectedNote() {
        String selected = spinnerNotes.getSelectedItem().toString();

        SharedPreferences prefs = getSharedPreferences("notes", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        editor.remove(selected);
        editor.apply();

        Toast.makeText(this, "Note deleted!", Toast.LENGTH_SHORT).show();

        finish();
    }
}
