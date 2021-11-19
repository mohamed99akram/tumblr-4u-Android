package com.example.tumblr4u.Models;


import android.content.Context;
import android.view.View;

/**
 * This is an abstract parent class. AdapterArray's elements are children from this class.
 * @author Mohamed Akram
 * @version 1.0
 * @Date 19-Nov-2021
 * */
public abstract class PostData {
    protected int mListLayoutId;
    protected int mViewType;

    protected final int IMAGE_TYPE = 1;
    protected final int TEXT_TYPE = 2;

    public PostData(int listLayoutId) {
        mListLayoutId = listLayoutId;
    }

    public int getListItemId() {
        return mListLayoutId;
    }

    public int getViewType() {
        return mViewType;
    }

    public void setListItemId(int listItemId) {
        mListLayoutId = listItemId;
    }
    /**
     * The adapter will use this method to create views according to the child
     * Each child will have its own implementation
     * @param listItemView is the View that will be filled by children & it is initialized in
     *                     AdapterArray
     * */
    public abstract View getView(Context context, View listItemView);
}