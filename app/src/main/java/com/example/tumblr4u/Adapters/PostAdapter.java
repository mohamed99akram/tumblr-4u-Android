package com.example.tumblr4u.Adapters;

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

    private List<Post> posts = new ArrayList<>();

    @NonNull
    @Override
    public PostAdapter.PostsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.view_posts_list_item, parent, false);
        PostsViewHolder viewHolder = new PostsViewHolder(v);
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

            // TODO add click listeners here?

        }

        /**
         * Action taken when recycling a view Load data from the list to the post View
         *
         * @param pos the position where the view will be put
         */
        public void makeResult(int pos) {
            Post currentPost = posts.get(pos);
            mWebView.loadDataWithBaseURL(null, currentPost.getHtml(), "text/html", "utf-8",
                    null);
            Glide.with(mBlogImage.getContext()).load(posts.get(pos).getBlogImageUrl()).into(mBlogImage);
            mBlogName.setText(currentPost.getBlogName());
            int notesCount = currentPost.getNotesCount();
            mNotesCountTextView.setText(
                    notesCount == 0 ? "" : notesCount == 1 ? "1 note" : notesCount + " notes");
        }
    }
}
