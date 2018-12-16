package com.example.kamil.postsapp.remote;

import com.example.kamil.postsapp.model.Comment;
import com.example.kamil.postsapp.model.Post;
import com.example.kamil.postsapp.model.User;

import io.reactivex.Observable;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiManager {
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";

    private ApiClient buildRetrofit() {
        OkHttpClient okHttpClienBuilder = new OkHttpClient();

        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClienBuilder)
                .build()
                .create(ApiClient.class);
    }

    public Observable<List<Post>> getAllPosts() {
        return buildRetrofit().getAllPosts();
    }

    public Observable<User> getUser(String userId) {
        return buildRetrofit().getUser(userId);
    }

    public Observable<Post> getPost(String postId) {
        return buildRetrofit().getPost(postId);
    }

    public Observable<List<Comment>> getCommentsForPost(String postId) {
        return buildRetrofit().getCommentsForPost(postId);
    }
}
