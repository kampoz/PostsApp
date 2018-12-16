package com.example.kamil.postsapp.remote;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

import java.util.List;


import com.example.kamil.postsapp.model.Comment;
import com.example.kamil.postsapp.model.Post;
import com.example.kamil.postsapp.model.User;

public interface ApiClient {

    @GET("/posts")
    Observable<List<Post>> getAllPosts();

    @GET("/users/{userId}")
    Observable<User> getUser(@Path("userId") String userId);

    @GET("/posts/{postId}/comments")
    Observable<List<Comment>> getCommentsForPost(@Path("postId") String postId);

    @GET("/posts/{postId}")
    Observable<Post> getPost(@Path("postId") String postId);

}
