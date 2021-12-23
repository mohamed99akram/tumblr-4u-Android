
package com.example.tumblr4u.ApiData.ViewPost;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Blog {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("avatar")
    @Expose
    private String avatar;
    @SerializedName("accent")
    @Expose
    private String accent;
    @SerializedName("headerImage")
    @Expose
    private String headerImage;
    @SerializedName("background")
    @Expose
    private String background;
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
    @SerializedName("isPrimary")
    @Expose
    private Boolean isPrimary;
    @SerializedName("blogVisitor")
    @Expose
    private Integer blogVisitor;
    @SerializedName("postsIds")
    @Expose
    private List<String> postsIds = null;
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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getAccent() {
        return accent;
    }

    public void setAccent(String accent) {
        this.accent = accent;
    }

    public String getHeaderImage() {
        return headerImage;
    }

    public void setHeaderImage(String headerImage) {
        this.headerImage = headerImage;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
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

    public Boolean getIsPrimary() {
        return isPrimary;
    }

    public void setIsPrimary(Boolean isPrimary) {
        this.isPrimary = isPrimary;
    }

    public Integer getBlogVisitor() {
        return blogVisitor;
    }

    public void setBlogVisitor(Integer blogVisitor) {
        this.blogVisitor = blogVisitor;
    }

    public List<String> getPostsIds() {
        return postsIds;
    }

    public void setPostsIds(List<String> postsIds) {
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
