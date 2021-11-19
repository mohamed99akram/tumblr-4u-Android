package com.example.tumblr4u.Repository;

import com.example.tumblr4u.Models.PostData;

import java.util.ArrayList;

public class WritePostRepository {
    private static WritePostRepository instance;
    private ArrayList<PostData> mPostData;

    public void setPostData(ArrayList<PostData> postData) {
        mPostData = postData;
    }

    /**
     * Gets one instance of the repo, using Singleton pattern
     * @return repository of WritePost module
     * */
    public static WritePostRepository getInstance(){
        if(instance == null){
            instance = new WritePostRepository();
        }
        return instance;
    }

    public void publishPostToDatabase(){

    }
}
