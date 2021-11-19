package com.example.tumblr4u.Models;


import android.content.Context;
import android.graphics.Bitmap;
import android.util.Base64;
import android.view.View;

import com.example.tumblr4u.R;

import java.io.ByteArrayOutputStream;

import jp.wasabeef.richeditor.RichEditor;

public class PostEditor extends PostData {
    private RichEditor mEditor;
    private String mImageBase64;
    // TODO should I delete this?
    boolean mISInitialized = false;

    public PostEditor(int listLayoutId) {
        super(listLayoutId);
        mViewType = super.TEXT_TYPE;
    }


    public PostEditor(int listLayoutId, Bitmap imageBitmap) {
        super(listLayoutId);
        mViewType = super.IMAGE_TYPE;
        // https://stackoverflow.com/a/15717721/13742330
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        //TODO PNG?
        imageBitmap.compress(Bitmap.CompressFormat.JPEG, 10, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        mImageBase64 = Base64.encodeToString(byteArray, Base64.DEFAULT);
    }

    public RichEditor getEditor() {
        return mEditor;
    }

    @Override
    public View getView(Context context, View listItemView) {
        if(context==null || listItemView == null){
            return null;
        }
        mEditor = listItemView.findViewById(R.id.editor_item);
        mEditor.setPadding(10, 10, 10, 10);
        mEditor.setPlaceholder("");
        mEditor.setEditorFontSize(22);

        if (mViewType == super.IMAGE_TYPE && !mISInitialized) {
            mISInitialized = true;
            //TODO change width to fill screen
            String html = "<html><body><img src='{IMAGE_PLACEHOLDER}' width = \"" + 400
                    + "\"/></body></html>";
            html = html.replace("{IMAGE_PLACEHOLDER}", "data:image/jpeg;base64," + mImageBase64);
            mEditor.loadDataWithBaseURL("file:///android_asset/", html, "text/html", "utf-8", "");
        }
        return listItemView;
    }
}
