package com.example.kamil.postsapp.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.kamil.postsapp.model.User;

public class UserViewModel extends ViewModel {

    public MutableLiveData<User> user = new MutableLiveData<>();

    public MutableLiveData<User> getScore(){
        if (user == null) {
            user = new MutableLiveData<User>();
        }
        return user;
    }
}
