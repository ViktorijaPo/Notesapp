package com.example.notesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.widget.Toolbar;


import java.util.ArrayList;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private ListView listNotes;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> noteList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.myToolbar);
        setSupportActionBar(findViewById(R.id.myToolbar));


        listNotes = findViewById(R.id.listNotes);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadNotes();
    }

    private void loadNotes() {
        SharedPreferences prefs = getSharedPreferences("notes", MODE_PRIVATE);
        Map<String, ?> allNotes = prefs.getAll();

        noteList = new ArrayList<>();

        for (Map.Entry<String, ?> entry : allNotes.entrySet()) {
            String name = entry.getKey();
            String content = entry.getValue().toString();
            noteList.add(name + ": " + content);
        }

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, noteList);
        listNotes.setAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.menuAdd) {
            Intent i = new Intent(this, AddNoteActivity.class);
            startActivity(i);
            return true;
        }

        if (id == R.id.menuDelete) {
            Intent i = new Intent(this, DeleteNoteActivity.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
