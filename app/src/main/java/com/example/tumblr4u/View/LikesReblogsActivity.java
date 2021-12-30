package com.example.tumblr4u.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tumblr4u.Adapters.LikesReblogsAdapter;
import com.example.tumblr4u.Models.Post;
import com.example.tumblr4u.R;
import com.example.tumblr4u.ViewModel.LikesReblogsViewModel;
import com.google.gson.Gson;

public class LikesReblogsActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private LikesReblogsViewModel mLikesReblogsViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_likes_reblogs);

        // get data from calling activity ( NotesActivity )
        Bundle extras = getIntent().getExtras();
        String postId;
        int notesCount;
        Post post;
        if (extras != null) {
            String postJSON = extras.getString("postJSON");
            Gson gson = new Gson();
            post = gson.fromJson(postJSON, Post.class);
            postId = post.getPostId();
            notesCount = post.getNotesCount();
        } else {
            Toast.makeText(this, "No data passed to LikesReblogsActivity",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        // On back pressed
        findViewById(R.id.likes_reblogs_back_button).setOnClickListener(v -> {
            onBackPressed();
        });
        TextView textView = findViewById(R.id.notes_count_likes_reblogs_activity);
        textView.setText(notesCount + " notes");
        // prepare recycler view TODO: adapter, listener
        mRecyclerView = findViewById(R.id.likes_reblogs_recycler_view);
        LikesReblogsAdapter adapter = new LikesReblogsAdapter(this, likeReblog -> {
            // TODO go to blog page
        });
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(adapter);
        //  ViewModel
        mLikesReblogsViewModel = new ViewModelProvider(this).get(LikesReblogsViewModel.class);
        mLikesReblogsViewModel.likeReblogList.observe(this, likeReblogList -> {
            adapter.setList(likeReblogList);
            findViewById(R.id.likes_reblogs_progress_bar).setVisibility(View.GONE);
        });
        // get likes, reblogs
        mLikesReblogsViewModel.getLikesReblogs(post);
    }
}