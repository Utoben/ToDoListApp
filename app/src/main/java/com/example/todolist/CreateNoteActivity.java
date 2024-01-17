package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class CreateNoteActivity extends AppCompatActivity {
    private EditText editTextNote;
    private RadioButton lowRadioButton;
    private RadioButton mediumRadioButton;
    private RadioButton highRadioButton;
    private Button addNoteButton;
    private Database database = Database.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);
        initViews();
        addNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveNote();
                int priority =  getPriority();
            }
        });
    }

    private void initViews(){
        editTextNote = findViewById(R.id.editTextNote);
        lowRadioButton = findViewById(R.id.lowRadioButton);
        mediumRadioButton = findViewById(R.id.mediumRadioButton);
        highRadioButton = findViewById(R.id.highRadioButton);
        addNoteButton =  findViewById(R.id.addNoteButton);
    }
    private  void saveNote(){

        String text = editTextNote.getText().toString().trim();
        int priority = getPriority();
        int id = database.getNotes().size();
        Note note = new Note(id, text, priority);
        database.add(note);

        finish();
    }
    private int getPriority(){
        int priority;
        if (lowRadioButton.isChecked()){
            priority = 0;
        } else if (mediumRadioButton.isChecked()){
            priority = 1;
        } else {
            priority = 2;
        }
        return priority;
    }

    public static Intent newIntent(Context context){
        Intent intent = new Intent(context, CreateNoteActivity.class);
        return intent;

    }
}