package com.example.tumblr4u.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tumblr4u.R;
import com.example.tumblr4u.models.Post;
import com.stfalcon.multiimageview.MultiImageView;

import java.util.ArrayList;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostsViewHolder> {

    private ArrayList<Post> posts;
    public PostAdapter(ArrayList<Post> p) {
        posts=p;
    }

    @NonNull
    @Override
    public  PostAdapter.PostsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.post_view, parent, false);
        PostsViewHolder viewHolder=new PostsViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PostAdapter.PostsViewHolder holder, int position) {
        holder.makeResult(position);
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    class PostsViewHolder extends RecyclerView.ViewHolder{

        public PostsViewHolder(@NonNull View itemView) {
            super(itemView);
            MultiImageView multiImageView=itemView.findViewById(R.id.following_posts_multi_img_view);

        }
        public void makeResult(int pos) {

        }
    }
}
