package com.example.tumblr4u.Models;


import android.content.Context;
import android.graphics.Bitmap;
import android.util.Base64;
import android.view.View;

import com.example.tumblr4u.R;

import java.io.ByteArrayOutputStream;

import jp.wasabeef.richeditor.RichEditor;

public class PostEditor extends PostData {
    private String innerHtml;
//    private String mImageBase64;
    // TODO should I delete this?
//    boolean mISInitialized = false;

    public PostEditor(int viewType) {
        mViewType = viewType;
        innerHtml = "";
    }

    /**
     * This constructor constructs an editor that will hold an image
     * <p>
     * It initializes the mImageBase64 private data member that will be shown in the html tag
     * </p>
     *
     * @param imageBitmap the Bitmap format of the image that the user chose.
     */
    public PostEditor(Bitmap imageBitmap) {
        mViewType = PostData.IMAGE_TYPE;
        // https://stackoverflow.com/a/15717721/13742330
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        //TODO PNG? 50 -> 100?
        imageBitmap.compress(Bitmap.CompressFormat.JPEG, 50, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        String imageBase64 = Base64.encodeToString(byteArray, Base64.DEFAULT);

        innerHtml = "<html><body><img src='{IMAGE_PLACEHOLDER}' width = \"" + 400
                + "\"/></body></html>";
        innerHtml = innerHtml.replace("{IMAGE_PLACEHOLDER}",
                "data:image/jpeg;base64," + imageBase64);
    }

    @Override
    public void storeData(Object object) {
        innerHtml = (String) object;
    }

    @Override
    public String getData() {
        return innerHtml;
    }
}
