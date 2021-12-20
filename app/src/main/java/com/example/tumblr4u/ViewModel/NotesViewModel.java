package com.example.tumblr4u.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.tumblr4u.Models.Comment;
import com.example.tumblr4u.Repository.Repository;

import java.util.ArrayList;
import java.util.List;

public class NotesViewModel extends AndroidViewModel {

    private Repository mRepository = Repository.INSTANTIATE();
    public MutableLiveData<List<Comment>> commentsList = new MutableLiveData<>();

    public NotesViewModel(@NonNull Application application) {
        super(application);
    }

    public void getComments(){
        List<Comment> tempComments = new ArrayList<>();

        tempComments.add(new Comment("1", "akram1", "", "comment1"));
        tempComments.add(new Comment("1", "akram1", "", "comment2"));
        tempComments.add(new Comment("2", "akram2", "", "comment3"));
        tempComments.add(new Comment("4", "akram4", "", "comment4"));

        tempComments.add(new Comment("3", "akram3", "", "comment5"));
        tempComments.add(new Comment("4", "akram4", "", "comment6"));
        tempComments.add(new Comment("1", "akram1", "", "comment7"));
        tempComments.add(new Comment("2", "akram2", "", "comment8"));
        tempComments.add(new Comment("1", "akram1", "", "comment9"));
        commentsList.setValue(tempComments);
    }
}
