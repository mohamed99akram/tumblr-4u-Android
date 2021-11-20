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

    /**
     * This constructor is used for text fields in the write post part
     */
    public PostEditor(int listLayoutId) {
        super(listLayoutId);
        mViewType = super.TEXT_TYPE;
    }

    /**
     * This constructor constructs an editor that will hold an image
     * <p>
     * It initializes the mImageBase64 private data member that will be shown in the html tag
     * </p>
     *
     * @param listLayoutId the xml file ID that the image is going to follow
     * @param imageBitmap  the Bitmap format of the image that the user chose.
     */
    public PostEditor(int listLayoutId, Bitmap imageBitmap) {
        super(listLayoutId);
        mViewType = super.IMAGE_TYPE;
        // https://stackoverflow.com/a/15717721/13742330
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        //TODO PNG? 50 -> 100?
        imageBitmap.compress(Bitmap.CompressFormat.JPEG, 50, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        mImageBase64 = Base64.encodeToString(byteArray, Base64.DEFAULT);
    }

    public RichEditor getEditor() {
        return mEditor;
    }

    public String getDataAsHtml() {
        if (mEditor == null) {
            return "";
        }
        return mEditor.getHtml();
    }

    /**
     * There would be an adapter that has its items as PostEditor objects This adapter needs to get
     * views according to their types. This is handled through overriding the function getView in
     * each child This function returns the View to the adapter according to the type of the part of
     * the post
     *
     * @param context      it is needed to access resources.
     * @param listItemView this is the list view that contains the objects that are elements in the
     *                     adapter list
     * @return it returns the View holding the object created, then it is used in the adapter
     */
    @Override
    public View getView(Context context, View listItemView) {
        if (context == null || listItemView == null) {
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
