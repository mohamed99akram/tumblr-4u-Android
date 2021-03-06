package com.example.tumblr4u.Fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.tumblr4u.Adapters.PostAdapter;
import com.example.tumblr4u.ApiData.RetrieveNotes.Note;
import com.example.tumblr4u.Models.Post;
import com.example.tumblr4u.R;
import com.example.tumblr4u.View.NotesActivity;
import com.example.tumblr4u.View.WritePostActivity;
import com.example.tumblr4u.ViewModel.HomeFragmentViewModel;
import com.google.gson.Gson;

import java.util.List;

/**
 * @author Mohamed Akram
 * @version 1.0
 * @since 17-08-2021
 */
public class HomeFragment extends Fragment {

    private View mRoot;
    private RecyclerView mRecyclerView;
    private HomeFragmentViewModel mHomeFragmentViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        // inflate the fragment
        mRoot = inflater.inflate(R.layout.fragment_home, container, false);

        // Add listener to write post button
        Button writePostButton = mRoot.findViewById(R.id.write_post_button_home_fragment);
        writePostButton.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), WritePostActivity.class);
            startActivity(intent);
        });

        // Add listener to refresh page
        SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout)mRoot.findViewById(R.id.swipeContainer);
        swipeRefreshLayout.setOnRefreshListener(() -> {
            mHomeFragmentViewModel.getposts();
        });

        // Prepare Recycler view
        mRecyclerView = mRoot.findViewById(R.id.posts_following);
        PostAdapter adapter = new PostAdapter(getActivity(), new PostAdapter.OnItemClickListener() {
            @Override
            public void onEditClickListener(Post post) {
                Toast.makeText(mRoot.getContext(), "Edit Pressed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCommentClickListener(Post post) {
//                Toast.makeText(mRoot.getContext(), "Add Comment Pressed", Toast.LENGTH_SHORT)
//                .show();
                Intent intent = new Intent(getActivity(), NotesActivity.class);
                Gson gson = new Gson();
                String postJSON = gson.toJson(post);
                intent.putExtra("postJSON", postJSON);
                Log.i("HomeFragment", "passed postJSON = "+postJSON);
                startActivity(intent);
            }

            @Override
            public void onLikeClickListener(Post post) {
                Toast.makeText(mRoot.getContext(), "Like Pressed", Toast.LENGTH_SHORT).show();
                mHomeFragmentViewModel.pressLikeButton(post);
            }

            @Override
            public void onShareClickListener(Post post) {
                Toast.makeText(mRoot.getContext(), "Share Pressed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onReblogClickListener(Post post) {
                Toast.makeText(mRoot.getContext(), "Reblog Pressed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNotesClickListener(Post post) {
                Toast.makeText(mRoot.getContext(), "Notes Pressed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRemoveClickListener(Post post) {
                Toast.makeText(mRoot.getContext(), "Remove Pressed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onImageOrNameClickListener(Post post) {
                Toast.makeText(mRoot.getContext(), "Image or Name Pressed",
                        Toast.LENGTH_SHORT).show();
            }
        });
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(adapter);


        mHomeFragmentViewModel = new ViewModelProvider(this).get(
                HomeFragmentViewModel.class);
        mHomeFragmentViewModel.getposts();
        //TODO move data between recycler view and view model - observers
        mHomeFragmentViewModel.postsList.observe(getViewLifecycleOwner(),
                posts -> {
                    adapter.setList(posts);
                    mRoot.findViewById(R.id.home_fragment_progress_bar).setVisibility(View.GONE);
                    swipeRefreshLayout.setRefreshing(false);
                });

        return mRoot;
    }
}