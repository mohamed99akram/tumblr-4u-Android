package com.example.tumblr4u.Models;

import static org.junit.Assert.*;

import org.junit.Test;

public class PostEditorTest {
    @Test
    public void getDataAsHtml_mEditorIsNotNull() {
        PostEditor postEditor = new PostEditor(0);
        assertTrue(postEditor.getDataAsHtml().isEmpty());
    }
}