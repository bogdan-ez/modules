package com.ebp.network.repository;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.Url;
import rx.Single;

public interface PostResourceService {
    @POST
    Single<ResponseBody> call(@Url String url);

    @POST
    Single<ResponseBody> call(@Url String url, @Body Object body);

    @POST
    Single<ResponseBody> call(@Url String url, @HeaderMap Map<String, String> headers);

    @POST
    Single<ResponseBody> call(@Url String url, @HeaderMap Map<String, String> headers, @Body Object body);
}