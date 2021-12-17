package com.example.tumblr4u.Models;

import java.util.ArrayList;

/**
 * @author Mohamed Akram
 * @version 1.0
 * @since 17-12-2021
 */
public class Post {
    private int blog_id;
    private String mType;
    private String mHtml;
    private ArrayList<String> mTags;
    private int mNotesCount;
    private String mBlogImageUrl;
    private String mBlogName;

    public Post(int blog_id, String type, String html, ArrayList<String> tags, int notesCount,
            String blogImageUrl, String blogName) {
        this.blog_id = blog_id;
        mType = type;
        mHtml = html;
        this.mTags = tags;
        this.mNotesCount = notesCount;
        this.mBlogImageUrl = blogImageUrl;
        this.mBlogName = blogName;
    }

    public String getBlogImageUrl() {
        return mBlogImageUrl;
    }

    public void setBlogImageUrl(String blogImageUrl) {
        mBlogImageUrl = blogImageUrl;
    }

    public String getBlogName() {
        return mBlogName;
    }

    public void setBlogName(String blogName) {
        mBlogName = blogName;
    }

    public int getBlog_id() {
        return blog_id;
    }

    public String getType() {
        return mType;
    }

    public String getHtml() {
        return mHtml;
    }

    public int getNotesCount() {
        return mNotesCount;
    }

    public void setNotesCount(int notesCount) {
        mNotesCount = notesCount;
    }

    public ArrayList<String> getTags() {
        return mTags;
    }

    public void setBlog_id(int blog_id) {
        this.blog_id = blog_id;
    }

    public void setType(String type) {
        mType = type;
    }

    public void setHtml(String html) {
        mHtml = html;
    }

    public void setTags(ArrayList<String> tags) {
        this.mTags = tags;
    }

    public void addTag(String tag) {
        this.mTags.add(tag);
    }
}
