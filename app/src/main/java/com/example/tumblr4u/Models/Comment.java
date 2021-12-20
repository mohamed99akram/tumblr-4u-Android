package com.example.tumblr4u.Models;

public class Comment {
//    private boolean isMine;
    private String blogId;
    private String userName;
    private String imageUrl;
    private String commentText;
//    public boolean isMine() {
//        return isMine;
//    }
//
//    public void setMine(boolean mine) {
//        isMine = mine;
//    }

    public String getCommentText() {
        return commentText;
    }

    public Comment(String blogId, String userName, String imageUrl, String commentText) {
        this.blogId = blogId;
        this.userName = userName;
        this.imageUrl = imageUrl;
        this.commentText = commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public String getBlogId() {
        return blogId;
    }

    public void setBlogId(String blogId) {
        this.blogId = blogId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getImageUrl() {
        if(imageUrl.isEmpty()){
            return "https://www.vbetnews.com/wp-content/uploads/2020/08/P2020-08-25-Salsburg_Liverpool-83.jpg.jpg";
        }
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
