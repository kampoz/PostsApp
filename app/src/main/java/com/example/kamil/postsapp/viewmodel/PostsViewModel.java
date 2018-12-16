package com.example.kamil.postsapp.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.kamil.postsapp.model.Post;

import java.util.ArrayList;
import java.util.List;

public class PostsViewModel extends ViewModel {

    public MutableLiveData<List<Post>> posts = new MutableLiveData<>();

    public MutableLiveData<List<Post>>getPosts() {
        if (posts == null) {
            posts = new MutableLiveData<List<Post>>();
        }
        return posts;
    }
}
