package com.example.tumblr4u.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tumblr4u.R;

public class NotesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
        Bundle extras = getIntent().getExtras();
        String postId;
        String blogId;
        int notesCount;
        if(extras!=null){
            postId = extras.getString("postId");
            blogId = extras.getString("blogId");
            notesCount = extras.getInt("notesCount");
        }
        else{
            return;
        }
        TextView textView =  findViewById(R.id.notes_count_notes_activity);
        textView.setText(notesCount+"");
        Toast.makeText(this, "Blog Id = "+ blogId, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "Post Id = "+ postId, Toast.LENGTH_SHORT).show();
    }
}