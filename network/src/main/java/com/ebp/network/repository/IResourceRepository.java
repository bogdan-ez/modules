package com.ebp.network.repository;

import android.support.annotation.NonNull;

import com.ebp.network.callback.ListOfObjectsCallback;
import com.ebp.network.callback.SingleObjectCallback;
import com.ebp.network.model.ResourceConfiguration;

public interface IResourceRepository {

    @NonNull
    void getSingleData(ResourceConfiguration configuration, final Class clazz, final SingleObjectCallback callback);

    @NonNull
    void getListData(ResourceConfiguration configuration, final Class clazz, final ListOfObjectsCallback callback);

    @NonNull
    void postSingleData(ResourceConfiguration configuration, final Class clazz, final SingleObjectCallback callback);
}
