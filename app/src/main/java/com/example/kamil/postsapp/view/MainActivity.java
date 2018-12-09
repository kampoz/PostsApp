package com.example.kamil.postsapp.view;

import android.annotation.SuppressLint;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.kamil.postsapp.R;
import com.example.kamil.postsapp.adapter.PostsAdapter;
import com.example.kamil.postsapp.databinding.ActivityMainBinding;
import com.example.kamil.postsapp.model.Post;
import com.example.kamil.postsapp.network.ApiManager;
import com.example.kamil.postsapp.viewmodel.PostsViewModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements PostsAdapter.Listener {

    private static final String TAG = "TAG1";
    private List<Post> posts = new ArrayList<>();
    private Disposable disposable;

    private PostsViewModel postsViewModel;
    private PostsAdapter adapter;


    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityMainBinding activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        postsViewModel = ViewModelProviders.of(this).get(PostsViewModel.class);
        activityMainBinding.setViewmodel(postsViewModel);
        activityMainBinding.setLifecycleOwner(this);

        ApiManager apiManager = new ApiManager();

        final RecyclerView recyclerView = findViewById(R.id.posts);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        disposable = apiManager.getAllPosts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                    List<Post> posts = new ArrayList<>();
                    posts.addAll(response);
                    postsViewModel.getPosts().setValue(posts);

                    recyclerView.setAdapter(new PostsAdapter(postsViewModel.getPosts().getValue(), recyclerView, this));
                    disposable.dispose();
                });
    }

    @Override
    public void onItemClick(String postId) {
        openPostFagment(postId);
    }

    private void openPostFagment(String postId) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fl_container, PostFragment.newInstance(postId), null)
                .addToBackStack(null)
                .commit();

    }
}



