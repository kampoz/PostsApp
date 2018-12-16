package com.example.kamil.postsapp.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kamil.postsapp.R;
import com.example.kamil.postsapp.databinding.PostBinding2;
import com.example.kamil.postsapp.viewmodel.PostViewModel;

import java.util.ArrayList;

public class Adapter2 extends RecyclerView.Adapter<Adapter2.PostViewHolder> {

    private ArrayList<PostViewModel> arrayList;
    private Context context;
    private LayoutInflater layoutInflater;

    public Adapter2(Context context, ArrayList<PostViewModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    public void setArrayList(ArrayList<PostViewModel> arrayList) {
        this.arrayList = arrayList;
    }

    class PostViewHolder extends RecyclerView.ViewHolder {
        private PostBinding2 postBinding2;

        public PostViewHolder(@NonNull PostBinding2 postBinding2) {
            super(postBinding2.getRoot());
            this.postBinding2 = postBinding2;
        }

        public void bind(PostViewModel postViewModel) {
            this. postBinding2.setPostviewmodel(postViewModel);
            postBinding2.executePendingBindings();
        }

        public PostBinding2 getPostBinding2() {
            return postBinding2;
        }
    }


    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(viewGroup.getContext());
        }
        PostBinding2 postBinding2 = DataBindingUtil.inflate(layoutInflater, R.layout.post_layout2, viewGroup, false);
        return new PostViewHolder(postBinding2);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder postViewHolder, int i) {
        PostViewModel postsViewModel = arrayList.get(i);
        postViewHolder.bind(postsViewModel);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}
