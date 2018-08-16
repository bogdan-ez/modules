package com.ebp.network.model;

import java.util.Map;

public class ResourceConfigurationBuilder {
    private String baseUrl;
    private String resourceUrl;
    private Map<String, String> headers;
    private Method method;
    private Object body;
    private LogLevel logLevel = LogLevel.NONE;

    public ResourceConfiguration build() {
        ResourceConfiguration configuration = new ResourceConfiguration();
        configuration.setBaseUrl(baseUrl);
        configuration.setResource(resourceUrl);
        configuration.setHeaders(headers);
        configuration.setBody(body);
        configuration.setMethod(method);
        configuration.setLogLevel(logLevel);

        return configuration;
    }

    public ResourceConfigurationBuilder baseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
        return this;
    }

    public ResourceConfigurationBuilder resourceUrl(String resourceUrl) {
        this.resourceUrl = resourceUrl;
        return this;
    }

    public ResourceConfigurationBuilder headers(Map<String, String> headers) {
        this.headers = headers;
        return this;
    }

    public ResourceConfigurationBuilder method(Method method) {
        this.method = method;
        return this;
    }

    public ResourceConfigurationBuilder body(Object body) {
        this.body = body;
        return this;
    }

    public ResourceConfigurationBuilder logLevel(LogLevel logLevel) {
        this.logLevel = logLevel;
        return this;
    }
}
