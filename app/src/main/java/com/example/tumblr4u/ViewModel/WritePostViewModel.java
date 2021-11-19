package com.example.tumblr4u.ViewModel;

import androidx.lifecycle.ViewModel;

import com.example.tumblr4u.Models.PostData;
import com.example.tumblr4u.Repository.WritePostRepository;

import java.util.ArrayList;
import java.util.List;

// TODO should this class extend ViewModel???
public class WritePostViewModel extends ViewModel {

    //TODO should this be MutableLiveData???
    private ArrayList<PostData> mPostData;

    private WritePostRepository mRepo;

    public void init(ArrayList<PostData> postData){
        if(mPostData != null){
            return;
        }
        mPostData = postData;
        mRepo = WritePostRepository.getInstance();
        mRepo.setPostData(mPostData);
    }
    public void addPostDataToList(PostData postData){
        mPostData.add(postData);
    }
    public void publishPost(){
        mRepo.publishPostToDatabase();
    }
    public List<PostData> getPostData(){
        return mPostData;
    }

}
