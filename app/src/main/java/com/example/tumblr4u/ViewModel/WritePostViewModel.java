package com.example.tumblr4u.ViewModel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;

import com.example.tumblr4u.Models.PostData;
import com.example.tumblr4u.Models.PostEditor;
import com.example.tumblr4u.Repository.WritePostRepository;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.richeditor.RichEditor;

/**
 * This class contains the data that the View returns from the user. And then it gives this data to
 * the repository when needed
 */
public class WritePostViewModel extends AndroidViewModel {
    private static final String TAG = "WritePostViewModel";
    //TODO should this be MutableLiveData???
    private ArrayList<PostData> mPostData;

    private WritePostRepository mRepo;

    public WritePostViewModel(@NonNull Application application) {
        super(application);
    }

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
     * @param postData a child of the class PostData representing a post object
     * @implNote Use notifyDataSetChanged after this method.
     */
    public void addPostDataToList(PostData postData) {
        if (postData == null) {
            Log.e(TAG, "Don't insert null value to the list");
            return;
        }
        if (mPostData == null) {
            Log.e(TAG, "init method will be called now");
            return;
        }
        mPostData.add(postData);
    }

    /**
     * This method will send the data to the repository. which in turn will send it to the backend
     */
    public void publishPost() {
        mRepo.publishPostToDatabase(getFinalHtml());
    }

    /**
     * This functions concatenates all html code
     *
     * @return String representing HTML data
     */

    public String getFinalHtml() {
        StringBuilder sentHtml = new StringBuilder();
        for (int i = 0; i < mPostData.size(); i++) {
            String tempHtml = (String)mPostData.get(i).getData();
            if (!tempHtml.isEmpty()) {
                sentHtml.append(tempHtml);
                sentHtml.append("<br>");
            }
        }
        return sentHtml.toString();
    }

    public List<PostData> getPostData() {
        return mPostData;
    }

}
