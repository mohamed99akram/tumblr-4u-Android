package com.example.tumblr4u.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tumblr4u.Adapters.LikesReblogsAdapter;
import com.example.tumblr4u.R;
import com.example.tumblr4u.ViewModel.LikesReblogsViewModel;

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
        if (extras != null) {
            postId = extras.getString("postId");
            notesCount = extras.getInt("notesCount");
        } else {
            Toast.makeText(this, "No data passed to LikesReblogsActivity",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        TextView textView = findViewById(R.id.notes_count_likes_reblogs_activity);
        textView.setText(notesCount+"");
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
        });
        // get likes, reblogs
        mLikesReblogsViewModel.getLikesReblogs(postId);
    }
}