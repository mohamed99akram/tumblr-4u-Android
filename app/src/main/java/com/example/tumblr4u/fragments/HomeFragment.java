package com.example.tumblr4u.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tumblr4u.R;
import com.example.tumblr4u.adapters.PostAdapter;

/**
 * @author Mahmoud Amr Nabil
 * @version 1.0
 * @since 11/8/2021
 */
public class HomeFragment extends Fragment {

    private View root;
    private RecyclerView posts;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_home, container, false);
        posts=root.findViewById(R.id.posts_following);


        GridLayoutManager layoutManager=new GridLayoutManager(getContext(),2);
        posts.setLayoutManager(layoutManager);
        posts.setHasFixedSize(false);
        posts.setNestedScrollingEnabled(false);
        PostAdapter adapter=new PostAdapter();
        posts.setAdapter(adapter);


        return root;
    }
}