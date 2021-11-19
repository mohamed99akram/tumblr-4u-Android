package com.example.tumblr4u.Models;

import java.util.ArrayList;

/**
 * @author Mahmoud Amr Nabil
 * @version 1.0
 * @since 11/7/2021
 */
public class Post {
    private int id;
    private String type;
    private ArrayList<String> imgsUrl;
    private String videoUrl;
    private String text;
    private ArrayList<String> tags;

    /**
     * default constructor for Post
     */
    public Post(){
        type="NoMedia";
    }

    public Post(int id, String type, ArrayList<String> imgsUrl, String videoUrl, String text, ArrayList<String> tags) {
        this.id = id;
        this.type = type;
        this.imgsUrl = imgsUrl;
        this.videoUrl = videoUrl;
        this.text = text;
        this.tags = tags;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ArrayList<String> getImgsUrl() {
        return imgsUrl;
    }

    public void setImgsUrl(ArrayList<String> imgsUrl) {
        this.imgsUrl = imgsUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }
}
