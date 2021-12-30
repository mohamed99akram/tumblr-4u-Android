
package com.example.tumblr4u.ApiData.RetrieveNotes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Note {

    @SerializedName("noteType")
    @Expose
    private String noteType;
    @SerializedName("blogId")
    @Expose
    private String blogId;
    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("isDeleted")
    @Expose
    private Boolean isDeleted;
    @SerializedName("_id")
    @Expose
    private String id;

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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
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

}
