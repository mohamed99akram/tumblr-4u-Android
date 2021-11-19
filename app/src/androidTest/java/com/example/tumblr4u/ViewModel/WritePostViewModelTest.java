package com.example.tumblr4u.ViewModel;
import static org.junit.Assert.assertEquals;

import com.example.tumblr4u.Models.PostData;
import com.example.tumblr4u.Models.PostEditor;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class WritePostViewModelTest {
    private WritePostViewModel mWritePostViewModel;

    @Before
    public void setup() {
        mWritePostViewModel = new WritePostViewModel();
        ArrayList<PostData> postData = new ArrayList<>();
        mWritePostViewModel = new WritePostViewModel();
        mWritePostViewModel.init(postData);
    }

    @Test
    public void addPostDataToList_nullIsNotAdded() {
        int sizeBefore = mWritePostViewModel.getPostData().size();
        mWritePostViewModel.addPostDataToList(null);
        assertEquals(sizeBefore, mWritePostViewModel.getPostData().size());
    }


    @Test
    public void addPostDataToList_addsToListSuccessfully() {
        mWritePostViewModel.addPostDataToList(new PostEditor(0));
        assertEquals(mWritePostViewModel.getPostData().size(), 1);
    }
}