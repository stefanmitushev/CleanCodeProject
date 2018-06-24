package com.anti.drama.mybookkeeper.WebServices;

public interface MyCallback<T> {
    T onResponse(T response);
    void onError(Throwable error);
}
