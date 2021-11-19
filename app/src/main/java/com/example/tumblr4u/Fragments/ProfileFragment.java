package com.example.tumblr4u.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.tumblr4u.R;
import com.example.tumblr4u.View.WritePostActivity;


public class ProfileFragment extends Fragment {

    private View root;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_profile, container, false);
        Button writePostButton = root.findViewById(R.id.write_post_button_home_fragment);
        writePostButton.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), WritePostActivity.class);
            startActivity(intent);
        });
        return root;
    }
}