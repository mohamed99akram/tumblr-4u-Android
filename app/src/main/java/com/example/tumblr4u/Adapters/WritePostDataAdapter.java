package com.example.tumblr4u.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.tumblr4u.Models.PostData;

import java.util.ArrayList;

public class WritePostDataAdapter extends ArrayAdapter<PostData> {
    public WritePostDataAdapter(Activity context, ArrayList<PostData> postData){
        super(context, 0, postData);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        PostData currentPostData = getItem(position);
        Context context = getContext();

        if(listItemView==null){
            listItemView = LayoutInflater.from(context).inflate(currentPostData.getListItemId(),parent, false);
        }
        return currentPostData.getView(context, listItemView);
    }
    @Override
    public int getItemViewType(int position) {
        return getItem(position).getViewType();
    }
}