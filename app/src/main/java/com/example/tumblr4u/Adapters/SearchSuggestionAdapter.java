package com.example.tumblr4u.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.tumblr4u.R;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.tumblr4u.Models.SearchSuggestionItem;

import java.util.ArrayList;

/**
 * Search suggestion adapter, manages the data of the recycler view
 * now is no longer used, as we use linear layout due to the light weight suggestion data that coming
 * */
@Deprecated
public class SearchSuggestionAdapter extends RecyclerView.Adapter<SearchSuggestionAdapter.SearchSuggestionHolder> {

    private ArrayList<SearchSuggestionItem> mSuggestedData;
    @NonNull
    @Override
    public SearchSuggestionHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SearchSuggestionHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.search_suggestion_recycler_view_element, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SearchSuggestionHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mSuggestedData.size();
    }

    public void setData(ArrayList<SearchSuggestionItem> data){
        mSuggestedData = data   ;
    }
    public class SearchSuggestionHolder extends RecyclerView.ViewHolder{

        private TextView mSearchData;
        public SearchSuggestionHolder(@NonNull View itemView) {
            super(itemView);
            mSearchData = (TextView) itemView.findViewById(R.id.search_suggestion_result_field);
        }
    }
}
