package com.example.tumblr4u.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tumblr4u.Models.LikeReblog;
import com.example.tumblr4u.R;

import java.util.ArrayList;
import java.util.List;

public class LikesReblogsAdapter extends RecyclerView.Adapter {

    /**
     * interface to handle click for each list item
     * */
    public interface OnItemClickListener{
        void onClick(LikeReblog likeReblog);
    }
    /**
     * data
     * */
    private List<LikeReblog> mLikeReblogs = new ArrayList<>();
    private Context mContext;
    private OnItemClickListener listener;
    /**
     * Constructor
     * */
    public LikesReblogsAdapter(Context context, OnItemClickListener listener){
        mContext = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v =
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.item_likes_reblogs, parent, false
                );
     return new LikesReblogsHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((LikesReblogsHolder) holder).bind(mLikeReblogs.get(position));
    }

    @Override
    public int getItemCount() {
        return mLikeReblogs.size();
    }

    class LikesReblogsHolder extends RecyclerView.ViewHolder{
        TextView mBlogName;
        ImageView mBlogImage;
        ImageView mLikeOrBlogImage;
        public LikesReblogsHolder(@NonNull View itemView) {
            super(itemView);
            mBlogImage = itemView.findViewById(R.id.likes_reblogs_image);
            mBlogName = itemView.findViewById(R.id.likes_reblogs_blog_name);
            mLikeOrBlogImage = itemView.findViewById(R.id.likes_reblogs_heart_or_reblog);
        }
        /**
         * action taken to fill view data first time or when recycling
         * */
        public void bind(LikeReblog currentLikeReblog){
            // load blog image
            Glide.with(mContext).load(currentLikeReblog.getBlogImageUrl()).into(mBlogImage);
            // blog name
            mBlogName.setText(currentLikeReblog.getBlogName());
            // heart or reblog
            if(currentLikeReblog.getLikeOrReblog()==LikeReblog.REBLOG_TYPE){
                mLikeOrBlogImage.setImageResource(R.drawable.repeat_ic_icon_24);
            }
            else{
                mLikeOrBlogImage.setImageResource(R.drawable.heart_icon_24);
            }
            // set on click listeners
            setOnClickListeners(currentLikeReblog);
        }
        public void setOnClickListeners(LikeReblog likeReblog){
            // go to that blog page
            itemView.findViewById(R.id.likes_reblogs_list_item).setOnClickListener(v -> {
                listener.onClick(likeReblog);
            });
        }
    }
    /**
     * Update likes, reblogs list
     * */
    public void setList(List<LikeReblog> likeReblogList){
        this.mLikeReblogs = likeReblogList;
        notifyDataSetChanged();
    }
}
