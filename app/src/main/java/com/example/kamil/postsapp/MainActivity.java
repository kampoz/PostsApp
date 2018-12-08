package com.example.kamil.postsapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.kamil.postsapp.adapter.PostsAdapter;
import com.example.kamil.postsapp.model.Post;
import com.example.kamil.postsapp.network.ApiManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "TAG1";
    private List<Post> posts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final RecyclerView recyclerView = findViewById(R.id.posts);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

//         ustawiamy animatora, który odpowiada za animację dodania/usunięcia elementów listy
//        recyclerView.setItemAnimator(new DefaultItemAnimator());      ???


        ApiManager.getAllPosts()
                .enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                Log.d(TAG, response.toString());
                posts = response.body();
                recyclerView.setAdapter(new PostsAdapter(posts, recyclerView));
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {

            }
        });

    }
}
