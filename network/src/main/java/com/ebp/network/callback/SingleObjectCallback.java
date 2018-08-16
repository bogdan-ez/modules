package com.ebp.network.callback;

public interface SingleObjectCallback<T> extends BasicCallback {
    void onSuccess(T t);
}
