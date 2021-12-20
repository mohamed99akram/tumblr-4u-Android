package com.example.tumblr4u.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.tumblr4u.Models.LikeReblog;
import com.example.tumblr4u.Repository.Repository;

import java.util.ArrayList;
import java.util.List;

public class LikesReblogsViewModel extends AndroidViewModel {
    private Repository mRepository = Repository.INSTANTIATE();
    public MutableLiveData<List<LikeReblog>> likeReblogList = new MutableLiveData<>();

    public LikesReblogsViewModel(@NonNull Application application) {
        super(application);
    }
    public void getLikesReblogs(String postId){
        //TODO ---------delete this --------
        List<LikeReblog> tempLikesReblogs = new ArrayList<>();
        tempLikesReblogs.add(new LikeReblog("1", "", "akram1" ,LikeReblog.LIKE_TYPE));
        tempLikesReblogs.add(new LikeReblog("2", "", "akram2" ,LikeReblog.REBLOG_TYPE));
        tempLikesReblogs.add(new LikeReblog("1", "", "akram3" ,LikeReblog.LIKE_TYPE));
        tempLikesReblogs.add(new LikeReblog("3", "", "akram4" ,LikeReblog.LIKE_TYPE));
        tempLikesReblogs.add(new LikeReblog("1", "", "akram5" ,LikeReblog.REBLOG_TYPE));
        tempLikesReblogs.add(new LikeReblog("2", "", "akram6" ,LikeReblog.LIKE_TYPE));
        likeReblogList.setValue(tempLikesReblogs);
    }
}
