package com.example.kamil.postsapp.oldVersion.view;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kamil.postsapp.R;
import com.example.kamil.postsapp.model.Comment;
import com.example.kamil.postsapp.model.Post;
import com.example.kamil.postsapp.model.User;
import com.example.kamil.postsapp.remote.ApiManager;
import com.example.kamil.postsapp.oldVersion.viewmodel.UserViewModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class PostFragment extends Fragment {

    private UserViewModel mViewModel;
    private String postId;
    private TextView tvTitle;
    private TextView tvBody;
    private TextView tvAuthor;
    private RecyclerView recyclerView;
    private List<Comment> comments = new ArrayList<>();

    public static PostFragment newInstance(String postId) {
        PostFragment userFragment = new PostFragment();
        userFragment.postId = postId;
        return userFragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.post_fragment, container, false);
        tvTitle = view.findViewById(R.id.tvTitle);
        tvBody = view.findViewById(R.id.tvContent);
        tvAuthor = view.findViewById(R.id.tvAuthor);
        recyclerView = view.findViewById(R.id.rv);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(UserViewModel.class);

        getPostObservable(postId)
                .map(post -> getUserObservable(post.getUserId())
                .subscribe())
                .subscribe();

        // TODO: Use the ViewModel


    }

    Observable<Post> getPostObservable(String postId) {
        ApiManager apiManager = new ApiManager();
        return apiManager.getPost(postId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(post -> {
                    tvTitle.setText(post.getTitle());
                    tvBody.setText(post.getBody());
                });
    }

    Observable<User> getUserObservable(String userId) {
        ApiManager apiManager = new ApiManager();
        return apiManager.getUser(userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(user -> {
                    recyclerView.setAdapter(new RecyclerView.Adapter() {
                        @NonNull
                        @Override
                        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                            return null;
                        }

                        @Override
                        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

                        }

                        @Override
                        public int getItemCount() {
                            return 0;
                        }
                    });

                });
    }

    Observable<List<Comment>> getCommentsForPost(String postId) {
        ApiManager apiManager = new ApiManager();
        return apiManager.getCommentsForPost(postId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(comments -> {
                    this.comments = comments;

                });
    }
}
