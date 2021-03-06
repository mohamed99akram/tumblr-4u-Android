package com.example.tumblr4u.ViewModel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.tumblr4u.ApiData.AddComment.CommentResponse;
import com.example.tumblr4u.ApiData.RetrieveBlog.BlogResponse;
import com.example.tumblr4u.ApiData.RetrieveBlog.Data;
import com.example.tumblr4u.ApiData.RetrieveNotes.Note;
import com.example.tumblr4u.ApiData.RetrieveNotes.NotesResponse;
import com.example.tumblr4u.GeneralPurpose.Prefs;
import com.example.tumblr4u.Models.Comment;
import com.example.tumblr4u.Models.Post;
import com.example.tumblr4u.Repository.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/**
 * ViewModel of NotesActivity
 * extends AndroidViewModel to access token, blog id
 * This class links NotesActivity to database to get comments & publish comments
 * */
public class NotesViewModel extends AndroidViewModel {

    private Repository mRepository = Repository.INSTANTIATE();
    public MutableLiveData<List<Comment>> commentsList = new MutableLiveData<>();
    private String mPostId;
    Post mPost;
    private final static String TAG = "NotesViewModel";

    public NotesViewModel(@NonNull Application application) {
        super(application);
    }

    /**
     * Get comments from database for a specific post
     * blogId only is provided each comment. So, for each comment, retrieve its blogName, blogAvatar
     * So, it will take longer time
     * @param post the post to get comments for
     *
     * */
    public void getComments(Post post) {
        this.mPost = post;
        mPostId = mPost.getPostId();

        List<Note> allNotes = mPost.getNotes();

        List<Comment> tempCommentsList = new ArrayList<>();
        if (allNotes == null) {
            Log.e(TAG, "allNotes list = null");
            commentsList.setValue(tempCommentsList);
            return;
        }
        for (Note note : allNotes) {
            String noteType = note.getNoteType();
            if (noteType.equals("comment")) {
                String currentBlogId = note.getBlogId();
                String commenterBlogId = currentBlogId;
                tempCommentsList.add(new Comment(
                        commenterBlogId,
                        "Name",
                        "",
                        note.getText()
                        , null));
            }
        }

        // retrieve blogs names for each comment
        new Thread(() -> {
            for (Comment comment : tempCommentsList) {
                if (comment.getBlogId() == null
                        || comment.getBlogId().isEmpty()) {
                    comment.setBlogId("dummy"); // if it is null -> error
                }
                try {
                    // execute and get response
                    Response<BlogResponse>
                            blogResponse = mRepository.getBlog(
                            Prefs.getToken(getApplication()),
                            comment.getBlogId()
                    ).execute();

                    // work with the response
                    if (blogResponse.isSuccessful()) {
                        if (blogResponse.body() != null) {
                            Data data =
                                    blogResponse.body().getRes().getData();
                            comment.setUserName(data.getName());
                            comment.setBlogData(data); // will be used for blog page when click
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
            commentsList.postValue(tempCommentsList);
        }).start();
    }

    /**
     * add a comment as text for some specific post
     *
     * @param post post to add comment for
     * @param commentText text to add as a comment for that post
     * */
    public void makeComment(Post post, String commentText) {
        mRepository.makeComment(
                Prefs.getToken(getApplication()),
                Prefs.getMyBlogId(getApplication()),
                post.getPostId(),
                commentText).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.i(TAG, "response = " + response.body());
                        List<Comment> tempList = commentsList.getValue();
                        if(tempList == null){
                            Log.e(TAG, "commentsList = null");
                            return;
                        }
                        tempList.add(0, new Comment(
                                Prefs.getMyBlogId(getApplication()),
                                Prefs.getMyBlogName(getApplication()),
                                "",
                                commentText,
                                null // TODO make it not null || if null go to your blog
                        ));
                        commentsList.setValue(tempList);
                        Log.i(TAG, "commentsList.size() after = " + commentsList.getValue().size());
                    } else {
                        Log.e(TAG, "response body = null");
                    }
                } else {
                    Log.e(TAG, "response is not successful");
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e(TAG, t.getMessage());
            }
        });
    }
}
