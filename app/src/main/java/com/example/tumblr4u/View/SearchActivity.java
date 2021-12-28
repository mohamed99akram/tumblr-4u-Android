package com.example.tumblr4u.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.tumblr4u.Fragments.SearchResultPageFragment;
import com.example.tumblr4u.Fragments.SearchSuggestionPageFragment;
import com.example.tumblr4u.R;
import com.example.tumblr4u.ViewModel.SearchPageViewModel;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    private SearchSuggestionPageFragment mSuggestionPage;
    private SearchResultPageFragment mResultPage;
    private ImageButton mBackButton;
    private EditText mSearchField;
    private SearchPageViewModel mViewModel;

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
        mViewModel = new ViewModelProvider(this).get(SearchPageViewModel.class);
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
    private void initOnChangeListners(){
        mSearchField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mViewModel.getSuggestedItems(mSearchField.getText().toString());
            }
        });
    }
    private void initObserver(){
        mViewModel.searchSuggestionItems.observe(this, new Observer<ArrayList<String>>() {
            @Override
            public void onChanged(ArrayList<String> strings) {
                mSuggestionPage.setData(strings);
            }
        });
    }
}