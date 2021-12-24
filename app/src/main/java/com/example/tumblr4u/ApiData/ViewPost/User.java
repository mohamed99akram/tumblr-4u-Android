
package com.example.tumblr4u.ApiData.ViewPost;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("age")
    @Expose
    private Integer age;
    @SerializedName("blogsId")
    @Expose
    private List<String> blogsId = null;
    @SerializedName("favoriteBlogs")
    @Expose
    private List<Object> favoriteBlogs = null;
    @SerializedName("following_blogs")
    @Expose
    private List<String> followingBlogs = null;
    @SerializedName("likes_posts_id")
    @Expose
    private List<Object> likesPostsId = null;
    @SerializedName("role")
    @Expose
    private String role;
    @SerializedName("isDeleted")
    @Expose
    private Boolean isDeleted;
    @SerializedName("isVerified")
    @Expose
    private Boolean isVerified;
    @SerializedName("isActivated")
    @Expose
    private Boolean isActivated;
    @SerializedName("isBlocked")
    @Expose
    private Boolean isBlocked;
    @SerializedName("__v")
    @Expose
    private Integer v;
    @SerializedName("followedTags")
    @Expose
    private List<String> followedTags = null;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public List<String> getBlogsId() {
        return blogsId;
    }

    public void setBlogsId(List<String> blogsId) {
        this.blogsId = blogsId;
    }

    public List<Object> getFavoriteBlogs() {
        return favoriteBlogs;
    }

    public void setFavoriteBlogs(List<Object> favoriteBlogs) {
        this.favoriteBlogs = favoriteBlogs;
    }

    public List<String> getFollowingBlogs() {
        return followingBlogs;
    }

    public void setFollowingBlogs(List<String> followingBlogs) {
        this.followingBlogs = followingBlogs;
    }

    public List<Object> getLikesPostsId() {
        return likesPostsId;
    }

    public void setLikesPostsId(List<Object> likesPostsId) {
        this.likesPostsId = likesPostsId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Boolean getIsVerified() {
        return isVerified;
    }

    public void setIsVerified(Boolean isVerified) {
        this.isVerified = isVerified;
    }

    public Boolean getIsActivated() {
        return isActivated;
    }

    public void setIsActivated(Boolean isActivated) {
        this.isActivated = isActivated;
    }

    public Boolean getIsBlocked() {
        return isBlocked;
    }

    public void setIsBlocked(Boolean isBlocked) {
        this.isBlocked = isBlocked;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }

    public List<String> getFollowedTags() {
        return followedTags;
    }

    public void setFollowedTags(List<String> followedTags) {
        this.followedTags = followedTags;
    }

}
