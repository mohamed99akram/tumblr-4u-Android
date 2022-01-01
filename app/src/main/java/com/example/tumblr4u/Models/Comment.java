package com.example.tumblr4u.Models;

import com.example.tumblr4u.ApiData.RetrieveBlog.Data;

/**
 * Auxiliary class that carries needed data of the comment
 * */
public class Comment {
    private String mBlogId;
    private String mUserName;
    private String mImageUrl;
    private String mCommentText;
    private Data mBlogData; // use it when you get this blog's page

    public Comment( String blogId, String userName, String imageUrl, String commentText, Data blogData) {

        this.mBlogId = blogId; // redundant
        this.mUserName = userName;
        this.mImageUrl = imageUrl;
        this.mCommentText = commentText;
        this.mBlogData = blogData;
    }

    public Data getBlogData() {
        return mBlogData;
    }

    public void setBlogData(Data blogData) {
        mBlogData = blogData;
    }

    public String getCommentText() {
        return mCommentText;
    }

    public void setCommentText(String commentText) {
        this.mCommentText = commentText;
    }

    public String getBlogId() {
        return mBlogId;
    }

    public void setBlogId(String blogId) {
        this.mBlogId = blogId;
    }

    public String getUserName() {
        return mUserName;
    }

    public void setUserName(String userName) {
        this.mUserName = userName;
    }

    /**
     * If the image URL is not provided, Mohamed Salah will appear
     * */
    public String getImageUrl() {
        if(mImageUrl.isEmpty()){
            return "https://www.vbetnews.com/wp-content/uploads/2020/08/P2020-08-25-Salsburg_Liverpool-83.jpg.jpg";
        }
        return mImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.mImageUrl = imageUrl;
    }
}
