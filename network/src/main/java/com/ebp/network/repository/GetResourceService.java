package com.ebp.network.repository;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Url;
import rx.Single;

public interface GetResourceService {
    @GET
    Single<ResponseBody> call(@Url String url);

    @GET
    Single<ResponseBody> call(@Url String url, @HeaderMap Map<String, String> headers);
}