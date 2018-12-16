package com.example.kamil.postsapp.remote;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;

import com.example.kamil.postsapp.model.Post;
import com.example.kamil.postsapp.viewmodel.PostViewModel;

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
                .subscribe(response -> {
                    ArrayList<PostViewModel> responseListPostViewModel = new ArrayList<>();
                    for (Post post : response) {
                        responseListPostViewModel.add(new PostViewModel(post));
                    }
                    postViewModel.getArrayListMutableLiveData().setValue(responseListPostViewModel);
                    disposable.dispose();
                }, Throwable::printStackTrace);
    }
}


