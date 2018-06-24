package com.anti.drama.mybookkeeper.WebServices;

public interface MyCallback<T> {
    T onResponse(T respoonse);
    void onError(Throwable error);
}
