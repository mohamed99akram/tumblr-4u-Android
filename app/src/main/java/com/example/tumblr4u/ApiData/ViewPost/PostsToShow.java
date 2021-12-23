
package com.example.tumblr4u.ApiData.ViewPost;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PostsToShow {

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
    private String state;
    @SerializedName("tags")
    @Expose
    private List<String> tags = null;
    @SerializedName("notesId")
    @Expose
    private String notesId;
    @SerializedName("__v")
    @Expose
    private Integer v;

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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getNotesId() {
        return notesId;
    }

    public void setNotesId(String notesId) {
        this.notesId = notesId;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }

}
