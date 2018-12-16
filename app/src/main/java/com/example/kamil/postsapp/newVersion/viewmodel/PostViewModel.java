package com.example.kamil.postsapp.newVersion.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.kamil.postsapp.model.Post;

import java.util.ArrayList;

public class PostViewModel extends ViewModel {
    private String id;
    private String body;
    private String title;
    private String userId;

    public MutableLiveData<ArrayList<PostViewModel>> arrayListMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<PostViewModel> postMutableLiveData = new MutableLiveData<>();

    /** Pusty konstruktor wymagany przy tworzeniu instancji przez ViewModelProviders.of...*/
    public PostViewModel() {
    }

    public PostViewModel(Post post) {
        this.id = post.getId();
        this.body = post.getBody();
        this.title = post.getTitle();
        this.userId = post.getUserId();
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public MutableLiveData<ArrayList<PostViewModel>> getPostsArrayListMutableLiveData() {
        return arrayListMutableLiveData;
    }

    public MutableLiveData<PostViewModel> getPostMutableLiveData() {
        return postMutableLiveData;
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
