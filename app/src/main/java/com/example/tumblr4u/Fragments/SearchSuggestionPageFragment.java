package com.example.tumblr4u.Fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.tumblr4u.Adapters.SearchSuggestionAdapter;
import com.example.tumblr4u.Models.SearchSuggestionItem;
import com.example.tumblr4u.R;

import java.util.ArrayList;


public class SearchSuggestionPageFragment extends Fragment {

    private View mRoot;
    private LinearLayout mContainer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mRoot = inflater.inflate(R.layout.fragment_search_suggestion_page, container, false);
        initViews();

        return mRoot;
    }
    private void initViews(){
        mContainer = (LinearLayout) mRoot.findViewById(R.id.search_suggestions_container);
    }
}