package com.example.tumblr4u.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tumblr4u.R;

import java.util.ArrayList;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostsViewHolder> {

    public PostAdapter() {

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
        return 0;
    }

    class PostsViewHolder extends RecyclerView.ViewHolder{

        public PostsViewHolder(@NonNull View itemView) {
            super(itemView);
        }
        public void makeResult(int pos) {

        }
    }
}
