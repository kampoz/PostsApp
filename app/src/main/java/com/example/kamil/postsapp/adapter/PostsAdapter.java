package com.example.kamil.postsapp.adapter;

import android.net.sip.SipSession;
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
    private Listener listener;

    private class PostViewHolder extends RecyclerView.ViewHolder {
        public TextView tvTitle;
        public TextView tvContent;

        public PostViewHolder(View vItem) {
            super(vItem);
            tvTitle = vItem.findViewById(R.id.post_title);
            tvContent = vItem.findViewById(R.id.post_content);
        }
    }

    public PostsAdapter(List<Post> posts, RecyclerView recyclerView, Listener listener) {
        this.posts = posts;
        this.recyclerView = recyclerView;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.post_layout, viewGroup, false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(posts.get(i).getUserId());
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

    public interface Listener{
        void onItemClick(String userid);
    }
}
