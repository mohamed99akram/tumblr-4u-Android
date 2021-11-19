package com.example.tumblr4u.View;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;


import com.example.tumblr4u.Adapters.WritePostDataAdapter;
import com.example.tumblr4u.Models.PostData;

import org.junit.Test;

import java.util.ArrayList;

class WritePostActivityTest {

    Activity context = new Activity();
    ArrayList<PostData> arrayList = new ArrayList<>();

    WritePostDataAdapter writePostDataAdapter = new WritePostDataAdapter(context, arrayList);
    int position = -1;
    View convertView = new View(context);
    ViewGroup parent = new ViewGroup(context) {
        @Override
        protected void onLayout(boolean changed, int l, int t, int r, int b) {

        }
    };

    @Test
    public void getView_negativePosition() {
//       assertThrows(writePostDataAdapter.getView(position,convertView,parent));
    }
}