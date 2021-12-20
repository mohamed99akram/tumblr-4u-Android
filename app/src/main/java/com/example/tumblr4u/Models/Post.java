package com.example.tumblr4u.Models;

import java.util.ArrayList;

/**
 * @author Mohamed Akram
 * @version 1.0
 * @since 17-12-2021
 */
public class Post {
//    private boolean isMine; // answered in ViewModel when comparing userId with sharedPreferences
    private String postId;
    private String blog_id;
    private String mType;
    private String mHtml;
    private ArrayList<String> mTags;
    private int mNotesCount;
    private String mBlogImageUrl;
    private String mBlogName;

//    public boolean isMine() {
//        return isMine;
//    }
//
//    public void setMine(boolean mine) {
//        isMine = mine;
//    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public void setBlog_id(String blog_id) {
        this.blog_id = blog_id;
    }

    public String getBlog_id() {
        return blog_id;
    }

    public Post( String postId, String blog_id, String type, String html, ArrayList<String> tags, int notesCount,
            String blogImageUrl, String blogName) {
//        this.isMine = isMine;
        this.postId = postId;
        this.blog_id = blog_id;
        mType = type;
        mHtml = html;
        this.mTags = tags;
        this.mNotesCount = notesCount;
        this.mBlogImageUrl = blogImageUrl;
        this.mBlogName = blogName;
    }

    public String getBlogImageUrl() {
        if(mBlogImageUrl.isEmpty()){
            return "https://www.vbetnews.com/wp-content/uploads/2020/08/P2020-08-25-Salsburg_Liverpool-83.jpg.jpg";
        }
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
