
package com.example.tumblr4u.ApiData.RetrieveBlog;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("blockedBlogs")
    @Expose
    private List<Object> blockedBlogs = null;
    @SerializedName("followers")
    @Expose
    private List<Object> followers = null;
    @SerializedName("privacy")
    @Expose
    private Boolean privacy;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("updated")
    @Expose
    private Integer updated;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("isBlockedFromPrimary")
    @Expose
    private Boolean isBlockedFromPrimary;
    @SerializedName("blogVisitor")
    @Expose
    private Integer blogVisitor;
    @SerializedName("followedTags")
    @Expose
    private List<Object> followedTags = null;
    @SerializedName("postsIds")
    @Expose
    private List<Object> postsIds = null;
    @SerializedName("isDeleted")
    @Expose
    private Boolean isDeleted;
    @SerializedName("__v")
    @Expose
    private Integer v;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Object> getBlockedBlogs() {
        return blockedBlogs;
    }

    public void setBlockedBlogs(List<Object> blockedBlogs) {
        this.blockedBlogs = blockedBlogs;
    }

    public List<Object> getFollowers() {
        return followers;
    }

    public void setFollowers(List<Object> followers) {
        this.followers = followers;
    }

    public Boolean getPrivacy() {
        return privacy;
    }

    public void setPrivacy(Boolean privacy) {
        this.privacy = privacy;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getUpdated() {
        return updated;
    }

    public void setUpdated(Integer updated) {
        this.updated = updated;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getIsBlockedFromPrimary() {
        return isBlockedFromPrimary;
    }

    public void setIsBlockedFromPrimary(Boolean isBlockedFromPrimary) {
        this.isBlockedFromPrimary = isBlockedFromPrimary;
    }

    public Integer getBlogVisitor() {
        return blogVisitor;
    }

    public void setBlogVisitor(Integer blogVisitor) {
        this.blogVisitor = blogVisitor;
    }

    public List<Object> getFollowedTags() {
        return followedTags;
    }

    public void setFollowedTags(List<Object> followedTags) {
        this.followedTags = followedTags;
    }

    public List<Object> getPostsIds() {
        return postsIds;
    }

    public void setPostsIds(List<Object> postsIds) {
        this.postsIds = postsIds;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }

}
