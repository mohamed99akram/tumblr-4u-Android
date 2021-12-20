package com.example.tumblr4u.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tumblr4u.R;
import com.example.tumblr4u.Models.Post;
import com.stfalcon.multiimageview.MultiImageView;

import java.util.ArrayList;
import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostsViewHolder> {

    /**
     * interface to handle clicks for this current item
     */
    public interface OnItemClickListener {
        void onEditClickListener(Post post);

        void onCommentClickListener(Post post);

        void onLikeClickListener(Post post);

        void onShareClickListener(Post post);

        void onReblogClickListener(Post post);

        void onNotesClickListener(Post post);

        void onRemoveClickListener(Post post);

        void onImageOrNameClickListener(Post post);
    }

    /**
     * data
     */
    private List<Post> mPosts = new ArrayList<>();
    private OnItemClickListener listener;
    private Context mContext;

    /**
     * Constructor
     */
    public PostAdapter(Context context, OnItemClickListener listener) {
        this.listener = listener;
        mContext = context;
    }

    /**
     * Called behind the scenes when a view is created for the first time viewType can handle
     * different item types with the help of getItemViewType(int position) function
     */
    @NonNull
    @Override
    public PostAdapter.PostsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.view_posts_list_item, parent, false);
        return new PostsViewHolder(v);
    }

    /**
     * Called behind the scenes when recycling a view -> when an existing view's data will be
     * changed
     */
    @Override
    public void onBindViewHolder(@NonNull PostAdapter.PostsViewHolder holder, int position) {
        holder.bind(mPosts.get(position));
    }

    /**
     * 25 January
     */
    @Override
    public int getItemCount() {
        return mPosts.size();
    }

    /**
     * Every view that is an item, will be like this
     */
    class PostsViewHolder extends RecyclerView.ViewHolder {
        WebView mWebView;
        TextView mNotesCountTextView;
        ImageView mBlogImage;
        TextView mBlogName;

        public PostsViewHolder(@NonNull View itemView) {
            super(itemView);
            mWebView = itemView.findViewById(R.id.post_web_view);
            mNotesCountTextView = itemView.findViewById(R.id.notes_count);
            mBlogImage = itemView.findViewById(R.id.view_post_blog_image);
            mBlogName = itemView.findViewById(R.id.view_post_blog_name);
        }

        /**
         * Action taken when recycling a view Load data from the list to the post View
         *
         * @param currentPost the current post in the list
         */
        public void bind(Post currentPost) {
            // load post data
            mWebView.loadDataWithBaseURL(null, currentPost.getHtml(), "text/html", "utf-8",
                    null);
            // load profile image
            Glide.with(mContext).load(currentPost.getBlogImageUrl()).into(
                    mBlogImage);
            // blog name
            mBlogName.setText(currentPost.getBlogName());
            // notes count
            int notesCount = currentPost.getNotesCount();
            mNotesCountTextView.setText(
                    notesCount == 0 ? "" : notesCount == 1 ? "1 note" : notesCount + " notes");
            // if this blog is mine, show edit & remove
            hideEdit_RemoveIfNotMine(currentPost);
            // onClickListeners
            setOnClickListeners(currentPost);
        }

        /**
         * This method should hide edit, remove icons if post is not mine
         */
        private void hideEdit_RemoveIfNotMine(Post currentPost) {
            // user's blogId
            String activeBlogId = mContext.getSharedPreferences("userDetails",
                    Context.MODE_PRIVATE).getString(mContext.getString(R.string.myBlogId), "234352");
            if (activeBlogId.equals(currentPost.getBlog_id())) {
                itemView.findViewById(R.id.edit_button).setVisibility(View.VISIBLE);
                itemView.findViewById(R.id.delete_button).setVisibility(View.VISIBLE);
                Log.i("PostAdapter", "mine, sharedBlogId = "+activeBlogId);
            }
            else{
                itemView.findViewById(R.id.edit_button).setVisibility(View.GONE);
                itemView.findViewById(R.id.delete_button).setVisibility(View.GONE);
                Log.i("PostAdapter", "others, sharedBlogId = "+activeBlogId);
            }
        }

        public void setOnClickListeners(Post post) {
            itemView.findViewById(R.id.edit_button).setOnClickListener(v -> {
                listener.onEditClickListener(post);
            });

            itemView.findViewById(R.id.comment_button).setOnClickListener(v -> {
                listener.onCommentClickListener(post);
            });
            itemView.findViewById(R.id.love_button).setOnClickListener(v -> {
                listener.onLikeClickListener(post);
            });
            itemView.findViewById(R.id.share_button).setOnClickListener(v -> {
                listener.onShareClickListener(post);
            });
            itemView.findViewById(R.id.reblog_button).setOnClickListener(v -> {
                listener.onReblogClickListener(post);
            });
            itemView.findViewById(R.id.notes_count).setOnClickListener(v -> {
                listener.onNotesClickListener(post);
            });
            itemView.findViewById(R.id.delete_button).setOnClickListener(v -> {
                listener.onRemoveClickListener(post);
            });
            itemView.findViewById(R.id.view_post_blog_image).setOnClickListener(v -> {
                listener.onImageOrNameClickListener(post);
            });
            itemView.findViewById(R.id.view_post_blog_name).setOnClickListener(v -> {
                listener.onImageOrNameClickListener(post);
            });
        }
    }

    /**
     * Update adapter's list to show the list in the recycler view
     *
     * @param postsList list of posts to put in recyclerView, it is updated in the ViewModel, and is
     *                  set in the View
     */
    public void setList(List<Post> postsList) {
        this.mPosts = postsList;
        notifyDataSetChanged();
    }
}
