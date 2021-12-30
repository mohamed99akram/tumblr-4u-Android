package com.example.tumblr4u.ApiData.WritePost;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UploadImageRequest {

    @SerializedName("file")
    @Expose
    private List<String> file = null;

    public UploadImageRequest(List<String> file) {
        this.file = file;
    }

    public List<String> getFile() {
        return file;
    }

    public void setFile(List<String> file) {
        this.file = file;
    }

}