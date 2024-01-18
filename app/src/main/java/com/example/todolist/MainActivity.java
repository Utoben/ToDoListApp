package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerViewNotes;
    private FloatingActionButton butttonAddNote;
    private Database database = Database.getInstance();
    private ArrayList<Note> notes = new ArrayList<>();
    public NotesAdapter notesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        notesAdapter = new NotesAdapter();
        recyclerViewNotes.setAdapter(notesAdapter);
        recyclerViewNotes.setLayoutManager(new LinearLayoutManager(this));

//        Random random = new Random();
//
//        for(int i = 0; i < 20; i++){
//            Note note = new Note(i,"Note" + i, random.nextInt(3) );
//            notes.add(note);
//        }
//        showNotes();

        butttonAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = CreateNoteActivity.newIntent(MainActivity.this);
                startActivity(intent);
            }
        });
    }
@   Override
    protected void onResume(){
        super.onResume();
        showNotes();
    }

    private  void showNotes(){
        notesAdapter.setNotes(database.getNotes());
//        recyclerViewNotes.removeAllViews();
//        for(Note note : database.getNotes() ){
//
//           );
//           view.setOnClickListener(new View.OnClickListener() {
//               @Override
//               public void onClick(View v) {
//                   database.remove(note.getId());
//                   showNotes();
//               }
//           });
//
//            recyclerViewNotes.addView(view);
//        }
    }

    private void initViews(){
        recyclerViewNotes = findViewById(R.id.recyclerViewNotes);
        butttonAddNote = findViewById(R.id.floatingActionButton);
    }

}