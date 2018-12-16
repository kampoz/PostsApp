package com.example.kamil.postsapp.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.kamil.postsapp.model.Post;

import java.util.ArrayList;

public class PostViewModel extends ViewModel {
    private String id;
    private String body;
    private String title;
    private String userId;

    public static MutableLiveData<ArrayList<PostViewModel>> arrayListMutableLiveData = new MutableLiveData<>();

    public PostViewModel() {
    }

    public PostViewModel(Post post) {
        this.id = post.getId();
        this.body = post.getBody();
        this.title = post.getTitle();
        this.userId = post.getUserId();
    }

    public void setUserId (String userId)
    {
        this.userId = userId;
    }

    public MutableLiveData<ArrayList<PostViewModel>> getArrayListMutableLiveData() {
        return arrayListMutableLiveData;
    }

    public String getId() {
        return id;
    }

    public String getBody() {
        return body;
    }

    public String getTitle() {
        return title;
    }

    public String getUserId() {
        return userId;
    }
}
