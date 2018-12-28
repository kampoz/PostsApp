package com.example.kamil.postsapp;

import android.content.Context;

/**
 * Created by Kamil on 2018-12-28.
 */
public class ClassToTest {

    Context context;

    public ClassToTest(Context context) {
        this.context = context;
    }

    public String getHelloWorldString() {
        return context.getString(R.string.hello_world);
    }

    public void startSecondMethod() {
        getHelloWorldString();
    }
}
