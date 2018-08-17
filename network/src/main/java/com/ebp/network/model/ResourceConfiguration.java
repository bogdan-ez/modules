package com.ebp.network.model;

import java.util.Map;

public class ResourceConfiguration {
    private String baseUrl;
    private String resource;
    private Map<String, String> headers;
    private Method method;
    private Object body;
    private LogLevel logLevel = LogLevel.NONE;

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object object) {
        this.body = object;
    }

    public LogLevel getLogLevel() {
        return logLevel;
    }

    public void setLogLevel(LogLevel logLevel) {
        this.logLevel = logLevel;
    }
}
