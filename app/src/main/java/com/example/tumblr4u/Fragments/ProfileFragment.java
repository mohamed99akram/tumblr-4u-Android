package com.example.tumblr4u.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.example.tumblr4u.Adapters.ProfilePageAdapter;
import com.example.tumblr4u.R;
import com.example.tumblr4u.View.WritePostActivity;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

/**
 * This fragment represents the profile page
 * */
public class ProfileFragment extends Fragment {

    private View root;
    private TabLayout mTabLayout;
    private ViewPager2 mViewPager;
    private ProfilePageAdapter mAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_profile, container, false);
        Button writePostButton = root.findViewById(R.id.write_post_button_profile_fragment);
        writePostButton.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), WritePostActivity.class);
            startActivity(intent);
        });


        mTabLayout = (TabLayout) root.findViewById(R.id.profile_tablayout);
        mViewPager = (ViewPager2) root.findViewById(R.id.profile_view_pager);

        mAdapter = new ProfilePageAdapter(getActivity());
        mViewPager.setAdapter(mAdapter);


        return root;
    }
}