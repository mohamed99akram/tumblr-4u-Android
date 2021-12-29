package com.example.tumblr4u.Fragments;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.tumblr4u.Adapters.SearchSuggestionAdapter;
import com.example.tumblr4u.Models.SearchSuggestionItem;
import com.example.tumblr4u.R;
import com.example.tumblr4u.View.SearchActivity;

import java.util.ArrayList;


public class SearchSuggestionPageFragment extends Fragment {

    private View mRoot;
    private LinearLayout mContainer;
    private SearchActivity mSearchActivity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mRoot = inflater.inflate(R.layout.fragment_search_suggestion_page, container, false);
        initViews();
        mSearchActivity = (SearchActivity) getActivity();

        return mRoot;
    }
    private void initViews(){
        mContainer = (LinearLayout) mRoot.findViewById(R.id.search_suggestions_hash_tags_container);
    }
    public void setData(ArrayList<String> data){
        LinearLayout layout;
        for(String item: data){
            layout = (LinearLayout) getLayoutInflater().inflate(R.layout.search_suggestion_recycler_view_element, null);
            ((TextView)layout.getChildAt(1)).setText(item);

            layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mSearchActivity.getResultOfWord(item);
                }
            });
            mContainer.addView(layout);
        }
    }
}