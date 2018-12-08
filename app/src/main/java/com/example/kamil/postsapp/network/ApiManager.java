package com.example.kamil.postsapp.network;

import com.example.kamil.postsapp.model.Comment;
import com.example.kamil.postsapp.model.Post;
import com.example.kamil.postsapp.model.User;

import io.reactivex.Observable;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiManager {
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";

//    private static Retrofit.Builder builder = new Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create());
//
//
//    private static Retrofit retrofit = builder.build();
//
//    private static ApiClient apiClient = retrofit.create(ApiClient.class);
//
//    public static Call<List<Post>> getAllPosts(){
//        return apiClient.getAllPosts();
//    }
//
//    public static Call<User> getUser(int userId){
//        return apiClient.getUser(userId);
//    }
//
//    public static Call<List<Comment>> getCommentsForPost(int postId){
//        return apiClient.getCommentsForPost(postId);
//    }

    //////
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

    public Observable<User> getUser(int userId) {
        return buildRetrofit().getUser(userId);
    }

    public Observable<List<Comment>> getCommentsForPost(int postId) {
        return buildRetrofit().getCommentsForPost(postId);
    }
}
