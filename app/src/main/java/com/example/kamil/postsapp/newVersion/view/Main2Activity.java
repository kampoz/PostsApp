package com.example.kamil.postsapp.newVersion.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.kamil.postsapp.R;
import com.example.kamil.postsapp.adapter.Adapter2;
import com.example.kamil.postsapp.remote.Repository;
import com.example.kamil.postsapp.newVersion.viewmodel.PostViewModel;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity implements Adapter2.Listener {
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

        postViewModel.getPostsArrayListMutableLiveData().observe(this, new Observer<ArrayList<PostViewModel>>() {
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

    @Override
    public void onItemClick(String postId) {
        openPostFragment(postId);
    }

    private void openPostFragment(String postId) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fl_container, PostFragment2.newInstance(postId), null)
                .addToBackStack(null)
                .commit();

    }
}
