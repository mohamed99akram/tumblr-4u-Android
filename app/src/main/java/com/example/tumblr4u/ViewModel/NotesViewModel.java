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
                        note.getNote().getText()));
            }
        }

        // retrieve blogs names for each comment
        new Thread(() -> {
            for (Comment comment : tempCommentsList) {
                if (comment.getBlogId() == null
                        || comment.getBlogId().isEmpty()) {
                    comment.setBlogId("dummy"); // if it is null -> error
                }
                // TODO remove this line
//                comment.setBlogId("61ae81b91b9ee885f03a6866");
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

//                        } else {
//                            Log.e(TAG, "response is not successful");
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<NotesResponse> call, Throwable t) {
//                        Log.e(TAG, t.getMessage());
//                    }
//                });


//        List<Comment> tempComments = new ArrayList<>();
//
//        tempComments.add(new Comment("1", "akram1", "", "comment1"));
//        tempComments.add(new Comment("1", "akram1", "", "comment2"));
//        tempComments.add(new Comment("2", "akram2", "", "comment3"));
//        tempComments.add(new Comment("4", "akram4", "", "comment4"));
//
//        tempComments.add(new Comment("3", "akram3", "", "comment5"));
//        tempComments.add(new Comment("4", "akram4", "", "comment6"));
//        tempComments.add(new Comment("1", "akram1", "", "comment7"));
//        tempComments.add(new Comment("2", "akram2", "", "comment8"));
//        tempComments.add(new Comment("1", "akram1", "", "comment9"));
//        commentsList.setValue(tempComments);
    }
}
