package com.example.tumblr4u.ViewModel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.tumblr4u.ApiData.RetrieveBlog.BlogResponse;
import com.example.tumblr4u.ApiData.RetrieveBlog.Data;
import com.example.tumblr4u.ApiData.ViewPost.HomePostsResponse;
import com.example.tumblr4u.ApiData.ViewPost.PostsToShow;
import com.example.tumblr4u.GeneralPurpose.Prefs;
import com.example.tumblr4u.Repository.Repository;
import com.example.tumblr4u.Models.Post;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author Mohamed Akram
 * @version 1.0
 * @since 20-12-2021
 */
public class HomeFragmentViewModel extends AndroidViewModel {
    private Repository repository = Repository.INSTANTIATE();

    public MutableLiveData<List<Post>> postsList = new MutableLiveData<>();

    private static final String TAG = "HomeFragmentViewModel";

    /**
     * This is to access sharedPreferences and get Token (AndroidViewModel)
     */
    public HomeFragmentViewModel(@NonNull Application application) {
        super(application);
    }


    public void getposts() {

        //--------- delete this ---------
        // this should be set when the user chooses the current blog or when the user signs in
//        Prefs.storeMyBlogId(getApplication(), "1");
        // testing data
//        tempList.add(new Post("1", "1", "type", "<h1>post 1</h1>", null, 34, "", "akram"));
//        tempList.add(new Post("2", "1", "type", "<h1>post 2</h1>", null, 12, "", "akram"));
//        tempList.add(new Post("3", "4", "type", "<h1>post 3</h1>", null, 45, "", "akram"));
//        tempList.add(new Post("4", "3", "type", "<h1>post 4</h1>"
//                + "<br>"
//                ,
//                null, 12, "", "akram"));
//        tempList.add(new Post("5", "2", "type", "<h1>post 5</h1>", null, 56, "", "akram"));
//
//        postsList.setValue(tempList);
        Log.i(TAG, "Token in " + TAG + " = " + Prefs.getToken(getApplication()));
        repository.requestHomePosts(Prefs.getToken(getApplication())).enqueue(
                new Callback<HomePostsResponse>() {
                    @Override
                    public void onResponse(Call<HomePostsResponse> call,
                            Response<HomePostsResponse> response) {
                        if (response.isSuccessful()) {
                            if (response.body() == null) {
                                Log.e(TAG, "Response.body() is null");
                                return;
                            }
                            // store my blog id
                            String myBlogId = response.body().getRes().getBlog().getId();
                            Prefs.storeMyBlogId(getApplication(), myBlogId);

                            // initialize posts

                            List<PostsToShow> postsToShows =
                                    response.body().getRes().getPostsToShow();
                            List<Post> tempList = new ArrayList<>();
                            for (PostsToShow post : postsToShows) {
                                //TODO get blog name, notes count
                                tempList.add(
                                        new Post(post.getId(), post.getBlogId(), post.getType(),
                                                post.getPostHtml(), post.getTags(), 0, "", "Name"));
                            }
                            new Thread(() -> {
                                // Perform execute here
                                // ---------- Retrieve Blogs ----------
                                for (Post post2 : tempList) {
                                    try {
                                        // execute and get the response
                                        Response<BlogResponse>
                                                blogResponse = repository.getBlog(
                                                Prefs.getToken(getApplication()),
                                                post2.getBlog_id()
//                                                "61ae81b91b9ee885f03a6866"
                                        ).execute();

                                        // work with the response
                                        if (blogResponse.isSuccessful()) {
                                            if (blogResponse.body() != null) {
                                                Data data =
                                                        blogResponse.body().getRes().getData();
                                                post2.setBlogName(data.getName());
                                            } else {
                                                Log.e(TAG, "blog response body = null");
                                            }
                                        } else {
                                            Log.e(TAG, "retrieve blog failed");
                                        }
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                                postsList.postValue(tempList);
                            }).start();
                        } else {
                            Log.e(TAG, "Response is not successful");
                        }
                    }

                    @Override
                    public void onFailure(Call<HomePostsResponse> call, Throwable t) {
                        Log.e(TAG, t.getMessage());
                    }
                }
        );
    }
}
