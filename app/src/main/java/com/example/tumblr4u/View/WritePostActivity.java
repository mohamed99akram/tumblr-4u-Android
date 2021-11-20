package com.example.tumblr4u.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.ClipData;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.tumblr4u.Adapters.WritePostDataAdapter;
import com.example.tumblr4u.Models.PostData;
import com.example.tumblr4u.Models.PostEditor;
import com.example.tumblr4u.R;
import com.example.tumblr4u.ViewModel.WritePostViewModel;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.richeditor.RichEditor;

public class WritePostActivity extends AppCompatActivity {
    private WritePostViewModel mWritePostViewModel;
    WritePostDataAdapter mWritePostDataAdapter;
    private ListView mListView;
    private static final String TAG = "WritePostActivity";

    /**
     * This function is called when creating a new activity, it overrides the onCreate in its
     * parent, AppCompatActivity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_post);

        // button event listeners
        addImageButtonListener();
        addClosePostListener();
        addSizeFontButtonListener();

        // ViewModel
        ArrayList<PostData> postData = new ArrayList<>();

        mWritePostViewModel = new WritePostViewModel(); // TODO change this to ViewModelProviders?

        mWritePostViewModel.init(postData);


        // listView & adapter
        mWritePostDataAdapter = new WritePostDataAdapter(this, postData);

        mListView= findViewById(R.id.list);

        mListView.setAdapter(mWritePostDataAdapter);
        mListView.setItemsCanFocus(true);
        // initial editor
        addEditor();
        addEditor();
        addEditor();
    }

    /**
     * Auxiliary function to add a new Rich Editor to ArrayList and update adapter
     */
    private void addEditor() {
        mWritePostViewModel.addPostDataToList(new PostEditor(R.layout.editor_list_item));
        mWritePostDataAdapter.notifyDataSetChanged();
    }


    /**
     * Auxiliary Function to add image button listener that will let the user choose the image to
     * their post
     */
    private void addImageButtonListener() {
        ImageButton pickImage = findViewById(R.id.add_image);
        pickImage.setOnClickListener(view -> {
            requestPermission();
            makeIntent();
        });
    }

    /**
     * mimic back button (x) button at the top left of the editor
     */
    private void addClosePostListener() {
        ImageButton closePost = findViewById(R.id.close_post);
        closePost.setOnClickListener(view -> {
            // TODO save state to draft
            this.onBackPressed();
        });
    }

    /**
     * This function handles clicking on the change font/size button
     */
    private void addSizeFontButtonListener() {
        ImageButton imageButton = findViewById(R.id.text_size_font);
        imageButton.setOnClickListener(v -> {
            RichEditor currentFocus = getCurrentFocus().findViewById(R.id.editor_item);
            currentFocus.setFontSize(7);
        });
    }

    /**
     * auxiliary function to check if permission is granted to access storage
     */
    private void requestPermission() {
        if (ActivityCompat.checkSelfPermission(WritePostActivity.this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(WritePostActivity.this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 100);
        }
    }

    /**
     * auxiliary function to Make an Intent to choose the image
     */
    private void makeIntent() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        intent.setType("image/*");// video/*");
        startActivityForResult(intent, 1);
    }

    /**
     * - This function is called when the intent returns data. - According to the user request, it
     * will perform its actions - It is overridden from its parent - Here, it will prepare bitmaps
     * of the chosen images
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            List<Bitmap> bitmaps = new ArrayList<>();
            ClipData clipData = data.getClipData();


            if (clipData != null) {
                for (int i = 0; i < clipData.getItemCount(); i++) {
                    Uri imageUri = clipData.getItemAt(i).getUri();
                    try {
                        InputStream is = getContentResolver().openInputStream(imageUri);

                        Bitmap bitmap = BitmapFactory.decodeStream(is);

                        bitmaps.add(bitmap);

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                Uri imageUri = data.getData();
                try {
                    InputStream is = getContentResolver().openInputStream(imageUri);

                    Bitmap bitmap = BitmapFactory.decodeStream(is);

                    bitmaps.add(bitmap);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            attachBitmapsToList(bitmaps);
        }
    }

    /**
     * Add images to the adapter list and notify it to update the screen
     *
     * @param bitmaps the list of bitmaps of images that will be displayed on the editor
     */
    private void attachBitmapsToList(List<Bitmap> bitmaps) {
        for (Bitmap bitmap : bitmaps) {
            mWritePostViewModel.addPostDataToList(
                    new PostEditor(R.layout.editor_list_item, bitmap));
            mWritePostDataAdapter.notifyDataSetChanged();
        }
    }
}