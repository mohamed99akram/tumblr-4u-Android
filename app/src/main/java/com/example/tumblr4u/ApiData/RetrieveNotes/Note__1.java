
package com.example.tumblr4u.ApiData.RetrieveNotes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Note__1 {

    @SerializedName("noteType")
    @Expose
    private String noteType;
    @SerializedName("blogId")
    @Expose
    private String blogId;
    @SerializedName("isDeleted")
    @Expose
    private Boolean isDeleted;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("rebloggingId")
    @Expose
    private String rebloggingId;
    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("commentingBlogId")
    @Expose
    private String commentingBlogId;
    @SerializedName("commentingBlogTitle")
    @Expose
    private String commentingBlogTitle;

    public String getNoteType() {
        return noteType;
    }

    public void setNoteType(String noteType) {
        this.noteType = noteType;
    }

    public String getBlogId() {
        return blogId;
    }

    public void setBlogId(String blogId) {
        this.blogId = blogId;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRebloggingId() {
        return rebloggingId;
    }

    public void setRebloggingId(String rebloggingId) {
        this.rebloggingId = rebloggingId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCommentingBlogId() {
        return commentingBlogId;
    }

    public void setCommentingBlogId(String commentingBlogId) {
        this.commentingBlogId = commentingBlogId;
    }

    public String getCommentingBlogTitle() {
        return commentingBlogTitle;
    }

    public void setCommentingBlogTitle(String commentingBlogTitle) {
        this.commentingBlogTitle = commentingBlogTitle;
    }

}
