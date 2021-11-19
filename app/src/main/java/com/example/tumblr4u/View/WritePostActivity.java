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

public class WritePostActivity extends AppCompatActivity {
    private WritePostViewModel mWritePostViewModel;
    WritePostDataAdapter mWritePostDataAdapter;
    private static final String TAG = "WritePostActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getSupportActionBar().hide();
        setContentView(R.layout.activity_write_post);
        addImageButtonListener();
        addClosePostListener();
        ArrayList<PostData> postData = new ArrayList<>();

        mWritePostViewModel = new WritePostViewModel(); // TODO change this to ViewModelProviders?

        mWritePostViewModel.init(postData);

        mWritePostDataAdapter = new WritePostDataAdapter(this, postData);

        ListView listView = findViewById(R.id.list);

        listView.setAdapter(mWritePostDataAdapter);
        listView.setItemsCanFocus(true);

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
     * Auxiliary Function to add button listener
     */
    private void addImageButtonListener() {
        ImageButton pickImage =findViewById(R.id.add_image);
        pickImage.setOnClickListener(view -> {
            requestPermission();
            makeIntent();
        });
    }
    /**
     * mimic back button
     * */
    private void addClosePostListener(){
        ImageButton closePost =findViewById(R.id.close_post);
        closePost.setOnClickListener(view -> {
            // TODO save state to draft
            this.onBackPressed();
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
     * Show images
     */
    private void attachBitmapsToList(List<Bitmap> bitmaps) {
        for (Bitmap bitmap : bitmaps) {
            mWritePostViewModel.addPostDataToList(
                    new PostEditor(R.layout.editor_list_item, bitmap));
            mWritePostDataAdapter.notifyDataSetChanged();
        }
    }
}