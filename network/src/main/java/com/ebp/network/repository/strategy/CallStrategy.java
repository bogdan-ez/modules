package com.ebp.network.repository.strategy;

import com.ebp.network.model.ResourceConfiguration;
import com.ebp.network.repository.service.ServiceResources;

import okhttp3.ResponseBody;
import rx.Single;

public interface CallStrategy {
    Single<ResponseBody> call(ServiceResources resources, ResourceConfiguration configuration);
}
