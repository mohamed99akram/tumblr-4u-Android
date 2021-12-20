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
import com.example.tumblr4u.Models.Comment;
import com.example.tumblr4u.R;

import java.util.ArrayList;
import java.util.List;

// https://sendbird.com/developer/tutorials/android-chat-tutorial-building-a-messaging-ui
public class CommentsAdapter extends RecyclerView.Adapter {
    /**
     * constants my comment or others' comments
     */
    private static final int COMMENT_MINE = 1;
    private static final int COMMENT_OTHERS = 2;

    /**
     * interface to handle clicks for this current item
     */
    public interface OnItemClickListener {
        void onImageClickListener(Comment comment);

        void onUserNameClickListener(Comment comment);
    }

    /**
     * data
     */
    private List<Comment> mComments = new ArrayList<>();
    private OnItemClickListener mListener;
    private Context mContext;

    /**
     * Constructor
     */
    public CommentsAdapter(Context context, OnItemClickListener listener) {
        this.mListener = listener;
        mContext = context;
    }

    /**
     * This method is called behind the scenes to differentiate comments. comments that are mine !=
     * comments that are others'
     */
    @Override
    public int getItemViewType(int position) {
        Comment currentComment = mComments.get(position);

        // user's blogId
        String activeBlogId = mContext.getSharedPreferences("userDetails",
                Context.MODE_PRIVATE).getString(mContext.getString(R.string.myBlogId), "23445");
        // if this comment is mine -> COMMENT_MINE
        if (activeBlogId.equals(currentComment.getBlogId())) {
            return COMMENT_MINE;
        } else {
            return COMMENT_OTHERS;
        }
    }

    @NonNull
    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;

        if (viewType == COMMENT_MINE) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comment_mine,
                    parent, false);
            return new CommentViewHolder(view);
        } else if (viewType == COMMENT_OTHERS) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comment_others,
                    parent, false);
            return new CommentViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((CommentViewHolder)holder).bind(mComments.get(position));
    }


    @Override
    public int getItemCount() {
        return mComments.size();
    }
//    public class CommentMineViewHolder extends RecyclerView.ViewHolder{
//
//        public CommentMineViewHolder(@NonNull View itemView) {
//            super(itemView);
//        }
//    }
    public class CommentViewHolder extends RecyclerView.ViewHolder {
        TextView mCommentText, mBlogName;
        ImageView mBlogImage;

        public CommentViewHolder(@NonNull View itemView) {
            super(itemView);
            // TODO check if this will be changed -> two classes?
            mCommentText = itemView.findViewById(R.id.comment_text);
            mBlogImage = itemView.findViewById(R.id.comment_blog_image);
            mBlogName = itemView.findViewById(R.id.comment_user_name);
        }
        public void bind(Comment currentComment){
            // load blog image
            Glide.with(mContext).load(currentComment.getImageUrl()).into(mBlogImage);
            // change blog name
            mBlogName.setText(currentComment.getUserName());
            // change comment text
            mCommentText.setText(currentComment.getCommentText());
        }
    }
    public void setList(List<Comment> comments){
        this.mComments = comments;
        notifyDataSetChanged();
    }
}
