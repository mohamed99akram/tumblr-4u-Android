package com.example.tumblr4u.ViewModel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.tumblr4u.ApiData.RetrieveBlog.BlogResponse;
import com.example.tumblr4u.ApiData.RetrieveBlog.Data;
import com.example.tumblr4u.ApiData.RetrieveNotes.Note;
import com.example.tumblr4u.GeneralPurpose.Prefs;
import com.example.tumblr4u.Models.LikeReblog;
import com.example.tumblr4u.Models.Post;
import com.example.tumblr4u.Repository.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;
/**
 * Extend AndroidViewModel to access token, blogId
 * This class is used inside NotesActivity to get likes and reblogs for some post
 * */
public class LikesReblogsViewModel extends AndroidViewModel {
    private Repository mRepository = Repository.INSTANTIATE();
    public MutableLiveData<List<LikeReblog>> likeReblogList = new MutableLiveData<>();
    private Post mPost;
    private final String TAG = "LikesReblogsViewModel";

    public LikesReblogsViewModel(@NonNull Application application) {
        super(application);
    }

    /**
     * Get likes and reblogs for some post
     * for each note (like or reblog), retrieve blog to get blogName
     * this happens in another thread not to block main thread and to get all notes in order
     * @param post the post to get likes and reblogs for
     * */
    public void getLikesReblogs(Post post) {
        mPost = post;
        List<Note> allNotes = mPost.getNotes();
        List<LikeReblog> tempLikeReblogList = new ArrayList<>();
        for (Note note : allNotes) {
            String noteType = note.getNoteType();
            if (noteType.equals("like") || noteType.equals("reblog")) {
                String currentBlogId = note.getBlogId();
                int likeOrReblog = noteType.equals("like") ? LikeReblog.LIKE_TYPE
                        : LikeReblog.REBLOG_TYPE;
                tempLikeReblogList.add(
                        new LikeReblog(currentBlogId, "", "Name", likeOrReblog, null));
            }
        }

        // retrieve blog names for each like or reblog
        new Thread(() -> {
            for (LikeReblog likeReblog : tempLikeReblogList) {
                if (likeReblog.getBlogId() == null || likeReblog.getBlogId().isEmpty()) {
                    likeReblog.setBlogId("dummy"); // to send anything to database if null or empty
                }

                try {
                    // execute and get response
                    Response<BlogResponse>
                            blogResponse = mRepository.getBlog(
                            Prefs.getToken(getApplication()),
                            likeReblog.getBlogId()
                    ).execute();

                    // work with the response
                    if (blogResponse.isSuccessful()) {
                        if (blogResponse.body() != null) {
                            Data data =
                                    blogResponse.body().getRes().getData();
                            likeReblog.setBlogName(data.getName());
                            likeReblog.setBlogData(data);
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
            likeReblogList.postValue(tempLikeReblogList);

        }).start();

    }
}
