package com.example.tumblr4u.ViewModel;

import androidx.lifecycle.ViewModel;

import com.example.tumblr4u.Models.PostData;
import com.example.tumblr4u.Repository.WritePostRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * This class contains the data that the View returns from the user. And then it gives this data to
 * the repository when needed
 */
// TODO should this class extend ViewModel???
public class WritePostViewModel extends ViewModel {

    //TODO should this be MutableLiveData???
    private ArrayList<PostData> mPostData;

    private WritePostRepository mRepo;

    /**
     * This method initializes the ViewModel of the WritePost module
     *
     * <p>
     * It takes a list of PostData as input and initializes the viewModel
     * </p>
     * <p>
     * It will be called inside the WritePostActivity to link the View to its ViewModel (data
     * instances)
     * </p>
     */
    public void init(ArrayList<PostData> postData) {
        if (mPostData != null) {
            return;
        }
        mPostData = postData;
        mRepo = WritePostRepository.getInstance();
        mRepo.setPostData(mPostData);
    }

    /**
     * This function adds a new entry to the list that the adapter watches.
     *
     * @implNote Use notifyDataSetChanged after this method.
     */
    public void addPostDataToList(PostData postData) {
        if(postData==null){
            return;
        }
        mPostData.add(postData);
    }

    /**
     * This method will send the data to the repository. which in turn will send it to the backend
     */
    public void publishPost() {
        mRepo.publishPostToDatabase();
    }

    public List<PostData> getPostData() {
        return mPostData;
    }

}
