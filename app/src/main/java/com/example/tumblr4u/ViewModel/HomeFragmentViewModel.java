package com.example.tumblr4u.ViewModel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.tumblr4u.ApiData.RetrieveBlog.BlogResponse;
import com.example.tumblr4u.ApiData.RetrieveBlog.Data;
import com.example.tumblr4u.ApiData.RetrieveNotes.Note;
import com.example.tumblr4u.ApiData.RetrieveNotes.NotesResponse;
import com.example.tumblr4u.ApiData.ViewPost.HomePostsResponse;
import com.example.tumblr4u.ApiData.ViewPost.PostsToShow;
import com.example.tumblr4u.GeneralPurpose.Prefs;
import com.example.tumblr4u.R;
import com.example.tumblr4u.Repository.Repository;
import com.example.tumblr4u.Models.Post;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import io.socket.client.IO;
import io.socket.client.Socket;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author Mohamed Akram
 * @version 1.0
 * @since 20-12-2021
 * Extends AndroidViewModel to access token, blogId
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

    /**
     * <p>
     * This function retrieves dashboard
     * </p>
     * interesting data returned from dashboard: myBlogId and posts to show
     * for each post: its blogId is also important
     * <p>
     *     Not all post's data is ready to be shown. Missing is blogName, NotesCount.
     * </p>
     * <p>
     *     So, for each post, go get its notes and its blog to have access to blogName, notesCount
     * </p>
     * THAT'S why it takes so long
     *
     * In order to send a bunch of requests, create a thread and send requests with execute.
     * It executes send request in the calling thread (synchronous)
     *
     * TODO: ask backend to give you ready data
     * TODO: retrieve posts one by one and show what becomes ready instead of loading everything
     *      then showing
     * */
    public void getposts() {


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
                            Prefs.storeMyBlogName(getApplication(),
                                    response.body().getRes().getBlog().getName());

                            // initialize posts

                            List<PostsToShow> postsToShows =
                                    response.body().getRes().getPostsToShow();
                            List<Post> tempList = new ArrayList<>();
                            for (PostsToShow post : postsToShows) {
                                //TODO get blog name, notes count
                                tempList.add(
                                        new Post(post.getId(), post.getBlogId(), post.getType(),
                                                post.getPostHtml(), post.getTags(), 0, 0, 0, "",
                                                "Name", null, null, post.getNotesId()));
                            }
                            new Thread(() -> {
                                // Perform execute here
                                // ! Don't do this!! Ask your backend team
                                // to give you blog name :(
                                // ---------- Retrieve Blogs ----------
                                for (Post post2 : tempList) {
                                    try {
                                        // execute and get the response
                                        Response<BlogResponse>
                                                blogResponse = repository.getBlog(
                                                Prefs.getToken(getApplication()),
                                                post2.getBlog_id()
//                                                "61ae81b91b9ee885f03a6866" // TODO remove this
                                        ).execute();

                                        // work with the response
                                        if (blogResponse.isSuccessful()) {
                                            if (blogResponse.body() != null) {
                                                Data data =
                                                        blogResponse.body().getRes().getData();
                                                post2.setBlogName(data.getName());
                                                post2.setBlogData(data);
                                                // TODO set avatar if it is the image url
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
                                // ---------- Retrieve Notes (For Notes Count) DON'T DO THIS
                                // ASK BACKEND TO GIVE IT TO YOU !!!!!!!!! :(((((
                                for (Post post : tempList) {
                                    try {
                                        // execute and get the response
                                        Response<NotesResponse>
                                                notesResponse = repository.getNotes(
                                                Prefs.getToken(getApplication()),
                                                post.getNotesId()
                                        ).execute();

                                        // work with the response
                                        if (notesResponse.isSuccessful()) {
                                            if (notesResponse.body() != null) {
                                                List<Note>
                                                        notesList =
                                                        notesResponse.body().getRes().getNotes();
                                                int notesCount =
                                                        notesList.size();
                                                int likesCount = 0;
                                                int reblogsCount = 0;
                                                for (Note note : notesList) {
                                                    String noteType = note.getNoteType();
                                                    if (noteType.equals("like")) {
                                                        likesCount++;
                                                    }
                                                    if (noteType.equals("reblog")) {
                                                        reblogsCount++;
                                                    }
                                                }
                                                post.setNotesCount(notesCount);
                                                post.setLikesCount(likesCount);
                                                post.setReblogsCount(reblogsCount);
                                                post.setNotes(notesList);
                                            } else {
                                                Log.e(TAG, "notes body = null");
                                            }
                                        } else {
                                            Log.e(TAG, "retrieve notes failed");
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

    /**
     * Press like button for some post (if liked -> removes like & else: add like)
     * <p>
     *     First: connect to socket and send that you pressed like
     * </p>
     * <p>
     *     Second: send like request to repository.
     * </p>
     * @param post the post to add lieke to.
     *             Needed: postId of that post
     * */
    public void pressLikeButton(Post post) {
        // ---------- emit like to socket ---------
        try {
            Socket mSocket = IO.socket("http://tumblr4u.eastus.cloudapp.azure.com:5000/");
            String postId = post.getPostId();
            JSONObject jsonObject = new JSONObject().put("postId", postId);
            Log.i(TAG, jsonObject.toString());
            mSocket.emit("like", jsonObject);
            mSocket.connect();

        } catch (URISyntaxException | JSONException e) {
            e.printStackTrace();
        }
        // ---------- send request -------------
        repository.pressLike(Prefs.getToken(getApplication()), Prefs.getMyBlogId(getApplication()),
                post.getPostId()).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.i(TAG, "press like response body = " + response.body());
                    } else {
                        Log.e(TAG, "press like response body = null");
                    }
                } else {
                    Log.e(TAG, "press like response not successful");
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e(TAG, t.getMessage());
            }
        });
    }
}
