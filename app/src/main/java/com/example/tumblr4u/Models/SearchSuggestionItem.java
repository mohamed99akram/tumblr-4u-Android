package com.example.tumblr4u.Models;

import java.util.ArrayList;

public class SearchSuggestionItem {
    private ArrayList<String> suggestedWords;

    public ArrayList<String> getData(){ return suggestedWords; }
    public void setData(ArrayList<String> data){
        this.suggestedWords = data;
    }
}
