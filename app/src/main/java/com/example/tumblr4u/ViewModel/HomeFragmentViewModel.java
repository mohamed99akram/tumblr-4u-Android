package com.example.tumblr4u.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tumblr4u.ApiData.HomePostsResponse;
import com.example.tumblr4u.Repository.Repository;
import com.example.tumblr4u.Models.Post;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author Mahmoud Amr Nabil
 * @version 1.0
 * @since 11/8,2021
 */
public class HomeFragmentViewModel extends ViewModel {
    private Repository repository = Repository.INSTANTIATE();
    ;
    public MutableLiveData<List<Post>> postsList = new MutableLiveData<>();
    ;

    public void getposts() {
        List<Post> tempList = new ArrayList<>();
        tempList.add(new Post("1", "1","type", "<h1>post 1</h1>", null, 34, "", "akram"));
        tempList.add(new Post("2", "1", "type", "<h1>post 2</h1>", null, 12, "", "akram"));
        tempList.add(new Post("3", "1","type", "<h1>post 3</h1>", null, 45, "", "akram"));
        tempList.add(new Post("4", "1","type", "<h1>post 4</h1>"
                +"<br>"
                + "<img src=\"https://lh4.googleusercontent"
                + ".com/-eyNmG5kC7tA/AAAAAAAAAAI/AAAAAAAAAAA/AMZuucnmoqzcEbugxMxTAp-6skbZvokADw"
                + "/photo.jpg?sz=256\">",
                null, 12, "", "akram"));
        tempList.add(new Post("5", "1", "type", "<h1>post 5</h1>", null, 56, "", "akram"));

        postsList.setValue(tempList);
//        repository.requestHomePosts().enqueue(new Callback<HomePostsResponse>() {
//            @Override
//            public void onResponse(Call<HomePostsResponse> call,
//                    Response<HomePostsResponse> response) {
//
//            }
//
//            @Override
//            public void onFailure(Call<HomePostsResponse> call, Throwable t) {
//
//            }
//        });
    }
}
