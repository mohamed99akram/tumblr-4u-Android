package com.example.tumblr4u.ApiData.ViewPost;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SinglePostResponse {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("blogId")
    @Expose
    private String blogId;
    @SerializedName("postHtml")
    @Expose
    private String postHtml;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("state")
    @Expose
    private String published;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBlogId() {
        return blogId;
    }

    public void setBlogId(String blogId) {
        this.blogId = blogId;
    }

    public String getPostHtml() {
        return postHtml;
    }

    public void setPostHtml(String postHtml) {
        this.postHtml = postHtml;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPublished() {
        return published;
    }

    public void setPublished(String published) {
        this.published = published;
    }
}
