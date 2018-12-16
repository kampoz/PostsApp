package com.example.kamil.postsapp.remote;

import android.arch.lifecycle.MutableLiveData;

import com.example.kamil.postsapp.model.Post;
import com.example.kamil.postsapp.viewmodel.PostViewModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class Repository {

    public MutableLiveData<ArrayList<PostViewModel>> arrayListMutableLiveData = new MutableLiveData<>();
    private ArrayList<PostViewModel>arrayList;
    private ArrayList<Post> items;
    private Disposable disposable;
    private PostViewModel postViewModel;
    ApiManager apiManager = new ApiManager();

    public void getAllPosts() {
        disposable = apiManager.getAllPosts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                    List<Post> posts = new ArrayList<>(response);
                    ArrayList<PostViewModel> responseListPostViewModel = new ArrayList<>();
                    for (Post post : response) {
                        responseListPostViewModel.add(new PostViewModel(post));
                    }
                    postViewModel.getArrayListMutableLiveData().setValue(responseListPostViewModel);
                    disposable.dispose();
                }, error -> error.printStackTrace());
    }
}


