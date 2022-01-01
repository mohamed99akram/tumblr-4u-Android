package com.example.tumblr4u.Adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.tumblr4u.Fragments.profile_following_fragment;
import com.example.tumblr4u.Fragments.profile_likes_fragment;
import com.example.tumblr4u.Fragments.profile_posts_fragment;
/**
 * Profile page adapter, manages the transactions between profile fragment
 * */
public class ProfilePageAdapter extends FragmentStateAdapter {

    public ProfilePageAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch(position){
            case 0:
                return new profile_posts_fragment();
            case 1:
                return new profile_likes_fragment();
            case 2:
                return new profile_following_fragment();
            default:
                return null;
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
