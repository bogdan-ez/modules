package com.ebp.network.provider;

import com.ebp.network.callback.ListOfObjectsCallback;
import com.ebp.network.callback.SingleObjectCallback;
import com.ebp.network.model.ResourceConfiguration;
import com.ebp.network.repository.ResourceRepository;

public class NetworkProviderImpl implements NetworkProvider {
    private final static String TAG = NetworkProviderImpl.class.getCanonicalName();
    private static ResourceRepository mRepository;

    private static ResourceRepository provideResourceRepository() {
        if (mRepository == null) {
            mRepository = new ResourceRepository();
        }
        return mRepository;
    }

    @Override
    public void makeGetCall(ResourceConfiguration configuration, final Class clazz, final SingleObjectCallback callback) {
        provideResourceRepository().getSingleData(configuration, clazz, callback);
    }

    @Override
    public void makeGetCall(ResourceConfiguration configuration, final Class clazz, final ListOfObjectsCallback callback) {
        provideResourceRepository().getListData(configuration, clazz, callback);
    }

    @Override
    public void makePostCall(ResourceConfiguration configuration, final Class clazz, final SingleObjectCallback callback) {
        provideResourceRepository().postSingleData(configuration, clazz, callback);
    }
}
