package com.example.tumblr4u.ViewModel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tumblr4u.ApiData.WritePost.CreatePostResponse;
import com.example.tumblr4u.ApiData.WritePost.UploadImageResponse;
import com.example.tumblr4u.GeneralPurpose.Prefs;
import com.example.tumblr4u.Models.PostData;
import com.example.tumblr4u.Models.PostEditor;
import com.example.tumblr4u.Repository.Repository;
import com.example.tumblr4u.Repository.WritePostRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.richeditor.RichEditor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * This class contains the data that the View returns from the user. And then it gives this data to
 * the repository when needed
 */
public class WritePostViewModel extends AndroidViewModel {
    private static final String TAG = "WritePostViewModel";
    public String mSentHtml = "";
    private ArrayList<PostData> mPostData;
    public MutableLiveData<Boolean> showProgressBar = new MutableLiveData<>();

    private Repository mRepo = Repository.INSTANTIATE();
    ;

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
     * Logic:
     * 1) put progress bar to let user wait
     * 2) choose images from the post list view
     * 3) remove \\n character from base64 of the image
     * 4) in a new thread:
     *      upload all images
     *      if uploaded successfully, put them in their order in one string
     *      publish the post html
     */
    public void publishPost() {
        showProgressBar.setValue(true);

        StringBuilder tempPublished = new StringBuilder();
        for (PostData postData : mPostData) {
            if (postData.getViewType() == PostData.IMAGE_TYPE) {
                PostEditor postEditor = (PostEditor) postData;
                Log.i(TAG, "imageBase64 = " + postEditor.getImageBase64()); // gives null
                tempPublished.append(postEditor.getImageBase64());
            } else if (postData.getViewType() == PostData.TEXT_TYPE) {
                tempPublished.append((String) postData.getData());
            }
        }
        Log.i(TAG, "tempPublished: " + tempPublished.toString());

        List<String> imagesBase64 = new ArrayList<>();
        for (PostData postData : mPostData) {
            if (postData.getViewType() == PostData.IMAGE_TYPE) {
                imagesBase64.add(
                        ((PostEditor) postData).getImageBase64().replace("\n", "").replace("\r",
                                ""));

            }
        }
        new Thread(() -> {
            // upload images and get URL
            List<String> imagesUrls = new ArrayList<>();
            if (imagesBase64.size() != 0) {
                try {
                    Response<UploadImageResponse> uploadImagesResponse = mRepo.uploadImages(
                            Prefs.getToken(getApplication()),
                            imagesBase64).execute();
                    if (uploadImagesResponse.isSuccessful()) {
                        if (uploadImagesResponse.body() != null) {
                            imagesUrls = uploadImagesResponse.body().getImages();
                        } else {
                            Log.e(TAG, "upload image body = null");

                        }
                    } else {
                        Log.e(TAG, "upload images response is not successful");

                    }
                } catch (IOException e) {
                    e.printStackTrace();

                }
            }
            // form the sent html
            int counter = 0;
            StringBuilder sentHtml = new StringBuilder();
            boolean trueHtml = true;
            for (PostData postData : mPostData) {
                if (postData.getViewType() == PostData.TEXT_TYPE) {
                    sentHtml.append((String) postData.getData());
                    sentHtml.append("<br>");
                } else if (postData.getViewType() == PostData.IMAGE_TYPE) {
                    String templateHtml = "<img src=\"{IMAGE_LINK}\" alt=\"\" width=\"400\">";
                    if (counter < imagesUrls.size()) {
                        String tempImageLink = (String) imagesUrls.get(counter++);
                        sentHtml.append(templateHtml.replace("{IMAGE_LINK}", tempImageLink));
                    } else {
                        trueHtml = false;
                        sentHtml.append("failed image here");
                    }
                    sentHtml.append("<br>");
                }
            }
            if (!trueHtml) {
                Log.i(TAG, "intended html:" + sentHtml.toString());
                return;
            }
            Log.i(TAG, "Post = " + sentHtml);
            // publish post here
            try {
                List<String> tags = new ArrayList<>();
                Response<String> createPostResponse =
                        mRepo.createPost(
                                Prefs.getToken(getApplication()),
                                Prefs.getMyBlogId(getApplication()),
                                sentHtml.toString(),
                                "text",
                                "published",
                                tags
                        ).execute();
                mSentHtml = sentHtml.toString();
                if (createPostResponse.isSuccessful()) {
                    if (createPostResponse.body() != null) {
                        Log.i(TAG, createPostResponse.body().toString());
                    } else {
                        Log.e(TAG, "create post response body = null");
                    }
                } else {
                    Log.e(TAG, "create post not successful");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            showProgressBar.postValue(false);
        }).start();

    }

    public String getSentHtml() {
        return mSentHtml;
    }

    public List<PostData> getPostData() {
        return mPostData;
    }

}
