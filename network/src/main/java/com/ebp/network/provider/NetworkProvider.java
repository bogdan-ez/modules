package com.ebp.network.provider;

import com.ebp.network.callback.ListOfObjectsCallback;
import com.ebp.network.callback.SingleObjectCallback;
import com.ebp.network.model.ResourceConfiguration;

import java.util.List;

public interface NetworkProvider {

    /**
     * Make GET call to remote resource which returns object into
     * {@link SingleObjectCallback#onSuccess(Object)} method in case of success call otherwise
     * return error message into {@link SingleObjectCallback#onError(String)} method.
     *
     * @param configuration the {@link ResourceConfiguration} to configure correct parameters
     * @param clazz         the {@link Class} of returned object
     * @param callback      the {@link SingleObjectCallback} for returning result to caller
     */
    void makeGetCall(ResourceConfiguration configuration, final Class clazz, final SingleObjectCallback callback);

    /**
     * Make GET call to remote resource which returns list of objects to
     * {@link ListOfObjectsCallback#onSuccess(List)} method in case of success call otherwise
     * return error message into {@link SingleObjectCallback#onError(String)} method.
     *
     * @param configuration the {@link ResourceConfiguration} to configure correct parameters
     * @param clazz         the {@link Class} of returned object
     * @param callback      the {@link ListOfObjectsCallback} for returning result to caller
     */
    void makeGetCall(ResourceConfiguration configuration, final Class clazz, final ListOfObjectsCallback callback);

    /**
     * Make POST call to remote resource which returns object into
     * {@link SingleObjectCallback#onSuccess(Object)} method in case of success call otherwise
     * return error message into {@link SingleObjectCallback#onError(String)} method .
     *
     * @param configuration the {@link ResourceConfiguration} to configure correct parameters
     * @param clazz         the {@link Class} of returned object
     * @param callback      the {@link SingleObjectCallback} for returning result to caller
     */
    void makePostCall(ResourceConfiguration configuration, final Class clazz, final SingleObjectCallback callback);
}
