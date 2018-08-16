package com.ebp.network.repository;

import android.support.annotation.NonNull;
import android.util.Log;

import com.ebp.network.callback.ListOfObjectsCallback;
import com.ebp.network.callback.SingleObjectCallback;
import com.ebp.network.model.ResourceConfiguration;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.ResponseBody;
import rx.Single;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static java.util.Arrays.asList;

public class ResourceRepository implements IResourceRepository {
    private final static String TAG = ResourceRepository.class.getCanonicalName();

    private ResourceClient api = new ResourceClient();

    @NonNull
    @Override
    public void getSingleData(ResourceConfiguration configuration, final Class clazz, final SingleObjectCallback callback) {
        callService(configuration)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ResponseBody>() {
                    @Override
                    public void onCompleted() {
                        Log.i(TAG, "Item receiving completed.");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "Error during loading call data. Message: " + e.getMessage());
                        callback.onError(e.getMessage());
                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try {
                            callback.onSuccess(new Gson().fromJson(responseBody.string(), clazz));
                        } catch (IOException e) {
                            Log.e(TAG, "Error during converting getSingleData data. Message: " + e.getMessage());
                            callback.onError(e.getMessage());
                        }
                    }
                });
    }

    @NonNull
    @Override
    public void getListData(ResourceConfiguration configuration, final Class clazz, final ListOfObjectsCallback callback) {
        callService(configuration)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ResponseBody>() {
                    @Override
                    public void onCompleted() {
                        Log.i(TAG, "Items receiving completed.");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "Error during loading list data. Message: " + e.getMessage());
                        callback.onError(e.getMessage());
                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try {
                            Object[] items = (Object[]) new Gson().fromJson(responseBody.string(), clazz);
                            callback.onSuccess(asList(items));
                        } catch (IOException e) {
                            Log.e(TAG, "Error during converting getListData data. Message: " + e.getMessage());
                            callback.onError(e.getMessage());
                        }
                    }
                });
    }

    @NonNull
    @Override
    public void postSingleData(ResourceConfiguration configuration, final Class clazz, final SingleObjectCallback callback) {
        postSingle(configuration)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ResponseBody>() {
                    @Override
                    public void onCompleted() {
                        Log.i(TAG, "Item receiving completed.");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "Error during posting call data. Message: " + e.getMessage());
                        callback.onError(e.getMessage());
                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try {
                            callback.onSuccess(new Gson().fromJson(responseBody.string(), clazz));
                        } catch (IOException e) {
                            Log.e(TAG, "Error during converting postSingleData data. Message: " + e.getMessage());
                            callback.onError(e.getMessage());
                        }
                    }
                });
    }

    private Single<ResponseBody> callService(ResourceConfiguration configuration) {
        if (configuration.getHeaders() != null && configuration.getHeaders().size() > 0) {
            return api.createGetClient(configuration).call(configuration.getResource(), configuration.getHeaders());
        } else {
            return api.createGetClient(configuration).call(configuration.getResource());
        }
    }

    private Single<ResponseBody> postSingle(ResourceConfiguration configuration) {
        if (configuration.getHeaders() != null && configuration.getHeaders().size() > 0 && configuration.getBody() != null) {
            return api.createPostClient(configuration).call(configuration.getResource(), configuration.getHeaders(), configuration.getBody());
        } else if (configuration.getHeaders() != null && configuration.getHeaders().size() > 0) {
            return api.createPostClient(configuration).call(configuration.getResource(), configuration.getHeaders());
        } else if (configuration.getBody() != null) {
            return api.createPostClient(configuration).call(configuration.getResource(), configuration.getBody());
        } else {
            return api.createPostClient(configuration).call(configuration.getResource());
        }
    }
}
