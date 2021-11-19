package com.example.tumblr4u.Repository;

import com.example.tumblr4u.Models.PostData;

import java.util.ArrayList;

public class WritePostRepository {
    private static WritePostRepository instance;
    private ArrayList<PostData> mPostData;

    public void setPostData(ArrayList<PostData> postData) {
        mPostData = postData;
    }


    public static WritePostRepository getInstance(){
        if(instance == null){
            instance = new WritePostRepository();
        }
        return instance;
    }

    public void publishPostToDatabase(){

    }
}
