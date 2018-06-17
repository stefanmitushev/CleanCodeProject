package com.example.smitushev.mybookkeeper.Activities.Base;

public interface OnTokenListener {
    void onTokenAcquired(String token);
    void onError();
}
