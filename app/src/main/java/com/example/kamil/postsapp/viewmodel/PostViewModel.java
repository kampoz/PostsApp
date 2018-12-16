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

    public MutableLiveData<ArrayList<PostViewModel>> arrayListMutableLiveData = new MutableLiveData<>();
    private ArrayList<PostViewModel> postsArrayList;

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
        postsArrayList = new ArrayList<>();
//        Post post = new Post("1", "BOdy 1", "Title 1", "111");
//        PostViewModel postViewModel = new PostViewModel(post);
//        postsArrayList.add(postViewModel);
        arrayListMutableLiveData.setValue(postsArrayList);

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
