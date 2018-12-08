package com.example.kamil.postsapp.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kamil.postsapp.R;
import com.example.kamil.postsapp.model.Post;

import java.util.ArrayList;
import java.util.List;

public class PostsAdapter extends RecyclerView.Adapter {

    private List<Post> posts = new ArrayList<>();
    private RecyclerView recyclerView;

    private class PostViewHolder extends RecyclerView.ViewHolder {
        public TextView tvTitle;
        public TextView tvContent;

        public PostViewHolder(View vItem) {
            super(vItem);
            tvTitle = vItem.findViewById(R.id.post_title);
            tvContent = vItem.findViewById(R.id.post_content);
        }
    }

    public PostsAdapter(List<Post> posts, RecyclerView recyclerView) {
        this.posts = posts;
        this.recyclerView = recyclerView;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.post_layout, viewGroup, false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        Post post = posts.get(i);
        ((PostViewHolder)viewHolder).tvTitle.setText(post.getTitle());
        ((PostViewHolder)viewHolder).tvContent.setText(post.getBody());
    }

    @Override
    public int getItemCount() {
        return 50;
    }
}
