package com.example.tumblr4u.Adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.tumblr4u.Fragments.LoginFragment1;
import com.example.tumblr4u.Fragments.LoginFragment2;
import com.example.tumblr4u.Fragments.LoginFragment3;

public class loginPageAdapter extends FragmentStateAdapter{

    public loginPageAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

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

    @Override
    public int getItemCount() {
        return 3;
    }
}
