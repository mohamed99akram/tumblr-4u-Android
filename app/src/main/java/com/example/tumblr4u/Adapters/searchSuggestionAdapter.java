package com.example.tumblr4u.Adapters;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tumblr4u.Models.SearchSuggestionItem;

public class searchSuggestionAdapter extends RecyclerView.Adapter<searchSuggestionAdapter.SearchSuggestionHolder> {

    @NonNull
    @Override
    public SearchSuggestionHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull SearchSuggestionHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class SearchSuggestionHolder extends RecyclerView.ViewHolder{

        private TextView mSearchData;
        public SearchSuggestionHolder(@NonNull View itemView) {
            super(itemView);
        }
        public void setData(String Data){
            mSearchData.setText(Data);
        }
    }
}
