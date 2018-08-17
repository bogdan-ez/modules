package com.ebp.network.repository;

import com.ebp.network.model.ResourceConfiguration;
import com.ebp.network.repository.service.GetResourceService;
import com.ebp.network.repository.service.PostResourceService;
import com.ebp.network.repository.service.ServiceResources;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.ebp.network.utils.LogMapper.getLogLevel;

public class ResourceClient {

    public GetResourceService createGetClient(ResourceConfiguration configuration) {
        return getRetrofitBuilder(configuration.getBaseUrl(), getOkHttpClient(getLogLevel(configuration.getLogLevel())))
                .create(GetResourceService.class);
    }

    public PostResourceService createPostClient(ResourceConfiguration configuration) {
        return getRetrofitBuilder(configuration.getBaseUrl(), getOkHttpClient(getLogLevel(configuration.getLogLevel())))
                .create(PostResourceService.class);
    }

    public ServiceResources createClient(ResourceConfiguration configuration) {
        return getRetrofitBuilder(configuration.getBaseUrl(), getOkHttpClient(getLogLevel(configuration.getLogLevel())))
                .create(ServiceResources.class);
    }

    private Retrofit getRetrofitBuilder(String baseUrl, OkHttpClient client) {
        return new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private OkHttpClient getOkHttpClient(HttpLoggingInterceptor.Level logLevel) {
        return new OkHttpClient.Builder().addInterceptor(getLoggingInterceptor(logLevel)).build();
    }

    private HttpLoggingInterceptor getLoggingInterceptor(HttpLoggingInterceptor.Level logLevel) {
        return new HttpLoggingInterceptor().setLevel(logLevel);
    }
}