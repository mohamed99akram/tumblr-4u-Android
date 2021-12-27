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

public class NotesViewModel extends AndroidViewModel {

    private Repository mRepository = Repository.INSTANTIATE();
    public MutableLiveData<List<Comment>> commentsList = new MutableLiveData<>();
    private String mPostId;
    Post mPost;
    private final static String TAG = "NotesViewModel";

    public NotesViewModel(@NonNull Application application) {
        super(application);
    }

    public void getComments(Post post) {
        this.mPost = post;
        mPostId = mPost.getPostId();
//        mPostId = "61ae667d8b4d5620ce937992"; // TODO remove this
//        mRepository.getNotes(Prefs.getToken(getApplication()), mPostId).enqueue(
//                new Callback<NotesResponse>() {
//                    @Override
//                    public void onResponse(Call<NotesResponse> call,
//                            Response<NotesResponse> response) {
//                        if (response.isSuccessful()) {
//                            if (response.body() == null) {
//                                Log.e(TAG, "response body = null");
//                                return;
//                            }
        // Get comments from notes
//                            List<Note> allNotes =
//                                    response.body().getRes().getNotes();
        List<Note> allNotes = mPost.getNotes();
        List<Comment> tempCommentsList = new ArrayList<>();
        for (Note note : allNotes) {
            String noteType = note.getNote().getNoteType();
            if (noteType.equals("comment")) {
                String currentCommentingBlogId =
                        note.getNote().getCommentingBlogId();
                String currentBlogId = note.getNote().getBlogId();
                String commenterBlogId = currentCommentingBlogId;
                if (currentCommentingBlogId == null) {
                    commenterBlogId = currentBlogId;
                }
                tempCommentsList.add(new Comment(
                        commenterBlogId,
                        "Name",
                        "",
                        note.getNote().getText()
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
//                comment.setBlogId("61ae81b91b9ee885f03a6866");// TODO remove this line
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

    public void makeComment(Post post, String commentText) {
        mRepository.makeComment(
                Prefs.getToken(getApplication()),
                Prefs.getMyBlogId(getApplication()),
                post.getPostId(),
                commentText
        ).enqueue(new Callback<CommentResponse>() {
            @Override
            public void onResponse(Call<CommentResponse> call, Response<CommentResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.i(TAG, "response = " + response.body().getRes().getMessege());
                        List<Comment> tempList = commentsList.getValue();
                        tempList.add(new Comment(
                                Prefs.getMyBlogId(getApplication()),
                                Prefs.getMyBlogName(getApplication()),
                                "",
                                commentText,
                                null // TODO make it not null || if null go to your blog
                        ));
                        commentsList.setValue(tempList);
                    } else {
                        Log.e(TAG, "response body = null");
                    }
                } else {
                    Log.e(TAG, "response is not successful");
                }
            }

            @Override
            public void onFailure(Call<CommentResponse> call, Throwable t) {
                Log.e(TAG, t.getMessage());
            }
        });
    }
}
