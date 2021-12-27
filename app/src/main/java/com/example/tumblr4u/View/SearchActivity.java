package com.example.tumblr4u.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.tumblr4u.Fragments.SearchResultPageFragment;
import com.example.tumblr4u.Fragments.SearchSuggestionPageFragment;
import com.example.tumblr4u.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

public class SearchActivity extends AppCompatActivity {

    private SearchSuggestionPageFragment mSuggestionPage;
    private SearchResultPageFragment mResultPage;
    private ImageButton mBackButton;
    private EditText mSearchField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initViews();
        initOnClickListners();

        // set the fragment manager
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.search_page_fragment_layout, mSuggestionPage, "suggestionPage")
                .commit();
    }

    private void initViews(){
        mSuggestionPage = new SearchSuggestionPageFragment();
        mResultPage = new SearchResultPageFragment();
        mBackButton = (ImageButton) findViewById(R.id.search_page_back_button);
        mSearchField = (EditText) findViewById(R.id.search_page_search_field);
    }
    private void initOnClickListners(){
        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
}