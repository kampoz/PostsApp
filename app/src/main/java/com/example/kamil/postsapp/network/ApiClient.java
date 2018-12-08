package com.example.kamil.postsapp.network;

import retrofit2.Call;
import retrofit2.http.GET;
import java.util.List;

import com.example.kamil.postsapp.model.Comment;
import com.example.kamil.postsapp.model.Post;
import com.example.kamil.postsapp.model.User;

public interface ApiClient {

    //https://jsonplaceholder.typicode.com/posts
    @GET("/posts")
    Call<List<Post>> getAllPosts();

    //https://jsonplaceholder.typicode.com/users/2
    @GET("/users/{UserId}")
    Call<User> getUser(int userId);

    //https://jsonplaceholder.typicode.com/posts/1/comments
    @GET("/posts/{postId}/comments")
    Call<List<Comment>> getCommentsForPost(int postId);

}
