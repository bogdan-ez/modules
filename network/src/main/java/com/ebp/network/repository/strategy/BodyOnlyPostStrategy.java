package com.ebp.network.repository.strategy;

import com.ebp.network.model.ResourceConfiguration;
import com.ebp.network.repository.service.ServiceResources;

import okhttp3.ResponseBody;
import rx.Single;

public class BodyOnlyPostStrategy implements CallStrategy {

    @Override
    public Single<ResponseBody> call(ServiceResources resources, ResourceConfiguration configuration) {
        return resources.post(configuration.getResource(), configuration.getBody());
    }
}
