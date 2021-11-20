package com.example.tumblr4u.Adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.tumblr4u.Fragments.LoginFragment1;
import com.example.tumblr4u.Fragments.LoginFragment2;
import com.example.tumblr4u.Fragments.LoginFragment3;

/**
 * Login page fragments adapter that manages the current viewed fragment
 * @author Omar Ahmed
 * @version 1.0
 * */

public class loginPageAdapter extends FragmentStateAdapter{

    public loginPageAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    /**
     * Return the needed fragment to the view pager to be viewed
     * @param position The position of the needed fragment
     * @return The fragment to be viewed
     * */
    @NonNull
    @Override
    public Fragment createFragment(int position) {

        switch(position) {
            case 0:
                return new LoginFragment1();
            case 1:
                return new LoginFragment2();
            case 2:
                return new LoginFragment3();
            default:
                return null;
        }
    }

    /**
     * Get the total number of fragments
     * @return The total number of fragments
     * */
    @Override
    public int getItemCount() {
        return 3;
    }
}
