package com.example.kamil.postsapp.remote;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;

import com.example.kamil.postsapp.model.Post;
import com.example.kamil.postsapp.newVersion.viewmodel.PostViewModel;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class Repository {
    private Disposable disposable;
    private PostViewModel postViewModel;
    ApiManager apiManager = new ApiManager();

    public void getAllPosts(AppCompatActivity context) {
        postViewModel = ViewModelProviders.of(context).get(PostViewModel.class);
        disposable = apiManager.getAllPosts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(postsList -> {
                    ArrayList<PostViewModel> responseListPostViewModel = new ArrayList<>();
                    for (Post post : postsList) {
                        responseListPostViewModel.add(new PostViewModel(post));
                    }
                    postViewModel.getPostsArrayListMutableLiveData().setValue(responseListPostViewModel);
                    disposable.dispose();
                }, error -> {
                    error.printStackTrace();
                    disposable.dispose();
                });
    }

    public void getSinglePostById(AppCompatActivity context, String postId) {
        postViewModel = ViewModelProviders.of(context).get(PostViewModel.class);
        disposable = apiManager.getPost(postId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(post -> {
                    postViewModel.getPostMutableLiveData().setValue(new PostViewModel(post));
                    disposable.dispose();
                }, error -> {
                    error.printStackTrace();
                    disposable.dispose();
                });
    }
}


