package com.example.kamil.postsapp.network;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import java.util.List;


import com.example.kamil.postsapp.model.Comment;
import com.example.kamil.postsapp.model.Post;
import com.example.kamil.postsapp.model.User;

public interface ApiClient {

    //https://jsonplaceholder.typicode.com/posts
    @GET("/posts")
    Observable<List<Post>> getAllPosts();

    //https://jsonplaceholder.typicode.com/users/2
    @GET("/users/{UserId}")
    Observable<User> getUser(int userId);

    //https://jsonplaceholder.typicode.com/posts/1/comments
    @GET("/posts/{postId}/comments")
    Observable<List<Comment>> getCommentsForPost(int postId);

}
