package com.example.tumblr4u.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tumblr4u.R;
import com.example.tumblr4u.adapters.PostAdapter;
import com.example.tumblr4u.models.Post;

import java.util.ArrayList;

/**
 * @author Mahmoud Amr Nabil
 * @version 1.0
 * @since 11/8/2021
 */
public class HomeFragment extends Fragment {

    private View root;
    private RecyclerView posts;


    @SuppressLint("FragmentLiveDataObserve")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_home, container, false);
        posts=root.findViewById(R.id.posts_following);

        /*LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
        posts.setLayoutManager(layoutManager);
        posts.setHasFixedSize(false);
        posts.setNestedScrollingEnabled(false);

        HomeFragmentViewModel homeFragmentViewModel = new HomeFragmentViewModel();

        homeFragmentViewModel.getposts().observe(this, new Observer<ArrayList<Post>>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onChanged(ArrayList<Post> posts1) {
                PostAdapter adapter=new PostAdapter(posts1);
                posts.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        });*/
        return root;
    }
}