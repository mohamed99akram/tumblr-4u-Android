
package com.example.tumblr4u.ApiData.ViewPost;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Res {

    @SerializedName("messege")
    @Expose
    private String messege;
    @SerializedName("user")
    @Expose
    private User user;
    @SerializedName("blog")
    @Expose
    private Blog blog;
    @SerializedName("postsToShow")
    @Expose
    private List<PostsToShow> postsToShow = null;

    public String getMessege() {
        return messege;
    }

    public void setMessege(String messege) {
        this.messege = messege;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Blog getBlog() {
        return blog;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }

    public List<PostsToShow> getPostsToShow() {
        return postsToShow;
    }

    public void setPostsToShow(List<PostsToShow> postsToShow) {
        this.postsToShow = postsToShow;
    }

}
