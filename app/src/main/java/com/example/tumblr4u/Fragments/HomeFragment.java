package com.example.tumblr4u.Fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tumblr4u.R;
import com.example.tumblr4u.View.WritePostActivity;

/**
 * @author Mahmoud Amr Nabil
 * @version 1.0
 * @since 11/8/2021
 */
public class HomeFragment extends Fragment {

    private View root;
    private RecyclerView posts;


//    @SuppressLint("FragmentLiveDataObserve")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_home, container, false);
        posts=root.findViewById(R.id.posts_following);
        Button writePostButton = root.findViewById(R.id.write_post_button_home_fragment);
        writePostButton.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), WritePostActivity.class);
            startActivity(intent);
        });
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