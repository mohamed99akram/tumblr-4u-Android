package com.example.tumblr4u.Models;

public class LikeReblog {
    public static final int LIKE_TYPE = 1;
    public static final int REBLOG_TYPE = 2;
    String blogId;
    String blogImageUrl;
    String blogName;
    int likeOrReblog;

    public LikeReblog(String blogId, String blogImageUrl, String blogName, int likeOrReblog) {
        this.blogId = blogId;
        this.blogImageUrl = blogImageUrl;
        this.blogName = blogName;
        this.likeOrReblog = likeOrReblog;
    }

    public String getBlogId() {
        return blogId;
    }

    public void setBlogId(String blogId) {
        this.blogId = blogId;
    }

    public String getBlogImageUrl() {
        if (blogImageUrl.isEmpty()) {
            return "https://www.vbetnews.com/wp-content/uploads/2020/08/P2020-08-25"
                    + "-Salsburg_Liverpool-83.jpg.jpg";

        }
        return blogImageUrl;
    }

    public void setBlogImageUrl(String blogImageUrl) {
        this.blogImageUrl = blogImageUrl;
    }

    public String getBlogName() {
        return blogName;
    }

    public void setBlogName(String blogName) {
        this.blogName = blogName;
    }

    public int getLikeOrReblog() {
        return likeOrReblog;
    }

    public void setLikeOrReblog(int likeOrReblog) {
        this.likeOrReblog = likeOrReblog;
    }
}
