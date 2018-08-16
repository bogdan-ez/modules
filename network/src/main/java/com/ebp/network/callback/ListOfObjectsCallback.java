package com.ebp.network.callback;

import java.util.List;

public interface ListOfObjectsCallback<T> extends BasicCallback {
    void onSuccess(List<T> objects);
}
