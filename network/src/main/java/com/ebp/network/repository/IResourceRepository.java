package com.ebp.network.repository;

import android.support.annotation.NonNull;

import com.ebp.network.callback.ListOfObjectsCallback;
import com.ebp.network.callback.SingleObjectCallback;
import com.ebp.network.model.ResourceConfiguration;

public interface IResourceRepository {

    /**
     * Make GET call to remote resource which returns object into callback
     *
     * @param configuration the {@link ResourceConfiguration} to configure correct parameters
     * @param clazz         the {@link Class} of returned object
     * @param callback      the {@link SingleObjectCallback} for returning result to caller
     */
    @NonNull
    void getSingleData(ResourceConfiguration configuration, final Class clazz, final SingleObjectCallback callback);

    /**
     * Make GET call to remote resource which returns list of objects
     *
     * @param configuration the {@link ResourceConfiguration} to configure correct parameters
     * @param clazz         the {@link Class} of returned object
     * @param callback      the {@link ListOfObjectsCallback} for returning result to caller
     */
    @NonNull
    void getListData(ResourceConfiguration configuration, final Class clazz, final ListOfObjectsCallback callback);

    /**
     * Make POST call to remote resource which returns into callback
     *
     * @param configuration the {@link ResourceConfiguration} to configure correct parameters
     * @param clazz         the {@link Class} of returned object
     * @param callback      the {@link SingleObjectCallback} for returning result to caller
     */
    @NonNull
    void postSingleData(ResourceConfiguration configuration, final Class clazz, final SingleObjectCallback callback);
}
