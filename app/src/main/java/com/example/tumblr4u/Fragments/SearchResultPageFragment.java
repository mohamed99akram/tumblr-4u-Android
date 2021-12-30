package com.example.tumblr4u.Fragments;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.tumblr4u.Adapters.PostAdapter;
import com.example.tumblr4u.Models.Post;
import com.example.tumblr4u.R;
import com.example.tumblr4u.View.NotesActivity;
import com.example.tumblr4u.View.SearchActivity;
import com.example.tumblr4u.View.WritePostActivity;
import com.example.tumblr4u.ViewModel.HomeFragmentViewModel;
import com.example.tumblr4u.ViewModel.SearchResultFragmentViewModel;
import com.google.gson.Gson;

public class SearchResultPageFragment extends Fragment {
    private View mRoot;
    private RecyclerView mRecyclerView;
    private SearchResultFragmentViewModel mSearchResultFragmentViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mRoot = inflater.inflate(R.layout.fragment_search_result_page, container, false);

        // Prepare Recycler view
        mRecyclerView = mRoot.findViewById(R.id.search_result_page_recyclerView);
        PostAdapter adapter = new PostAdapter(getActivity(), new PostAdapter.OnItemClickListener() {
            @Override
            public void onEditClickListener(Post post) {
                Toast.makeText(mRoot.getContext(), "Edit Pressed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCommentClickListener(Post post) {
//                Toast.makeText(mRoot.getContext(), "Add Comment Pressed", Toast.LENGTH_SHORT)
//                .show();
                Intent intent = new Intent(getActivity(), NotesActivity.class);
                Gson gson = new Gson();
                String postJSON = gson.toJson(post);
                intent.putExtra("postJSON", postJSON);
                Log.i("HomeFragment", "passed postJSON = "+postJSON);
//                intent.putExtra("postId", post.getPostId());
//                intent.putExtra("blogId", post.getBlog_id());
//                intent.putExtra("notesCount", post.getNotesCount());
//                intent.putExtra("likesCount", post.getLikesCount());
//                intent.putExtra("reblogsCount", post.getReblogsCount());
                startActivity(intent);
            }

            @Override
            public void onLikeClickListener(Post post) {
                Toast.makeText(mRoot.getContext(), "Like Pressed", Toast.LENGTH_SHORT).show();
                mSearchResultFragmentViewModel.pressLikeButton(post);
            }

            @Override
            public void onShareClickListener(Post post) {
                Toast.makeText(mRoot.getContext(), "Share Pressed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onReblogClickListener(Post post) {
                Toast.makeText(mRoot.getContext(), "Reblog Pressed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNotesClickListener(Post post) {
                Toast.makeText(mRoot.getContext(), "Notes Pressed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRemoveClickListener(Post post) {
                Toast.makeText(mRoot.getContext(), "Remove Pressed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onImageOrNameClickListener(Post post) {
                Toast.makeText(mRoot.getContext(), "Image or Name Pressed",
                        Toast.LENGTH_SHORT).show();
            }
        });
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(adapter);

        // ViewModel
//        HomeFragmentViewModel = ViewModelProviders.of(this).get(HomeFragmentViewModel.class);

        mSearchResultFragmentViewModel = new ViewModelProvider(this).get(
                SearchResultFragmentViewModel.class);

        //TODO move data between recycler view and view model - observers
        mSearchResultFragmentViewModel.postsList.observe(getViewLifecycleOwner(),
                posts -> {
                    adapter.setList(posts);
                    mRoot.findViewById(R.id.search_result_fragment_progress_bar).setVisibility(View.GONE);
                });

        // go to the search activity and get the search word
        getResultOfWord();

        return mRoot;
    }

    public void getResultOfWord(){
        String searchWord = ((SearchActivity)getActivity()).getSearchWord();
        mSearchResultFragmentViewModel.getResultPosts(searchWord);
    }
}