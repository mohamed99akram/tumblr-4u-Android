package com.example.tumblr4u.Models;

import com.example.tumblr4u.ApiData.RetrieveBlog.Data;
import com.example.tumblr4u.ApiData.RetrieveNotes.Note;

import java.util.List;

/**
 * @author Mohamed Akram
 * @version 1.0
 * @since 17-12-2021
 */
public class Post {
    private String postId;
    private String blog_id;
    private String mType;
    private String mHtml;
    private List<String> mTags;
    private int mNotesCount;
    private int mLikesCount;
    private int mReblogsCount;
    private String mBlogImageUrl;
    private String mBlogName;
    private List<Note> mNotes;
    private Data mBlogData;
    private String mNotesId;

    public Post(String postId, String blog_id, String type, String html, List<String> tags,
            int notesCount, int likesCount, int reblogsCount,
            String blogImageUrl, String blogName, List<Note> notes, Data blogData, String notesId) {
        this.mLikesCount = likesCount;// redundant
        this.mReblogsCount = reblogsCount;// redundant
        this.postId = postId;
        this.blog_id = blog_id;// redundant
        mType = type;
        mHtml = html;
        this.mTags = tags;
        this.mNotesCount = notesCount; // redundant
        this.mBlogImageUrl = blogImageUrl;
        this.mBlogName = blogName;// redundant
        this.mNotes = notes;
        this.mBlogData = blogData;
        this.mNotesId = notesId;
    }

    public String getNotesId() {
        return mNotesId;
    }

    public void setNotesId(String notesId) {
        mNotesId = notesId;
    }

    public List<Note> getNotes() {
        return mNotes;
    }

    public void setNotes(List<Note> notes) {
        mNotes = notes;
    }

    public Data getBlogData() {
        return mBlogData;
    }

    public void setBlogData(Data blogData) {
        mBlogData = blogData;
    }

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

    public int getLikesCount() {
        return mLikesCount;
    }

    public void setLikesCount(int likesCount) {
        mLikesCount = likesCount;
    }

    public int getReblogsCount() {
        return mReblogsCount;
    }

    public void setReblogsCount(int reblogsCount) {
        mReblogsCount = reblogsCount;
    }

    public String getBlogImageUrl() {
        if (mBlogImageUrl.isEmpty()) {
            return "https://www.vbetnews.com/wp-content/uploads/2020/08/P2020-08-25"
                    + "-Salsburg_Liverpool-83.jpg.jpg";
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

    public List<String> getTags() {
        return mTags;
    }

    public void setType(String type) {
        mType = type;
    }

    public void setHtml(String html) {
        mHtml = html;
    }

    public void setTags(List<String> tags) {
        this.mTags = tags;
    }

    public void addTag(String tag) {
        this.mTags.add(tag);
    }
}
