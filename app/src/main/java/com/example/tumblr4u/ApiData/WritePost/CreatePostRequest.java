package com.example.tumblr4u.ApiData.WritePost;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CreatePostRequest {

    @SerializedName("postHtml")
    @Expose
    private String postHtml;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("tags")
    @Expose
    private String tags = null;

    public CreatePostRequest(String postHtml, String type, String state,
            String tags) {
        this.postHtml = postHtml;
        this.type = type;
        this.state = state;
        this.tags = tags;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

}