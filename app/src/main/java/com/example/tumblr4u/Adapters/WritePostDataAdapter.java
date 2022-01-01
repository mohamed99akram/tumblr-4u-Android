package com.example.tumblr4u.Adapters;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.tumblr4u.Models.PostData;
import com.example.tumblr4u.R;

import java.util.ArrayList;

import jp.wasabeef.richeditor.RichEditor;

public class WritePostDataAdapter extends ArrayAdapter<PostData> {
    public WritePostDataAdapter(Activity context, ArrayList<PostData> postData) {
        super(context, 0, postData);
    }

    /**
     * This is the method that the adapter uses to create views to view in the list view of the
     * write post module
     *
     * @param position    the position of the item that will be displayed
     * @param convertView the view that will be reused
     * @param parent      the parent of the view
     */
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;
        PostData currentPostData = getItem(position);
        Context context = getContext();


        // supposed to inflate if new
        if (listItemView == null || ((int)listItemView.getTag()!=position)) {
            listItemView = LayoutInflater.from(context).inflate(R.layout.editor_list_item,
                    parent, false);
        }
        listItemView.setTag(position);
        RichEditor editor =(RichEditor) listItemView.findViewById(R.id.editor_item);
        editor.setPadding(10, 10, 10, 10);
        editor.setEditorFontSize(22);
        if (currentPostData.getViewType() == PostData.TEXT_TYPE) {
            editor.setHtml((String) currentPostData.getData());
        } else if (currentPostData.getViewType() == PostData.IMAGE_TYPE) {
            editor.setHtml("");
            editor.loadDataWithBaseURL("file:///android_asset/", (String) currentPostData.getData(),
                    "text/html", "utf-8", "");
        }
        editor.setOnTextChangeListener(new RichEditor.OnTextChangeListener() {
            @Override
            public void onTextChange(String text) {
                currentPostData.storeData(text);
            }
        });
        return editor;
    }



    @Override
    public int getItemViewType(int position) {
        return getItem(position).getViewType();
    }
}
