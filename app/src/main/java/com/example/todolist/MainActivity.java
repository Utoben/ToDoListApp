package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        Random random = new Random();

        for(int i = 0; i < 20; i++){
            Note note = new Note(i,"Note" + i, random.nextInt(3) );
            notes.add(note);
        }
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
        recyclerViewNotes.removeAllViews();
        for(Note note : database.getNotes() ){
           View view = getLayoutInflater().inflate(
                   R.layout.note_item,
                   recyclerViewNotes,
                   false
           );
           view.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   database.remove(note.getId());
                   showNotes();
               }
           });
            TextView textViewNote = view.findViewById(R.id.textViewNote);
            textViewNote.setText(note.getText());

            int colorResId;
            switch (note.getPriority()){
                case 0:
                    colorResId = android.R.color.holo_green_light;
                    break;
                case 1:
                    colorResId = android.R.color.holo_orange_light;
                    break;
//                case 2:
//                    colorResId = android.R.color.holo_green_light;
//                    break;
//                case 3:
//                    colorResId = android.R.color.holo_green_light;
//                    break;
                default:
                    colorResId = android.R.color.holo_red_light;
            }
            int color = ContextCompat.getColor(this,colorResId);
            textViewNote.setBackgroundColor(color);
            recyclerViewNotes.addView(view);
        }
    }

    private void initViews(){
        recyclerViewNotes = findViewById(R.id.recyclerViewNotes);
        butttonAddNote = findViewById(R.id.floatingActionButton);
    }

}