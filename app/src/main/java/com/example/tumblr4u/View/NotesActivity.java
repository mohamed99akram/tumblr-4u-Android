package com.example.tumblr4u.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tumblr4u.Adapters.CommentsAdapter;
import com.example.tumblr4u.Models.Comment;
import com.example.tumblr4u.Models.Post;
import com.example.tumblr4u.R;
import com.example.tumblr4u.ViewModel.NotesViewModel;
import com.google.gson.Gson;

/**
 * - This class shows comments, it gets post data from dashboard as json string and converts this
 * string into java object then shows comments.
 * - it allow the user to add a new comment
 * - Lets the user go for likesReblogsActivity
 * */
public class NotesActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private NotesViewModel mNotesViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        // get data from calling activity (comments button of post)
        Bundle extras = getIntent().getExtras();
        Post post;
        String postId;
        String blogId;
        String postJSON;
        int notesCount;
        int likesCount;
        int reblogsCount;
        if (extras != null) {
            postJSON = extras.getString("postJSON");
            Gson gson = new Gson();
            post = gson.fromJson(postJSON, Post.class);
            postId = post.getPostId();
            blogId = post.getBlog_id();
            notesCount = post.getNotesCount();
            likesCount = post.getLikesCount();
            reblogsCount = post.getReblogsCount();

        } else {
            return;
        }
        Toast.makeText(this, "Blog Id = " + blogId, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "Post Id = " + postId, Toast.LENGTH_SHORT).show();
        // set notes count
        TextView textView = findViewById(R.id.notes_count_notes_activity);
        textView.setText(notesCount + "");

        TextView textView1 = findViewById(R.id.notes_likes_count);
        textView1.setText(likesCount + " Likes");

        TextView textView2 = findViewById(R.id.notes_reblogs_count);
        textView2.setText(reblogsCount + " Reblogs");

        EditText addCommentEditText = findViewById(R.id.notes_add_comment);
        // Add comment listener
        findViewById(R.id.reply_button).setOnClickListener(v -> {
            String commentText = addCommentEditText.getText().toString();
            if(commentText.isEmpty()){
            }
            else{
                mNotesViewModel.makeComment(post, commentText);
                addCommentEditText.setText("");
            }
        });
        // Add back button click listener
        findViewById(R.id.notes_back_button).setOnClickListener(v -> {
            onBackPressed();
        });
        // set on click listener to go to LikesReblogs page
        findViewById(R.id.notes_likes_reblogs).setOnClickListener(v -> {
            Intent intent = new Intent(this, LikesReblogsActivity.class);
            intent.putExtra("postJSON", postJSON);
            startActivity(intent);
        });
        // prepare recycler view
        mRecyclerView = findViewById(R.id.comments_recycler_view);
        CommentsAdapter adapter = new CommentsAdapter(this,
                new CommentsAdapter.OnItemClickListener() {
                    @Override
                    public void onImageClickListener(Comment comment) {

                    }

                    @Override
                    public void onUserNameClickListener(Comment comment) {

                    }
                });
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(adapter);

        // ViewModel
        mNotesViewModel = new ViewModelProvider(this).get(NotesViewModel.class);
        mNotesViewModel.commentsList.observe(this, comments -> {
            adapter.setList(comments);
            findViewById(R.id.notes_progress_bar).setVisibility(View.GONE);
        });
        // get comments of this note
        mNotesViewModel.getComments(post);
    }
}