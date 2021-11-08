package com.example.tumblr4u;

import androidx.lifecycle.MutableLiveData;

import com.example.tumblr4u.models.Post;

import java.util.ArrayList;

/**
 * @author Mahmoud Amr Nabil
 * @version 1.0
 * @since 11/8/2021
 */
public class Repository {
    public MutableLiveData<ArrayList<Post>> requestHolidays() {
        MutableLiveData<ArrayList<Post>> posts = new MutableLiveData<>();
        ArrayList<Post>temp=new ArrayList<>();
        ArrayList<String>imgsUrl=new ArrayList<>();
        imgsUrl.add("https://c1.staticflickr.com/5/4636/25316407448_de5fbf183d_o.jpg");
        imgsUrl.add("https://i.redd.it/tpsnoz5bzo501.jpg");
        imgsUrl.add("https://i.redd.it/qn7f9oqu7o501.jpg");
        imgsUrl.add("https://i.redd.it/j6myfqglup501.jpg");
        ArrayList<String>tags=new ArrayList<>();
        tags.add("#banana ");
        temp.add(new Post(1,"multi_imgs",imgsUrl,"none","lady gaga eats kaka with donald trump and 4 others",tags));
        temp.add(new Post(1,"multi_imgs",imgsUrl,"none","lady gaga eats kaka with donald trump and 4 others",tags));
        temp.add(new Post(1,"multi_imgs",imgsUrl,"none","lady gaga eats kaka with donald trump and 4 others",tags));
        temp.add(new Post(1,"multi_imgs",imgsUrl,"none","lady gaga eats kaka with donald trump and 4 others",tags));
        posts.setValue(temp);
        return posts;
    }
}
