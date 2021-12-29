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
    //TODO should this be MutableLiveData???
    private ArrayList<PostData> mPostData;
    public MutableLiveData<Boolean> showProgressBar = new MutableLiveData<>();
    //    private WritePostRepository mRepo;
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
//        mRepo = Repository.INSTANTIATE();
//        mRepo.setPostData(mPostData);
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
        showProgressBar.setValue(true);
//        mRepo.publishPost(Prefs.getToken(getApplication()), Prefs.getMyBlogId(getApplication())
//        , getFinalHtml(), "text");
//        showProgressBar.setValue(false);
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
                imagesBase64.add(((PostEditor) postData).getImageBase64());
//                imagesBase64.add((String)postData.getData());
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
//                            return;
                        }
                    } else {
                        Log.e(TAG, "upload images response is not successful");
//                        return;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
//                    return;
                }
            }
            // form the sent html
            int counter = 0;
            StringBuilder sentHtml = new StringBuilder();
            for (PostData postData : mPostData) {
                if (postData.getViewType() == PostData.TEXT_TYPE) {
                    sentHtml.append((String) postData.getData());
                    sentHtml.append("<br>");
                } else if (postData.getViewType() == PostData.IMAGE_TYPE) {
                    String templateHtml = "<img src=\"{IMAGE_LINK}\" alt=\"\">";
                    if (counter < imagesUrls.size()) {
                        String tempImageLink = (String) imagesUrls.get(counter++);
                        sentHtml.append(templateHtml.replace("{IMAGE_LINK}", tempImageLink));
                        sentHtml.append("<br>");
                    } else {
//                        sentHtml.append(templateHtml.replace("{IMAGE_LINK}",
//                                "https://www.vbetnews.com/wp-content/uploads/2020/08/P2020-08-25"
//                                        + "-Salsburg_Liverpool-83.jpg.jpg"));
                        sentHtml.append((String)postData.getData());
                        sentHtml.append("<br>");
                    }
                }
            }
            Log.i(TAG, "Post = " + sentHtml);
            // publish post here
            try {
                Response<String> createPostResponse =
                        mRepo.createPost(
                                Prefs.getToken(getApplication()),
                                Prefs.getMyBlogId(getApplication()),
                                sentHtml.toString(),
                                "text",
                                "published",
                                "cmp"
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

//        StringBuilder sentHtml = new StringBuilder();
//        new Thread(()->{
//            for(PostData postData:mPostData){
//                if(postData.getViewType()== PostData.TEXT_TYPE){
//                    sentHtml.append((String)postData.getData()).append("<br>");
//                }
//                else if(postData.getViewType()==PostData.IMAGE_TYPE){
//                    try {
//                        Response<UploadImageResponse> uploadImageResponse = mRepo.uploadImage(
//                                Prefs.getToken(getApplication()),
//                                ((PostEditor) postData).getImageBase64()
//                        ).execute();
//                        if(uploadImageResponse.isSuccessful()){
//                            if(uploadImageResponse.body()!=null){
//                                sentHtml.append(uploadImageResponse.body().getImages());
//                            }else{
//                                Log.e(TAG, "upload image body = null");
//                            }
//                        }else{
//                            Log.e(TAG, "upload image not successfule");
//                        }
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//
//                }
//            }
//        }).start();
    }

    public String getSentHtml() {
        return mSentHtml;
    }

    /**
     * This functions concatenates all html code
     *
     * @return String representing HTML data
     */

//    public String getFinalHtml() {
//        StringBuilder sentHtml = new StringBuilder();
//        for (PostData postData: mPostData) {
//            int currentViewType = postData.getViewType();
//            if(currentViewType==PostData.TEXT_TYPE){
//                sentHtml.append(((PostEditor)postData).getData());
//                sentHtml.append("<br>");
//            }
//            else if(currentViewType==PostData.IMAGE_TYPE){
//                String imageBase64 = ((PostEditor)postData).getImageBase64();
//                String url = uploadImageGetUrl(imageBase64);
//                sentHtml.append("<img src=\"").append(url).append("\">");
//                sentHtml.append("<br>");
//            }
////            String tempHtml = (String)mPostData.get(i).getData();
////            if (!tempHtml.isEmpty()) {
////                sentHtml.append(tempHtml);
////                sentHtml.append("<br>");
////            }
//        }
//        mSentHtml = sentHtml.toString();
//        return mSentHtml;
//    }
//    public String uploadImageGetUrl(String imageBase64){
//        final String[] url = new String[1];
//        mRepo.uploadImage(
//                Prefs.getToken(getApplication()),
//                imageBase64).enqueue(new Callback<UploadImageResponse>() {
//            @Override
//            public void onResponse(Call<UploadImageResponse> call,
//                    Response<UploadImageResponse> response) {
//                if(response.isSuccessful()){
//                    if(response.body()!=null){
//                        url[0] = response.body().getRes().getData();
//                        Log.e(TAG, "uploaded image successfully, url = "+ url[0]);
//                    }else{
//                        Log.e(TAG, "upload image body = null");
//                    }
//                }else{
//                    Log.e(TAG, "Upload image not successful");
//                }
//            }
//
//            @Override
//            public void onFailure(Call<UploadImageResponse> call, Throwable t) {
//                Log.e(TAG, t.getMessage());
//            }
//        });
////        try {
////            Response<UploadImageResponse> response =  mRepo.uploadImage(
////                    Prefs.getToken(getApplication()),
////                    imageBase64).execute();
////
////            if(response.isSuccessful()){
////                if(response.body()!=null){
////                    String url = response.body().getRes().getData();
////                    Log.e(TAG, "uploaded image successfully, url = "+ url);
////                    return url;
////
////                }else{
////                    Log.e(TAG, "upload image body = null");
////                }
////            }else{
////                Log.e(TAG, "Upload image not successful");
////            }
////        } catch (IOException e) {
////            e.printStackTrace();
////        }
//        return url[0];
//    }
    public List<PostData> getPostData() {
        return mPostData;
    }

}
