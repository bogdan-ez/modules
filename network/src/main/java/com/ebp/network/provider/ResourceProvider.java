package com.ebp.network.provider;

public class ResourceProvider {
    private static NetworkProvider mNetworkProvider;
    private static FileProvider mFileProvider;

    public NetworkProvider provideNetworkProvider() {
        if (mNetworkProvider == null) {
            mNetworkProvider = new NetworkProvider();
        }
        return mNetworkProvider;
    }

    public FileProvider provideFileProvider() {
        if (mFileProvider == null) {
            mFileProvider = new FileProvider();
        }
        return mFileProvider;
    }
}
