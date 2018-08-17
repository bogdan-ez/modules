package com.ebp.network.repository;

import android.support.annotation.NonNull;
import android.util.Log;

import com.ebp.network.callback.ListOfObjectsCallback;
import com.ebp.network.callback.SingleObjectCallback;
import com.ebp.network.model.ResourceConfiguration;
import com.ebp.network.repository.strategy.BodyOnlyPostStrategy;
import com.ebp.network.repository.strategy.CallStrategy;
import com.ebp.network.repository.strategy.HeadersOnlyGetStrategy;
import com.ebp.network.repository.strategy.HeadersOnlyPostStrategy;
import com.ebp.network.repository.strategy.MaximalPostStrategy;
import com.ebp.network.repository.strategy.MinimalGetStrategy;
import com.ebp.network.repository.strategy.MinimalPostStrategy;
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
        get(configuration)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ResponseBody>() {
                    @Override
                    public void onCompleted() {
                        Log.i(TAG, "Item receiving completed.");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "Error during loading data. Message: " + e.getMessage());
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
        get(configuration)
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
        post(configuration)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ResponseBody>() {
                    @Override
                    public void onCompleted() {
                        Log.i(TAG, "Item receiving completed.");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "Error during posting data. Message: " + e.getMessage());
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

    private Single<ResponseBody> get(ResourceConfiguration configuration) {
        CallStrategy strategy;
        if (configuration.getHeaders() != null && configuration.getHeaders().size() > 0) {
            strategy = new HeadersOnlyGetStrategy();
            return strategy.call(api.createClient(configuration), configuration);
        } else {
            strategy = new MinimalGetStrategy();
            return strategy.call(api.createClient(configuration), configuration);
        }
    }

    private Single<ResponseBody> post(ResourceConfiguration configuration) {
        CallStrategy strategy;
        if (configuration.getBody() != null && configuration.getHeaders() != null && configuration.getHeaders().size() > 0) {
            strategy = new MaximalPostStrategy();
            return strategy.call(api.createClient(configuration), configuration);
        } else if (configuration.getHeaders() != null && configuration.getHeaders().size() > 0) {
            strategy = new HeadersOnlyPostStrategy();
            return strategy.call(api.createClient(configuration), configuration);
        } else if (configuration.getBody() != null) {
            strategy = new BodyOnlyPostStrategy();
            return strategy.call(api.createClient(configuration), configuration);
        } else {
            strategy = new MinimalPostStrategy();
            return strategy.call(api.createClient(configuration), configuration);
        }
    }
}
