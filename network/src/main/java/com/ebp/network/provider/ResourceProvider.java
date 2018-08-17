package com.ebp.network.provider;

public class ResourceProvider {
    private static NetworkProvider mNetworkProvider;
    private static FileProvider mFileProvider;

    public NetworkProvider provideNetworkProvider() {
        if (mNetworkProvider == null) {
            mNetworkProvider = new NetworkProviderImpl();
        }
        return mNetworkProvider;
    }

    public FileProvider provideFileProvider() {
        if (mFileProvider == null) {
            mFileProvider = new FileProviderImpl();
        }
        return mFileProvider;
    }
}
