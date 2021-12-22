package com.example.tumblr4u.Models;


import android.content.Context;
import android.view.View;

/**
 * This is an abstract parent class. AdapterArray's elements are children from this class.
 *
 * @author Mohamed Akram
 * @version 1.0
 * @since 19-11, 2021
 */
public abstract class PostData {
    protected int mViewType;
    //TODO add itemId (editor_item) protected member
    public static final int IMAGE_TYPE = 1;
    public static final int TEXT_TYPE = 2;
    public int getViewType() {
        return mViewType;
    }
    public abstract void storeData(Object object);
    public abstract Object getData();
}
