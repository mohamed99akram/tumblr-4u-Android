package com.example.tumblr4u.ViewModel;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

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
        mWritePostViewModel.init(postData);
    }

    @Test
    public void addPostDataToList_nullIsNotAdded() {
        int sizeBefore = mWritePostViewModel.getPostData().size();
        mWritePostViewModel.addPostDataToList(null);
        assertEquals(sizeBefore, mWritePostViewModel.getPostData().size());
    }

    @Test
    public void addPostDataToList_mPostDataNotInitialized(){
        mWritePostViewModel = new WritePostViewModel();
        mWritePostViewModel.addPostDataToList(new PostEditor(0));
        assertNull(mWritePostViewModel.getPostData());
    }
    @Test
    public void addPostDataToList_addsToListSuccessfully() {
        mWritePostViewModel.addPostDataToList(new PostEditor(0));
        assertEquals(mWritePostViewModel.getPostData().size(), 1);
    }

    @Test
    public void getFinalHtml_listDoesNotContainEmptyString(){
        mWritePostViewModel.addPostDataToList(new PostEditor(0));
        String finalHtml = mWritePostViewModel.getFinalHtml();
        assertTrue(finalHtml.isEmpty());
    }
/*
    @Test
    public void getFinalHtml_returnsTrueHtml(){
        mWritePostViewModel.addPostDataToList(new PostEditor(0));
        mWritePostViewModel.addPostDataToList(new PostEditor(0));
        Context context = new Activity();
        View listLayoutItem = new View(context);
        View view1 = new View(context);
        view1.setId(0);
        ArrayList<View> vl = new ArrayList<>();
        vl.add(view1);
        listLayoutItem.addChildrenForAccessibility(vl);
        ((PostEditor)mWritePostViewModel.getPostData().get(0)).getView(context,listLayoutItem);
        ((PostEditor)mWritePostViewModel.getPostData().get(1)).getView(context,listLayoutItem);
        ((PostEditor)mWritePostViewModel.getPostData().get(0)).getEditor().setHtml("Hello");
        ((PostEditor)mWritePostViewModel.getPostData().get(1)).getEditor().setHtml("World");
        assertEquals(mWritePostViewModel.getFinalHtml(),"Hello<br>World");

    }*/
}