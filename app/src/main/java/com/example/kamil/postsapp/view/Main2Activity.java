package com.example.kamil.postsapp.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.kamil.postsapp.R;
import com.example.kamil.postsapp.adapter.Adapter1;
import com.example.kamil.postsapp.adapter.Adapter2;
import com.example.kamil.postsapp.model.Post;
import com.example.kamil.postsapp.remote.ApiManager;
import com.example.kamil.postsapp.remote.Repository;
import com.example.kamil.postsapp.viewmodel.PostViewModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class Main2Activity extends AppCompatActivity {
    private PostViewModel postViewModel;
    private RecyclerView recyclerView;
    private Adapter2 adapter2;
    FloatingActionButton fab;
    Repository repository = new Repository();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        recyclerView = findViewById(R.id.recyclerview);
        postViewModel = ViewModelProviders.of(this).get(PostViewModel.class);
        fab = findViewById(R.id.floatingActionButton);

        postViewModel.getArrayListMutableLiveData().observe(this, new Observer<ArrayList<PostViewModel>>() {
            @Override
            public void onChanged(@Nullable ArrayList<PostViewModel> postViewModels) {
                adapter2 = new Adapter2(Main2Activity.this, postViewModels);
                recyclerView.setLayoutManager(new LinearLayoutManager(Main2Activity.this));
                recyclerView.setAdapter(adapter2);
            }
        });

        fab.setOnClickListener(v -> requestForAllPosts());
    }

    private void requestForAllPosts() {
        repository.getAllPosts(this);
    }
}
